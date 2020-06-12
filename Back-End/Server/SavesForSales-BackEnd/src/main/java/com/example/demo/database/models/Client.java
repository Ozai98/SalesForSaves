package com.example.demo.database.models;

public interface Client {
    public int getId();
    public String getName();
    public String getMail();
    public String getPassword();
    public byte[] getAvatar();

    public void setId(Integer id);
    public void setName(String nombre);
    public void setMail(String correo);
    public void setPassword(String password);
    public void setAvatar(byte[] avatar);
}