package liste;

import java.util.ArrayList;

import daoobjects.ClientDAO;
import objmetiers.Client;


public final class ListeClient implements ClientDAO {

	private static ListeClient instance;

	private ArrayList<Client> listeclient;

	public static ListeClient getInstance() {

		if (instance == null) {
			instance = new ListeClient();
		}

		return instance;
	}

	private ListeClient() {

		this.listeclient = new ArrayList<Client>();
		this.listeclient.add(new Client("Lambert", "Ludovic", 0));
		this.listeclient.add(new Client("Cazzoli", "Valentin", 1));
	}

	public void ajout(Client client) {

		if (this.listeclient.size() == 0) {
			client.setId_client(0);
		} else {
			int id = this.listeclient.get(this.listeclient.size() - 1).getId_client() + 1;
			client.setId_client(id);
		}

		this.listeclient.add(client);
	}

	public void supprimer(Client client) {

		int idx = this.listeclient.indexOf(client);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			this.listeclient.remove(idx);
		}
	}

	public Client getById(int id) {
		Client client = new Client();
		boolean trouve=false;
		int i=0;
		while (trouve==false && i<this.listeclient.size()){
			if (this.listeclient.get(i).getId_client() == id) {
				client = this.listeclient.get(i);
				trouve=true;
				}
			else
				i++;			
		}
		if(i>=this.listeclient.size()){
			System.out.println("Aucun client avec cet id");
			client=null;}
		return client;
	}

	@Override
	public void modifier(Client client) {
		int idx = this.listeclient.indexOf(client);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.listeclient.set(idx, client);
		}

	}

	public ArrayList<Client> tout() {
		return this.listeclient;

	}



}
