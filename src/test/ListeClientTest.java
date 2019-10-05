package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import liste.ListeClient;
import metiers.ClientM;

public class ListeClientTest {

	private static ClientM client;

	@BeforeClass
	public static void setUp() throws Exception {
		client = new ClientM("test", "test2");
		//ListeClient.getInstance().ajout(client);

	}

	@Test
	public void testAjout() {
		assertNotNull(ListeClient.getInstance().ajout(client));
		// System.out.println("ajout"+client.toString());
	}

	@Test
	public void testGet() {
		int id = client.getId();
		System.out.println(client.toString());
		ClientM cBdd = ListeClient.getInstance().getById(id);
		assertNotNull(cBdd);
	}

	@Test
	public void testModifier() {
		String prenom = "test1";
		client.setPrenom(prenom);
		assertNotNull(ListeClient.getInstance().modifier(client));
	}

	@Test
	public void testSupprimer() {
		assertNotNull(ListeClient.getInstance().supprimer(client));
	}

}
