package testsql;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import mysqlobjects.MysqlAbonnement;
import metiers.AbonnementM;

public class MysqlAbonnementTest {

	private static AbonnementM abo;

	@BeforeClass
	public static void setUp() throws Exception {
		abo = new AbonnementM(3,4);
		MysqlAbonnement.getInstance().ajout(abo);
	}

	@Test
	public void testAjout() {
		AbonnementM abom = new AbonnementM(5,6);
		assertNotNull(MysqlAbonnement.getInstance().ajout(abom));
		assertNotNull(MysqlAbonnement.getInstance().supprimer(abom));
	}

	@Test
	public void testGet() {
		int id1 = abo.getId_client();
		int id2 = abo.getId_revue();
		AbonnementM cBdd = MysqlAbonnement.getInstance().getById(id1,id2);
		assertEquals(cBdd,abo);
	}

	@Test
	public void testModifier() {
		abo.setDate_fin(LocalDate.of(2020,9,12));
		assertNotNull(MysqlAbonnement.getInstance().modifier(abo));
	}

	@Test
	public void testSupprimer(){
		assertNotNull(MysqlAbonnement.getInstance().supprimer(abo));
	}

	@Test
	public void testGetAll(){
		ArrayList<AbonnementM> m=null;
		m=MysqlAbonnement.getInstance().tout();
		assertNotNull(m);
	}

}
