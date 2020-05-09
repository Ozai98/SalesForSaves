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

    private Dao<Product, Integer> productDao;

    public ProductRepositoryDao(){
        this.productDao = DaoController.getInstance().productDao();
    }
    
    @Override
    public void create(Product prod) throws Exception {
        productDao.create(prod);
    }

    @Override
    public <G> List<Product> search(G param) throws Exception {
        return productDao.queryBuilder().where().like("nombre", "%" + param + "%").query();
    }

    @Override
    public Product getById(int ID) throws Exception {
        return productDao.queryForId(ID);
    }

    @Override
    public void update(Product obj) throws Exception {
        productDao.update(obj);

    }

    @Override
    public void refresh(Product obj) throws Exception {
        productDao.refresh(obj);

    }
    
}
