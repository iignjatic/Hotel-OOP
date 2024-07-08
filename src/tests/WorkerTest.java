package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import entity.Workers;
import enums.Function;
import enums.Gender;
import enums.LevelOfEducation;
import manage.WorkerManager;

class WorkerTest {

	public static WorkerManager wm = new WorkerManager("testZaposleni.txt");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			wm.saveUsers();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        File[] delete = {
                new File("testZaposleni.txt")
        };

        for (File fajl : delete) {
            fajl.delete();
        }
	}

	@Test
	public void testAddWorker() {
		wm.addWorker("ivana", "dcfsd", "ivana", "ignjatic", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg", LevelOfEducation.V, 5, Function.receptionist);
		Workers w = wm.workersMap.get("ivana");
		assertTrue((w instanceof Workers));
	}

	@Test
	public void testAllWorkers() {
		wm.addWorker("ivana", "dcfsd", "ivana", "ignjatic", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg", LevelOfEducation.V, 5, Function.receptionist);
		wm.addWorker("dejan", "dcfsd", "stefan", "ignjatic", Gender.M, LocalDate.of(2004, 5, 18), "sgvrg", LevelOfEducation.VI, 5, Function.admin);
		List<Workers> workers = wm.showWorkers();
		assertTrue(workers.size() == 2);
	}


	@Test 
	public void changeWorker() {
		wm.addWorker("ivana", "dcfsd", "ivana", "ignjatic", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg", LevelOfEducation.V, 5, Function.receptionist);
		Workers w = wm.workersMap.get("ivana");
		wm.changeWorker(w.getUsername(), w.getPassword(), "sofija", "tesic", w.getGender(), w.getDate(), w.getPhoneNumber(), w.getLevel(),
				w.getInternship(), w.getFunction());
		assertTrue(w.getName().equals("sofija") && w.getSurname().equals("tesic"));
	}
	

	@Test
	public void testremoveWorker() {
		wm.removeWorker("ivana");
		assertTrue(wm.showWorkers().size() == 1);
	}
	
	@Test
	public void testSerialization() {
		wm.addWorker("ivana", "dcfsd", "ivana", "ignjatic", Gender.Ž, LocalDate.of(2004, 5, 18), "sgvrg", LevelOfEducation.V, 5, Function.receptionist);
		wm.addWorker("dejan", "dcfsd", "stefan", "ignjatic", Gender.M, LocalDate.of(2004, 5, 18), "sgvrg", LevelOfEducation.VI, 5, Function.admin);
		wm.removeWorker("ivana");
		wm.saveUsers();
		wm.loadUsers();
		assertTrue(wm.showWorkers().size() == 1);

	}




}
