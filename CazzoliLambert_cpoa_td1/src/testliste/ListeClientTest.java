package testliste;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import liste.ListeClient;
import metiers.ClientM;

public class ListeClientTest {
	private static ClientM client;

	@BeforeClass
	public static void setUp() throws Exception {
		client = new ClientM("test", "test2");
		ListeClient.getInstance().ajout(client);
	}

	@Test
	public void testAjout() {
		assertNotEquals(-1,client.getId());
	}

	@Test
	public void testGet() {
		int id = client.getId();
		ClientM cBdd = ListeClient.getInstance().getById(id);
		assertEquals(cBdd,client);
	}

	@Test
	public void testModifier() {
		String prenom = "test1";
		client.setPrenom(prenom);
		assertNotNull(ListeClient.getInstance().modifier(client));
	}

	@Test
	public void testSupprimer(){
		assertNotNull(ListeClient.getInstance().supprimer(client));
	}

	@Test
	public void testGetAll(){
		ArrayList<ClientM> m=null;
		m=ListeClient.getInstance().tout();
		assertNotNull(m);
	}
}
