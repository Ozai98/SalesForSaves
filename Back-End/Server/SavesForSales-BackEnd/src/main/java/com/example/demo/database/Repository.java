package com.example.demo.database;

import java.util.List;

public interface Repository<T> {
	void create(T obj) throws Exception;
	<G> List<T> search(G obj) throws Exception;
	T getById(int id) throws Exception;
	void update(T obj) throws Exception;
	void refresh(T obj) throws Exception;
	
	static UsuarioRepositoryDao Usuario(){
		return new UsuarioRepositoryDao();
	}
	static ProveedorRepositoryDao Proveedor(){
		return new ProveedorRepositoryDao();
	}
	static ProductoRepositoryDao Producto(){
		return new ProductoRepositoryDao();
	}
	static HistoricoRepositoryDao Historico(){
		return new HistoricoRepositoryDao();
	}
}