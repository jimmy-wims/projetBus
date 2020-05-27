package dao;

import java.util.ArrayList;

import element.Arret;
import element.LigneDeBus;
import element.Reseau;

public class ArretDAOJSON extends DAO<Arret>{
	
	private static int newCle = 0;
	private static ArretDAOJSON ArretDAO = new ArretDAOJSON();
	
	private ArretDAOJSON()
	{
	}
	
	public  static synchronized ArretDAOJSON getInstance()
	{
		if (ArretDAO  == null)
			ArretDAO  = new ArretDAOJSON();
		return ArretDAO;
	}

	public Arret create(Arret obj) {
		Reseau reseau = jsonManager.getData();
		
	}
	
	public Bus delete(Bus obj) {
		Reseau reseau = jsonManager.getData();
		ArrayList<Bus> lesBus = reseau.getLesBus();
		lesBus.remove(obj);
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
