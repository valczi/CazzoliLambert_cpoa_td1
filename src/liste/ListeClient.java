package liste;

import java.util.ArrayList;

import daoobjects.ClientDAO;
import metiers.*;

public final class ListeClient implements ClientDAO {

	private static ListeClient instance;

	private ArrayList<ClientM> ListeClient;

	public static ListeClient getInstance() {

		if (instance == null) {
			instance = new ListeClient();
		}

		return instance;
	}

	private ListeClient() {

		this.ListeClient = new ArrayList<ClientM>();
		this.ListeClient.add(new ClientM("Lambert", "Ludovic", 0));
		this.ListeClient.add(new ClientM("Cazzoli", "Valentin", 1));
	}

	public boolean ajout(ClientM ClientM) {

		if (this.ListeClient.size() == 0) {
			ClientM.setId(0);
		} else {
			int id = this.ListeClient.get(this.ListeClient.size() - 1).getId() + 1;
			ClientM.setId(id);
		}

		boolean ok = this.ListeClient.add(ClientM);
		return ok;

	}

	public boolean supprimer(ClientM ClientM) {
		ClientM a;

		int idx = this.ListeClient.indexOf(ClientM);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			a = this.ListeClient.remove(idx);
		}
		return ClientM.equals(a);
	}

	public ClientM getById(int id) {
		ClientM ClientM = new ClientM();
		boolean trouve = false;
		int i = 0;
		while (trouve == false && i < this.ListeClient.size()) {
			if (this.ListeClient.get(i).getId() == id) {
				ClientM = this.ListeClient.get(i);
				trouve = true;
			} else
				i++;
		}
		if (i >= this.ListeClient.size()) {
			System.out.println("Aucun ClientM avec cet id");
			ClientM = null;
		}
		return ClientM;
	}

	@Override
	public boolean modifier(ClientM ClientM) {
		int idx = this.ListeClient.indexOf(ClientM);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.ListeClient.set(idx, ClientM);
		}
		return true;
	}

	public ArrayList<ClientM> tout() {
		return this.ListeClient;

	}

}
