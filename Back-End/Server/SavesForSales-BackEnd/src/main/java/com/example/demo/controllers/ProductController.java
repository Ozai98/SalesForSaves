package com.example.demo.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

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

import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    private Repository<Product> productRepository;
    private Repository<Provider> providerRepository;

    
    
    @PostConstruct
    public void init() {
        setProductoRepository(Repository.Product());
        setProveedorRepository(Repository.Provider());
    }

    public Product normalizeProducto(Product product){
        try{
            providerRepository.refresh(product.getProvider());
        }catch(Exception ex){
            Services.handleError(ex);
        }            
        Services.normalize(product.getProvider());
        return product;
    }
    
    public void setProductoRepository(Repository<Product> repository){
        this.productRepository = repository;
    }
    
    public void setProveedorRepository(Repository<Provider> repository){
        this.providerRepository = repository;
    }

    @GetMapping("/search/{name}")
    public Response<Product[]> searchProducts(@PathVariable String name) {
        try {
            List<Product> result =  productRepository.search(name);
            Product[] response = new Product[result.size()]; int i = 0;
            for(Product product: result) response[i++] = normalizeProducto(product);
            return new Response<Product []>(true, response, "Ok");
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
            return new Response<Product>(true, normalizeProducto(product), "Ok: " + product.getProvider().getId());
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response<Product>(false, null, ex);
        }

    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Product> create(String name, Double price, Integer idProvider, String image, Double cuantity){
        if(price <= 0) return new Response<Product>(false, null, "price invalido");
        
        if(image == null) image = "";
        
        Product nProduct = new Product();
        nProduct.setName(name);
        nProduct.setPrice(price);
        nProduct.setImage(image);
        nProduct.setCuantity(cuantity);
        nProduct.setPublicationDate(new Date());
        Provider creator;
        try {
            creator = providerRepository.getById(idProvider);
            if(creator == null) return new Response<Product>(false, null, "idProvider don't match with any provider id: " + idProvider);
            nProduct.setProvider(creator);
            productRepository.create(nProduct);
            return new Response<Product>(true, normalizeProducto(nProduct), "Ok. " + creator.getName());
        } catch (Exception e) {
            return new Response<Product>(false, null, e);
        }
    }
}
