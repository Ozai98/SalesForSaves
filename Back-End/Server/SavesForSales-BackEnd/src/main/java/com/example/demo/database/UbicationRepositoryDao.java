package com.example.demo.database;

import com.example.demo.database.models.Ubication;

public class UbicationRepositoryDao implements Repository<Ubication> {

	private static final Repository<Ubication> UBICATION = RepositoryController.Ubication();

	@Override
	public void create(Ubication obj) throws Exception {
		UBICATION.create(obj);

	}

	@Override
	public Ubication getById(int id) throws Exception {
		return UBICATION.getById(id);
	}

	@Override
	public void update(Ubication obj) throws Exception {
		UBICATION.update(obj);

	}

	@Override
	public void refresh(Ubication obj) throws Exception {
		UBICATION.refresh(obj);

	}

}