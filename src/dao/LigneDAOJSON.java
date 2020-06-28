package dao;

import java.util.ArrayList;

import modele.LigneDeBus;
import modele.Reseau;

public class LigneDAOJSON extends DAO<LigneDeBus>{
	
	private static int newCle = 1;
	private static LigneDAOJSON LigneDeBusDAO = new LigneDAOJSON();
	
	private LigneDAOJSON()
	{
	}
	
	public  static synchronized LigneDAOJSON getInstance()
	{
		if (LigneDeBusDAO  == null)
			LigneDeBusDAO  = new LigneDAOJSON();
		return LigneDeBusDAO;
	}

	public LigneDeBus create(LigneDeBus obj) {
		Reseau reseau = jsonManager.getData();
		boolean present = false;
		ArrayList<LigneDeBus> lesLignes = reseau.getLesLignes();
		for(int i = 0; i < lesLignes.size() && present == false; i++) {
			if((lesLignes.get(i)).getNom().equals(obj.getNom()))
				present = true;
		}
		if(present == false) {
			obj.setCle(newCle);
			newCle = newCle + 1;
			reseau.addLigne(new LigneDeBus(obj.getNom(),obj.getListeArret(), obj.getCle()));
			jsonManager.setData(reseau);
			return obj;
		}
		return null;
	}
	
	public LigneDeBus delete(LigneDeBus obj) {
		Reseau reseau = jsonManager.getData();
		for(LigneDeBus l : reseau.getLesLignes()) {
			if(l.getNom().equals(obj.getNom())) {
				reseau.getLesLignes().remove(l);
			}
		}
		jsonManager.setData(reseau);
		return obj;
	}
	
	public LigneDeBus update(LigneDeBus obj) {
		Reseau reseau = jsonManager.getData();
		for(LigneDeBus l : reseau.getLesLignes())
		{
			if(l.getCle() == obj.getCle())
			{
				l.setNom(obj.getNom());
				l.setListeArret(obj.getListeArret());
			}
		}
		jsonManager.setData(reseau);
		return obj;
	}
	
	public ArrayList<LigneDeBus> saveAll(ArrayList<LigneDeBus> lesLignes)
	{
		Reseau reseau = jsonManager.getData();
		reseau.setLesLignes(lesLignes);
		jsonManager.setData(reseau);
		return lesLignes;
	}
	
	public ArrayList<LigneDeBus> findAll()
	{
		return jsonManager.getData().getLesLignes();
	}
	
	public LigneDeBus findByName(String nom)
	{
		Reseau reseau = jsonManager.getData();
		for(LigneDeBus l : reseau.getLesLignes())
		{
			if(l.getNom().equals(nom))
				return l;
		}
		return null;
	}
	
}
