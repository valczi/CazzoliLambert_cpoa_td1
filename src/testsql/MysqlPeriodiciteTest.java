package testsql;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import mysqlobjects.MysqlPeriodicite;
import metiers.PeriodiciteM;

public class MysqlPeriodiciteTest {

	private static PeriodiciteM perio;

	@BeforeClass
	public static void setUp() throws Exception {
		perio = new PeriodiciteM("testlibelle");
		MysqlPeriodicite.getInstance().ajout(perio);
	}

	@Test
	public void testAjout() {
		assertNotEquals(-1,perio.getId());
	}

	@Test
	public void testGet() {
		int id = perio.getId();
		PeriodiciteM cBdd = MysqlPeriodicite.getInstance().getById(id);
		assertEquals(cBdd,perio);
	}

	@Test
	public void testModifier() {
		String libelle = "test1";
		perio.setLibelle((libelle));
		assertTrue(MysqlPeriodicite.getInstance().modifier(perio));
	}

	@Test
	public void testSupprimer(){
		assertTrue(MysqlPeriodicite.getInstance().supprimer(perio));
	}

	@Test
	public void testGetAll(){
		ArrayList<PeriodiciteM> m=null;
		m=MysqlPeriodicite.getInstance().tout();
		assertNotNull(m);
	}

}
