package daofactory;

import daoobjects.*;
import liste.*;

public class ListeDaofactory extends Daofactory{
	public  ListeAbonnement getAbonnement(){
		
		return ListeAbonnement.getInstance();
	}
	
	public  ListeClient getClient(){
		
		return ListeClient.getInstance();

	}
	
	public  ListePeriodicite getPeriodicite(){
		
		return ListePeriodicite.getInstance();

	}

	public FactureDAO getFacture() {
		return ListeFacture.getInstance();
	}


	public ListeRevue getRevue() {
		return ListeRevue.getInstance();
	}
	

}
