package com.example.demo.controllers;

import com.example.demo.database.ClientRepository;
import com.example.demo.database.ProductRepository;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Product;
import com.example.demo.database.models.Provider;
import com.example.demo.services.Services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.database.Repository;
import com.example.demo.database.RepositoryController;
import com.example.demo.services.FileSystem;

import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;
    private ClientRepository<Provider> providerRepository;

    public ProductController(){
        productRepository = RepositoryController.Product();        
        providerRepository = RepositoryController.Provider();

    }

    public static Product normalize(Product product){
        try{
            RepositoryController.Provider().refresh(product.getProvider());
        }catch(Exception ex){
            Services.handleError(ex);
        }            
        ProviderController.normalize(product.getProvider());
        return product;
    }

    @GetMapping(value={"/search/{name}", "/search"})
    public Response<Product[]> searchProducts(@PathVariable(required = false) String name) {
        try {
            List<Product> result;
            if(name == null || name.isEmpty()) result = productRepository.search("");
            else result =  productRepository.search(name);
            
            Product[] response = new Product[result.size()]; 
            int i = 0;
            for(Product product: result) response[i++] = normalize(product);
            return new Response(true, response, "Ok");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response<Product []>(false, null, ex);
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/get-by-id/{id}")
    public Response<Product> getProduct( @PathVariable Integer id) {
        try {
            Product product = productRepository.getById(id);
            if(product == null) return new Response<Product>(false, null, "Product no Found");
            return new Response(true, normalize(product), "Ok:");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }

    }

    @PostMapping(value = "/create")
    public Response<Product> create(String name, Double price, Integer idProvider, MultipartFile image, Double quantity){
        if(price <= 0) return new Response(false, null, "Invalid Price");
        
        Product nProduct = new Product();
        nProduct.setName(name);
        nProduct.setPrice(price);
        nProduct.setQuantity(quantity);
        nProduct.setPublicationDate(new Date());
        
        try {
            
            String imgName = FileSystem.DEFAULT_IMG;
            if(image != null) {
                FileSystem.FileSystemRespone<String> res =  FileSystem.saveFile(new FileSystem.SFSFile(image.getBytes(), image.getOriginalFilename()));
                if(!res.ok) return new Response(false, null, res.ex);
                else imgName = res.msg;
            }
            nProduct.setImage(imgName);
            
            Provider creator = providerRepository.getById(idProvider);
            if(creator == null) return new Response(false, null, "idProvider don't match with any provider id: " + idProvider);
            nProduct.setProvider(creator);
            productRepository.create(nProduct);
            return new Response(true, normalize(nProduct), "Ok. ");
        } catch (Exception e) {
            return new Response(false, null, e);
        }
    }
}
