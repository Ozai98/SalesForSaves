/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

/**
 *
 * @author German le yo
 */

@DatabaseTable(tableName = "Historic")
public class Historic {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, columnName = "user")
    User user;
    @DatabaseField(foreign = true, columnName = "product")
    Product product;
    @DatabaseField
    private Date timeReserve;
    @DatabaseField
    private Date buyDate;
    @DatabaseField
    private double cuantity;
    @DatabaseField
    String state;
    
    public Historic(){
        id = -1;
    }
    
    public Historic(Historic other){
        id = other.id;
        user = new User(other.user);
        product = new Product(other.product);
        timeReserve = other.timeReserve;
        buyDate = other.buyDate;
        cuantity = other.cuantity;
        state = other.state;
    }
    
    public int getId(){return id;}
    public User getUser(){return user;}
    public Product getProduct(){return product;}
    public Date getTimeReserve(){return timeReserve;}
    public Date getBuyDate(){return buyDate;}
    public double getCuantity(){return cuantity;}
    public String getState(){return state;}
    
    public void setId(int id){this.id = id;}
    public void setUser(User usr){user = usr;}
    public void setProduct(Product prod){product = prod;}
    public void setTimeReserve(Date time){timeReserve = time;}
    public void setBuyDate(Date date){buyDate = date;}
    public void setCuantity(double cuantity){this.cuantity = cuantity;}
    public void setState(String state){this.state = state;}
    
}
