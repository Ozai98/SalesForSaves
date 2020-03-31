
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
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.Services;

/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/proveedor")
public class ProveedorController {

    private Dao<Proveedor, Integer> proveedorDao;

    @PostConstruct
    public void init() {
        proveedorDao = DatabaseController.getInstance().proveedorDao();
    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ProveedorResponse crear(String nombre, String correo, String password)
    {

        // Verifing email
        if(!Services.validateEmail(correo)) return new ProveedorResponse(false, null, "bad email");


        // Creating new user
        Proveedor newProvider = new Proveedor();
        newProvider.setNombre(nombre);
        newProvider.setCorreo(correo);
        newProvider.setPassword(Services.cryptPassword(password));
        try{
            // Saving new user
            proveedorDao.create(newProvider);
            return new ProveedorResponse(true, newProvider, "provider created created");
        }catch(SQLException ex){
            // Error saving
            Services.handleError(ex);
            return new ProveedorResponse(false, null, ex);
        }
    }
    
}

class ProveedorResponse{
    public final boolean ok;
    public final Proveedor proveedor;
    public final String msg;

    public ProveedorResponse(boolean ok, Proveedor proveedor, String msg){
        this.ok = ok;
        this.proveedor = proveedor;
        this.msg = msg;
    }

    public ProveedorResponse(boolean ok, Proveedor proveedor, Exception ex){
        this.ok = ok;
        this.proveedor = proveedor;
        this.msg = ex.getCause().getMessage();
    }
}