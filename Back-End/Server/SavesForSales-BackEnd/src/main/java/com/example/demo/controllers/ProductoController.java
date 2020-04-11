package com.example.demo.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;

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
import com.example.demo.database.DatabaseController;
import com.j256.ormlite.stmt.Where;

@RestController
@EnableAutoConfiguration
@RequestMapping("/producto")
public class ProductoController {

    private Dao<Producto, Integer> productoDao;
    private Dao<Proveedor, Integer> proveedorDao;

    private Producto normalizeProducto(Producto producto){
        try{
            proveedorDao.refresh(producto.getProveedor());
            ProveedorController.normalizeProveedor(producto.getProveedor());
        }catch(SQLException ex){
            Services.handleError(ex);
        }
        
        return producto;
    }
    
    @PostConstruct
    public void init() {
        productoDao = DatabaseController.getInstance().productoDao();
        proveedorDao = DatabaseController.getInstance().proveedorDao();
    }

    @GetMapping("/search/{nombre}")
    public Response<Producto[]> searchProductos(@PathVariable String nombre) {
        try {
            List<Producto> result =  productoDao.queryBuilder().where().like("nombre", "%" + nombre + "%").query();
            Producto[] response = new Producto[result.size()]; int i = 0;
            for(Producto producto: result) response[i++] = normalizeProducto(producto);
            return new Response(true, response, "Ok");
        } catch (SQLException ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public Response<Producto> getProducto( @PathVariable Integer id) {
        try {
            Producto product = productoDao.queryForId(id);
            if(product == null) return new Response(false, null, "Product no Found");
            return new Response(true, normalizeProducto(product), "Ok: " + product.getProveedor().getId());
        } catch (SQLException ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }

    }

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response<Producto> crear(String nombre, int precio, int id_proveedor){
        if(precio <= 0) return new Response(false, null, "precio invalido");
        Producto nProducto = new Producto();
        nProducto.setNombre(nombre);
        nProducto.setPrecio(precio);
        Proveedor creador;
        try {
            creador = proveedorDao.queryForId(id_proveedor);
            if(creador == null) return new Response(false, null, "id_proveedor don't match with any provider id: " + id_proveedor);
            nProducto.setProveedor(creador);
            productoDao.create(nProducto);
            return new Response(true, nProducto, "Ok. " + creador.getNombre());
        } catch (SQLException e) {
            return new Response(false, null, e);
        }
    }
}
