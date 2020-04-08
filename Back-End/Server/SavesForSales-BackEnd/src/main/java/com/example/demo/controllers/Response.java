package com.example.demo.controllers;

public class Response<T> {
	public final boolean ok;
	public final T clase;
	public final String msg;
	Response(boolean ok, T clase, String msg){
		this.ok = ok;
		this.clase = clase;
		this.msg = msg;
	}
	Response(boolean ok, T clase, Exception ex){
		this.ok = ok;
		this.clase = clase;
		this.msg = ex.getCause().getMessage();
	}
}