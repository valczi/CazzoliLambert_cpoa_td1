package mysqlobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import daofactory.Connexion;
import daoobjects.RevueDAO;
import metiers.RevueM;

public class MysqlRevue implements RevueDAO{

	private static MysqlRevue instance;

	public MysqlRevue() {
		super();
	}

	public static MysqlRevue getInstance() {

		if (instance == null) {
			instance = new MysqlRevue();
		}
		return instance;
	} 

	public boolean ajout(RevueM Revue) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"INSERT INTO Revue(titre,description,tarif_numero,visuel,id_periodicite) VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, Revue.getTitre());
			requete.setString(2, Revue.getDescription());
			requete.setDouble(3, Revue.getTarif_numero());
			requete.setString(4, Revue.getVisuel());
			requete.setInt(5, Revue.getId_periodicite());
			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();
			if (res.next()) {
				Revue.setId_revue(res.getInt(1));
			}
			if (requete != null)
				requete.close();
			return true;

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public boolean supprimer(RevueM Revue) {

		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM Revue WHERE id_Revue="+ Revue.getId_revue());
			if (requete != null)
				requete.close();
			return true;

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public boolean modifier(RevueM Revue) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"UPDATE Revue SET titre=?,description=?,tarif_numero=?,visuel=?,id_periodicite=? WHERE id_Revue=?");

			requete.setString(1, Revue.getTitre());
			requete.setString(2, Revue.getDescription());
			requete.setDouble(3, Revue.getTarif_numero());
			requete.setString(4, Revue.getVisuel());
			requete.setInt(5, Revue.getId_periodicite());
			requete.setInt(6, Revue.getId_revue());
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			return true;
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public ArrayList<RevueM> tout() {
		ArrayList<RevueM> listeRevue = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue");
			ResultSet res = requete.executeQuery();
			while (res.next()) {

				listeRevue.add(new RevueM(res.getInt("id_revue"), res.getString("titre"), res.getString("description"),
						res.getDouble("tarif_numero"), res.getString("visuel"), res.getInt("id_periodicite")));
			}
			if (requete != null)
				requete.close();
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			listeRevue=null;
		}
		return listeRevue;
	}

	public RevueM getById(int id) {
		RevueM Revue = new RevueM();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"SELECT id_revue,titre,description,tarif_numero,visuel,id_periodicite FROM Revue WHERE id_Revue=?");
			requete.setInt(1,id);
			ResultSet res = requete.executeQuery();
			res.next();
			Revue = new RevueM(res.getInt("id_revue"), res.getString("titre"), res.getString("description"),
					res.getDouble("tarif_numero"), res.getString("visuel"), res.getInt("id_periodicite"));
			if (requete != null)
				requete.close();
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			Revue=null;
		}

		return Revue;

	}
	
	public boolean perioExist(int idp) {
		boolean resultat;
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Revue WHERE id_periodicite=?");
			requete.setInt(1, idp);
			ResultSet res = requete.executeQuery();
			
			if (res.next())
				resultat= true;
			else
				resultat= false;
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			resultat= false;
		}
		return resultat;

	}
}
