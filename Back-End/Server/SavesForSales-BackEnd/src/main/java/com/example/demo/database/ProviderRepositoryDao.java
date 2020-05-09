/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Provider;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

public class ProviderRepositoryDao implements Repository<Provider>{
 
    private Dao<Provider, Integer> proveedorDao;
    
    public ProviderRepositoryDao(){
        this.proveedorDao = DaoController.getInstance().proveedorDao();
    }
    
    @Override
    public void create(Provider usr) throws SQLException{
        proveedorDao.create(usr);
    }

    @Override
    public <G> List<Provider> search(G email)  throws SQLException{
        return proveedorDao.queryForEq("correo", email);
    }

    @Override
    public Provider getById(int ID)  throws SQLException{
        return proveedorDao.queryForId(ID);
    }

    @Override
    public void update(Provider usr)  throws SQLException{
        proveedorDao.update(usr);
    }

    @Override
    public void refresh(Provider prov) throws Exception {
        proveedorDao.refresh(prov);
    }
    
}
