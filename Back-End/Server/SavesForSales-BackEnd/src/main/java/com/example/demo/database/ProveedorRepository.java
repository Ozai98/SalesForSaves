/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Proveedor;
import java.util.List;

/**
 *
 * @author German le yo
 */
public interface ProveedorRepository {
    void create(Proveedor usr) throws Exception;
    List<Proveedor> getByEmail(String email) throws Exception;
    Proveedor getById(int ID) throws Exception;
    void update(Proveedor prov) throws Exception;
    void refresh(Proveedor prov) throws Exception;
    
}
