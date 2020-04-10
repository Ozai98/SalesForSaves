
package com.example.demo.controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Usuario;
import com.example.demo.database.DatabaseController;
import com.example.demo.database.models.Producto;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.Services;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/usuario")
public class UsuarioController {

    private Dao<Usuario, Integer> userDao;

    private static Usuario normalizeUser(Usuario usuario){
        usuario.setPassword("");
        return usuario;
    }
    
    @PostConstruct
    public void init() {
        userDao = DatabaseController.getInstance().userDao();
    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> crear(String nombre, String correo, String password)
    {

        // Verifing email
        if(!Services.validateEmail(correo)) return new Response(false, null, "bad email");


        // Creating new user
        Usuario newUser = new Usuario();
        newUser.setNombre(nombre);
        newUser.setCorreo(correo);
        newUser.setPassword(Services.cryptPassword(password));
        try{
            // Saving new user
            userDao.create(newUser);
            return new Response(true, normalizeUser(newUser), "user created");
        }catch(SQLException ex){
            // Error saving
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }  
    
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> login(String correo, String password){
        try{
            List<Usuario> users = userDao.queryForEq("correo", correo);
            if(users.isEmpty()) return new Response(false, null, "User no found");
            Usuario usr = users.get(0);
            if(usr.getPassword().compareTo(Services.cryptPassword(password)) != 0) return new Response(false, null, "Bad Password");
            
            return new Response(true, normalizeUser(usr), "Ok");
        }catch(SQLException ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/get-by-email", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> getByCorreo(String correo) {
        try{
            List<Usuario> users = userDao.queryForEq("correo", correo);
            if(users.isEmpty()) return new Response(false, null, "User no found");
            return new Response(true, normalizeUser(users.get(0)), "Ok");
        }catch(SQLException ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/get-by-id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> getById(int id) {
        try{
            Usuario user = userDao.queryForId(id);
            if(user == null) return new Response(false, null, "User no Found");
            return new Response(true, normalizeUser(user), "Ok");
        }catch(SQLException ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> updateUser(int id, String nombre, String password) {
        try{
            Usuario user = userDao.queryForId(id);
            if(user == null) return new Response(false, null, "User no Found");
            if(nombre != null) user.setNombre(nombre);
            if(password != null) user.setPassword(Services.cryptPassword(password));
            userDao.update(user);
            return new Response(true, normalizeUser(user), "Ok");
        }catch(SQLException ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
}

