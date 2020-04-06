package com.example.demo.controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Producto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@EnableAutoConfiguration
@RequestMapping("/producto")
public class ProductoController{
	@GetMapping("/{proveedor}")
	public Producto[] getProductos(@PathVariable String proveedor){
            Producto product = new Producto();
            product.setProveedor(proveedor);
            return new Producto[]{product, product, product};
	}
	@GetMapping("/{id}/{proveedor}")
	public Producto getProducto(@PathVariable String proveedor, @PathVariable Long id){
            Producto product = new Producto();
            product.setProveedor(proveedor);
            return product;
	}
}