/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;


import com.example.demo.database.ClientRepository;
import com.example.demo.database.HistoricRepository;
import com.example.demo.database.ProductRepository;
import com.example.demo.database.RepositoryController;
import com.example.demo.database.models.Historic;
import com.example.demo.database.models.Product;
import com.example.demo.database.models.User;
import com.example.demo.services.Services;

import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/historic")
public class HistoricController {
    
    private static final String RESERVED_STATE = "RESERVED";
    private static final String HISROTIC_STATE = "HISTORIC";
    
    private HistoricRepository historicRepository;
    private ClientRepository<User> userRepository;
    private ProductRepository productRepository;
    
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
    @Transactional
    @PostMapping(value = "/reserve", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic> reserve(Integer idUser, Integer idProduct, Double quantity, @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") Date reserveDate){
        
        if(idUser == null || idProduct == null || quantity == null || reserveDate == null) 
            return new Response<Historic>(false, null, String.format("Missing parameters. missing idUser: %b, missing idProduct: %b, missing quantity: %b, missing reserveDate: %b",
                    idUser == null, idProduct == null, quantity == null, reserveDate == null));
        
        try{
            User usr = userRepository.getById(idUser);
            if(usr == null) return new Response<Historic>(false, null, "User no Found");
            Product prod = productRepository.getById(idProduct);
            if(prod == null) return new Response<Historic>(false, null, "Product no Found");
            
            prod.setQuantity(prod.getQuantity()-quantity);
            productRepository.update(prod);

            Historic newHistoric = new Historic();
            newHistoric.setQuantity(quantity);
            newHistoric.setState(RESERVED_STATE);
            newHistoric.setTimeReserve(reserveDate);
            newHistoric.setReserveMoment(new Date());
            newHistoric.setUser(usr);
            newHistoric.setProduct(prod);

            historicRepository.create(newHistoric);
            
            return new Response<Historic>(true, normalizeHistoric(newHistoric), "Reserved product");

        }catch (Exception e) {
            Services.handleError(e);
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
            if(hist.getState().compareTo(RESERVED_STATE) != 0) return new Response<Historic>(false, null, "Not a reserved element");

            hist.setState(HISROTIC_STATE);
            hist.setBuyDate(new Date());
            
            productRepository.refresh(hist.getProduct());
            hist.getProduct().setQuantity(hist.getProduct().getQuantity() - hist.getQuantity());
            
            historicRepository.update(hist);
            productRepository.update(hist.getProduct());

            return new Response<Historic>(true, normalizeHistoric(hist), "Buyed product");
            
        }catch (Exception e) {
            return new Response<Historic>(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-user-reserved", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic[]> getUserReserved(Integer idUser){
        try{
            List<Historic> list = historicRepository.getForUserAndState(idUser, RESERVED_STATE);
            Historic[] exit = new Historic[list.size()];
            
            int i = 0;
            for (Historic historico : list) {
                exit[i++] = normalizeHistoric(historico);
            }
            
            return new Response<Historic[]>(true, exit, "Reserve Found");
        }catch(Exception e){
            return new Response<>(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-user-historic", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic[]> getUserHistoric(Integer idUser){
        try{
            List<Historic> list = historicRepository.getForUserAndState(idUser, HISROTIC_STATE);
            Historic[] exit = new Historic[list.size()];
            
            int i = 0;
            for (Historic historico : list) {
                exit[i++] = normalizeHistoric(historico);
            }
            return new Response<Historic[]>(true, exit, "Historic Found");
        }catch(Exception e){
            return new Response<>(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-provider-reserved", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic[]> getProviderReserved(Integer idProv){
        try{
            List<Historic> list = historicRepository.getForProviderAndState(idProv, RESERVED_STATE);
            Historic[] exit = new Historic[list.size()];
            
            int i = 0;
            for (Historic historico : list) {
                exit[i++] = normalizeHistoric(historico);
            }
            return new Response<Historic[]>(true, exit, "Historic Found");
        }catch(Exception e){
            return new Response<>(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-provider-historic", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historic[]> getProviderHistoric(Integer idProv){
        try{
            List<Historic> list = historicRepository.getForProviderAndState(idProv, HISROTIC_STATE);
            Historic[] exit = new Historic[list.size()];
            
            int i = 0;
            for (Historic historico : list) {
                exit[i++] = normalizeHistoric(historico);
            }
            return new Response<>(true, exit, "Historic Found");
        }catch(Exception e){
            return new Response<>(false, null, e);
        }
    }
}
