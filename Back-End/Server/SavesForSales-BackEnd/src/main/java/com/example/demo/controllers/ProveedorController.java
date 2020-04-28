
package com.example.demo.controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Proveedor;
import com.example.demo.database.ProveedorRepository;
import com.example.demo.database.ProveedorRepositoryDao;
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
@CrossOrigin
@RequestMapping("/proveedor")
public class ProveedorController {

    private ProveedorRepository proveedorRepository;

    public static Proveedor normalizeProveedor(Proveedor proveedor){
        proveedor.setPassword("");
        return proveedor;
    }
    
    @PostConstruct
    public void init() {
        setProveedorRepository(new ProveedorRepositoryDao());
    }
    
    public void setProveedorRepository(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Proveedor> crear(String nombre, String correo, String password, String avatar)
    {

        // Verifing email
        if(!Services.validateEmail(correo)) return new Response(false, null, "bad email");

        // Creating new provider
        if(avatar == null) avatar = "";
        
        Proveedor newProvider = new Proveedor();
        newProvider.setNombre(nombre);
        newProvider.setCorreo(correo);
        newProvider.setPassword(Services.cryptPassword(password));
        newProvider.setAvatar(avatar);
        try{
            // Saving new provider
            proveedorRepository.create(newProvider);
            return new Response(true, normalizeProveedor(newProvider), "provider created");
        }catch(Exception ex){
            // Error saving
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Proveedor> login(String correo, String password){
        try{
            List<Proveedor> proveedores = proveedorRepository.getByEmail(correo);
            if(proveedores.isEmpty()) return new Response(false, null, "User no found");
            Proveedor proveedor = proveedores.get(0);
            if(proveedor.getPassword().compareTo(Services.cryptPassword(password)) != 0) return new Response(false, null, "Bad Password");
            return new Response(true, normalizeProveedor(proveedor), "Ok");
        }catch(Exception ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @GetMapping("/get-by-id/{id}")
    public Response<Proveedor> getById(@PathVariable Integer id) {
        try{
            Proveedor proveedor = proveedorRepository.getById(id);
            if(proveedor == null) return new Response(false, null, "Proveedor no Found");
            return new Response(true, normalizeProveedor(proveedor), "Ok");
        }catch(Exception ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Proveedor> updateProveedor(Integer id, String nombre, String password, String avatar, String ubicacion) {
        try{
            Proveedor proveedor = proveedorRepository.getById(id);
            if(proveedor == null) return new Response(false, null, "Proveedor no Found");
            if(nombre != null) proveedor.setNombre(nombre);
            if(password != null) proveedor.setPassword(Services.cryptPassword(password));
            if(avatar != null) proveedor.setAvatar(avatar);
            if(ubicacion != null) proveedor.setUbicacion(ubicacion);
            proveedorRepository.update(proveedor);
            return new Response(true, normalizeProveedor(proveedor), "Ok");
        }catch(Exception ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
}