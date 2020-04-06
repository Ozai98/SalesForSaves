/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.example.demo.database.models.Proveedor;
import com.example.demo.database.models.Usuario;
import com.example.demo.services.Services;
import com.example.demo.database.models.Producto;

/**
 *
 * @author German le yo
 */
public class DatabaseController {

    private static final String DB_CONNECTION = "jdbc:mysql://fO2PgMO03q:hpVY4ehAVj@remotemysql.com:3306/fO2PgMO03q?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //private static final String DB_CONNECTION = "jdbc:mysql://root:0000@localhost:3306/savesforsales?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    private static DatabaseController instance;

    private final ConnectionSource connection;
    private final Dao<Usuario, Integer> userDao;    
    private final Dao<Proveedor, Integer> proveedorDao;
    private final Dao<Producto, Integer> productoDao;

    private DatabaseController() throws SQLException {
        connection = new JdbcConnectionSource(DB_CONNECTION);
        userDao = DaoManager.createDao(connection, Usuario.class);        
        proveedorDao = DaoManager.createDao(connection, Proveedor.class);
        productoDao = DaoManager.createDao(connection, Producto.class);
    }

    public static DatabaseController getInstance(){
        if(instance == null) try {
            instance = new DatabaseController();
        } catch (SQLException e) {
            Services.handleError(e);
        }
        return instance;
    } 

    public Dao<Usuario, Integer> userDao(){
        return userDao;
    }
    
    public Dao<Proveedor, Integer> proveedorDao(){
        return proveedorDao;
    }

    public Dao<Producto, Integer> productoDao(){
        return productoDao;
    }
}
