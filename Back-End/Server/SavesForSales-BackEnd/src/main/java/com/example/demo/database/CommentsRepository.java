package com.example.demo.database;

import java.util.List;

import com.example.demo.database.models.Comments;

public interface CommentsRepository extends Repository<Comments> {
	List<Comments> searchByProvider(int id) throws Exception;
}