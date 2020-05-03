/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Usuario;
import java.util.List;

/**
 *
 * @author German le yo
 */
public interface UsuarioRepository {
    
    void create(Usuario usr) throws Exception;
    List<Usuario> getByEmail(String email) throws Exception;
    Usuario getById(int ID) throws Exception;
    void update(Usuario usr) throws Exception;
    void refresh(Usuario usr) throws Exception;
    
}
