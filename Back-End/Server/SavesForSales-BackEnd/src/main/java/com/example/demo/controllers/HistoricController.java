/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;


import com.example.demo.database.HistoricRepository;
import com.example.demo.database.Repository;
import com.example.demo.database.RepositoryController;
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
    
    private HistoricRepository historicRepository;
    private Repository<User> userRepository;
    private Repository<Product> productRepository;
    
    public static Historic normalizeHistoric(Historic historic) throws Exception {
        RepositoryController.Product().refresh(historic.getProduct());
        RepositoryController.User().refresh(historic.getUser());
        ClientController.<User>normalize(historic.getUser());
        ProductController.normalize(historic.getProduct());
        return historic;
    }
    
    public HistoricController(){
        this.historicRepository = RepositoryController.Historic();
        this.userRepository = RepositoryController.User();
        this.productRepository = RepositoryController.Product();
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
            
            Historic newH = new Historic();
            newH.setCuantity(cuantity);
            newH.setState(RESERVA_STATE);
            newH.setTimeReserve(new Date());
            newH.setUser(usr);
            newH.setProduct(prod);

            historicRepository.create(newH);
            
            return new Response<Historic>(true, normalizeHistoric(newH), "Reserved product");
        }catch (Exception e) {
            return new Response<Historic>(false, null, e);
        }
    }
    
    @PostMapping(value = "/buyed-product", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic> buyedProduct(Integer idHistoric){
        if(idHistoric == null) return new Response<Historic>(false, null, String.format("Missing parameters. missing idHistoric: %b" , idHistoric == null));
        
        try{
            Historic hist = historicRepository.getById(idHistoric);
            
            if(hist == null) return new Response<Historic>(false, null, "Historic no Found");
            
            if(hist.getTimeReserve().before(new Date())) return new Response<Historic>(false, null, String.format("Expired reservation date"));
            if(hist.getState().compareTo(RESERVA_STATE) != 0) return new Response<Historic>(false, null, "Not a reserved element");

            hist.setState(HISTORICO_STATE);
            hist.setBuyDate(new Date());
            historicRepository.update(hist);
            
            return new Response<Historic>(true, normalizeHistoric(hist), "Buyed product");
            
        }catch (Exception e) {
            return new Response<Historic>(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-user-reserved", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic[]> getUserReserved(Integer idUser){
        try{
            List<Historic> list = historicRepository.getForUserAndState(idUser, RESERVA_STATE);
            Historic[] exit = new Historic[list.size()];
            
            int i = 0;
            for (Historic historico : list) {
                exit[i++] = normalizeHistoric(historico);
            }
            
            return new Response(true, exit, "Reserve Found");
        }catch(Exception e){
            return new Response(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-user-historic", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic[]> getUserHistoric(Integer idUser){
        try{
            List<Historic> list = historicRepository.getForUserAndState(idUser, HISTORICO_STATE);
            Historic[] exit = new Historic[list.size()];
            
            int i = 0;
            for (Historic historico : list) {
                exit[i++] = normalizeHistoric(historico);
            }
            return new Response(true, exit, "Historic Found");
        }catch(Exception e){
            return new Response(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-provder-reserved", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic[]> getProviderReserved(Integer idProv){
        try{
            List<Historic> list = historicRepository.getForProviderAndState(idProv, HISTORICO_STATE);
            Historic[] exit = new Historic[list.size()];
            
            int i = 0;
            for (Historic historico : list) {
                exit[i++] = normalizeHistoric(historico);
            }
            return new Response(true, exit, "Historic Found");
        }catch(Exception e){
            return new Response(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-provder-historic", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic[]> getProviderHistoric(Integer idProv){
        return null;
    }
}
