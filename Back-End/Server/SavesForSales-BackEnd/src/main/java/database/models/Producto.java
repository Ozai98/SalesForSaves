package database.models;

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
	@DatabaseField
	private String proveedor;

	public int getId(){return id;}
	public int getPrecio(){return precio;}
	public String getName(){return name;}
	public String getProveedor(){return proveedor;}

	public void setPrecio(int p){precio=p;}
	public void setName(String n){name=n;}
	public void setProveedor(String p){proveedor=p;}
}
