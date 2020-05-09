/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Historic;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.example.demo.database.models.Provider;
import com.example.demo.database.models.User;
import com.example.demo.services.Services;
import com.example.demo.database.models.Product;
import com.example.demo.savesforsales.Index;

/**
 *
 * @author German le yo
 */
public class DaoController {

    private static final String REMOTE_DB_CONNECTION = "jdbc:mysql://sfsback:0000@190.157.224.51/SavesForSales?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOCAL_DB_CONNECTION = "jdbc:mysql://sfsback:0000@localhost:3306/savesforsales?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    private static DaoController instance;

    private final ConnectionSource connection;
    private final Dao<User, Integer> userDao;    
    private final Dao<Provider, Integer> proveedorDao;
    private final Dao<Product, Integer> productoDao;
    private final Dao<Historic, Integer> historicoDao;

    private DaoController(String dbConnection) throws SQLException {
        connection = new JdbcConnectionSource(dbConnection);
        userDao = DaoManager.createDao(connection, User.class);        
        proveedorDao = DaoManager.createDao(connection, Provider.class);
        productoDao = DaoManager.createDao(connection, Product.class);
        historicoDao = DaoManager.createDao(connection, Historic.class);
    }

    public static DaoController getInstance(){
        if(instance == null) try {
            if(Index.REMOTE) instance = new DaoController(REMOTE_DB_CONNECTION);
            else instance = new DaoController(LOCAL_DB_CONNECTION);
        } catch (SQLException e) {
            Services.handleError(e);
        }
        return instance;
    } 

    public Dao<User, Integer> userDao(){
        return userDao;
    }
    
    public Dao<Provider, Integer> proveedorDao(){
        return proveedorDao;
    }

    public Dao<Product, Integer> productoDao(){
        return productoDao;
    }
    
    public Dao<Historic, Integer> historicoDao(){
        return historicoDao;
    }
}
