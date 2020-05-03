/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Producto;
import com.j256.ormlite.dao.Dao;
import java.util.List;

/**
 *
 * @author German le yo
 */
public class ProductoRepositoryDao implements Repository<Producto>{

    private Dao<Producto, Integer> productoDao;

    public ProductoRepositoryDao(){
        this.productoDao = DaoController.getInstance().productoDao();
    }
    
    @Override
    public void create(Producto prod) throws Exception {
        productoDao.create(prod);
    }

    @Override
    public <G> List<Producto> search(G param) throws Exception {
        return productoDao.queryBuilder().where().like("nombre", "%" + param + "%").query();
    }

    @Override
    public Producto getById(int ID) throws Exception {
        return productoDao.queryForId(ID);
    }

    @Override
    public void update(Producto obj) throws Exception {
        productoDao.update(obj);

    }

    @Override
    public void refresh(Producto obj) throws Exception {
        productoDao.refresh(obj);

    }
    
}
