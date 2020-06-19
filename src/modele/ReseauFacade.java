package modele;

import java.util.ArrayList;
import java.util.LinkedList;

import dao.DAO;
import daoFactory.DAOFactory;

public class ReseauFacade {
	
	private static ReseauFacade facade;
	private DAOFactory daoFactory;
	private Reseau reseau;
	
	private ReseauFacade()
	{
		daoFactory = DAOFactory.getFactory(SourceDonnees.json);
		reseau = new Reseau();
	}
	
	public  static synchronized ReseauFacade getInstance()
	{
		if (facade  == null)
			facade  = new ReseauFacade();
		return facade;
	}
	
	public ArrayList<LigneDeBus> getLesLignes()
	{
		reseau.setLesLignes(daoFactory.getLigneDAO().findAll());
		return reseau.getLesLignes();
	}
	
	public ArrayList<Bus> getLesBus()
	{
		reseau.setLesBus(daoFactory.getBusDAO().findAll());
		return reseau.getLesBus();
	}
	
	public LigneDeBus findLigne(String name)
	{
		return (LigneDeBus)daoFactory.getLigneDAO().findByName(name);
	}
	
	public Bus ajouterBus(int numero)
	{
		Bus b = reseau.addBus(new Bus(numero));
		return (Bus)daoFactory.getBusDAO().create(b);
	}
	
	public LigneDeBus ajouterLigne(String nom)
	{
		LigneDeBus l = reseau.addLigne(new LigneDeBus(nom));
		return (LigneDeBus)daoFactory.getLigneDAO().create(l);
	}
	
	public LigneDeBus enregisterLigne(String nom)
	{
		Arret arret;
		LigneDeBus ligne = null;
		
		System.out.println("apple ->" + nom + ".");
		
		for(LigneDeBus l : reseau.getLesLignes())
		{
			System.out.println("pomme ->" + l.getNom() + ".");
			if(l.getNom().equals(nom))
			{
				
				ligne = l;
			}
		}
		for(Arret a : ligne.getListeArret())
		{
			arret = (Arret)daoFactory.getArretDAO().findByName(a.getNom());
			if(arret == null)
			{
				daoFactory.getArretDAO().create(a);
			}
		}
		
		return ligne;
	}
	
	public ArrayList<LigneDeBus> ajouterArret(String nom, String position, ArrayList<String> listLigne, ArrayList<LigneDeBus> lesLignes)
	{
		
		for(LigneDeBus l : reseau.getLesLignes()) {
			for(LigneDeBus li : lesLignes) {
				if(l.getNom().equals(li.getNom())) {
					l.addArret(new Arret(nom,position, listLigne));
				}
			}
		}
		
		return lesLignes;
	}
	
	public void saveAll()
	{
		daoFactory.getLigneDAO().saveAll(reseau.getLesLignes());
		
		for(LigneDeBus l : reseau.getLesLignes())
			daoFactory.getArretDAO().saveAll(l.getListeArret());
			
		daoFactory.getBusDAO().saveAll(reseau.getLesBus());
	}
}
