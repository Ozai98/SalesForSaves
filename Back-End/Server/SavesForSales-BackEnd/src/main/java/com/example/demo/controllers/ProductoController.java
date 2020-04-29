package com.example.demo.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Producto;
import com.example.demo.database.models.Proveedor;
import com.example.demo.services.Services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.database.ProductoRepository;
import com.example.demo.database.ProductoRepositoryDao;
import com.example.demo.database.ProveedorRepository;
import com.example.demo.database.ProveedorRepositoryDao;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/producto")
public class ProductoController {

    private ProductoRepository prodcutoRepository;
    private ProveedorRepository proveedorRepository;

    private Producto normalizeProducto(Producto producto){
        try{
            proveedorRepository.refresh(producto.getProveedor());
        }catch(Exception ex){
            Services.handleError(ex);
        }            
        ProveedorController.normalizeProveedor(producto.getProveedor());
        return producto;
    }
    
    @PostConstruct
    public void init() {
        setProductoRepository(new ProductoRepositoryDao());
        setProveedorRepository(new ProveedorRepositoryDao());
    }
    
    public void setProductoRepository(ProductoRepository repository){
        this.prodcutoRepository = repository;
    }
    
    public void setProveedorRepository(ProveedorRepository repository){
        this.proveedorRepository = repository;
    }

    @GetMapping("/search/{nombre}")
    public Response<Producto[]> searchProductos(@PathVariable String nombre) {
        try {
            List<Producto> result =  prodcutoRepository.search(nombre);
            Producto[] response = new Producto[result.size()]; int i = 0;
            for(Producto producto: result) response[i++] = normalizeProducto(producto);
            return new Response(true, response, "Ok");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/get-by-id/{id}")
    public Response<Producto> getProducto( @PathVariable Integer id) {
        try {
            Producto product = prodcutoRepository.getById(id);
            if(product == null) return new Response(false, null, "Product no Found");
            return new Response(true, normalizeProducto(product), "Ok: " + product.getProveedor().getId());
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }

    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Producto> crear(String nombre, Double precio, Integer id_proveedor, String imagen, Double cantidad){
        if(precio <= 0) return new Response(false, null, "precio invalido");
        
        if(imagen == null) imagen = "";
        
        Producto nProducto = new Producto();
        nProducto.setNombre(nombre);
        nProducto.setPrecio(precio);
        nProducto.setImagen(imagen);
        nProducto.setCantidad(cantidad);
        nProducto.setFechaPublicacion(new Date());
        Proveedor creador;
        try {
            creador = proveedorRepository.getById(id_proveedor);
            if(creador == null) return new Response(false, null, "id_proveedor don't match with any provider id: " + id_proveedor);
            nProducto.setProveedor(creador);
            prodcutoRepository.create(nProducto);
            return new Response(true, normalizeProducto(nProducto), "Ok. " + creador.getNombre());
        } catch (Exception e) {
            return new Response(false, null, e);
        }
    }

    public Response<Producto> searchProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
