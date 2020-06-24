package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Ubication")
public class Ubication {
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private Double lat;
	@DatabaseField(columnName = "long")
	private Double longitud;

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
	public Double getLat() {
		return lat;
	}
	public Double getLongitud() {
		return longitud;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}


}