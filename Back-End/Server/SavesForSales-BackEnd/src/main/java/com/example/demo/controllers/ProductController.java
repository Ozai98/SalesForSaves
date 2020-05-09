package com.example.demo.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Product;
import com.example.demo.database.models.Provider;
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
public class ProductController {

    private Repository<Product> productoRepository;
    private Repository<Provider> proveedorRepository;

    
    
    @PostConstruct
    public void init() {
        setProductoRepository(Repository.Producto());
        setProveedorRepository(Repository.Proveedor());
    }

    public Product normalizeProducto(Product producto){
        try{
            proveedorRepository.refresh(producto.getProveedor());
        }catch(Exception ex){
            Services.handleError(ex);
        }            
        Services.normalize(producto.getProveedor());
        return producto;
    }
    
    public void setProductoRepository(Repository<Product> repository){
        this.productoRepository = repository;
    }
    
    public void setProveedorRepository(Repository<Provider> repository){
        this.proveedorRepository = repository;
    }

    @GetMapping("/search/{nombre}")
    public Response<Product[]> searchProductos(@PathVariable String nombre) {
        try {
            List<Product> result =  productoRepository.search(nombre);
            Product[] response = new Product[result.size()]; int i = 0;
            for(Product producto: result) response[i++] = normalizeProducto(producto);
            return new Response<Product []>(true, response, "Ok");
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response<Product []>(false, null, ex);
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/get-by-id/{id}")
    public Response<Product> getProducto( @PathVariable Integer id) {
        try {
            Product product = productoRepository.getById(id);
            if(product == null) return new Response<Product>(false, null, "Product no Found");
            return new Response<Product>(true, normalizeProducto(product), "Ok: " + product.getProveedor().getId());
        } catch (Exception ex) {
            Services.handleError(ex);
            return new Response<Product>(false, null, ex);
        }

    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Product> crear(String nombre, Double precio, Integer id_proveedor, String imagen, Double cantidad){
        if(precio <= 0) return new Response<Product>(false, null, "precio invalido");
        
        if(imagen == null) imagen = "";
        
        Product nProducto = new Product();
        nProducto.setNombre(nombre);
        nProducto.setPrecio(precio);
        nProducto.setImagen(imagen);
        nProducto.setCantidad(cantidad);
        nProducto.setFechaPublicacion(new Date());
        Provider creador;
        try {
            creador = proveedorRepository.getById(id_proveedor);
            if(creador == null) return new Response<Product>(false, null, "id_proveedor don't match with any provider id: " + id_proveedor);
            nProducto.setProveedor(creador);
            productoRepository.create(nProducto);
            return new Response<Product>(true, normalizeProducto(nProducto), "Ok. " + creador.getNombre());
        } catch (Exception e) {
            return new Response<Product>(false, null, e);
        }
    }
}
