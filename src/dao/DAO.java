package dao;


import java.util.ArrayList;

import datasourceManagement.JSONManager;

public abstract class DAO<T> {
	
	public JSONManager  jsonManager = JSONManager.getInstance();
	
	public abstract T delete(T obj);
	
	public abstract T create(T obj);

	public abstract T update(T obj);
	
	public abstract ArrayList<T> saveAll(ArrayList<T> elements);
	
	public abstract T findByName(String name);
	
	public abstract ArrayList<T> findAll();
}
