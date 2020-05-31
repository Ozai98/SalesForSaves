/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Rate;
import java.util.List;

/**
 *
 * @author German le yo
 */
public interface RateRepositoy extends Repository<Rate>{
    List<Rate> getByProvider(int provId)  throws Exception ;
}
