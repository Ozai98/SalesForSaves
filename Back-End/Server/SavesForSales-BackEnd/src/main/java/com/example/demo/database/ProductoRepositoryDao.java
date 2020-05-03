/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Producto;
import com.example.demo.database.models.Proveedor;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author German le yo
 */
public class ProductoRepositoryDao implements ProductoRepository{

    private Dao<Producto, Integer> productoDao;

    public ProductoRepositoryDao(){
        this.productoDao = DaoController.getInstance().productoDao();
    }
    
    @Override
    public void create(Producto prod) throws Exception {
        productoDao.create(prod);
    }

    @Override
    public List<Producto> search(String param) throws Exception {
        return productoDao.queryBuilder().where().like("nombre", "%" + param + "%").query();
    }

    @Override
    public Producto getById(int ID) throws Exception {
        return productoDao.queryForId(ID);
    }

    @Override
    public void refresh(Producto prod) throws Exception {
        productoDao.refresh(prod);
    }
    
}
