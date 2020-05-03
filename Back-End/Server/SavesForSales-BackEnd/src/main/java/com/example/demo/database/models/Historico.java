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

@DatabaseTable(tableName = "Historico")
public class Historico {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, columnName = "usuario")
    Usuario usuario;
    @DatabaseField(foreign = true, columnName = "producto")
    Producto producto;
    @DatabaseField
    private Date tiempo_reserva;
    @DatabaseField
    private Date fecha_compra;
    @DatabaseField
    private double cantidad;
    @DatabaseField
    String estado;
    
    public Historico(){
        id = -1;
    }
    
    public Historico(Historico other){
        id = other.id;
        usuario = new Usuario(other.usuario);
        producto = new Producto(other.producto);
        tiempo_reserva = other.tiempo_reserva;
        fecha_compra = other.fecha_compra;
        cantidad = other.cantidad;
        estado = other.estado;
    }
    
    public int getId(){return id;}
    public Usuario getUsuario(){return usuario;}
    public Producto getProducto(){return producto;}
    public Date getTiempoReserva(){return tiempo_reserva;}
    public Date getFechaCompra(){return fecha_compra;}
    public double getCantidad(){return cantidad;}
    public String getEstado(){return estado;}
    
    public void setId(int id){this.id = id;}
    public void setUsuario(Usuario usr){usuario = usr;}
    public void setProducto(Producto prod){producto = prod;}
    public void setTiempoReserva(Date tiempo){tiempo_reserva = tiempo;}
    public void setFechaCompra(Date fecha){fecha_compra = fecha;}
    public void setCantidad(double cantidad){this.cantidad = cantidad;}
    public void setEstado(String state){estado = state;}
    
}
