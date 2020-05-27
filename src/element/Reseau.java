package element;

import java.util.ArrayList;

public class Reseau {
	private ArrayList<LigneDeBus> lesLignes;
	private ArrayList<Bus> lesBus;
	
	public Reseau(ArrayList<LigneDeBus> lesLignes, ArrayList<Bus> lesBus) {
		super();
		this.lesLignes = lesLignes;
		this.lesBus = lesBus;
	}

	public ArrayList<LigneDeBus> getLesLignes() {
		return lesLignes;
	}

	public void setLesLignes(ArrayList<LigneDeBus> lesLignes) {
		this.lesLignes = lesLignes;
	}

	public ArrayList<Bus> getLesBus() {
		return lesBus;
	}

	public void setLesBus(ArrayList<Bus> lesBus) {
		this.lesBus = lesBus;
	}
}
