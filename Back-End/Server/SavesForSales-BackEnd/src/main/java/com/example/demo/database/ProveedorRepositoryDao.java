/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Proveedor;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author German le yo
 */
public class ProveedorRepositoryDao implements ProveedorRepository{
 
    private Dao<Proveedor, Integer> proveedorDao;
    
    public ProveedorRepositoryDao(){
        this.proveedorDao = DaoController.getInstance().proveedorDao();
    }
    
    @Override
    public void create(Proveedor usr) throws SQLException{
        proveedorDao.create(usr);
    }

    @Override
    public List<Proveedor> getByEmail(String email)  throws SQLException{
        return proveedorDao.queryForEq("correo", email);
    }

    @Override
    public Proveedor getById(int ID)  throws SQLException{
        return proveedorDao.queryForId(ID);
    }

    @Override
    public void update(Proveedor usr)  throws SQLException{
        proveedorDao.update(usr);
    }

    @Override
    public void refresh(Proveedor prov) throws Exception {
        proveedorDao.refresh(prov);
    }
    
}
