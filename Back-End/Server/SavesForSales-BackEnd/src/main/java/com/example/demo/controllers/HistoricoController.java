/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;

import com.example.demo.database.HistoricoRepository;
import com.example.demo.database.HistoricoRepositoryDao;
import com.example.demo.database.ProductoRepository;
import com.example.demo.database.ProductoRepositoryDao;
import com.example.demo.database.ProveedorRepository;
import com.example.demo.database.ProveedorRepositoryDao;
import com.example.demo.database.UsuarioRepository;
import com.example.demo.database.UsuarioRepositoryDao;
import com.example.demo.database.models.Historico;
import com.example.demo.database.models.Producto;
import com.example.demo.database.models.Usuario;
import com.example.demo.services.Services;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class HistoricoController {
    
    private static final String RESERVA_STATE = "RESERVA";
    private static final String HISTORICO_STATE = "HISTORICO";
    
    private HistoricoRepository historicoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;
    private ProveedorRepository proveedorRepository;
    
    public static Historico normalizeHistorico(Historico historico, ProveedorRepository proveedorRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository){
        try{
            productoRepository.refresh(historico.getProducto());
            historico.setProducto(ProductoController.normalizeProducto(historico.getProducto(), proveedorRepository));
            usuarioRepository.refresh(historico.getUsuario());
            historico.setUsuario(UsuarioController.normalizeUser(historico.getUsuario()));
        }catch(Exception e){
            Services.handleError(e);
        }
        
        return historico;
    }
    
    @PostConstruct
    public void init() {
        setHistoricoRepository(new HistoricoRepositoryDao());
        setUsuarioRepository(new UsuarioRepositoryDao());
        setProductoRepository(new ProductoRepositoryDao());
        setProveedorRepository(new ProveedorRepositoryDao());
    }
    
    public void setHistoricoRepository(HistoricoRepository repository){
        this.historicoRepository = repository;
    }
    
    public void setUsuarioRepository(UsuarioRepository repository){
        this.usuarioRepository = repository;
    }
    
    public void setProductoRepository(ProductoRepository repository){
        this.productoRepository = repository;
    }
    
    public void setProveedorRepository(ProveedorRepository repository){
        this.proveedorRepository = repository;
    }
    
    @PostMapping(value = "/reservar", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historico> reservar(Integer idUser, Integer idProducto, Double cantidad, @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") Date fechaReserva){
        
        if(idUser == null || idProducto == null || cantidad == null || fechaReserva == null) 
            return new Response(false, null, String.format("Missing parameters. missing idUser: %b, missing idProducto: %b, missing cantidad: %b, missing fechaReserva: %b",
                    idUser == null, idProducto == null, cantidad == null, fechaReserva == null));
        
        try{
            Usuario usr = usuarioRepository.getById(idUser);
            if(usr == null) return new Response(false, null, "Usuario no Found");
            Producto prod = productoRepository.getById(idProducto);
            if(prod == null) return new Response(false, null, "Producto no Found");
            
            Historico nuevo = new Historico();
            nuevo.setCantidad(cantidad);
            nuevo.setEstado(RESERVA_STATE);
            nuevo.setTiempoReserva(fechaReserva);
            nuevo.setUsuario(usr);
            nuevo.setProducto(prod);

            historicoRepository.create(nuevo);
            
            return new Response(true, normalizeHistorico(nuevo, proveedorRepository, productoRepository, usuarioRepository), "Reserved product");
            
        }catch (Exception e) {
            return new Response(false, null, e);
        }
    }
    
    @PostMapping(value = "/buyed-product", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historico> buyedProduct(Integer idHistorico){
        if(idHistorico == null) return new Response(false, null, String.format("Missing parameters. missing idHistorico: %b" , idHistorico == null));
        
        try{
            Historico hist = historicoRepository.getById(idHistorico);
            
            if(hist == null) return new Response(false, null, "Historico no Found");
            
            if(hist.getTiempoReserva().before(new Date())) return new Response(false, null, String.format("Expired reservation date"));
            if(hist.getEstado().compareTo(RESERVA_STATE) != 0) return new Response(false, null, "Not a reserved element");

            hist.setEstado(HISTORICO_STATE);
            hist.setFechaCompra(new Date());
            historicoRepository.update(hist);
            
            return new Response(true, normalizeHistorico(hist, proveedorRepository, productoRepository, usuarioRepository), "Buyed product");
            
        }catch (Exception e) {
            return new Response(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-user-reserved", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historico[]> getUserReserved(Integer idUser){
        try{
            List<Historico> list = historicoRepository.getForUserAndState(idUser, RESERVA_STATE);
            Historico[] exit = new Historico[list.size()];
            
            int i = 0;
            for (Historico historico : list) {
                exit[i++] = normalizeHistorico(historico, proveedorRepository, productoRepository, usuarioRepository);
            }
            
            return new Response(true, exit, "Reserve Found");
        }catch(Exception e){
            return new Response(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-user-historic", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Historico[]> getUserHistoric(Integer idUser){
        try{
            List<Historico> list = historicoRepository.getForUserAndState(idUser, HISTORICO_STATE);
            Historico[] exit = new Historico[list.size()];
            
            int i = 0;
            for (Historico historico : list) {
                exit[i++] = normalizeHistorico(historico, proveedorRepository, productoRepository, usuarioRepository);
            }
            return new Response(true, exit, "Historic Found");
        }catch(Exception e){
            return new Response(false, null, e);
        }
    }
    
    @PostMapping(value = "/get-provder-reserved", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Producto> getProviderReserved(Integer idProv){
        return null;
    }
    
    @PostMapping(value = "/get-provder-historic", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Producto> getProviderHistoric(Integer idProv){
        return null;
    }
}
