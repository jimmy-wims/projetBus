package view;

import java.util.LinkedList;

import dao.BusDAOJSON;
import dao.DAO;
import dao.LigneDAOJSON;
import element.Bus;
import element.LigneDeBus;

public class Test {

	public static void main(String[] args) {
		DAO<Bus> bus = BusDAOJSON.getInstance();
		DAO<LigneDeBus> ligne = LigneDAOJSON.getInstance();
		bus.create(new Bus(42));
		ligne.create(new LigneDeBus("uneLigne",null));
	}

}
