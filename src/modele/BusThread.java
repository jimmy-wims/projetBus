package modele;

import java.util.Observable;

public class BusThread extends Thread{
	private int tpsAttente;
	private Boolean roule;
	private int numeroArret;
	private LigneDeBus ligne;
	private Boolean attend;
	private Boolean enRoute;
	
	public BusThread(String name) {
		super(name);
		tpsAttente = 0;
		roule = false;
		numeroArret = 0;
		attend = false;
		enRoute = false;
	}
	
	public synchronized void run() {
		while(true) {
			System.out.print("");
			if(attend == true) {
				try {
					Thread.sleep(tpsAttente);
					tpsAttente = 0;
					attend = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getTpsAttente() {
		return tpsAttente;
	}

	public void setTpsAttente(int tpsAttente) {
		this.tpsAttente = tpsAttente;
	}

	public Boolean getRoule() {
		return roule;
	}

	public void setRoule(Boolean roule) {
		this.roule = roule;
	}

	public int getNumeroArret() {
		return numeroArret;
	}

	public void setNumeroArret(int numeroArret) {
		this.numeroArret = numeroArret;
	}

	public LigneDeBus getLigne() {
		return ligne;
	}

	public void setLigne(LigneDeBus ligne) {
		this.ligne = ligne;
	}

	public Boolean getAttend() {
		return attend;
	}

	public void setAttend(Boolean attend) {
		this.attend = attend;
	}

	public Boolean getEnRoute() {
		return enRoute;
	}

	public void setEnRoute(Boolean enRoute) {
		this.enRoute = enRoute;
	}
}
