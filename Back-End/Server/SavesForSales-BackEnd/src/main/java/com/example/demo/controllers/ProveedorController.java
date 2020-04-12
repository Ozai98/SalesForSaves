
package com.example.demo.controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Proveedor;
import com.example.demo.database.DatabaseController;
import com.example.demo.database.models.Usuario;
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

    private Dao<Proveedor, Integer> proveedorDao;

    public static Proveedor normalizeProveedor(Proveedor proveedor){
        proveedor.setPassword("");
        return proveedor;
    }
    
    @PostConstruct
    public void init() {
        proveedorDao = DatabaseController.getInstance().proveedorDao();
    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Proveedor> crear(String nombre, String correo, String password)
    {

        // Verifing email
        if(!Services.validateEmail(correo)) return new Response(false, null, "bad email");

        // Creating new provider
        Proveedor newProvider = new Proveedor();
        newProvider.setNombre(nombre);
        newProvider.setCorreo(correo);
        newProvider.setPassword(Services.cryptPassword(password));
        try{
            // Saving new provider
            proveedorDao.create(newProvider);
            return new Response(true, normalizeProveedor(newProvider), "provider created");
        }catch(SQLException ex){
            // Error saving
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Proveedor> login(String correo, String password){
        try{
            List<Proveedor> proveedores = proveedorDao.queryForEq("correo", correo);
            if(proveedores.isEmpty()) return new Response(false, null, "User no found");
            Proveedor proveedor = proveedores.get(0);
            if(proveedor.getPassword().compareTo(Services.cryptPassword(password)) != 0) return new Response(false, null, "Bad Password");
            return new Response(true, normalizeProveedor(proveedor), "Ok");
        }catch(SQLException ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @GetMapping("/get-by-id/{id}")
    public Response<Proveedor> getById(@PathVariable int id) {
        try{
            Proveedor proveedor = proveedorDao.queryForId(id);
            if(proveedor == null) return new Response(false, null, "Proveedor no Found");
            return new Response(true, normalizeProveedor(proveedor), "Ok");
        }catch(SQLException ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Proveedor> updateProveedor(int id, String nombre, String password) {
        try{
            Proveedor proveedor = proveedorDao.queryForId(id);
            if(proveedor == null) return new Response(false, null, "Proveedor no Found");
            if(nombre != null) proveedor.setNombre(nombre);
            if(password != null) proveedor.setPassword(Services.cryptPassword(password));
            proveedorDao.update(proveedor);
            return new Response(true, normalizeProveedor(proveedor), "Ok");
        }catch(SQLException ex){
             Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    
}