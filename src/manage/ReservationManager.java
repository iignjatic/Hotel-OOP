package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import entity.AdditionalServices;
import entity.Guests;
import entity.PriceList;
import entity.Reservation;
import entity.Room;
import entity.TypeOfRoom;
import enums.StatusReservation;

public class ReservationManager {

	private RoomManager roomManager;
	private static int resID = 0;
	String fileName;
	
	public HashMap<Integer, Reservation> reservationsMap = new HashMap<>();
	
	public ReservationManager(RoomManager roomManager, String file) {
		this.roomManager = roomManager;
		this.fileName = file;
	}
	

	public void createReservation(Guests g, TypeOfRoom typeRoom,Room room, AdditionalServicesManager a,
			LocalDate start,LocalDate end, ArrayList<AdditionalServices> addServices, StatusReservation status, 
			LocalDate processing_date, PriceListManager pm) {
		
		if (g == null || typeRoom == null || start == null || end == null || addServices == null || pm == null) {
	        throw new IllegalArgumentException("Jedan ili više ulaznih parametara su null.");
	    }
		
		int maxID = 0;
		for(PriceList p: pm.pricelistMap.values()) {
			if (p.getId()>maxID)
				maxID = p.getId();
		}
		
		LocalDate startRes = start;
		
		PriceList seasonP = pm.pricelistMap.get(maxID);
		PriceList regularP = pm.pricelistMap.get(1);
		float totalPrice = 0;
		
		while(!startRes.isAfter(end)) {
			if((seasonP.getStartDate().isEqual(startRes) || (startRes.isAfter(seasonP.getStartDate()))) 
					&& (seasonP.getEndDate().isEqual(startRes) || startRes.isBefore(seasonP.getEndDate()))){
					for(AdditionalServices service: addServices) {
						totalPrice += seasonP.getServices().get(service.getService()).getPrice(); 
					}
					totalPrice += seasonP.getTypeOfRooms().get(typeRoom.getType()).getPrice();
			}
			else {
				for(AdditionalServices service: addServices) {
					totalPrice += regularP.getServices().get(service.getService()).getPrice(); 
				}
				totalPrice += regularP.getTypeOfRooms().get(typeRoom.getType()).getPrice();
			}
			startRes = startRes.plusDays(1);
		}
		
	
		Reservation r = new Reservation(g, typeRoom, room,start, end, addServices, status, processing_date, totalPrice);
		resID++;
		reservationsMap.put(resID, r);
		
		saveReservations();
		


	}
	
	public Boolean isRoomTypeAvailable(TypeOfRoom type, LocalDate start, LocalDate end, RoomManager roomM) {
		
		int countOfTakenOnes = 0;
		
		for (Reservation res : reservationsMap.values()) {
			if(res.getType().equals(type) && res.getStatusOfReservation() == StatusReservation.POTVRDJENA) {
				LocalDate takenStart = res.getStart();
				LocalDate takenEnd = res.getEnd();
				if (!(end.isBefore(takenStart) || start.isAfter(takenEnd))) {//ili ako je zauzeta za taj datum
					countOfTakenOnes ++;
				}
			}
			
		}
		
		int countOfRooms = 0;
		for (int id : roomM.rooms.keySet()) {
			if (roomM.rooms.get(id).getTypeRoom().getType().equals(type.getType())) {
				countOfRooms ++;
			}
		}
		
		if(countOfRooms>countOfTakenOnes) {
			return true;
		}
			else {
		return false;
		}
	}
	
	public Boolean isRoomAvailable(Room r,LocalDate start, LocalDate end) {
		for (Reservation res : reservationsMap.values()) {
			if (res.getR()!=null) {
			if(res.getR().getRoomID() == r.getRoomID()) {
				LocalDate takenStart = res.getStart();
				LocalDate takenEnd = res.getEnd();
				if (!(end.isBefore(takenStart) || start.isAfter(takenEnd))) {//ili ako je zauzeta za taj datum
					return false;
				}
				}
			}
			
		}
		return true;
	}
	
	public ArrayList<Integer> availableTypeRooms(LocalDate start, LocalDate end, TypeOfRoom type) { // dostupne sobe
	
		ArrayList<Integer> rooms = new ArrayList<>();
		
		for(Room r : this.roomManager.rooms.values()) {
			if(r.getTypeRoom().getType().equals(type.getType())) {
			if(isRoomAvailable(r, start, end)) {
				rooms.add(r.getRoomID());
			}
			}
		}
		return rooms;
	}
	
	public Room findRoom(TypeOfRoom type, LocalDate start, LocalDate end) {
		for(Room r : this.roomManager.rooms.values()) {
			if(isRoomAvailable(r, start, end)) {
				if(r.getTypeRoom().getType().equals(type.getType())) {
					return r;
				}
			}
			}
		return null;
		}
	
	
	
	
	
	public void showReservations() {
		System.out.println("____________________________");
		System.out.println("PRIKAZ TRENUTNIH REZERVACIJA");
		for (int reservationID: reservationsMap.keySet()) {
			System.out.print(reservationID);
			System.out.println(reservationsMap.get(reservationID).toFileString());
		}
		
	}
	
	public void showUserReservation(String username) {
		System.out.println("__________________________________");
		System.out.println("PRIKAZ REZERVACIJA ZA " + username);
		for(int reservationID: reservationsMap.keySet()) {
			Reservation r = reservationsMap.get(reservationID);
			if (r.getG().getUsername().equals(username)) {
				System.out.print(reservationID);
				System.out.println(r.toFileString());
				break;
			}
		}
	}


	public void saveReservations() {			//cuvanje u fajl rezervacija
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, false));
			pw.println("ID rezervacije, Korisnicko ime, Broj sobe,Tip sobe, Prvi dan, Poslednji dan, Dodatne usluge, Status, Cijena, Datum obrade");

			for (int id : reservationsMap.keySet()) {
				pw.print(id);
				pw.println(reservationsMap.get(id).toFileString());
			}
			pw.close();
		} catch (IOException e) {

		}
	}

	public void loadReservations(GuestManager gManager, RoomManager rManager, TypeOfRoomManager tManager) {			//citanje iz fajla rezervacija
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));
			String line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				int id = Integer.parseInt(tokens[0]);
				Reservation r = new Reservation();
				resID++;
				Guests g = gManager.guestsMap.get(tokens[1]);
				r.setG(g);
				TypeOfRoom type = tManager.typeRooms.get(tokens[2]);
				r.setType(type);
				if (tokens[3].equals("-")) {
					r.setR(null);
				}
				else {
				Room room = rManager.rooms.get(Integer.parseInt(tokens[3]));
				r.setR(room);

				}
			
				r.setStart(LocalDate.parse(tokens[4]));
				r.setEnd(LocalDate.parse(tokens[5]));
				
				ArrayList<AdditionalServices> a= new ArrayList<>();

				
				//if(tokens.length > 5) {
					String services = tokens[6];
					String[] serviceTokens = services.split("-");
					for(String service: serviceTokens) {
						AdditionalServices addService = new AdditionalServices(service);
						a.add(addService);
						r.setAddServices(a);
						r.addServiceToFileString(a);
					}
				//}
				StatusReservation status = StatusReservation.valueOf(tokens[7]);
				r.setStatusOfReservation(status);
				
				r.setTotalPrice(Float.parseFloat(tokens[8]));
				
				if (tokens[9].equals("null")) {
					r.setProcessing_date(null);
				}
				else {
				r.setProcessing_date(LocalDate.parse(tokens[9]));
				}
				reservationsMap.put(id, r);
			}
			br.close();
		} catch (IOException e) {

		}
	}

	
}
