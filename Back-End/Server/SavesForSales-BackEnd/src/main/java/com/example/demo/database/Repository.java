package com.example.demo.database;

import java.util.List;

public interface Repository<T> {
	void create(T obj) throws Exception;
	<G> List<T> search(G obj) throws Exception;
	T getById(int id) throws Exception;
	void update(T obj) throws Exception;
	void refresh(T obj) throws Exception;
	
	static UserRepositoryDao User(){
		return new UserRepositoryDao();
	}
	static ProviderRepositoryDao Provider(){
		return new ProviderRepositoryDao();
	}
	static ProductRepositoryDao Product(){
		return new ProductRepositoryDao();
	}
	static HistoricRepositoryDao Historic(){
		return new HistoricRepositoryDao();
	}
	static RateRepositoryDao Rate(){
		return new RateRepositoryDao();
	}
}