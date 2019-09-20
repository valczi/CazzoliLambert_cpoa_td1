package objmetiers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.Connexion;

public class Revue{

	public void AjoutRev(int id, String titre, String desc, double tarif, String visuel, int idperio) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"insert into Revue(id_Revue,titre,description,tarif_numero,visuel,id_periodicite) values(?,?,?,?,?,?)");
			requete.setInt(1, id);
			requete.setString(2, titre);
			requete.setString(3, desc);
			requete.setDouble(4, tarif);
			requete.setString(5, visuel);
			requete.setInt(6, idperio);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
	
	public void ModifRevTitre(int id, String Titre) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("UPDATE Revue SET titre = ? WHERE id_Revue	= ?");
			requete.setString(1, Titre);
			requete.setInt(2, id);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	public void ModifRevDesc(int id, String desc) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("UPDATE Revue SET Desc = ? WHERE id_Revue	= ?");
			requete.setString(1, desc);
			requete.setInt(2, id);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	public void ModifRevTarif(int id, double Tarif) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Revue SET tarif_numero = ? WHERE id_Revue	= ?");
			requete.setDouble(1, Tarif);
			requete.setInt(2, id);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	public void ModifRevVisuel(int id, String visu) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Revue SET visuel = ? WHERE id_Revue	= ?");
			requete.setString(1, visu);
			requete.setInt(2, id);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	public void ModifRevPerio(int id, int id_perio) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Revue SET id_periodicite WHERE id_Revue = ? ");
			requete.setInt(1, id);
			requete.setInt(2, id_perio);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
	
	public void SupprRev(int id) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from Revue where id_Revue= ?");
			requete.setInt(1, id);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

}
