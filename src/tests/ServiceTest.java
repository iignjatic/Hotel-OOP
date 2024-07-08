package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import entity.AdditionalServices;
import manage.AdditionalServicesManager;


class ServiceTest {


	public static AdditionalServicesManager sm = new AdditionalServicesManager("testUsluga.txt");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sm.saveServices();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        File[] delete = {
                new File("testUsluga.txt")
        };

        for (File fajl : delete) {
            fajl.delete();
        }
	}

	@Test
	public void testAddService() {
		sm.addAdditionalService("spa", 15);
		AdditionalServices service = sm.services.get("spa");
		assertTrue((service instanceof AdditionalServices));
	}


	@Test 
	public void changeService() {
		sm.addAdditionalService("spa", 15);
		AdditionalServices s = sm.services.get("spa");
		sm.changeService(s.getService(), 18);
		assertTrue(s.getPrice() == 18);
	}
	

	@Test
	public void testRemoveService() {
		sm.addAdditionalService("masaza", 10);
		sm.removeAdditionalService("spa");
		assertTrue(sm.services.size() == 1);
	}
	
	@Test
	public void testSerialization() {
		sm.addAdditionalService("masaza", 10);
		sm.addAdditionalService("spa", 15);
		sm.removeAdditionalService("masaza");
		sm.saveServices();		
		sm.loadServices();		
		assertTrue(sm.services.size() == 1);

	}




}
