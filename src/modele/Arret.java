package modele;

import java.util.ArrayList;

public class Arret {
	private String nom;
	private String position;
	private ArrayList<String> listNomLigne;
	private int cle;
	
	public Arret(String nom, String position) {
		this.nom = nom;
		this.position = position;
	}
	
	public Arret(String nom, String position, ArrayList<String> listLigne) {
		this.nom = nom;
		this.position = position;
		this.listNomLigne = listLigne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public ArrayList<String> getListNomLigne() {
		return listNomLigne;
	}

	public void setListNomLigne(ArrayList<String> listNomLigne) {
		this.listNomLigne = listNomLigne;
	}

	public int getCle() {
		return cle;
	}

	public void setCle(int cle) {
		this.cle = cle;
	}

	@Override
	public String toString() {
		return "Arret [nom=" + nom + ", position=" + position + ", listNomLigne=" + listNomLigne + ", cle=" + cle + "]";
	}
}
