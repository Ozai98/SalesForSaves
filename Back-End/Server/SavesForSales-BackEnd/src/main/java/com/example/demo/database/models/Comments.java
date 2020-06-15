package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "coments")
public class Comments {
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(foreign = true, columnName = "user")
	private User user;
	@DatabaseField(foreign = true, columnName = "provider")
	private Provider provider;
	@DatabaseField
	private String comment;

	public Comments() {
	}

	public Comments(Comments comments) {
		id = comments.id;
		user = comments.user;
		provider = comments.provider;
		comment = comments.comment;
	}

	public void setId(int id) {this.id = id;}
	public void setUser(User user) {this.user = user;}
	public void setProvider(Provider provider) {this.provider = provider;}
	public void setComment(String comment) {this.comment = comment;}

	public int getId() {return id;}
	public User getUser() {return user;}
	public Provider getProvider() {return provider;}
	public String getComment() {return comment;}
}