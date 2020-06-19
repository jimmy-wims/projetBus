package view;

import java.util.ArrayList;
import java.util.LinkedList;

import dao.ArretDAOJSON;
import dao.BusDAOJSON;
import dao.DAO;
import dao.LigneDAOJSON;
import modele.Arret;
import modele.Bus;
import modele.LigneDeBus;
import modele.ReseauFacade;

public class Test {

	public static void main(String[] args) {
		DAO<Bus> bus = BusDAOJSON.getInstance();
		DAO<LigneDeBus> ligne = LigneDAOJSON.getInstance();
		DAO<Arret> daoArret = ArretDAOJSON.getInstance();
		
		
		//ligne.create(new LigneDeBus("ligneTest"));
		
		ReseauFacade facade = ReseauFacade.getInstance();
		facade.getLesLignes();
		facade.getLesBus();
		
		ArrayList<String> lesLignes= new ArrayList<String>();
		lesLignes.add("uneLigne"); lesLignes.add("listeLigne");
		//Arret arret = new Arret("arret1", "ici", lesLignes);
		ArrayList<LigneDeBus> listeLigne = new ArrayList<LigneDeBus>();
		listeLigne.add(new LigneDeBus("uneLigne"));
		listeLigne.add(new LigneDeBus("listeLigne"));
		facade.ajouterArret("arret1", "ici", lesLignes, listeLigne);
		for(String s : lesLignes)
			facade.enregisterLigne(s);
		//bus.create(new Bus(34));
		
		/*ArrayList<String> lesLignes= new ArrayList<String>();
		lesLignes.add("ligneTest");
		Arret arret = new Arret("testCreation", "Paris", lesLignes);
		
		daoArret.delete(arret);
		ArrayList<String> lesLignes= new ArrayList<String>();
		lesLignes.add("ligneTest");
		
		daoArret.create(arret);
		lesLignes.add("uneLigne");
		arret.setListNomLigne(lesLignes);
		daoArret.update(arret);*/
		
		//daoArret.saveAll("testCreation")
	}

}
