package com.example.demo.database;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.database.models.Messages;

public interface MessagesRepository extends Repository<Messages>{
	public List<Messages> searchByInvolved(Integer id) throws SQLException;
}