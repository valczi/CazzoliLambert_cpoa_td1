package mysqlobjects;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daofactory.Connexion;
import daoobjects.AbonnementDAO;
import metiers.AbonnementM;

public class MysqlAbonnement implements AbonnementDAO {
	private static MysqlAbonnement instance;

	public MysqlAbonnement() {
		super();
	}

	public static MysqlAbonnement getInstance() {

		if (instance == null) {
			instance = new MysqlAbonnement();
		}
		return instance;
	}

	public boolean ajout(AbonnementM Abonnement) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"INSERT INTO Abonnement( id_client,  id_revue,  date_debut,  date_fin) VALUES(?,?,?,?)");
			requete.setInt(1, Abonnement.getId_client());
			requete.setInt(2, Abonnement.getId_revue());
			requete.setDate(3, Date.valueOf(Abonnement.getDate_debut()));
			requete.setDate(4, Date.valueOf(Abonnement.getDate_fin()));
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			return true;

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public boolean supprimer(AbonnementM Abonnement) {

		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("DELETE FROM Abonnement WHERE id_client=? AND id_revue=?");
			requete.setInt(1, Abonnement.getId_client());
			requete.setInt(2, Abonnement.getId_revue());
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			return true;

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public boolean modifier(AbonnementM Abonnement) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"UPDATE Abonnement SET date_debut=?,date_fin=? WHERE id_client=? AND id_revue= ?");
			requete.setDate(1, Date.valueOf(Abonnement.getDate_debut()));
			requete.setDate(2, Date.valueOf(Abonnement.getDate_fin()));
			requete.setInt(3, Abonnement.getId_client());
			requete.setInt(4, Abonnement.getId_revue());
			requete.executeUpdate();
			// System.out.println(Abonnement.getId());
			if (requete != null)
				requete.close();
			return true;
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	@Override
	public ArrayList<AbonnementM> tout() {
		ArrayList<AbonnementM> listeAbonnement = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("SELECT id_client,id_revue,date_debut,date_fin FROM Abonnement");
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				listeAbonnement.add(new AbonnementM(res.getInt("id_client"), res.getInt("id_revue"),
						res.getDate("date_debut").toLocalDate(), res.getDate("date_fin").toLocalDate()));
			}
			if (requete != null)
				requete.close();
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			listeAbonnement = null;
		}
		return listeAbonnement;
	}

	public AbonnementM getById(int id, int id2) {
		AbonnementM Abonnement = new AbonnementM();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("SELECT date_debut,date_fin FROM Abonnement WHERE id_client=? AND id_revue=?");
			requete.setInt(1, id);
			requete.setInt(2, id2);
			ResultSet res = requete.executeQuery();
			res.next();
			Abonnement = new AbonnementM(id, id2, res.getDate("date_debut").toLocalDate(),
					res.getDate("date_fin").toLocalDate());
			if (requete != null)
				requete.close();
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			Abonnement = null;
		}
		return Abonnement;

	}

	public boolean revExist(int idRev) {
		boolean resultat;
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Abonnement WHERE id_revue=?");
			requete.setInt(1, idRev);
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
	
	public boolean cliExist(int id) {
		boolean resultat;
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Abonnement WHERE id_client=?");
			requete.setInt(1, id);
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
