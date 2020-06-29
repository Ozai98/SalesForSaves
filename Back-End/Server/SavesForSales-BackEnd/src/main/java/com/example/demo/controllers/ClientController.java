package com.example.demo.controllers;

import com.example.demo.database.ClientRepository;
import com.example.demo.database.RepositoryController;
import java.util.List;

import com.example.demo.database.models.Client;
import com.example.demo.database.models.Provider;
import com.example.demo.database.models.User;
import com.example.demo.services.Services;
import java.util.HashMap;
import org.springframework.web.multipart.MultipartFile;

public class ClientController<T extends Client> {

	protected ClientRepository<T> repository;

	public ClientController(ClientRepository<T> repository) {
		this.repository = repository;
	}

	public static <T extends Client> T normalize(T instance) {
		instance.setPassword("");
		return instance;
	}

	public Response<T> getById(Integer id) {
		try {
			T instance = repository.getById(id);
			if (instance == null)
				return new Response<>(false, null, "Client no Found");
			return new Response<>(true, normalize(instance), "Ok");
		} catch (Exception ex) {
			Services.handleError(ex);
			return new Response<>(false, null, ex);
		}
	}

	public Response<T> create(String name, String mail, String password, MultipartFile avatar, T instance) {

		if (name == null || mail == null || password == null)
			return new Response<>(false, null, String.format("Missing parameters: name: %b, mail: %b, password: %b",
					name == null, mail == null, password == null));

		if (!Services.validateEmail(mail))
			return new Response<>(false, null, "bad email");

		instance.setName(name);
		instance.setMail(mail);
		instance.setPassword(Services.cryptPassword(password));
		try {

			if (avatar != null) {
				instance.setAvatar(avatar.getBytes());
			}

			// Saving new user
			repository.create(instance);
			return new Response<>(true, normalize(instance), "Client created");
		} catch (Exception ex) {
			// Error saving
			Services.handleError(ex);
			return new Response<>(false, null, ex);
		}
	}

	public Response<T> login(String mail, String password) {
		try {
			List<T> clients = repository.getByEmail(mail);
			if (clients.isEmpty())
				return new Response<>(false, null, "Client no found");

			T instance = clients.get(0);
			if (instance.getPassword().compareTo(Services.cryptPassword(password)) != 0) {
				return new Response<>(false, null, "Bad Password");
			}
			return new Response<>(true, normalize(instance), "Ok");
		} catch (Exception ex) {
			Services.handleError(ex);
			return new Response<>(false, null, ex);
		}
	}
        
        private Client tryUserLogin(String mail, String password){
            try {
                List<User> clients = RepositoryController.User().getByEmail(mail);
                if (clients.isEmpty()) return null;

                User instance = clients.get(0);
                if (instance.getPassword().compareTo(Services.cryptPassword(password)) != 0)  return null;

                return normalize(instance);
            } catch (Exception ex) {
                Services.handleError(ex);
                return null;
            }
        }

        private Client tryProviderLogin(String mail, String password){
            try {
                List<Provider> clients = RepositoryController.Provider().getByEmail(mail);
                if (clients.isEmpty()) return null;

                Provider instance = clients.get(0);
                if (instance.getPassword().compareTo(Services.cryptPassword(password)) != 0)  return null;

                return normalize(instance);
            } catch (Exception ex) {
                Services.handleError(ex);
                return null;
            }
        }

        public Response<HashMap> generalLogin(String mail, String password){

            Client response = tryUserLogin(mail, password);
            if(response != null){
                HashMap exit = new HashMap();
                exit.put("isProvider", false);
                exit.put("client", response);
                return new Response(true, exit, "Ok");
            }

            response = tryProviderLogin(mail, password);
            if(response != null){
                HashMap exit = new HashMap();
                exit.put("isProvider", true);
                exit.put("client", response);
                return new Response(true, exit, "Ok");
            }

            return new Response(false, null, "Client not found");
        }

	public Response<T> update(String name, String password, MultipartFile avatar, T instance) throws Exception {
		if (instance == null)
			return new Response<>(false, null, "User no Found");

		if (name != null)
			instance.setName(name);

		if (password != null)
			instance.setPassword(Services.cryptPassword(password));

		if (avatar != null) {
			instance.setAvatar(avatar.getBytes());
		}

		repository.update(instance);
		return new Response<>(true, normalize(instance), "Ok");
	}
}
