/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.User;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;


public class UserRepositoryDao implements ClientRepository<User>{

    private Dao<User, Integer> userDao;
    
    public UserRepositoryDao(){
        this.userDao = DaoController.getInstance().userDao();
    }
    
    @Override
    public void create(User usr) throws SQLException{
        userDao.create(usr);
    }

    @Override
    public List<User> getByEmail(String email)  throws SQLException{
        return userDao.queryForEq("mail", email);
    }

    @Override
    public User getById(int ID)  throws SQLException{
        return userDao.queryForId(ID);
    }

    @Override
    public void update(User usr)  throws SQLException{
        userDao.update(usr);
    }

    @Override
    public void refresh(User obj) throws Exception {
        userDao.refresh(obj);
    }
    
}
