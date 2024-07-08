package manage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import entity.TypeOfRoom;

public class TypeOfRoomManager {
	
	String fileName;

	
	public TypeOfRoomManager(String file) {
		this.fileName = file;
	}
	
	public TypeOfRoomManager() {
		
	}
	
	public HashMap<String, TypeOfRoom> typeRooms = new HashMap<>();

	public void addTypeOfRoom(String type, int price) {
		TypeOfRoom t1 = new TypeOfRoom(type, price);
		typeRooms.put(type, t1);
		this.saveTypeOfRooms();
	}
	
	public void removeTypeOfRoom(String service) {
		if (typeRooms.containsKey(service)) {
			typeRooms.remove(service);
			this.saveTypeOfRooms();
		}
	}
	
	public void changeTypeOfRoom(String type, int price) {
		if (typeRooms.containsKey(type)) {
			TypeOfRoom t = typeRooms.get(type);
			t.setPrice(price);
			this.saveTypeOfRooms();
		}
	}
	



public void saveTypeOfRooms() {			//cuvanje u fajl rezervacija
	try {
		PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, false));
		pw.println("Tip sobe, Cijena");

		for (String type : typeRooms.keySet()) {
			pw.println(typeRooms.get(type).toFileString());
		}
		pw.close();
	} catch (IOException e) {

	}
}

public void loadTypeOfRoom() {			//citanje iz fajla rezervacija
	try {
		BufferedReader br = new BufferedReader(new FileReader(this.fileName));
		String line = br.readLine();

		while ((line = br.readLine()) != null) {
			String[] tokens = line.split(",");
			String type = tokens[0];
			TypeOfRoom t = new TypeOfRoom();
			t.setType(tokens[0]);
			t.setPrice(Float.parseFloat(tokens[1]));
			

			typeRooms.put(type, t);
		}
		br.close();
	} catch (IOException e) {

	}
}
}

	
	
	

