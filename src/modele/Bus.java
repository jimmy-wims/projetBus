package modele;

public class Bus {
	private int numero;
	private int cle;
	private Boolean roule;
	private LigneDeBus ligne;
	private int numeroArret;

	public Bus(int numero) {
		this.numero = numero;
		this.roule = false;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCle() {
		return cle;
	}

	public void setCle(int cle) {
		this.cle = cle;
	}

	public Boolean getRoule() {
		return roule;
	}

	public void setRoule(Boolean roule) {
		this.roule = roule;
	}

	public LigneDeBus getLigne() {
		return ligne;
	}

	public void setLigne(LigneDeBus ligne) {
		this.ligne = ligne;
		this.numeroArret = 0;
	}

	public int getNumeroArret() {
		return numeroArret;
	}

	public void setNumeroArret(int numeroArret) {
		this.numeroArret = numeroArret;
	}
}
