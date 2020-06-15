package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Ubication")
public class Ubication {
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private double lat;
	@DatabaseField(columnName = "long")
	private double longitud;

	public Ubication() {
	}
	public Ubication(Ubication ubication) {
		id = ubication.id;
		lat = ubication.lat;
		longitud = ubication.longitud;
	}

	public int getId() {
		return id;
	}
	public double getLat() {
		return lat;
	}
	public double getLongitud() {
		return longitud;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


}