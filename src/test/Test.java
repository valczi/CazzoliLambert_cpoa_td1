package test;

import daofactory.Daofactory;
import daofactory.Persistance;
import metiers.ClientM;

public class Test {
	public static void main(String[] args) { 
Daofactory daof=Daofactory.getDaofactory(Persistance.Mysql);
ClientM m=new ClientM("Jojo","Bernard",-1);
daof.getClient().ajout(m);
System.out.println(m.toString());
	}
}
