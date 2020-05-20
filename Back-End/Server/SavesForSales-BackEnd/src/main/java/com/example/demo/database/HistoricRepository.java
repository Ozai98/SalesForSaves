/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Historic;
import java.util.List;

/**
 *
 * @author German le yo
 */
public interface HistoricRepository extends Repository<Historic>{
    
    List<Historic> getForUserAndState(Integer usrId, String state) throws Exception;   
    List<Historic> getForProviderAndState(Integer usrId, String state) throws Exception;
}
