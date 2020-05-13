package com.example.demo.database;

import com.example.demo.database.models.Provider;
import com.example.demo.database.models.User;

public interface Repository<T> {
    void create(T obj) throws Exception;
    T getById(int id) throws Exception;
    void update(T obj) throws Exception;
    void refresh(T obj) throws Exception;

    static ClientRepository<User> User(){
            return new UserRepositoryDao();
    }
    static ClientRepository<Provider> Provider(){
            return new ProviderRepositoryDao();
    }
    static ProductRepository Product(){
            return new ProductRepositoryDao();
    }
    static HistoricRepository Historic(){
            return new HistoricRepositoryDao();
    }
}