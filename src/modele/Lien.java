package modele;

public class Lien {
	Object obj;
	private int cle1;
	private int cle2;
	
	public Lien(int cle1, int cle2) {
		this.cle1 = cle1;
		this.cle2 = cle2;
	}

	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}


	public int getCle1() {
		return cle1;
	}

	public void setCle1(int cle1) {
		this.cle1 = cle1;
	}

	public int getCle2() {
		return cle2;
	}

	public void setCle2(int cle2) {
		this.cle2 = cle2;
	}

	@Override
	public String toString() {
		return "Lien [obj=" + obj + ", cle1=" + cle1 + ", cle2=" + cle2 + "]";
	}
}
