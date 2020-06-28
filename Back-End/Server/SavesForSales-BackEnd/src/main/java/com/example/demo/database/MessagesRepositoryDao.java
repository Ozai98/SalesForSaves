package com.example.demo.database;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.database.models.Messages;
import com.j256.ormlite.dao.Dao;

public class MessagesRepositoryDao implements MessagesRepository {

	private final Dao<Messages, Integer> messagesDao;

	public MessagesRepositoryDao(){
		messagesDao = DaoController.getInstance().messagesDao();
	}

	@Override
	public void create(Messages obj) throws Exception {
		messagesDao.create(obj);
	}

	@Override
	public Messages getById(int id) throws Exception {
		
		return messagesDao.queryForId(id);
	}

	@Override
	public void update(Messages obj) throws Exception {
		messagesDao.update(obj);
	}

	@Override
	public void refresh(Messages obj) throws Exception {
		messagesDao.refresh(obj);
	}

	@Override
	public List<Messages> searchByInvolved(
		Integer id,
		Integer idReciber) throws SQLException
	{
		return messagesDao.queryBuilder().where().eq("user", id).and().eq("provider", idReciber).or().eq("user", idReciber).and().eq("provider", id).query();
	}
	
}