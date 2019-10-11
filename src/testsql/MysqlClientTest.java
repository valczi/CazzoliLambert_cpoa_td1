package testsql;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import mysqlobjects.MysqlClient;
import metiers.ClientM;

public class MysqlClientTest {

	private static ClientM client;

	@BeforeClass
	public static void setUp() throws Exception {
		client = new ClientM("test", "test2");
		MysqlClient.getInstance().ajout(client);
	}

	@Test
	public void testAjout() {
		assertNotEquals(-1,client.getId());
	}

	@Test
	public void testGet() {
		int id = client.getId();
		ClientM cBdd = MysqlClient.getInstance().getById(id);
		System.out.println("cbdd :"+cBdd.toString());
		System.out.println("client :"+client.toString());
		assertEquals(cBdd,client);
	}

	@Test
	public void testModifier() {
		String prenom = "test1";
		client.setPrenom(prenom);
		assertTrue(MysqlClient.getInstance().modifier(client));
	}

	@Test
	public void testSupprimer(){
		assertTrue(MysqlClient.getInstance().supprimer(client));
	}

	@Test
	public void testGetAll(){
		ArrayList<ClientM> m=null;
		m=MysqlClient.getInstance().tout();
		assertNotNull(m);
	}

}
