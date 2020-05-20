package com.example.demo.database;

import java.util.List;

import com.example.demo.database.models.Rate;
import com.example.demo.database.models.User;
import com.j256.ormlite.dao.Dao;

public class RateRepositoryDao implements RateRepositoy {

	private Dao<Rate, Integer> rateDao;

	RateRepositoryDao(){
		this.rateDao = DaoController.getInstance().rateDao();
	}

	@Override
	public void create(Rate obj) throws Exception {
		rateDao.create(obj);
	}

	@Override
	public List<Rate> getByProvider(int provId) throws Exception {
		return rateDao.queryBuilder().where().eq("provider", provId).query();
	}

	@Override
	public Rate getById(int id) throws Exception {
		return rateDao.queryForId(id);
	}

	@Override
	public void update(Rate obj) throws Exception {
		rateDao.update(obj);

	}

	@Override
	public void refresh(Rate obj) throws Exception {
		rateDao.refresh(obj);

	}
	
}