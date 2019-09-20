package objmetiers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import connexion.Connexion;

public class Abonnement{

	
	public void AjoutAbo(int idcl, int idrev, String datedeb, String datefin) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("insert into Abonnement(id_client,id_Revue,date_debut,date_fin) values(?,?,?,?)");
			requete.setInt(1, idcl);
			requete.setInt(2, idrev);
			char a = '/';
			char b = '-';
			datedeb.trim();
			datefin.trim();
			datefin=datefin.replace(a, b);
			datedeb=datedeb.replace(a, b);

			System.out.println(datedeb);
			System.out.println(datefin);
			LocalDate d1 = LocalDate.parse(datedeb);
			LocalDate d2 = LocalDate.parse(datefin);

			requete.setDate(3, Date.valueOf(d1));
			requete.setDate(4, Date.valueOf(d2));
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	public void ModifAboDeb(int id_cl, int id_Revue, String date) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Abonnement SET date_debut = ? WHERE id_client	= ? and id_Revue=?");
			requete.setString(1, date);
			requete.setInt(2, id_cl);
			requete.setInt(3, id_Revue);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
	public void ModifAboFin(int id_cl, int id_Revue, String date) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Abonnement SET date_fin = ? WHERE id_client	= ? and id_Revue=?");
			requete.setString(1, date);
			requete.setInt(2, id_cl);
			requete.setInt(3, id_Revue);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	public void ModifAboRev(int id_cl, int id_Revue) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Abonnement SET id_Revue = ? WHERE id_client	= ? ");
			requete.setInt(1, id_cl);
			requete.setInt(2, id_Revue);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	
	public void SupprAbo(int id, int id2) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("delete from Abonnement where id_client= ? and id_Revue=?");
			requete.setInt(1, id);
			requete.setInt(2, id2);
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
