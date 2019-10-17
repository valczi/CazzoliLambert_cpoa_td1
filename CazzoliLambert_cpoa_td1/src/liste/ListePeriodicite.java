package liste;
import java.util.ArrayList;
import java.util.List;

import daoobjects.*;
import metiers.*;

public class ListePeriodicite implements PeriodiciteDAO{


		private static ListePeriodicite instance;

		private List<PeriodiciteM> donnees;

 
		public static ListePeriodicite getInstance() {

			if (instance == null) {
				instance = new ListePeriodicite();
			}

			return instance;
		}

		private ListePeriodicite() {

			this.donnees = new ArrayList<PeriodiciteM>();

			this.donnees.add(new PeriodiciteM(1, "Mensuel"));
			this.donnees.add(new PeriodiciteM(2, "Quotidien"));
		}


		@Override
		public boolean ajout(PeriodiciteM objet) {

			objet.setId(3);
			// Ne fonctionne que si l'objet m�tier est bien fait...
			while (this.donnees.contains(objet)) {

				objet.setId(objet.getId() + 1);
			}
			boolean ok = this.donnees.add(objet);
			
			return ok;
		}

		@Override
		public boolean modifier(PeriodiciteM objet) {
			
			// Ne fonctionne que si l'objet m�tier est bien fait...
			int idx = this.donnees.indexOf(objet);
			if (idx == -1) {
				throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
			} else {
				
				this.donnees.set(idx, objet);
			}
			
			return true;
		}

		@Override
		public boolean supprimer(PeriodiciteM objet) {

			PeriodiciteM supprime;
			
			// Ne fonctionne que si l'objet m�tier est bien fait...
			int idx = this.donnees.indexOf(objet);
			if (idx == -1) {
				throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
			} else {
				supprime = this.donnees.remove(idx);
			}
			
			return objet.equals(supprime);
		}

		public PeriodiciteM getById(int id) {
			PeriodiciteM client = new PeriodiciteM();
			boolean trouve=false;
			int i=0;
			while (trouve==false && i<this.donnees.size()){
				if (this.donnees.get(i).getId() == id) {
					client = this.donnees.get(i);
					trouve=true;
					}
				else
					i++;			
			}
			if(i>=this.donnees.size()){
				System.out.println("Aucune p�riodicit� avec avec cet id");
				client=null;}
			return client;
		}

		@Override
		public ArrayList<PeriodiciteM> tout() {
			return (ArrayList<PeriodiciteM>) this.donnees;
		}
	}


