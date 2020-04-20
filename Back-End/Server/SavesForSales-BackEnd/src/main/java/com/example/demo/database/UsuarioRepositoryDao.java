/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Usuario;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author German le yo
 */
public class UsuarioRepositoryDao implements UsuarioRepository{

    private Dao<Usuario, Integer> userDao;
    
    public UsuarioRepositoryDao(){
        this.userDao = DaoController.getInstance().userDao();
    }
    
    @Override
    public void create(Usuario usr) throws SQLException{
        userDao.create(usr);
    }

    @Override
    public List<Usuario> getByEmail(String email)  throws SQLException{
        return userDao.queryForEq("correo", email);
    }

    @Override
    public Usuario getById(int ID)  throws SQLException{
        return userDao.queryForId(ID);
    }

    @Override
    public void update(Usuario usr)  throws SQLException{
        userDao.update(usr);
    }
    
}