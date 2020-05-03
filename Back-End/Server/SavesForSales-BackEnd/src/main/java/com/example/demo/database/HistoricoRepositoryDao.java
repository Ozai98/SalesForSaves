/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Historico;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author German le yo
 */
public class HistoricoRepositoryDao implements Repository<Historico>{
    
     private Dao<Historico, Integer> historicoDao;
    
    public HistoricoRepositoryDao(){
        this.historicoDao = DaoController.getInstance().historicoDao();
    }
    
    @Override
    public void create(Historico historico) throws SQLException{
        historicoDao.create(historico);
    }

    @Override
    public <G> List<Historico> search(G usr) throws SQLException {
        return historicoDao.queryBuilder().where().eq("Usuario_id", usr).query();
    }

    @Override
    public Historico getById(int ID) throws SQLException {
        return historicoDao.queryForId(ID);
    }

    @Override
    public void update(Historico hist) throws SQLException{
        historicoDao.update(hist);
    }

    @Override
    public void refresh(Historico obj) throws Exception {
        historicoDao.refresh(obj);

    }
}
