package view;
 
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
 
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import modele.Arret;
import modele.Bus;
import modele.BusThread;
import modele.Lien;
import modele.LigneDeBus;
import modele.ReseauFacade;
import modele.SaisieThread;
 
public class ReseauView extends JFrame implements Observer {
 
	/** Pour éviter un warning venant du JFrame */
	private static final long serialVersionUID = -8123406571694511514L;
	private static ReseauFacade facade = ReseauFacade.getInstance();
	private static ArrayList<Bus> listBus;
	private static mxGraph graph = new mxGraph();
	private static Object parent = graph.getDefaultParent();
	private static ArrayList<BusThread> listThread = new ArrayList<BusThread>();
	private static ArrayList<LigneDeBus> listLigne = new ArrayList<LigneDeBus>();
	private static ArrayList<Object> lesObjetArrets = new ArrayList<Object>();
    private static ArrayList<Lien> lesLiens = new ArrayList<Lien>();
    private static Boolean dessine;
 
	public ReseauView() {
		super("Réseau de bus");
		init();
		listBus = facade.getLesBus();
		for(Bus b : listBus) {
			listThread.add(new BusThread("" + b.getNumero()));
		}
		listLigne = facade.getLesLignes();
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		dessine();
	}

	void init() {
		facade.addObserver(this); // (2) ajout d'observateur
	}
	 
	@SuppressWarnings("deprecation")
	public void update(Observable observable, Object objectConcerne) {
		listBus = facade.getLesBus();
		dessine = false;
		for(int i = 0; i < listBus.size(); i++) {
			if(listThread.get(i).getName().equals("" + listBus.get(i).getNumero())) {
				LigneDeBus ligneBt = listThread.get(i).getLigne();
				LigneDeBus ligneBus = listBus.get(i).getLigne();
				if(ligneBt!=null && ligneBus!= null && !ligneBt.getNom().equals(ligneBus.getNom())) {
					listThread.get(i).setLigne(ligneBus);
					listThread.get(i).setNumeroArret(0);
					listThread.get(i).setEnRoute(false);
					listThread.get(i).setRoule(true);
				}
			}
		}
		reset();
		dessine = true;
		System.out.println("notification");
	}
	
	public Object editLien(BusThread bt) {
		int nLien = 0;
		for(int i = 0; i < lesLiens.size(); i++) {
			Lien lien = lesLiens.get(i);
			Arret arret =  bt.getLigne().getListeArret().get(bt.getNumeroArret());
			Arret arretPrecedent = bt.getLigne().getListeArret().get(bt.getNumeroArret() - 1);
			if(lien.getCle1() == arretPrecedent.getCle() && lien.getCle2() == arret.getCle())
				nLien = i;
		}
		return lesLiens.get(nLien).getObj();
	}
  
	public void dessine() {
	    int x = 100;
	    int y = 30;
	    
	    int nbCells = 0;
	    
	    graph.getModel().beginUpdate();

	    try {
	    	for(Arret a : facade.getLesArrets())
	    	{
	    		if(a.getCle() > lesObjetArrets.size())
	    			lesObjetArrets.add(graph.insertVertex(parent, null, a.getNom(), x, y, 80, 30));
	    		else
	    			lesObjetArrets.add(a.getCle()-1,graph.insertVertex(parent, null, a.getNom(), x, y, 80, 30));
		      nbCells = nbCells + 1;
	    	  x = x + 500;
		      if(nbCells % 2 == 0)
		      {
		    	  y = y + 200;
		    	  x = 100;
		      }
	    	}
	    } finally {
	      graph.getModel().endUpdate();
	    }
	    
	    graph.setCellsMovable(false); //empeche l'utilisateur de bouger les cellules
	    graph.setCellsEditable(false); //empeche la modification du texte des cellules
	    graph.setCellsResizable(false); //empeche la redimension des cellules
	    
	    
	    for(LigneDeBus l : facade.getLesLignes()) {
	    	ArrayList<Arret> lesArrets = l.getListeArret();
	    	for(int i = 0; i < lesArrets.size(); i++) {
	    		if(i != lesArrets.size() - 1) {
	    			Arret a = lesArrets.get(i);
	    			Arret arretSuivant = lesArrets.get(i+1);
	    			Lien lien = new Lien(a.getCle(), arretSuivant.getCle());
	    			lien.setObj(graph.insertEdge(parent, null, "",lesObjetArrets.get(a.getCle()-1),lesObjetArrets.get(arretSuivant.getCle()-1)));
	    			lesLiens.add(lien);
	    		}
	    			
	    	}
	    }
	 
	    getContentPane().repaint();
	}
	
