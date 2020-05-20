package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Rate")
public class Rate {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(foreign = true, columnName = "provider")
	private Provider provider;
	@DatabaseField
	private int rate;
	@DatabaseField(foreign = true, columnName = "user")
	private User user;

	public Rate(){}

	public Rate(Rate rate){
            this.id = rate.id;
            this.provider = new Provider(rate.provider);
            this.rate = rate.rate;
            this.user = new User(rate.user);
	}

	public int getId() {return id;}
	public Provider getProvider() {return provider;}
	public int getRate() {return rate;}
	public User getUser() {return user;}

	public void setId(int id) {this.id = id;}		
	public void setProvider(Provider provider) {this.provider = provider;}
	public void setRate(int rate) {this.rate = rate;}
	public void setUser(User user) {this.user = user;}

	
}