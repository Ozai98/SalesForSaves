/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Historic;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author German le yo
 */
public class HistoricRepositoryDao implements Repository<Historic>{
    
     private Dao<Historic, Integer> historicoDao;
    
    public HistoricRepositoryDao(){
        this.historicoDao = DaoController.getInstance().historicoDao();
    }
    
    @Override
    public void create(Historic historico) throws SQLException{
        historicoDao.create(historico);
    }

    @Override
    public <G> List<Historic> search(G usr) throws SQLException {
        return historicoDao.queryBuilder().where().eq("usuario", usr).query();
    }

    @Override
    public Historic getById(int ID) throws SQLException {
        return historicoDao.queryForId(ID);
    }

    @Override
    public void update(Historic hist) throws SQLException{
        historicoDao.update(hist);
    }

    @Override
    public void refresh(Historic obj) throws Exception {
        historicoDao.refresh(obj);

    }
}
