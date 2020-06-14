package com.example.demo.controllers;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.database.CommentsRepository;
import com.example.demo.database.DaoController;
import com.example.demo.database.models.Comments;
import com.j256.ormlite.dao.Dao;

public class CommentRepositoryDao implements CommentsRepository {

	private Dao<Comments, Integer> commentsDao;

	public CommentRepositoryDao() {
		commentsDao = DaoController.getInstance().commentsDao();
	}

	@Override
	public void create(Comments obj) throws Exception {
		commentsDao.create(obj);

	}

	@Override
	public Comments getById(int id) throws Exception {
		return commentsDao.queryForId(id);
	}

	@Override
	public void update(Comments obj) throws Exception {
		commentsDao.update(obj);

	}

	@Override
	public void refresh(Comments obj) throws Exception {
		commentsDao.refresh(obj);

	}

	@Override
	public List<Comments> searchByProvider(int id) throws Exception {
		List<Comments> result = commentsDao.queryBuilder().where().eq("provider", id).query();
		return result;
	}
	
}