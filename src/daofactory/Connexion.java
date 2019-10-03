package daofactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {

	private static Connexion connexion = null;

	private Connection maConnexion;

	private Connexion() {

		Properties accesBdd1 = new Properties();

		try {
			InputStream source = getClass().getResourceAsStream("/Properties");
			accesBdd1.loadFromXML(source);

		} catch (IOException e) {
			e.printStackTrace();

		}

		try {

			// Class.forName("com.mysql.cj.jdbc.Driver");
			maConnexion = DriverManager.getConnection(accesBdd1.getProperty("url"), accesBdd1.getProperty("login"),
					accesBdd1.getProperty("pass"));
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
	}

	public static Connexion getInstance() {
		if (connexion == null)
			connexion = new Connexion();
		return connexion;
	}

	public Connection getMaConnexion() {
		return maConnexion;
	}

}
