
package com.example.demo.controllers;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Provider;
import com.example.demo.database.Repository;

import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.Services;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/provider")
public class ProviderController extends ClientController<Provider>{

	private Repository<Provider> providerRepository;
	
	@PostConstruct
	public void init() {
		setProviderRepository(Repository.Proveedor());
	}
	
	public void setProviderRepository(Repository<Provider> providerRepository){
		this.providerRepository = providerRepository;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Provider> create(String name, String mail, String password, String avatar)
	{
		return super.create(name, mail, password, avatar, new Provider());
	}
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Provider> login(String mail, String password){
		return super.login(mail, password);
	}
	/**
	@GetMapping("/get-by-id/{id}")
	
	public Response<Proveedor> getById(@PathVariable Integer id) {
		try{
			Proveedor provider = providerRepository.getById(id);
			if(provider == null) return new Response<Proveedor>(false, null, "Proveedor no Found");
			return new Response<Proveedor>(true, Services.normalize(provider), "Ok");
		}catch(Exception ex){
			 Services.handleError(ex);
			return new Response<Proveedor>(false, null, ex);
		}
	}*/
	
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Provider> updateProvider(Integer id, String name, String password, String avatar, String ubication) {
		try{
			Provider provider = providerRepository.getById(id);
			if(ubication != null) provider.setUbication(ubication);
			return super.update(name, password, avatar, provider);
		}catch(Exception ex){
			 Services.handleError(ex);
			return new Response<Provider>(false, null, ex);
		}
	}
	
}