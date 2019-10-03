package test;

import java.time.LocalDate;

import daofactory.Daofactory;
import daofactory.Persistance;
import metiers.*;

public class Maintest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Daofactory daof=Daofactory.getDaofactory(Persistance.Mysql);
		daof.getAbonnement().ajout(new AbonnementM(1, 1, LocalDate.of(1999, 9, 29), LocalDate.of(2002, 5, 3)));
		AbonnementM M=daof.getAbonnement().getById(1,1);
		System.out.println(M.toString());
		M.setDate_debut(LocalDate.of(1999,1,22));
		daof.getAbonnement().modifier(M);
		System.out.println(daof.getAbonnement().tout().toString());
		daof.getAbonnement().supprimer(M);
	

		
	}

}
