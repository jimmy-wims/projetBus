package element;

public class Arret {
	private String nom;
	private String position;
	
	public Arret(String nom, String position) {
		super();
		this.nom = nom;
		this.position = position;
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
}
