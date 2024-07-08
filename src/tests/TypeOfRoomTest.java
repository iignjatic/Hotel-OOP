package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import entity.TypeOfRoom;
import manage.TypeOfRoomManager;

class TypeOfRoomTest {


	public static TypeOfRoomManager tm = new TypeOfRoomManager("testTipoviSoba.txt");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tm.saveTypeOfRooms();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        File[] delete = {
                new File("testTipoviSoba.txt")
        };

        for (File fajl : delete) {
            fajl.delete();
        }
	}

	@Test
	public void testAddTypeOfRoom() {
		tm.addTypeOfRoom("cetverokrevetna", 150);
		TypeOfRoom t = tm.typeRooms.get("cetverokrevetna");
		assertTrue((t instanceof TypeOfRoom));
	}


	@Test 
	public void changeTypeOfRoom() {
		tm.addTypeOfRoom("cetverokrevetna", 150);
		TypeOfRoom t = tm.typeRooms.get("cetverokrevetna");
		tm.changeTypeOfRoom(t.getType(), 170);
		assertTrue(t.getPrice() == 170);
	}
	

	@Test
	public void testremoveTypeOfRoom() {
		tm.removeTypeOfRoom("cetverokrevetna");
		assertTrue(tm.typeRooms.size() == 0);
	}
	
	@Test
	public void testSerialization() {
		tm.saveTypeOfRooms();
		tm.loadTypeOfRoom();
		assertTrue(tm.typeRooms.size() == 0);

	}



}
