package objmetiers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.Connexion;

public class Periodicite{
	
	
	public void AjoutPer(int id, String libelle) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"insert into Periodicite(id_periodicite,libelle) values(?,?)");
			requete.setString(2, libelle);
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

	public void ModifPer(int id, String libelle) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Periodicite SET libelle = ? WHERE id_periodicite	= ?");
			requete.setString(1, libelle);
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
		
		public void SupprPer(int id) {
			try {
				Connection laConnexion = Connexion.creeConnexion();
				PreparedStatement requete = laConnexion.prepareStatement("delete from Periodicite where id_periodicite= ?");
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