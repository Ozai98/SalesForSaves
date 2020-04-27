package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author German le yo
 */
@DatabaseTable(tableName = "Usuario")
public class Usuario{

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

    public Usuario(){
        nombre = correo = password = "";
        id = -1;
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
    
    public void setId(int id){
        this.id=id;
    }

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

    // ---------------------------
}