
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
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.Services;

/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/usuario")
public class UsuarioController {

    private Dao<Usuario, Integer> userDao;

    @PostConstruct
    public void init() {
        userDao = DatabaseController.getInstance().userDao();
    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public UserResponse crear(String nombre, String correo, String password)
    {

        // Verifing email
        if(!Services.validateEmail(correo)) return new UserResponse(false, null, "bad email");


        // Creating new user
        Usuario newUser = new Usuario();
        newUser.setNombre(nombre);
        newUser.setCorreo(correo);
        newUser.setPassword(Services.cryptPassword(password));
        try{
            // Saving new user
            userDao.create(newUser);
            return new UserResponse(true, newUser, "user created");
        }catch(SQLException ex){
            // Error saving
            Services.handleError(ex);
            return new UserResponse(false, null, ex);
        }
    }   
    
    public static class UserResponse{
        public final boolean ok;
        public final Usuario usuario;
        public final String msg;

        public UserResponse(boolean ok, Usuario usuario, String msg){
            this.ok = ok;
            this.usuario = usuario;
            this.msg = msg;
        }

        public UserResponse(boolean ok, Usuario usuario, Exception ex){
            this.ok = ok;
            this.usuario = usuario;
            this.msg = ex.getCause().getMessage();
        }
    }
}

