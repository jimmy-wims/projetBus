package dao;


import java.util.LinkedList;

import datasourceManagement.JSONManager;

public abstract class DAO<T> {
	
	public JSONManager  jsonManager = JSONManager.getInstance();
	
	public abstract T delete(T obj);
	
	public abstract T create(T obj);

	public abstract T update(T obj);
	
	public abstract LinkedList<T> saveAll(T reseau);

	public abstract T findById(int cle);
	
	public abstract T findByName(String name);
	
	public abstract LinkedList<T> findAll();
}
