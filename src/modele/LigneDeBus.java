package modele;

import java.util.ArrayList;

public class LigneDeBus {
	private int cle;
	private String nom;
	private ArrayList<Arret> listeArret;
	
	public LigneDeBus(String nom, ArrayList<Arret> listeArret) {
		this.nom = nom;
		this.listeArret = listeArret;
	}
	
	public LigneDeBus(String nom) {
		this.nom = nom;
		this.listeArret = new ArrayList<Arret>();
	}
	
	public LigneDeBus(String nom, ArrayList<Arret> listeArret, int cle) {
		this.nom = nom;
		this.listeArret = listeArret;
		this.cle = cle;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Arret> getListeArret() {
		return listeArret;
	}

	public void setListeArret(ArrayList<Arret> listeArret) {
		this.listeArret = listeArret;
	}
	
	public void addArret(Arret arret) {
		listeArret.add(arret);
	}

	public int getCle() {
		return cle;
	}

	public void setCle(int cle) {
		this.cle = cle;
	}

	@Override
	public String toString() {
		return "LigneDeBus [cle=" + cle + ", nom=" + nom + ", listeArret=" + listeArret + "]";
	}
}
