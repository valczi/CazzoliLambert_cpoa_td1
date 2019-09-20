package objmetiers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.Connexion;



public class Client{

	
	public void AjoutCl(int id, String nom, String prenom, int no_rue, String voie, int codepost, String ville,
			String pays) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement(
					"insert into Client(id_client,nom,prenom,no_rue,voie,code_postal,ville,pays) values(?,?,?,?,?,?,?,?)");
			requete.setInt(1,id);
			requete.setString(2, nom);
			requete.setString(3, prenom);
			requete.setInt(4, no_rue);
			requete.setString(5, voie);
			requete.setInt(6, codepost);
			requete.setString(7, ville);
			requete.setString(8, pays);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	public void SupprCl(int id) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client= ?");

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

	public void ModifClNom(int id, String nom) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("UPDATE Client SET nom = ? WHERE id_client	= ?");
			requete.setString(1, nom);
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

	public void ModifClPrenom(int id, String pren) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Client SET prenom = ? WHERE id_client	= ?");
			requete.setString(1, pren);
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

	public void ModifClVoie(int id, String voie) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Client SET voie = ? WHERE id_client	= ?");
			requete.setString(1, voie);
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

	public void ModifClRue(int id, int no_rue) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Client SET no_rue = ? WHERE id_client	= ?");
			requete.setInt(1, no_rue);
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

	public void ModifClCode(int id, int code) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Client SET code_postal = ? WHERE id_client	= ?");
			requete.setInt(1, code);
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

	public void ModifClVille(int id_cl, String ville) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Client SET ville = ? WHERE id_client	= ?");
			requete.setString(1, ville);
			requete.setInt(2, id_cl);
			requete.executeUpdate();
			if (requete != null)
				requete.close();
			if (laConnexion != null)
				laConnexion.close();

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}

	public void ModifClPays(int id_cl, String pays) {
		try {

			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("UPDATE Client SET pays = ? WHERE id_client	= ?");
			requete.setString(1, pays);
			requete.setInt(2, id_cl);
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

