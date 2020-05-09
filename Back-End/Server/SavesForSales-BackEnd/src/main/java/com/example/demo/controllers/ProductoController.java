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
import com.example.demo.database.Repository;

import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/producto")
public class ProductoController {

    private Repository<Producto> productoRepository;
    private Repository<Proveedor> proveedorRepository;

    
    
    @PostConstruct
    public void init() {
        setProductoRepository(Repository.Producto());
        setProveedorRepository(Repository.Proveedor());
    }

    public Producto normalizeProducto(Producto producto){
        try{
            proveedorRepository.refresh(producto.getProveedor());
        }catch(Exception ex){
            Services.handleError(ex);
        }            
        Services.normalize(producto.getProveedor());
        return producto;
    }
    
    public void setProductoRepository(Repository<Producto> repository){
        this.productoRepository = repository;
    }
    
    public void setProveedorRepository(Repository<Proveedor> repository){
        this.proveedorRepository = repository;
    }

    @GetMapping("/search/{nombre}")
    public Response<Producto[]> searchProductos(@PathVariable String nombre) {
        try {
            List<Producto> result =  productoRepository.search(nombre);
            Producto[] response = new Producto[result.size()]; int i = 0;
            for(Producto producto: result) response[i++] = normalizeProducto(producto);
            return new Response<Producto []>(true, response, "Ok");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response<Producto []>(false, null, ex);
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/get-by-id/{id}")
    public Response<Producto> getProducto( @PathVariable Integer id) {
        try {
            Producto product = productoRepository.getById(id);
            if(product == null) return new Response<Producto>(false, null, "Product no Found");
            return new Response<Producto>(true, normalizeProducto(product), "Ok: " + product.getProveedor().getId());
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response<Producto>(false, null, ex);
        }

    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Producto> crear(String nombre, Double precio, Integer id_proveedor, String imagen, Double cantidad){
        if(precio <= 0) return new Response<Producto>(false, null, "precio invalido");
        
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
            if(creador == null) return new Response<Producto>(false, null, "id_proveedor don't match with any provider id: " + id_proveedor);
            nProducto.setProveedor(creador);
            productoRepository.create(nProducto);
            return new Response<Producto>(true, normalizeProducto(nProducto), "Ok. " + creador.getNombre());
        } catch (Exception e) {
            return new Response<Producto>(false, null, e);
        }
    }
}
