package com.example.demo.database;

import com.example.demo.database.models.Provider;
import com.example.demo.database.models.User;

public interface Repository<T> {
    void create(T obj) throws Exception;
    T getById(int id) throws Exception;
    void update(T obj) throws Exception;
    void refresh(T obj) throws Exception;

    
}