	public void redessine() {
	    int x = 100;
	    int y = 30;
	    
	    int nbCells = 0;
	    String contenuCell;
	    
	    graph.getModel().beginUpdate();
	   
	    graph.setCellsMovable(false); //empeche l'utilisateur de bouger les cellules
	    graph.setCellsEditable(false); //empeche la modification du texte des cellules
	    graph.setCellsResizable(false); //empeche la redimension des cellules
	    
	    
	    
	    for(BusThread b : listThread) {
	    	if(b.getLigne() != null && b.getAttend() == false) {
	    		System.out.println(b.getName() + " -> " + b.getEnRoute() + " - roule : " + b.getRoule());
		    	if(b.getRoule()) {
		    		b.setTpsAttente(1000 + (int)(Math.random() * ((10000 - 1000) + 1)));
		    		b.setAttend(true);
		    		if(!b.getEnRoute()) {
			    		if(b.getNumeroArret() > 0) {
			    			Object obj = editLien(b);
				    		contenuCell = (String) ((mxCell) obj).getValue();
				    		contenuCell = contenuCell.replace(b.getName(), "");
				    		contenuCell = contenuCell.replace(" - ", "");
				    		if(contenuCell.equals(""))
				    			graph.getModel().setValue(obj, "");
				    		else
				    			graph.getModel().setValue(obj, contenuCell);
			    		}
			    		Arret a = b.getLigne().getListeArret().get(b.getNumeroArret());
			    		int numArret = a.getCle()-1;
			    		contenuCell = (String) ((mxCell) lesObjetArrets.get(numArret)).getValue();
			    		if(contenuCell.contains("Bus"))
			    			graph.getModel().setValue(lesObjetArrets.get(numArret), ((mxCell) lesObjetArrets.get(numArret)).getValue() + " - " + b.getName());
			    		else
			    			graph.getModel().setValue(lesObjetArrets.get(numArret), contenuCell + " : Bus " + b.getName());
			    		b.setEnRoute(true);
						b.setNumeroArret(b.getNumeroArret() + 1);
						if(b.getNumeroArret() > b.getLigne().getListeArret().size()-1) {
							b.setRoule(false);
						}
		    		}
		    		else
		    		{
			    		Arret a = b.getLigne().getListeArret().get(b.getNumeroArret()-1);
			    		int numArret = a.getCle()-1;
			    		contenuCell = (String) ((mxCell) lesObjetArrets.get(numArret)).getValue();
			    		contenuCell = contenuCell.replace("" + b.getName(), "");
			    		contenuCell = contenuCell.replace(" - ", "");
			    		String[] tabContenu = contenuCell.split(" : Bus ");
			    		System.out.println("");
			    		System.out.println("");
			    		for(String s : tabContenu) {
			    			System.out.print(s);
			    		}
			    		System.out.print(".");
			    		System.out.println("");
			    		if(tabContenu.length == 1)
			    			graph.getModel().setValue(lesObjetArrets.get(numArret), a.getNom());
			    		else {
			    			System.out.println("okokokkooko");
			    			graph.getModel().setValue(lesObjetArrets.get(numArret), contenuCell);
			    		}
			    		Object obj = editLien(b);
			    		contenuCell = (String) ((mxCell) obj).getValue();
			    		if(contenuCell.equals(""))
			    			graph.getModel().setValue(obj, b.getName());
			    		else
			    			graph.getModel().setValue(obj, contenuCell + " - " + b.getName());
			    		b.setEnRoute(false);
		    		}
		    	} else {
		    		if(b.getNumeroArret() != 0) {
		    			Arret a = b.getLigne().getListeArret().get(b.getNumeroArret()-1);
			    		int numArret = a.getCle()-1;
			    		contenuCell = (String) ((mxCell) lesObjetArrets.get(numArret)).getValue();
			    		contenuCell = contenuCell.replace("" + b.getName(), "");
			    		contenuCell = contenuCell.replace(" - ", "");
			    		String[] tabContenu = contenuCell.split(" : Bus ");
			    		if(tabContenu.length == 1)
			    			graph.getModel().setValue(lesObjetArrets.get(numArret), a.getNom());
			    		else {
			    			System.out.println("okokokkooko");
			    			graph.getModel().setValue(lesObjetArrets.get(numArret), contenuCell);
			    		}
			    		b.setNumeroArret(0);
		    		}
		    		b.setAttend(false);
		    		b.setLigne(null);
		    	}
	    	}
	    }
	 
	    graph.getModel().endUpdate();

	    getContentPane().repaint();
	    dessine = true;
	}
	
	public void reset() {
		graph.getModel().beginUpdate();
		
		for(Object objet : lesObjetArrets) {
			graph.getModel().remove(objet);
		}
		
		for(Object objet : lesLiens) {
			graph.getModel().remove(objet);
		}
		
		graph.getModel().endUpdate();
		
		lesObjetArrets.clear();
		lesLiens.clear();
		dessine();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReseauView frame = new ReseauView();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 650);
		frame.setVisible(true);
		Thread thread = new Thread();
		SaisieThread saisieThread = new SaisieThread("saisie", listBus, listLigne);
		int max = facade.getLesLignes().size();
		int nLigne;
		dessine = true;
   
		while(true) {
				for(BusThread bt : listThread) {
					if(bt.getRoule() == false && bt.getAttend() == false && bt.getNumeroArret() == 0) {
						nLigne = (int)(Math.random() * ((max) + 1));
						if(nLigne < listLigne.size()) { 
							bt.setLigne(listLigne.get(nLigne));
							bt.setNumeroArret(0);
							bt.setRoule(true);
							bt.setEnRoute(false);
							bt.setTpsAttente(5000 + (int)(Math.random() * ((10000 - 5000) + 1)));
							dessine = true;
						} else {
							bt.setNumeroArret(0);
							bt.setRoule(false);
							bt.setLigne(null);
							bt.setTpsAttente(0);
						}
					}
					if(!bt.isAlive())
						bt.start();
				}
				if(dessine == true)
					frame.redessine();
				
				try {
					Thread.sleep(1000);
				} catch(Exception e) {}
				
				if(!saisieThread.isAlive())
					saisieThread.start();
				
				if(saisieThread.isModifier()) {
					facade.modifierBus(saisieThread.getBus(), saisieThread.getLigne());
					saisieThread.setModifier(false);
				}
		}
	}
}