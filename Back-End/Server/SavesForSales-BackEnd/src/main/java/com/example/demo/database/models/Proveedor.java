package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author German le yo
 */
@DatabaseTable(tableName = "Proveedor")
public class Proveedor{

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nombre;
    @DatabaseField
    private String correo;
    @DatabaseField
    private String password;
    @DatabaseField
    private String avatar;
    @DatabaseField
    private String ubicacion;

    public Proveedor(){
        nombre = correo = password = "";
    }

    public Proveedor(Proveedor other){
        id = other.id;
        nombre = other.nombre;
        correo = other.correo;
        password = other.password;
        avatar = other.avatar;
        ubicacion = other.ubicacion;
    }
    
    // ---- GETS AND SETS ---------

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }

    // ---------------- SETTERS---------------
    
    public void setId(int id){this.id = id;}
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // ---------------------------
}