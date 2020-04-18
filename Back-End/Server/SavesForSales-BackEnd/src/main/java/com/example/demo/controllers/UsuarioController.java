
package com.example.demo.controllers;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Usuario;
import com.example.demo.database.UsuarioRepository;
import com.example.demo.database.UsuarioRepositoryDao;
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
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    
    private static Usuario normalizeUser(Usuario usuario){
        usuario.setPassword("");
        return usuario;
    }
    
    @PostConstruct
    public void init() {
        setUsuarioRepository(new UsuarioRepositoryDao());
    }
    
    public void setUsuarioRepository(UsuarioRepository repository){
        this.usuarioRepository = repository;
    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> crear(String nombre, String correo, String password, String avatar)
    {

        // Verifing email
        if(!Services.validateEmail(correo)) return new Response(false, null, "bad email");


        // Creating new user
        
        if(avatar == null) avatar = "";
        
        Usuario newUser = new Usuario();
        newUser.setNombre(nombre);
        newUser.setCorreo(correo);
        newUser.setPassword(Services.cryptPassword(password));
        newUser.setAvatar(avatar);
        try{
            // Saving new user
            usuarioRepository.create(newUser);
            return new Response(true, normalizeUser(newUser), "user created");
        }catch(Exception ex){
            // Error saving
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }  
    
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> login(String correo, String password){
        System.out.printf("Trying to login. Correo: %s. Pass: %s\n", correo, password);
        try{
            List<Usuario> users = usuarioRepository.getByEmail(correo);
            if(users.isEmpty()) return new Response(false, null, "User no found");
            Usuario usr = users.get(0);
            if(usr.getPassword().compareTo(Services.cryptPassword(password)) != 0) return new Response(false, null, "Bad Password");
            
            return new Response(true, normalizeUser(usr), "Ok");
        }catch(Exception ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/get-by-email", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> getByCorreo(String correo) {
        try{
            List<Usuario> users = usuarioRepository.getByEmail(correo);
            if(users.isEmpty()) return new Response(false, null, "User no found");
            return new Response(true, normalizeUser(users.get(0)), "Ok");
        }catch(Exception ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @GetMapping("/get-by-id/{id}")
    public Response<Usuario> getById(@PathVariable int id) {
        try{
            Usuario user = usuarioRepository.getById(id);
            if(user == null) return new Response(false, null, "User no Found");
            return new Response(true, normalizeUser(user), "Ok");
        }catch(Exception ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Usuario> updateUser(Integer id, String nombre, String password, String avatar) {
        try{
            Usuario user = usuarioRepository.getById(id);
            if(user == null) return new Response(false, null, "User no Found");
            if(nombre != null) user.setNombre(nombre);
            if(password != null) user.setPassword(Services.cryptPassword(password));
            if(avatar != null) user.setAvatar(avatar);
            usuarioRepository.update(user);
            return new Response(true, normalizeUser(user), "Ok");
        }catch(Exception ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
}

