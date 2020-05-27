package element;

import java.util.ArrayList;

public class LigneDeBus {
	private String nom;
	private ArrayList<Arret> listeArrets;
	
	public LigneDeBus(String nom, ArrayList<Arret> listeArrets) {
		super();
		this.nom = nom;
		this.listeArrets = listeArrets;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Arret> getListeArrets() {
		return listeArrets;
	}

	public void setListeArrets(ArrayList<Arret> listeArrets) {
		this.listeArrets = listeArrets;
	}
}
