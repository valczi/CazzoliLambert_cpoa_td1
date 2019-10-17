package daofactory;

import daoobjects.*;
public abstract class Daofactory{
	
public static Daofactory getDaofactory(Persistance cible){
Daofactory daof=null;
switch (cible){
	case Mysql:
		daof=new MysqlDaofactory();
		break;
	case Liste:
		daof=new ListeDaofactory();
	default:
		break;
		}	

return daof;
}
public abstract AbonnementDAO getAbonnement();
public abstract ClientDAO getClient();
public abstract PeriodiciteDAO getPeriodicite();
public abstract RevueDAO getRevue();
}
