/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;


import com.example.demo.database.Repository;
import com.example.demo.database.models.Historic;
import com.example.demo.database.models.Product;
import com.example.demo.database.models.User;
import com.example.demo.services.Services;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@RequestMapping("/historic")
public class HistoricController {
    
    private static final String RESERVA_STATE = "RESERVA";
    private static final String HISTORICO_STATE = "HISTORICO";
    
    private Repository<Historic> historicRepository;
    private Repository<User> userRepository;
    private Repository<Product> productRepository;
    
    public Historic normalizeHistorico(Historic historic) throws Exception {
        productRepository.refresh(historic.getProduct());
        userRepository.refresh(historic.getUser());
        Services.normalize(historic.getUser());
        Services.normalize(historic.getProduct().getProvider());
        return historic;
    }
    
    @PostConstruct
    public void init() {
        setHistoricoRepository(Repository.Historic());
        setUsuarioRepository(Repository.User());
        setProductoRepository(Repository.Product());
    }
    
    public void setHistoricoRepository(Repository<Historic> repository){
        this.historicRepository = repository;
    }
    
    public void setUsuarioRepository(Repository<User> repository){
        this.userRepository = repository;
    }
    
    public void setProductoRepository(Repository<Product> repository){
        this.productRepository = repository;
    }
    
    @PostMapping(value = "/reserve", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic> reserve(Integer idUser, Integer idProducto, Double cuantity){
        
        if(idUser == null || idProducto == null || cuantity == null){ 
            return new Response<Historic>(false, null, String.format("Missing parameters. missing idUser: %b, missing idProducto: %b, missing cuantity: %b",
                    idUser == null, idProducto == null, cuantity == null));}
        
        try{
            User usr = userRepository.getById(idUser);
            if(usr == null) return new Response<Historic>(false, null, "Usuario no Found");
            Product prod = productRepository.getById(idProducto);
            if(prod == null) return new Response<Historic>(false, null, "Producto no Found");
            Date nDate = new Date();
            if(nDate.compareTo(prod.getTimeLimit())>0)return new Response<Historic>(false, null, "too late");

            Historic nuevo = new Historic();
            nuevo.setCuantity(cuantity);
            nuevo.setState(RESERVA_STATE);
            nuevo.setTimeReserve(new Date());
            nuevo.setUser(usr);
            nuevo.setProduct(prod);

            historicRepository.create(nuevo);
            
            return new Response<Historic>(true, normalizeHistorico(nuevo), "Reserved product");
            
        }catch (Exception e) {
            return new Response<Historic>(false, null, e);
        }
    }

    @GetMapping(value = "/buyed/{id}")
    public Response<Historic []> searchHistoric(@PathVariable Integer id){
        try {
            List<Historic> result = historicRepository.search(id);
            Historic[] response = new Historic[result.size()]; int i = 0;
            for(Historic historic: result) response[i++] = normalizeHistorico(historic);
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
            Historic hist = historicRepository.getById(idHistorico);
            
            if(hist == null) return new Response<Historic>(false, null, "Historico no Found");
            
            if(hist.getTimeReserve().before(new Date())) return new Response<Historic>(false, null, String.format("Expired reservation date"));
            if(hist.getState().compareTo(RESERVA_STATE) != 0) return new Response<Historic>(false, null, "Not a reserved element");

            hist.setState(HISTORICO_STATE);
            hist.setBuyDate(new Date());
            historicRepository.update(hist);
            
            return new Response<Historic>(true, normalizeHistorico(hist), "Buyed product");
            
        }catch (Exception e) {
            return new Response<Historic>(false, null, e);
        }
    }
    
}
