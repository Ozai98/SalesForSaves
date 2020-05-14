/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Client;
import java.util.List;

/**
 *
 * @author German le yo
 */
public interface ClientRepository<T extends Client> extends Repository<T>{
    List<T> getByEmail(String email) throws Exception;
}
