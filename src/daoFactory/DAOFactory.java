package daoFactory;

import dao.DAO;
import modele.Arret;
import modele.Bus;
import modele.LigneDeBus;
import modele.SourceDonnees;

public abstract class DAOFactory {
	
	public abstract DAO<LigneDeBus> getLigneDAO();
	
	public abstract DAO<Bus> getBusDAO();
	
	public abstract DAO<Arret> getArretDAO();
	
	public static DAOFactory getFactory(SourceDonnees sd)
	{
		DAOFactory daoFactory = null;
		switch(sd.toString()) {
			case "json" :
				daoFactory = DAOJSONFactory.getInstance();
			break;
		}
		return daoFactory;
	}
	
	
}