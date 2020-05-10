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
 
    private Dao<Provider, Integer> providerDao;
    
    public ProviderRepositoryDao(){
        this.providerDao = DaoController.getInstance().providerDao();
    }
    
    @Override
    public void create(Provider usr) throws SQLException{
        providerDao.create(usr);
    }

    @Override
    public <G> List<Provider> search(G email)  throws SQLException{
        return providerDao.queryForEq("mail", email);
    }

    @Override
    public Provider getById(int ID)  throws SQLException{
        return providerDao.queryForId(ID);
    }

    @Override
    public void update(Provider usr)  throws SQLException{
        providerDao.update(usr);
    }

    @Override
    public void refresh(Provider prov) throws Exception {
        providerDao.refresh(prov);
    }
    
}
