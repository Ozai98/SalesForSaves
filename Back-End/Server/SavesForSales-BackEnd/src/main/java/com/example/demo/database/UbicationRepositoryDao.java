package com.example.demo.database;

import com.example.demo.database.models.Ubication;
import com.j256.ormlite.dao.Dao;

public class UbicationRepositoryDao implements Repository<Ubication> {

	private Dao<Ubication, Integer> ubicationDao = DaoController.getInstance().ubicationDao();

	@Override
	public void create(Ubication obj) throws Exception {
		ubicationDao.create(obj);

	}

	@Override
	public Ubication getById(int id) throws Exception {
		return ubicationDao.queryForId(id);
	}

	@Override
	public void update(Ubication obj) throws Exception {
		ubicationDao.update(obj);

	}

	@Override
	public void refresh(Ubication obj) throws Exception {
		ubicationDao.refresh(obj);

	}

}