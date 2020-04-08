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
import com.example.demo.database.DatabaseController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/producto")
public class ProductoController{

	private Dao<Producto, Integer> productoDao;

    @PostConstruct
    public void init() {
        productoDao = DatabaseController.getInstance().productoDao();
    }



	@GetMapping("/{proveedor}")
	public Producto[] getProductos(@PathVariable String proveedor){
            Producto product = new Producto();
            product.setProveedor(proveedor);
            return new Producto[]{product, product, product};
	}
        
	@GetMapping("/{id}/{proveedor}")
	public ProductResponse getProducto(@PathVariable String proveedor, @PathVariable Integer id){
			Producto product;
		try {
			product = productoDao.queryForId(id);
			return  ProductResponse.producto(true, product, "");
		} catch (SQLException e) {
			e.printStackTrace();
			return  ProductResponse.producto(false, null, "");
		}
			
			
	}
	public interface ProductResponse{
		static ProductResponse producto(boolean ok, Producto p, String msg){
			return new ProductoResp(ok, p, msg);
		}
		static ProductResponse productos(boolean o, Producto p[], String s){
			return new ProductosResp(o, p, s);
		}
	}
	public static class ProductoResp implements ProductResponse{
		public final boolean ok;
		public final Producto producto;
		public final String msg;
		public ProductoResp(boolean ok, Producto product, String msg){
            this.ok = ok;
            this.producto = product;
			this.msg = msg;
		}
	}
	public static class ProductosResp implements ProductResponse{
		public final boolean ok;
		public final Producto productos[];
		public final String msg;
		ProductosResp(boolean o, Producto p[], String s){
			this.ok = o;
			this.productos = p;
			this.msg = s;
		}
	}
}