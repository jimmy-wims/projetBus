package dao;

import java.util.ArrayList;

import modele.Arret;
import modele.LigneDeBus;
import modele.Reseau;

public class ArretDAOJSON extends DAO<Arret>{
	
	private static int newCle = 0;
	private static ArretDAOJSON arretDAO = new ArretDAOJSON();
	
	private ArretDAOJSON()
	{
	}
	
	public  static synchronized ArretDAOJSON getInstance()
	{
		if (arretDAO  == null)
			arretDAO  = new ArretDAOJSON();
		return arretDAO;
	}

	public Arret create(Arret obj) {
		Reseau reseau = jsonManager.getData();
		
		for(String s : obj.getListNomLigne() ) {
			for(LigneDeBus l : reseau.getLesLignes()) {
				if(l.getNom().equals(s))
				{
					obj.setCle(newCle);
					System.out.println("arret -> " + l);
					l.addArret(obj);
					newCle = newCle + 1;
				}
			}
		}
		jsonManager.setData(reseau);
		return obj;
	}
	
	public Arret delete(Arret obj) {
		Reseau reseau = jsonManager.getData();
		ArrayList<Arret> lesArrets = new ArrayList<Arret>();
		
		for(LigneDeBus l : reseau.getLesLignes()) {
			for(Arret a : l.getListeArret()) {
				if(!a.getNom().equals(obj.getNom()))
					lesArrets.add(a);
			}
			l.setListeArret(lesArrets);
		}
		jsonManager.setData(reseau);
		return obj;
	}
	
	public Arret update(Arret obj) {
		Reseau reseau = jsonManager.getData();
		
		ArrayList<Arret> lesArrets = new ArrayList<Arret>();
		
		
		for(LigneDeBus l : reseau.getLesLignes())
		{
			lesArrets.clear();
			for(Arret a : l.getListeArret()) {
				if(a.getCle() == obj.getCle())
				{
					lesArrets.add(obj);
				}
				else
				{
					lesArrets.add(a);
				}
			}
			l.setListeArret(lesArrets);
		}
		
		jsonManager.setData(reseau);
		return obj;
	}
	
	public ArrayList<Arret> saveAll(ArrayList<Arret> lesArrets)
	{
		Reseau reseau = jsonManager.getData();
		
		for(LigneDeBus l : reseau.getLesLignes()) {
			l.getListeArret().clear();
			for(Arret a : lesArrets) {
				for(String s : a.getListNomLigne() ) {
					if(l.getNom().equals(s))
						l.addArret(a);
				}
			}
		}
		
		jsonManager.setData(reseau);
		return lesArrets;
	}
	
	public ArrayList<Arret> findAll()
	{
		Reseau reseau = jsonManager.getData();
		ArrayList<Arret> listArret = new ArrayList<Arret>(); 
		
		for(LigneDeBus l : reseau.getLesLignes()) {
			for(Arret a : l.getListeArret()) {
				listArret.add(a);
			}
		}
		return listArret;
	}
	
	public Arret findByName(String nom)
	{
		Reseau reseau = jsonManager.getData();
		for(LigneDeBus l : reseau.getLesLignes())
		{
			for(Arret a : l.getListeArret()) {
				if(a.getNom().equals(nom))
					return a;
			}
		}
		return null;
	}
}
