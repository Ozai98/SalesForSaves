package com.example.demo.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.User;
import com.example.demo.database.RepositoryController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.Services;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
@CrossOrigin
public class UserController extends ClientController<User>{

    public UserController() {
        super(RepositoryController.User());
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<User> create(String name, String mail, String password, String avatar) {
        return super.create(name, mail, password, avatar, new User());
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<User> login(String mail, String password) {
        return super.login(mail, password);
    }

    @PostMapping(value = "/get-by-email", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<User> getByEmail(String mail) {
        try {
            List<User> users = this.repository.getByEmail(mail);
            if (users.isEmpty()) return new Response<User>(false, null, "User no found");
            return new Response(true, super.normalize(users.get(0)), "Ok");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public Response<User>getById(@PathVariable Integer id) {
        return super.getById(id);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<User> updateUser(Integer id, String name, String password, String avatar) {
        if(id == null) return new Response(false, null, "Missing ID");
        try{
            return super.update(name, password, avatar, repository.getById(id));
        }catch (Exception ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
}
