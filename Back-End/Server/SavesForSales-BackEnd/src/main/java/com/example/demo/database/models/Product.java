package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName="Producto")
public class Product{
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private double precio;
	@DatabaseField
	private String nombre;
	@DatabaseField(foreign = true, columnName = "proveedor")
	Provider proveedor;
        @DatabaseField
        private String imagen;
        @DatabaseField
        private double cantidad;
        @DatabaseField
        private Date fecha_publicacion;
        
        public Product(){}
        
        public Product(Product other){
            id = other.id;
            precio = other.precio;
            nombre = other.nombre;
            proveedor = new Provider(other.proveedor);
            imagen = other.imagen;
            cantidad = other.cantidad;
            fecha_publicacion = other.fecha_publicacion;
            
        }
        
	public int getId(){return id;}
	public double getPrecio(){return precio;}
	public String getNombre(){return nombre;}
	public Provider getProveedor(){return proveedor;}
        public String getImagen(){return imagen;}
        public double getCantidad(){return cantidad;}
        public Date getFechaPublicacion(){return fecha_publicacion;}

        public void setId(int id){this.id = id;}
	public void setPrecio(double p){precio=p;}
	public void setNombre(String n){nombre=n;}
	public void setProveedor(Provider p){proveedor=p;}
        public void setImagen(String i){imagen=i;}
        public void setCantidad(double c){cantidad = c;}
        public void setFechaPublicacion(Date d){fecha_publicacion = d;}
}
