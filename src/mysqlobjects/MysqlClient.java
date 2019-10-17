package mysqlobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import daofactory.Connexion;
import metiers.*;
import daoobjects.*;

public class MysqlClient implements ClientDAO {

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

	public boolean ajout(ClientM Client) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"INSERT INTO Client(nom,prenom,no_rue,voie,code_postal,ville,pays) VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, Client.getNom());
			requete.setString(2, Client.getPrenom());
			requete.setString(3, Client.getNo_rue());
			requete.setString(4, Client.getVoie());
			requete.setString(5, Client.getCode_postal());
			requete.setString(6, Client.getVille());
			requete.setString(7, Client.getPays());
			requete.executeUpdate();

			ResultSet res = requete.getGeneratedKeys();
			if (res.next()) {
				Client.setId(res.getInt(1));
			}
			if (requete != null)
				requete.close();
			if (res != null) {
				res.close();
			}
			return true;

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public boolean supprimer(ClientM Client) {

		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM Client WHERE id_client=" + Client.getId());
			if (requete != null)
				requete.close();
			return true;

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public boolean modifier(ClientM Client) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"UPDATE Client SET nom=?,prenom=?,no_rue=?,voie=?,code_postal=?,ville=?,pays=? WHERE id_client=?");
			requete.setString(1, Client.getNom());
			requete.setString(2, Client.getPrenom());
			requete.setString(3, Client.getNo_rue());
			requete.setString(4, Client.getVoie());
			requete.setString(5, Client.getCode_postal());
			requete.setString(6, Client.getVille());
			requete.setString(7, Client.getPays());
			requete.setInt(8, Client.getId());
			requete.executeUpdate();
			// System.out.println(Client.getId());
			// System.out.println("Le Client a ete modifie.");
			if (requete != null)
				requete.close();
			return true;
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	@Override
	public ArrayList<ClientM> tout() {
		ArrayList<ClientM> listeClient = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("SELECT id_client,nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client");
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				listeClient.add(new ClientM(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
						res.getString("no_rue"), res.getString("voie"), res.getString("code_postal"),
						res.getString("ville"), res.getString("pays")));
			}
			if (requete != null)
				requete.close();
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			listeClient = null;

		}
		return listeClient;
	}

	public ClientM getById(int id) {
		System.out.println("id : "+id);
		ClientM Client = new ClientM();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT nom,prenom,no_rue,voie,code_postal,ville,pays FROM Client WHERE id_client=" + id);
			ResultSet res = requete.executeQuery();
			res.next();
			Client = new ClientM(id, res.getString("nom"), res.getString("prenom"), res.getString("no_rue"),
					res.getString("voie"), res.getString("code_postal"), res.getString("ville"), res.getString("pays"));
			if (requete != null)
				requete.close();
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());

		}
		if (Client.getNom() == null || Client.getPrenom() == null)
			Client = null;
		return Client;

	}

}
