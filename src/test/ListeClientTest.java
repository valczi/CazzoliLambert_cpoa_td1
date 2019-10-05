package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import liste.*;
import metiers.ClientM;

public class ListeClientTest {
	private ClientM client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@BeforeClass
	public void setUp() throws Exception {
		System.out.println("yo les poto");
		client = new ClientM("test", "test2", 0);
		ListeClient.getInstance().ajout(client);
	}

	@Test
	public void testGet() {
		int id = client.getId();
		ClientM cBdd = ListeClient.getInstance().getById(id);
		assertNotNull(cBdd);
	}

	@Test
	public void testAjout() {
		assertNotNull(ListeClient.getInstance().getById(client.getId()));
	}

	

	@Test
	public void testModifier() {
		String prenom = "test1";
		client.setPrenom(prenom);
		assertNotNull(ListeClient.getInstance().modifier(client));
	}
	
	@Test
	public void testSupprimer() {
		int id = client.getId();
		ListeClient.getInstance().supprimer(client);
		ClientM cbdd = ListeClient.getInstance().getById(id);
		assertNull(cbdd);
	}
}
