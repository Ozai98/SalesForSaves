/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Historico;
import com.example.demo.database.models.Usuario;
import java.util.List;

/**
 *
 * @author German le yo
 */
public interface HistoricoRepository {
    
    void create(Historico historico) throws Exception;
    List<Historico> getForUserAndState(Integer usrId, String state) throws Exception;
    Historico getById(int ID) throws Exception;
    void update(Historico hist)throws Exception;
}
