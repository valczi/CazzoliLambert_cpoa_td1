package daofactory;

import mysqlobjects.*;


public class MysqlDaofactory extends Daofactory{

	public MysqlAbonnement getAbonnement(){
		
		return MysqlAbonnement.getInstance();
	}
	public  MysqlClient getClient(){
		
		return MysqlClient.getInstance();

	}
	
	public MysqlPeriodicite getPeriodicite(){
		
		return MysqlPeriodicite.getInstance();

	}
	public MysqlRevue getRevue() {
		return MysqlRevue.getInstance();
	}

}
