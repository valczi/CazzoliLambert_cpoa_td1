package daofactory;


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

	public ListeRevue getRevue() {
		return ListeRevue.getInstance();
	}
	

}
