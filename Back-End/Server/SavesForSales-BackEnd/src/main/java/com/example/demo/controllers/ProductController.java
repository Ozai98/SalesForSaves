package com.example.demo.controllers;

import com.example.demo.database.ClientRepository;
import com.example.demo.database.ProductRepository;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Product;
import com.example.demo.database.models.Provider;
import com.example.demo.services.Services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.database.RepositoryController;

import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

	public static final String DEFAULT_CATEGORY = "NO CATEGORY";

	private ProductRepository productRepository;
	private ClientRepository<Provider> providerRepository;

	public ProductController() {
		productRepository = RepositoryController.Product();
		providerRepository = RepositoryController.Provider();

	}

	public static Product normalize(Product product) {
		try {
			RepositoryController.Provider().refresh(product.getProvider());
			RepositoryController.Ubication().refresh(product.getProvider().getUbication());
		} catch (Exception ex) {
			Services.handleError(ex);
		}
		ProviderController.normalize(product.getProvider());
		product.setImage(product.getImage());
		return product;
	}

	@GetMapping(value={"/search/{name}", "/search"})
	public Response<Product[]> searchProducts(@PathVariable(required = false) String name) {
		try {
			List<Product> result;
			if(name == null || name.isEmpty()) result = productRepository.searchValid("");
			else result =  productRepository.searchValid(name);
			
			Product[] response = new Product[result.size()]; 
			int i = 0;
			for(Product product: result) response[i++] = normalize(product);
			return new Response<>(true, response, "Ok");
		} catch (Exception ex) {
			Services.handleError(ex);
			return new Response<Product []>(false, null, ex);
		}
	}
	
	@GetMapping(value={"/search-category/{category}", "/search-category"})
	public Response<Product[]> searchCategory(@PathVariable(required = false) String category) {
		try {
			List<Product> result;
			if(category == null || category.isEmpty()) result = productRepository.searchCategory("");
			else result =  productRepository.searchCategory(category.toUpperCase());
			
			Product[] response = new Product[result.size()]; 
			int i = 0;
			for(Product product: result) response[i++] = normalize(product);
			return new Response<>(true, response, "Ok");
		} catch (Exception ex) {
			Services.handleError(ex);
			return new Response<Product []>(false, null, ex);
		}
	}

	@GetMapping("/get-by-id/{id}")
	public Response<Product> getProduct( @PathVariable Integer id) {
		try {
			Product product = productRepository.getById(id);
			if(product == null) return new Response<Product>(false, null, "Product no Found");
			return new Response<Product>(true, normalize(product), "Ok:");
		} catch (Exception ex) {
			Services.handleError(ex);
			return new Response<Product>(false, null, ex);
		}

	}


	@PostMapping(value = "/create")
	public Response<Product> create(String name, Double price, Integer idProvider, MultipartFile image, Double quantity,  
			Date timeLimit, String category, double originalPrice){
		if(price <= 0) return new Response<Product>(false, null, "price invalido");
		if(category == null || category.isEmpty()) category = DEFAULT_CATEGORY;
		else category = category.toUpperCase();
		
		Product nProduct = new Product();
		nProduct.setName(name);
		nProduct.setPrice(price);
		nProduct.setQuantity(quantity);
		nProduct.setPublicationDate(new Date());
		nProduct.setTimeLimit(timeLimit);
		nProduct.setCategory(category);
		nProduct.setSaved(originalPrice-price);
		Provider creator;
		try {
			if(image != null) {
				nProduct.setImage(image.getBytes());
			}
			creator = providerRepository.getById(idProvider);
			if(creator == null) return new Response<>(false, null, "idProvider don't match with any provider id: " + idProvider);
			nProduct.setProvider(creator);
			productRepository.create(nProduct);
			return new Response<>(true, normalize(nProduct), "Ok. ");
		} catch (Exception e) {
			return new Response<>(false, null, e);
		}
	}
}
