package com.example.demo.database.models;

public interface Client {
	public int getId();
    public String getNombre();
    public String getCorreo();
    public String getPassword();
	public String getAvatar();

	public void setId(int id);
    public void setNombre(String nombre);
    public void setCorreo(String correo);
    public void setPassword(String password);
    public void setAvatar(String avatar);
}