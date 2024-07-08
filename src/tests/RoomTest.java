package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import entity.Room;
import entity.TypeOfRoom;
import enums.StatusRoom;
import manage.RoomManager;

class RoomTest {


	public static RoomManager rm = new RoomManager("testSoba.txt");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rm.saveRooms();;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        File[] delete = {
                new File("testSoba.txt")
        };

        for (File fajl : delete) {
            fajl.delete();
        }
	}

	@Test
	public void testAddRoom() {
		TypeOfRoom t = new TypeOfRoom("jednokrevetna",90);
		List<String> criteria = new ArrayList<>();
		rm.addRoom(t, StatusRoom.slobodno, criteria);
		Room r = rm.rooms.get(100);
		assertTrue((r instanceof Room));
	}


	@Test 
	public void changeRoom() {
		TypeOfRoom t = new TypeOfRoom("jednokrevetna",90);
		List<String> criteria = new ArrayList<>();
		rm.addRoom(t, StatusRoom.slobodno, criteria);
		Room r = rm.rooms.get(99);
		rm.changeRoom(r.getRoomID(), r.getTypeRoom(),StatusRoom.zauzeto, r.getCriteria());
		assertTrue(r.getStatusRoom() == StatusRoom.zauzeto);
	}
	

	@Test
	public void testRemoveRoom() {
		rm.removeRoom(99);
		assertTrue(rm.rooms.size() == 1);
	}
	
	



}
