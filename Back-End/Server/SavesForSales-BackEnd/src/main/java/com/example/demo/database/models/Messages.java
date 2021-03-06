package com.example.demo.database.models;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Messages")
public class Messages {
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(foreign = true, columnName = "user")
	private User user;
	@DatabaseField(foreign = true, columnName = "provider")
	private Provider provider;
	@DatabaseField
	private Date timeSend;
	@DatabaseField
	private String content;
	@DatabaseField
	private Boolean senderProvider;

	public void setId(int id) {
		this.id = id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setTimeSend(java.util.Date date) {
		this.timeSend = date;
	}
	public void setSenderProvider(Boolean senderProvider) {
		this.senderProvider = senderProvider;
	}

	public Boolean getSenderProvider() {
		return senderProvider;
	}
	public String getContent() {
		return content;
	}
	public Provider getProvider() {
		return provider;
	}
	public Date getTimeSend() {
		return timeSend;
	}
	public User getUser() {
		return user;
	}
	public int getId() {
		return id;
	}
}