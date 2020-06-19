package daoFactory;

import dao.ArretDAOJSON;
import dao.BusDAOJSON;
import dao.DAO;
import dao.LigneDAOJSON;
import modele.Arret;
import modele.Bus;
import modele.LigneDeBus;

public class DAOJSONFactory extends DAOFactory{
	private static DAOJSONFactory DAOJSONFactory = new DAOJSONFactory(); 
	
	private DAOJSONFactory()
	{
	}
	
	public static synchronized DAOJSONFactory getInstance()
	{
		if ( DAOJSONFactory == null)
			DAOJSONFactory  = new DAOJSONFactory();
		return DAOJSONFactory;
	}
	
	public DAO<LigneDeBus> getLigneDAO()
	{
		DAO<LigneDeBus> daoLigne = LigneDAOJSON.getInstance();
		return daoLigne;
	}
	
	public DAO<Bus> getBusDAO()
	{
		DAO<Bus> daoBus = BusDAOJSON.getInstance();
		return daoBus;
	}
	
	public DAO<Arret> getArretDAO() 
	{
		DAO<Arret> daoArret = ArretDAOJSON.getInstance();
		return daoArret;
	}
}

