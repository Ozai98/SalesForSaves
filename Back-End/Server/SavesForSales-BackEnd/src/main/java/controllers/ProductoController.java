package controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import database.models.*;
import database.*;
import org.springframework.web.bind.annotation.RequestMapping;
import services.Services;

@RestController
@EnableAutoConfiguration
@RequestMapping("/{proveedor}")
public class ProductoController{
	@GetMapping("/")
	public Producto[] getProductos(@PathVariable String proveedor){

	}
	@GetMapping("/{id}")
	public Producto getProducto(@PathVariable String proveedor, @PathVariable Long id){

	}
	
}