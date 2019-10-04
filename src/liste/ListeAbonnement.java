package liste;

import java.time.LocalDate;
import java.util.ArrayList;
import daoobjects.AbonnementDAO;
import metiers.AbonnementM;

public class ListeAbonnement implements AbonnementDAO {
	private static ListeAbonnement instance;

	private ArrayList<AbonnementM> ListeAbo;

	public static ListeAbonnement getInstance() {

		if (instance == null) {
			instance = new ListeAbonnement();
		}

		return instance;
	}

	private ListeAbonnement() {
		this.ListeAbo = new ArrayList<AbonnementM>();
		this.ListeAbo.add(new AbonnementM(1, 2, LocalDate.of(1999, 9, 29), LocalDate.of(2002, 5, 3)));
		this.ListeAbo.add(new AbonnementM(1, 1, LocalDate.of(2002, 9, 29), LocalDate.of(2018, 1, 6)));
		this.ListeAbo.add(new AbonnementM(3, 3, LocalDate.of(1995, 5, 21), LocalDate.of(2004, 2, 6)));
	}

	public boolean ajout(AbonnementM AbonnementM) {
		boolean ok = this.ListeAbo.add(AbonnementM);
		return ok;

	}

	public boolean supprimer(AbonnementM AbonnementM) {
		AbonnementM a;

		int idx = this.ListeAbo.indexOf(AbonnementM);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			a = this.ListeAbo.remove(idx);
		}
		return AbonnementM.equals(a);
	}

	public AbonnementM getById(int id_cl, int id_revue) {
		AbonnementM AbonnementM = new AbonnementM();
		boolean trouve = false;
		int i = 0;
		while (trouve == false && i < this.ListeAbo.size()) {
			if (this.ListeAbo.get(i).getId_client() == id_cl && this.ListeAbo.get(i).getId_revue() == id_cl) {
				AbonnementM = this.ListeAbo.get(i);
				trouve = true;
			} else
				i++;
		}
		if (i >= this.ListeAbo.size()) {
			System.out.println("Aucun AbonnementM avec cet id");
			AbonnementM = null;
		}
		return AbonnementM;
	}

	public boolean modifier(AbonnementM AbonnementM) {
		int idx = this.ListeAbo.indexOf(AbonnementM);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.ListeAbo.set(idx, AbonnementM);
		}
		return true;
	}

	public ArrayList<AbonnementM> tout() {
		return this.ListeAbo;
	}


}
