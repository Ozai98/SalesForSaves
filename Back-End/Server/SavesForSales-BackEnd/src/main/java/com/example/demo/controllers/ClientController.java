package com.example.demo.controllers;

import java.util.List;

import com.example.demo.database.Repository;
import com.example.demo.database.models.Client;
import com.example.demo.services.Services;

public class ClientController<T extends Client > {

	private Repository<T> repository;
	
	public Response<T> create(String name, String mail, String password, String avatar, T newUser){
		if(!Services.validateEmail(mail)) return new Response<T>(false, null, "bad email");
		
		if(avatar == null) avatar = "";
		
		newUser.setName(name);
		newUser.setMail(mail);
		newUser.setPassword(Services.cryptPassword(password));
		newUser.setAvatar(avatar);
		try{
			// Saving new user
			repository.create(newUser);
			return new Response<T>(true, (T) Services.normalize(newUser), "user created");
		}catch(Exception ex){
			// Error saving
			Services.handleError(ex);
			return new Response<T>(false, null, ex);
		}
	}
	
	public Response<T> login(String mail, String password){
		try{
			List<T> clients = repository.search(mail);
			if(clients.isEmpty()) return new Response<T>(false, null, "User no found");
			T provider =  clients.get(0);
			if(provider.getPassword().compareTo(Services.cryptPassword(password)) != 0) return new Response<T>(false, null, "Bad Password");
			return new Response<T>(true, Services.normalize(provider), "Ok");
		}catch(Exception ex){
			 Services.handleError(ex);
			return new Response<T>(false, null, ex);
		}
	}

	public Response<T> update(String name, String password, String avatar, T user) throws Exception {
		if(user == null) return new Response<T>(false, null, "User no Found");
		if(name != null) user.setName(name);
		if(password != null) user.setPassword(Services.cryptPassword(password));
		if(avatar != null) user.setAvatar(avatar);
		repository.update((T)user);
		return new Response<T>(true, Services.normalize(user), "Ok");
	}
}