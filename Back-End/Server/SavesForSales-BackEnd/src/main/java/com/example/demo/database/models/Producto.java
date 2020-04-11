package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="Producto")
public class Producto{
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private int precio;
	@DatabaseField
	private String nombre;
	@DatabaseField(foreign = true, columnName = "proveedor")
	Proveedor proveedor;

        public Producto(){}
        
	public int getId(){return id;}
	public int getPrecio(){return precio;}
	public String getNombre(){return nombre;}
	public Proveedor getProveedor(){return proveedor;}

	public void setPrecio(int p){precio=p;}
	public void setNombre(String n){nombre=n;}
	public void setProveedor(Proveedor p){proveedor=p;}
}
