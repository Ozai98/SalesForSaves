/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;


import com.example.demo.database.Repository;
import com.example.demo.database.models.Historic;
import com.example.demo.database.models.Product;
import com.example.demo.database.models.Provider;
import com.example.demo.database.models.User;
import com.example.demo.services.Services;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/historico")
public class HistoricController {
    
    private static final String RESERVA_STATE = "RESERVA";
    private static final String HISTORICO_STATE = "HISTORICO";
    
    private Repository<Historic> historicoRepository;
    private Repository<User> usuarioRepository;
    private Repository<Product> productoRepository;
    private Repository<Provider> proveedorRepository;
    
    public Historic normalizeHistorico(Historic historico) throws Exception {
        productoRepository.refresh(historico.getProducto());
        usuarioRepository.refresh(historico.getUsuario());
        Services.normalize(historico.getUsuario());
        Services.normalize(historico.getProducto().getProveedor());
        return historico;
    }
    
    @PostConstruct
    public void init() {
        setHistoricoRepository(Repository.Historico());
        setUsuarioRepository(Repository.Usuario());
        setProductoRepository(Repository.Producto());
        setProveedorRepository(Repository.Proveedor());
    }
    
    public void setHistoricoRepository(Repository<Historic> repository){
        this.historicoRepository = repository;
    }
    
    public void setUsuarioRepository(Repository<User> repository){
        this.usuarioRepository = repository;
    }
    
    public void setProductoRepository(Repository<Product> repository){
        this.productoRepository = repository;
    }
    public void setProveedorRepository(Repository<Provider> proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }
    
    @PostMapping(value = "/reservar", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic> reservar(Integer idUser, Integer idProducto, Double cantidad){
        
        if(idUser == null || idProducto == null || cantidad == null){ 
            return new Response(false, null, String.format("Missing parameters. missing idUser: %b, missing idProducto: %b, missing cantidad: %b",
                    idUser == null, idProducto == null, cantidad == null));}
        
        try{
            User usr = usuarioRepository.getById(idUser);
            if(usr == null) return new Response<Historic>(false, null, "Usuario no Found");
            Product prod = productoRepository.getById(idProducto);
            if(prod == null) return new Response<Historic>(false, null, "Producto no Found");
            
            Historic nuevo = new Historic();
            nuevo.setCantidad(cantidad);
            nuevo.setEstado(RESERVA_STATE);
            nuevo.setTiempoReserva(new Date());
            nuevo.setUsuario(usr);
            nuevo.setProducto(prod);

            historicoRepository.create(nuevo);
            
            return new Response<Historic>(true, normalizeHistorico(nuevo), "Reserved product");
            
        }catch (Exception e) {
            return new Response<Historic>(false, null, e);
        }
    }

    @GetMapping(value = "/buyed/{id}")
    public Response<Historic []> searchHistorico(@PathVariable Integer id){
        try {
            List<Historic> result = historicoRepository.search(id);
            Historic[] response = new Historic[result.size()]; int i = 0;
            for(Historic historico: result) response[i++] = normalizeHistorico(historico);
            return new Response<Historic []>(true, response, "ok");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response<Historic []>(false, null, ex);
        }
        
    }
    
    @PostMapping(value = "/buyed-product", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic> buyedProduct(Integer idHistorico){
        if(idHistorico == null) return new Response<Historic>(false, null, String.format("Missing parameters. missing idHistorico: %b" , idHistorico == null));
        
        try{
            Historic hist = historicoRepository.getById(idHistorico);
            
            if(hist == null) return new Response<Historic>(false, null, "Historico no Found");
            
            if(hist.getTiempoReserva().before(new Date())) return new Response<Historic>(false, null, String.format("Expired reservation date"));
            if(hist.getEstado().compareTo(RESERVA_STATE) != 0) return new Response<Historic>(false, null, "Not a reserved element");

            hist.setEstado(HISTORICO_STATE);
            hist.setFechaCompra(new Date());
            historicoRepository.update(hist);
            
            return new Response<Historic>(true, normalizeHistorico(hist), "Buyed product");
            
        }catch (Exception e) {
            return new Response<Historic>(false, null, e);
        }
    }
    
}
