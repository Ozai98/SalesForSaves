
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
@RequestMapping("/proveedor")
public class ProviderController extends ClientController<Provider>{

	private Repository<Provider> proveedorRepository;
	
	@PostConstruct
	public void init() {
		setProveedorRepository(Repository.Proveedor());
	}
	
	public void setProveedorRepository(Repository<Provider> proveedorRepository){
		this.proveedorRepository = proveedorRepository;
	}

	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Provider> crear(String nombre, String correo, String password, String avatar)
	{
		return super.crear(nombre, correo, password, avatar, new Provider());
	}
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Provider> login(String correo, String password){
		return super.login(correo, password);
	}
	/**
	@GetMapping("/get-by-id/{id}")
	
	public Response<Proveedor> getById(@PathVariable Integer id) {
		try{
			Proveedor proveedor = proveedorRepository.getById(id);
			if(proveedor == null) return new Response<Proveedor>(false, null, "Proveedor no Found");
			return new Response<Proveedor>(true, Services.normalize(proveedor), "Ok");
		}catch(Exception ex){
			 Services.handleError(ex);
			return new Response<Proveedor>(false, null, ex);
		}
	}*/
	
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Provider> updateProveedor(Integer id, String nombre, String password, String avatar, String ubicacion) {
		try{
			Provider proveedor = proveedorRepository.getById(id);
			if(ubicacion != null) proveedor.setUbicacion(ubicacion);
			return super.update(nombre, password, avatar, proveedor);
		}catch(Exception ex){
			 Services.handleError(ex);
			return new Response<Provider>(false, null, ex);
		}
	}
	
}