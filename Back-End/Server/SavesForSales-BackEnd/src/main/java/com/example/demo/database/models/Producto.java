package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="productos")
public class Producto{
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private int precio;
	@DatabaseField
	private String name;
	@DatabaseField(foreign = true, columnName = "proveedor_id")
	Proveedor proveedor;

	public int getId(){return id;}
	public int getPrecio(){return precio;}
	public String getName(){return name;}
	public Proveedor getProveedor(){return proveedor;}

	public void setPrecio(int p){precio=p;}
	public void setName(String n){name=n;}
	public void setProveedor(Proveedor p){proveedor=p;}
}
