package tests;


import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Maid;
import entity.Room;
import entity.TypeOfRoom;
import enums.Function;
import enums.Gender;
import enums.LevelOfEducation;
import enums.StatusRoom;
import manage.MaidManager;
import manage.RoomManager;
import manage.WorkerManager;

class MaidTest {

	public static WorkerManager wm = new WorkerManager();
	public static RoomManager rm = new RoomManager();
	public static MaidManager mm = new MaidManager(wm);
	
	
	public static void testSomeoneClean() {
		wm.addWorker("jana", "dfdsds", "jana", "janic", Gender.Ž, LocalDate.of(1980, 5, 12),"5646554" ,
				LevelOfEducation.IV,  1, Function.maid);
		wm.addWorker("sonja", "dfdsds", "sonja", "janic", Gender.Ž, LocalDate.of(1980, 5, 12),"5646554" ,
				LevelOfEducation.IV,  1, Function.maid);
		
		TypeOfRoom t = new TypeOfRoom();
		List<String> criteria = new ArrayList<>();
		rm.addRoom(t, StatusRoom.slobodno, criteria);
		Room r = rm.rooms.get(1);
		
		Maid m1 = (Maid)wm.workersMap.get("jana");
		m1.setNumOfRoomsToClean(1);
		mm.someoneClean(r);	
		
		Maid m2 = (Maid)wm.workersMap.get("sonja");

		
		assertTrue(m2.getNumOfRoomsToClean() == 1);

	}

}
