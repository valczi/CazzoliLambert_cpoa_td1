package mysqlobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import daofactory.Connexion;
import daoobjects.PeriodiciteDAO;
import metiers.PeriodiciteM;

public class MysqlPeriodicite implements PeriodiciteDAO{
	private static MysqlPeriodicite instance;

	public MysqlPeriodicite() {
		super();
	}

	public static MysqlPeriodicite getInstance() {

		if (instance == null) {
			instance = new MysqlPeriodicite();
		}
		return instance;
	} 

	public boolean ajout(PeriodiciteM Periodicite) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"INSERT INTO Periodicite(libelle) VALUES(?)",
					Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, Periodicite.getLibelle());
			requete.executeUpdate();
			ResultSet res = requete.getGeneratedKeys();
			if (res.next()) {
				Periodicite.setId(res.getInt(1));
			}
			if (requete != null)
				requete.close();
			return true;

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public boolean supprimer(PeriodiciteM Periodicite) {

		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM Periodicite WHERE id_Periodicite=" + Periodicite.getId());
			if (requete != null)
				requete.close();
			return true;

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	public boolean modifier(PeriodiciteM Periodicite) {
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Periodicite SET libelle=? WHERE id_Periodicite=?");
			requete.setString(1, Periodicite.getLibelle());
			requete.setInt(2, Periodicite.getId());
			requete.executeUpdate();
			//System.out.println(Periodicite.getId());
			System.out.println("Le Periodicite a �t� modifi�.");
			if (requete != null)
				requete.close();
			return true;
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
			return false;
		}

	}

	@Override
	public ArrayList<PeriodiciteM> tout() {
		ArrayList<PeriodiciteM> listePeriodicite = new ArrayList<>();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT id_Periodicite,libelle FROM Periodicite");
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				listePeriodicite.add(new PeriodiciteM(res.getInt("id_periodicite"), res.getString("libelle")));
			}
			if (requete != null)
				requete.close();
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());

		}
		return listePeriodicite;
	}

	public PeriodiciteM getById(int id) {
		PeriodiciteM Periodicite = new PeriodiciteM();
		try {
			Connection laConnexion = Connexion.getInstance().getMaConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("SELECT libelle FROM Periodicite WHERE id_Periodicite= ?");
			requete.setInt(1,id);
			ResultSet res = requete.executeQuery();
			res.next();
			Periodicite = new PeriodiciteM(id, res.getString("libelle"));
			if (requete != null)
				requete.close();
			if (res != null)
				res.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());

		}
	//	if (Periodicite.getLibelle() == null)
		//	Periodicite = null;
		return Periodicite;

	}
	
	

}
