package testliste;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import liste.ListeRevue;
import metiers.RevueM;

public class ListeRevueTest {

	private static RevueM rev;

	@BeforeClass
	public static void setUp() throws Exception {
		rev = new RevueM("MegaTest","cool",4,"cool.jpg",2);
		ListeRevue.getInstance().ajout(rev);
	}

	@Test
	public void testAjout() {
		assertNotEquals(-1,rev.getId_revue());
	}

	@Test
	public void testGet() {
		int id = rev.getId_revue();
		RevueM cBdd = ListeRevue.getInstance().getById(id);
		assertEquals(cBdd,rev);
	}

	@Test
	public void testModifier() {
		String description = "desciptert";
		rev.setDescription(description);
		assertNotNull(ListeRevue.getInstance().modifier(rev));
	}

	@Test
	public void testSupprimer(){
		assertNotNull(ListeRevue.getInstance().supprimer(rev));
	}

	@Test
	public void testGetAll(){
		ArrayList<RevueM> m=null;
		m=ListeRevue.getInstance().tout();
		assertNotNull(m);
	}

}
