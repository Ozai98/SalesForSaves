/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Historic;
import com.example.demo.database.models.Product;
import com.example.demo.database.models.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author German le yo
 */
public class HistoricRepositoryDao implements HistoricRepository{
    
     private Dao<Historic, Integer> historicDao;
    
    public HistoricRepositoryDao(){
        this.historicDao = DaoController.getInstance().historicDao();
    }
    
    @Override
    public void create(Historic historic) throws SQLException{
        historicDao.create(historic);
    }

    public Historic getById(int ID) throws SQLException {
        return historicDao.queryForId(ID);
    }

    @Override
    public void update(Historic hist) throws SQLException{
        historicDao.update(hist);
    }

    @Override
    public void refresh(Historic obj) throws Exception {
        historicDao.refresh(obj);
    }
    
    @Override
    public List<Historic> getForUserAndState(Integer usrId, String state) throws SQLException {
        return historicDao.queryBuilder().where().eq("user", usrId).and().eq("state", state).query();
    }

    @Override
    public List<Historic> getForProviderAndState(Integer provId, String state) throws Exception {
        	
        QueryBuilder<Provider, Integer> provQuery = DaoController.getInstance().providerDao().queryBuilder();
        provQuery.where().eq("id", provId);
        QueryBuilder<Product, Integer> prodQuery = DaoController.getInstance().productDao().queryBuilder();
        prodQuery.join(provQuery);
        QueryBuilder<Historic, Integer> prodHist = historicDao.queryBuilder();
        prodHist.where().eq("state", state);
        return prodHist.join(prodQuery).query();
    }
}
