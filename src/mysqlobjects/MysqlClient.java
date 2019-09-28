package mysqlobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import daofactory.Connexion;
import objmetiers.*;
import daoobjects.*;

public class MysqlClient implements ClientDAO {

	Scanner sc = new Scanner(System.in);
	private static MysqlClient instance;

	public MysqlClient() {
		super();
	}

	public static MysqlClient getInstance() {

		if (instance == null) {
			instance = new MysqlClient();
		}
		return instance;
	}

	public void ajout(Client client) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"INSERT INTO client(nom,prenom,no_rue,voie,code_postal,ville,pays,ca_cumule) VALUES(?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, client.getNom());
			requete.setString(2, client.getPrenom());
			requete.setString(3, client.getNo_rue());
			requete.setString(4, client.getVoie());
			requete.setString(5, client.getCode_postal());
			requete.setString(6, client.getVille());
			requete.setString(7, client.getPays());
			requete.executeUpdate();

			ResultSet res = requete.getGeneratedKeys();
			if (res.next()) {
				client.setId_client(res.getInt(1));
			}
			if (requete != null)
				requete.close();
			if (res != null) {
				res.close();
			}

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}

	public void supprimer(Client client) {

		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM client WHERE id_client=" + client.getId_client());
			if (requete != null)
				requete.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}

	public void modifier(Client client) {
		try {

			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE client SET nom=?,prenom=? WHERE id_client=?");
			requete.setString(1, client.getNom());
			requete.setString(2, client.getPrenom());
			requete.setInt(3, client.getId_client());
			requete.executeUpdate();
			System.out.println(client.getId_client());
			System.out.println("Le client a été modifié.");
			if (requete != null)
				requete.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}

	@Override
	public ArrayList<Client> tout() {
		ArrayList<Client> listeclient = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT id_client,nom,prenom FROM client");
			ResultSet res = requete.executeQuery();
			while (res.next()) {

				listeclient.add(new Client(res.getString("nom"), res.getString("prenom")));
			}
			if (requete != null)
				requete.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());

		}
		return listeclient;
	}

	public Client getById(int id) {
		Client client = new Client();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("SELECT id_client,nom,prenom FROM client WHERE id_client=" + id);
			ResultSet res = requete.executeQuery();
			client = new Client(res.getString("nom"), res.getString("prenom"));
			if (requete != null)
				requete.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());

		}
		if (client.getNom() == null || client.getPrenom() == null)
			client = null;
		return client;

	}

}
