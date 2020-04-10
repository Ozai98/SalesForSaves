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
import com.example.demo.services.Services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.database.DatabaseController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/producto")
public class ProductoController {

    private Dao<Producto, Integer> productoDao;

    @PostConstruct
    public void init() {
        productoDao = DatabaseController.getInstance().productoDao();
    }

    @GetMapping("/{nombre}")
    public Response<Producto> getProductos(@PathVariable String nombre) {
        try {
            List<Producto> result = productoDao.queryBuilder().where().eq("name", nombre).query();
            return new Response(true, (Producto[]) result.toArray(), "");
        } catch (SQLException ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }

    @GetMapping("/{id}/{proveedor}")
    public Response<Producto> getProducto(@PathVariable String proveedor, @PathVariable Integer id) {
        Producto product;
        try {
            product = productoDao.queryForId(id);
            return new Response(true, product, "");
        } catch (SQLException ex) {
            Services.handleError(ex);
            return new Response(false, null, ex);
        }

    }
}
