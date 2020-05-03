/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Producto;
import com.example.demo.database.models.Proveedor;
import java.util.List;

/**
 *
 * @author German le yo
 */
public interface ProductoRepository {
    
    void create(Producto prod) throws Exception;
    List<Producto> search(String param) throws Exception;
    Producto getById(int ID) throws Exception;
    void refresh(Producto prod) throws Exception;
    
}
