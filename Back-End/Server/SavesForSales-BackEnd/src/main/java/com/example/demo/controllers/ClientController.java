package com.example.demo.controllers;

import com.example.demo.database.ClientRepository;
import java.util.List;

import com.example.demo.database.models.Client;
import com.example.demo.services.Services;

public class ClientController<T extends Client> {

    protected ClientRepository<T> repository;

    public ClientController(ClientRepository<T> repository){
        this.repository = repository;
    }
    
    public static <T extends Client> T normalize(T instance) {
        instance.setPassword("");
        return instance;
    }
    
    public Response<T> getById(Integer id){
        try {
            T instance = repository.getById(id);
            if (instance == null) return new Response(false, null, "Client no Found");
            return new Response(true, normalize(instance), "Ok");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    public Response<T> create(String name, String mail, String password, String avatar, T instance) {
        if (!Services.validateEmail(mail)) return new Response(false, null, "bad email");
        
        if (avatar == null) avatar = "";

        instance.setName(name);
        instance.setMail(mail);
        instance.setPassword(Services.cryptPassword(password));
        instance.setAvatar(avatar);
        try {
            // Saving new user
            repository.create(instance);
            return new Response(true, normalize(instance), "user created");
        } catch (Exception ex) {
            // Error saving
            Services.handleError(ex);
            return new Response<T>(false, null, ex);
        }
    }

    public Response<T> login(String mail, String password) {
        try {
            List<T> clients = repository.getByEmail(mail);
            if (clients.isEmpty()) return new Response(false, null, "Client no found");
            
            T instance = clients.get(0);
            if (instance.getPassword().compareTo(Services.cryptPassword(password)) != 0) {
                return new Response(false, null, "Bad Password");
            }
            return new Response(true, normalize(instance), "Ok");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }

    public Response<T> update(String name, String password, String avatar, T instance) throws Exception {
        if (instance == null) return new Response(false, null, "User no Found");
        
        if (name != null) instance.setName(name);
        
        if (password != null)  instance.setPassword(Services.cryptPassword(password));
        
        if (avatar != null) instance.setAvatar(avatar);
        
        repository.update(instance);
        return new Response(true, normalize(instance), "Ok");
    }
}