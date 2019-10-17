package liste;

import java.util.ArrayList;
import java.util.List;

import daoobjects.RevueDAO;
import metiers.RevueM;

public class ListeRevue implements RevueDAO {
	private static ListeRevue instance;

	private List<RevueM> donnees;

	public static ListeRevue getInstance() {

		if (instance == null) {
			instance = new ListeRevue();
		}

		return instance;
	}

	private ListeRevue() {

		this.donnees = new ArrayList<RevueM>();
		this.donnees.add(new RevueM(1, "Mega revue", "Une revue enorme", 2.0, "Megarevue.jpg", 1));
		this.donnees.add(new RevueM(2, "Bof revue", "Une revue pas ouf", 2.0, "bof.jpg", 2));
	}

	public boolean ajout(RevueM objet) {
		if (this.donnees.size() == 0) {
			objet.setId_revue(0);
		} else {
			int id = this.donnees.get(this.donnees.size() - 1).getId_revue() + 1;
			objet.setId_revue(id);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	public boolean modifier(RevueM objet) {

		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {

			this.donnees.set(idx, objet);
		}

		return true;
	}

	@Override
	public boolean supprimer(RevueM objet) {

		RevueM supprime;

		// Ne fonctionne que si l'objet metier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return objet.equals(supprime);
	}

	public RevueM getById(int id) {
		RevueM client = new RevueM();
		boolean trouve = false;
		int i = 0;
		while (trouve == false && i < this.donnees.size()) {
			if (this.donnees.get(i).getId_revue() == id) {
				client = this.donnees.get(i);
				trouve = true;
			} else
				i++;
		}
		if (i >= this.donnees.size()) {
			System.out.println("Aucune periodicite avec avec cet id");
			client = null;
		}
		return client;
	}

	@Override
	public ArrayList<RevueM> tout() {
		return (ArrayList<RevueM>) this.donnees;
	}
}
