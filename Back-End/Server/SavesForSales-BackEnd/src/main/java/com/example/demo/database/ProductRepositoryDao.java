/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Product;
import com.j256.ormlite.dao.Dao;
import java.util.List;

/**
 *
 * @author German le yo
 */
public class ProductRepositoryDao implements Repository<Product>{

    private Dao<Product, Integer> productoDao;

    public ProductRepositoryDao(){
        this.productoDao = DaoController.getInstance().productoDao();
    }
    
    @Override
    public void create(Product prod) throws Exception {
        productoDao.create(prod);
    }

    @Override
    public <G> List<Product> search(G param) throws Exception {
        return productoDao.queryBuilder().where().like("nombre", "%" + param + "%").query();
    }

    @Override
    public Product getById(int ID) throws Exception {
        return productoDao.queryForId(ID);
    }

    @Override
    public void update(Product obj) throws Exception {
        productoDao.update(obj);

    }

    @Override
    public void refresh(Product obj) throws Exception {
        productoDao.refresh(obj);

    }
    
}
