package testliste;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import liste.ListeAbonnement;
import metiers.AbonnementM;

public class ListeAbonnementTest {

	private static AbonnementM abo;

	@BeforeClass
	public static void setUp() throws Exception {
		abo = new AbonnementM(3,4);
		ListeAbonnement.getInstance().ajout(abo);
	}

	@Test
	public void testAjout() {
		AbonnementM abom = new AbonnementM(5,6);
		assertNotNull(ListeAbonnement.getInstance().ajout(abom));
		assertNotNull(ListeAbonnement.getInstance().supprimer(abom));
	}

	@Test
	public void testGet() {
		int id1 = abo.getId_client();
		int id2 = abo.getId_revue();
		AbonnementM cBdd = ListeAbonnement.getInstance().getById(id1,id2);
		assertEquals(cBdd,abo);
	}

	@Test
	public void testModifier() {
		abo.setDate_fin(LocalDate.of(2020,9,12));
		assertNotNull(ListeAbonnement.getInstance().modifier(abo));
	}

	@Test
	public void testSupprimer(){
		assertNotNull(ListeAbonnement.getInstance().supprimer(abo));
	}

	@Test
	public void testGetAll(){
		ArrayList<AbonnementM> m=null;
		m=ListeAbonnement.getInstance().tout();
		assertNotNull(m);
	}
}
