package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author German le yo
 */
@DatabaseTable(tableName = "Provider")
public class Provider implements Client{

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
    @DatabaseField
    private String ubication;

    public Provider(){
        name = mail = password = avatar = ubication = "";
        id = -1;
    }
    
    public Provider(Provider prov){
        this.id = prov.id;
        this.name = prov.name;
        this.mail = prov.mail;
        this.password = prov.password;
        this.avatar = prov.avatar;
        this.ubication = prov.ubication;
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
    
    public String getUbication() {
        return ubication;
    }

    // ---------------- SETTERS---------------
    
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
    
    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    // ---------------------------
}