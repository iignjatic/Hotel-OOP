package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import entity.Guests;
import enums.Gender;
import manage.GuestManager;

class GuestsTest {


	public static GuestManager gm = new GuestManager("testGosti.txt");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			gm.saveGuests();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        File[] delete = {
                new File("testGosti.txt")
        };

        for (File fajl : delete) {
            fajl.delete();
        }
	}

	@Test
	public void testAddGuest() {
		gm.addGuests("ivana", "dcfsd", "ivana", "ignjatic", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg");
		Guests g = gm.guestsMap.get("ivana");
		assertTrue((g instanceof Guests));
	}



	@Test 
	public void testChangeGuests() {
		gm.addGuests("ivana", "dcfsd", "ivana", "ignjatic", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg");
		Guests g = gm.guestsMap.get("ivana");
		gm.changeGuests(g.getUsername(), g.getPassword(), "maja", g.getSurname(), g.getGender(), g.getDate(), g.getPhoneNumber());
		assertTrue(g.getName().equals("maja"));
	}
	

	@Test
	public void testremoveWorker() {
		gm.addGuests("maja", "dcfsd", "maja", "maric", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg");
		gm.removeGuest("ivana");
		assertTrue(gm.guestsMap.size() == 1);
	}
	
	@Test
	public void testSerialization() {
		gm.addGuests("ivana", "dcfsd", "ivana", "ignjatic", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg");
		gm.addGuests("maja", "dcfsd", "maja", "maric", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg");
		gm.removeGuest("ivana");
		gm.saveGuests();
		gm.loadGuests();
		assertTrue(gm.guestsMap.size() == 1);

	}




}
