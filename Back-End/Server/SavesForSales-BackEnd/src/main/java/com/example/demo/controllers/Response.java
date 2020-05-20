package com.example.demo.controllers;

public class Response<T> {
	public final boolean ok;
	public final T classX;
	public final String msg;

	Response(boolean ok, T classX, String msg) {
		this.ok = ok;
		this.classX = classX;
		this.msg = msg;
	}

	Response(boolean ok, T clase, Exception ex) {
		this.ok = ok;
		this.classX = clase;
		StringBuilder msg = new StringBuilder();
		msg.append("Exception. ");
		for (Throwable current = ex; current != null; current = current.getCause())
			msg.append(" caused By ").append(current.getMessage());
		this.msg = msg.toString();
		ex.printStackTrace();
	}
}