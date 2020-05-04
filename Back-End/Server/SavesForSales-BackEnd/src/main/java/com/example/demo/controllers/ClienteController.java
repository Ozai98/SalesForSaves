package com.example.demo.controllers;

import java.util.List;

import com.example.demo.database.Repository;
import com.example.demo.database.models.Cliente;
import com.example.demo.services.Services;

public class ClienteController<T> {

	private Repository<T> repository;
	
	public Response<T> crear(String nombre, String correo, String password, String avatar, Cliente c){
		if(!Services.validateEmail(correo)) return new Response<T>(false, null, "bad email");
		
		if(avatar == null) avatar = "";
		
		Cliente newUser = c;
		newUser.setNombre(nombre);
		newUser.setCorreo(correo);
		newUser.setPassword(Services.cryptPassword(password));
		newUser.setAvatar(avatar);
		try{
			// Saving new user
			repository.create((T) newUser);
			return new Response<T>(true, (T) Services.normalize(newUser), "user created");
		}catch(Exception ex){
			// Error saving
			Services.handleError(ex);
			return new Response<T>(false, null, ex);
		}
	}
	
	public Response<T> login(String correo, String password){
		try{
			List<T> clientes = repository.search(correo);
			if(clientes.isEmpty()) return new Response<T>(false, null, "User no found");
			Cliente proveedor = (Cliente) clientes.get(0);
			if(proveedor.getPassword().compareTo(Services.cryptPassword(password)) != 0) return new Response<T>(false, null, "Bad Password");
			return new Response<T>(true, (T) Services.normalize(proveedor), "Ok");
		}catch(Exception ex){
			 Services.handleError(ex);
			return new Response<T>(false, null, ex);
		}
	}

	public Response<T> update(String nombre, String password, String avatar, Cliente user) throws Exception {
		if(user == null) return new Response<T>(false, null, "User no Found");
		if(nombre != null) user.setNombre(nombre);
		if(password != null) user.setPassword(Services.cryptPassword(password));
		if(avatar != null) user.setAvatar(avatar);
		repository.update((T)user);
		return new Response<T>(true, (T)Services.normalize(user), "Ok");
	}
}