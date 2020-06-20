package view;
 
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
 
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import modele.Arret;
import modele.Bus;
import modele.Lien;
import modele.LigneDeBus;
import modele.ReseauFacade;
 
public class ReseauView extends JFrame implements Observer {
 
	/** Pour éviter un warning venant du JFrame */
	private static final long serialVersionUID = -8123406571694511514L;
	private static ReseauFacade facade = ReseauFacade.getInstance();
	private static ArrayList<Bus> listBus;
	private static mxGraph graph = new mxGraph();
	private static Object parent = graph.getDefaultParent();
 
	public ReseauView() {
		super("Réseau de bus");
		init();
		listBus = facade.getLesBus();
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		draw();
	}

	void init() {
		facade.addObserver(this); // (2) ajout d'observateur
	}
	 
	public void update(Observable observable, Object objectConcerne) {
		draw();  // (3) traitement de l'observation
		System.out.println("notification");
	}
  
	public void draw() {
		System.out.println("la + " + listBus.get(1).getNumeroArret());
	    ArrayList<Object> lesObjetArrets = new ArrayList<Object>();
	    ArrayList<Lien> lesLiens = new ArrayList<Lien>();
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
	    
	    for(Bus b : listBus) {
	    	System.out.println("size " + b.getRoule());
	    	if(b.getRoule()) {
	    		Arret a = b.getLigne().getListeArret().get(b.getNumeroArret());
	    		int numArret = a.getCle()-1;
	    		System.out.println("ppppppppppppppppppp " + numArret);
	    		//graph.removeCells(new Object[] {test.get(numArret)});
	    		graph.getModel().setValue(lesObjetArrets.get(numArret), a.getNom() + " : Bus " + b.getNumero());
	    	}
	    }
	    
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
	    
	    for(Lien l : lesLiens) {
	    	System.out.println(l);
	    }
	 
	    getContentPane().repaint();
	    //displayModel((mxCell) parent,"");
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
    
		listBus.get(1).setRoule(true);
		listBus.get(1).setLigne(facade.findLigne("ligne2"));
   
		while(true) {
			try {
				java.lang.Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			frame.draw();
			listBus.get(1).setNumeroArret(listBus.get(1).getNumeroArret()+1);
		}
    
    /*ArrayList<String> lignes = new ArrayList<>();
    lignes.add("uneLigne");
    lignes.add("listeLigne");
    facade.ajouterArret("Arret2","Sud",lignes);*/
	}
  
	private void displayModel(mxCell cell, String indent) {
		System.out.println(indent+cell.getValue()+"("+cell.getClass().getName()+")");
		int nbChilds = cell.getChildCount();
		indent = indent + "  ";
		for (int i=0; i<nbChilds ; i++) {
			displayModel((mxCell) cell.getChildAt(i), indent);
		}
	}
}