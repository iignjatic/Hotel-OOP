package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Room;
import entity.TypeOfRoom;
import enums.StatusRoom;

public class RoomManager {
	private static int roomID = 99;
	String fileName ;
	
	public RoomManager(String file) {
		this.fileName = file;
	}
	
	public RoomManager() {
		
	}
	
	public HashMap<Integer, Room> rooms = new HashMap<>();
	

	public void addRoom(TypeOfRoom typeRoom, StatusRoom status, List<String> criteria) { // dodavanje sobe
		Room r = new Room(typeRoom, status,roomID, criteria);
		rooms.put(roomID, r);
		roomID++;
		this.saveRooms();
		
	}

	public void changeRoom(int id, TypeOfRoom type, StatusRoom status, List<String> criteria) { // mijenjanje dostupne sobe
		if (rooms.containsKey(id)) {
			Room r = rooms.get(id);
			r.setTypeRoom(type);
			r.setStatusRoom(status);
			r.setCriteria(criteria);
			this.saveRooms();
		}
	}
	
	public void removeRoom(int id) {
		if(rooms.containsKey(id)){
			rooms.remove(id);
			this.saveRooms();
		}
	}
	

	public void showRooms() {
		System.out.println("Trenutna lista soba: ");
		System.out.println("--------------------");
		for (int id : rooms.keySet()) {
			System.out.println(rooms.get(id).toFileString());
		}
		System.out.println("_____________________");
	}


	public void loadRooms(MaidManager m, TypeOfRoomManager tm) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));
			String line = null;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				Room r = new Room();
				TypeOfRoom t = new TypeOfRoom();
				roomID++;
				r.setRoomID(Integer.parseInt(tokens[0]));
				t = tm.typeRooms.get(tokens[1]);
				r.setTypeRoom(t);
				r.setStatusRoom(StatusRoom.valueOf(tokens[2]));
				
				if(r.getStatusRoom() == StatusRoom.spremanje) {
					m.someoneClean(r);
				}
				
				List<String> criterias = new ArrayList<>();
				for(int i = 3; i< tokens.length; i++) {
					criterias.add(tokens[i]);
				}
				r.setCriteria(criterias);
				
				rooms.put(Integer.parseInt(tokens[0]), r);
			}
			br.close();

		} catch (IOException e) {

		}
	}

	public void saveRooms() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, false));
			pw.println("ID sobe, Tip sobe");

			for (int roomID : rooms.keySet()) {
				//pw.print(roomID);
				pw.println(rooms.get(roomID).toFileString());
			}
			pw.close();

		} catch (Exception e) {

		}
	}
}
