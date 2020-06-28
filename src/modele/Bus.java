package modele;

public class Bus {
	private int numero;
	private int cle;
	private String nomLigne;

	public Bus(int numero) {
		this.numero = numero;
		nomLigne = "";
	}

	public Bus(int numero, int cle, String ligne) {
		this.numero = numero;
		this.cle = cle;
		this.nomLigne = ligne;
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

	public String getNomLigne() {
		return nomLigne;
	}

	public void setNomLigne(String nomLigne) {
		this.nomLigne = nomLigne;
	}
}
