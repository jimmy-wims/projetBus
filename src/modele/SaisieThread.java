package modele;

import java.util.ArrayList;
import java.util.Scanner;

public class SaisieThread extends Thread{
	private int numBus;
	private String nomLigne;
	private ArrayList<Bus> listBus;
	private ArrayList<LigneDeBus> listLigne;
	private Bus bus;
	private LigneDeBus ligne;
	private boolean modifier;
	
	public SaisieThread(String name, ArrayList<Bus> lesBus, ArrayList<LigneDeBus> lesLignes) {
		super(name);
		numBus = 0;
		nomLigne = "";
		listBus = lesBus;
		listLigne = lesLignes;
		modifier = false;
	}
	
	public synchronized void run() {
		while(true) {
			System.out.println("Veuillez saisir un numéro");
			Scanner sc = new Scanner(System.in);
			numBus = sc.nextInt();
			System.out.println("Veuillez saisir le nom d'une ligne");
			sc.nextLine();
			nomLigne = sc.nextLine();
			try {
				for(Bus b : listBus) {
					if(b.getNumero() == numBus) {
						if(nomLigne.equals("depot")) {
							bus = b;
							ligne = null;
							modifier = true;
						} else {
							for(LigneDeBus l : listLigne)
							{
								if(l.getNom().equals(nomLigne)) {
									bus = b;
									ligne = l;
									modifier = true;
								}
							}
						}
					}
				}
				this.wait(5000);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public int getNumBus() {
		return numBus;
	}

	public void setNumBus(int numBus) {
		this.numBus = numBus;
	}

	public String getNomLigne() {
		return nomLigne;
	}

	public void setNomLigne(String nomLigne) {
		this.nomLigne = nomLigne;
	}

	public ArrayList<Bus> getListBus() {
		return listBus;
	}

	public void setListBus(ArrayList<Bus> listBus) {
		this.listBus = listBus;
	}

	public ArrayList<LigneDeBus> getListLigne() {
		return listLigne;
	}

	public void setListLigne(ArrayList<LigneDeBus> listLigne) {
		this.listLigne = listLigne;
	}

	public boolean isModifier() {
		return modifier;
	}

	public void setModifier(boolean modifier) {
		this.modifier = modifier;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public LigneDeBus getLigne() {
		return ligne;
	}

	public void setLigne(LigneDeBus ligne) {
		this.ligne = ligne;
	}
}

