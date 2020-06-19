package dao;

import java.util.ArrayList;

import modele.Bus;
import modele.Reseau;

public class BusDAOJSON extends DAO<Bus>{
	
	private static int newCle = 0;
	private static BusDAOJSON BusDAO = new BusDAOJSON();
	
	private BusDAOJSON()
	{
	}
	
	public  static synchronized BusDAOJSON getInstance()
	{
		if (BusDAO  == null)
			BusDAO  = new BusDAOJSON();
		return BusDAO;
	}

	public Bus create(Bus obj) {
		Reseau reseau = jsonManager.getData();
		boolean present = false;
		ArrayList<Bus> lesBus = reseau.getLesBus();
		for(int i = 0; i < lesBus.size() && present == false; i++) {
			if((lesBus.get(i)).getNumero() == obj.getNumero())
				present = true;
		}
			
		if(present == false) {
			obj.setCle(newCle);
			newCle = newCle + 1;
			reseau.addBus(obj);
			jsonManager.setData(reseau);
			return obj;
		}
		return null;
	}
	
	public Bus delete(Bus obj) {
		Reseau reseau = jsonManager.getData();
		reseau.getLesBus().remove(obj);
		jsonManager.setData(reseau);
		return obj;
	}
	
	public Bus update(Bus obj) {
		Reseau reseau = jsonManager.getData();
		for(Bus b : reseau.getLesBus())
		{
			if(b.getCle() == obj.getCle())
			{
				b.setNumero(obj.getNumero());
			}
		}
		jsonManager.setData(reseau);
		return obj;
	}
	
	public ArrayList<Bus> saveAll(ArrayList<Bus> lesBus)
	{
		Reseau reseau = jsonManager.getData();
		reseau.setLesBus(lesBus);
		jsonManager.setData(reseau);
		return lesBus;
	}
	
	public ArrayList<Bus> findAll()
	{
		return jsonManager.getData().getLesBus();
	}
	
	public Bus findByName(String numero)
	{
		Reseau reseau = jsonManager.getData();
		for(Bus b : reseau.getLesBus())
		{
			if(b.getNumero() == Integer.parseInt(numero))
				return b;
		}
		return null;
	}
	
}
