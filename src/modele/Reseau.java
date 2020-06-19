package modele;

import java.util.ArrayList;

import daoFactory.DAOFactory;

public class Reseau {
	private ArrayList<LigneDeBus> lesLignes;
	private ArrayList<Bus> lesBus;
	
	public Reseau()
	{	
		lesLignes = new ArrayList<LigneDeBus>();
		lesBus = new ArrayList<Bus>();
	}

	public ArrayList<LigneDeBus> getLesLignes() {
		return lesLignes;
	}

	public ArrayList<LigneDeBus> setLesLignes(ArrayList<LigneDeBus> p_lesLignes) {
		lesLignes = p_lesLignes;
		return lesLignes;
	}

	public ArrayList<Bus> getLesBus() {
		return lesBus;
	}

	public ArrayList<Bus> setLesBus(ArrayList<Bus> p_lesBus) {
		lesBus = p_lesBus;
		return lesBus;
	}
	
	public Bus addBus(Bus bus) {
		lesBus.add(bus);
		return bus;
	}
	
	public LigneDeBus addLigne(LigneDeBus ligne) {
		lesLignes.add(ligne);
		return ligne;
	}

	@Override
	public String toString() {
		return "Reseau [lesLignes=" + lesLignes + ", lesBus=" + lesBus + "]";
	}
}
