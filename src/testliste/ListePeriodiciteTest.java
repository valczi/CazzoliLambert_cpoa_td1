package testliste;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import liste.ListePeriodicite;
import metiers.PeriodiciteM;

public class ListePeriodiciteTest {

	private static PeriodiciteM perio;

	@BeforeClass
	public static void setUp() throws Exception {
		perio = new PeriodiciteM("testlibelle");
		ListePeriodicite.getInstance().ajout(perio);
	}

	@Test
	public void testAjout() {
		assertNotEquals(-1,perio.getId());
	}

	@Test
	public void testGet() {
		int id = perio.getId();
		PeriodiciteM cBdd = ListePeriodicite.getInstance().getById(id);
		assertEquals(cBdd,perio);
	}

	@Test
	public void testModifier() {
		String libelle = "test1";
		perio.setLibelle((libelle));
		assertNotNull(ListePeriodicite.getInstance().modifier(perio));
	}

	@Test
	public void testSupprimer(){
		assertNotNull(ListePeriodicite.getInstance().supprimer(perio));
	}

	@Test
	public void testGetAll(){
		ArrayList<PeriodiciteM> m=null;
		m=ListePeriodicite.getInstance().tout();
		assertNotNull(m);
	}

}
