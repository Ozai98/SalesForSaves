/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Product;
import java.util.List;

/**
 *
 * @author German le yo
 */
public interface ProductRepository extends Repository<Product>{
    List<Product> search(String param) throws Exception;
}
