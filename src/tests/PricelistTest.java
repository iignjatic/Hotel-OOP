package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import entity.AdditionalServices;
import entity.PriceList;
import entity.TypeOfRoom;
import manage.PriceListManager;

class PricelistTest {

	public static PriceListManager pm = new PriceListManager("testCjenovnik.txt");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pm.savePricelist();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        File[] delete = {
                new File("testCjenovnik.txt")
        };

        for (File fajl : delete) {
            fajl.delete();
        }
	}

	@Test
	public void testAddPricelist() {
		HashMap<String, TypeOfRoom> typeRooms = new HashMap<>();
		HashMap<String, AdditionalServices> services = new HashMap<>();
		pm.createPricelist(LocalDate.of(2024, 2, 20), LocalDate.of(2024, 4, 30), typeRooms, services);
		PriceList p = pm.pricelistMap.get(2); 
		assertTrue((p instanceof PriceList));
	}


	@Test
	public void testRemovePriceList() {
		pm.removePricelist(2);
		assertTrue(pm.pricelistMap.size() == 0);
	}
	
	




}
