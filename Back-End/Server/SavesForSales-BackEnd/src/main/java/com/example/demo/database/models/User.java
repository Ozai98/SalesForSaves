package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author German le yo
 */
@DatabaseTable(tableName = "User")
public class User implements Client{

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String name;
	@DatabaseField
	private String mail;
	@DatabaseField
	private String password;
	@DatabaseField
	private String avatar;

	public User(){
		name = mail = password = "";
	}
	
	public User(User other){
		id = other.id;
		name = other.name;
		mail = other.mail;
		password = other.password;
		avatar= other.avatar;
	}

	// ---- GETS AND SETS ---------

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setId(Integer id){this.id = id;}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	// ---------------------------
}