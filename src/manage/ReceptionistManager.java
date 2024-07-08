package manage;


import entity.Reservation;
import entity.Room;
import enums.StatusReservation;
import enums.StatusRoom;

public class ReceptionistManager {
	//rezervacije(potvrdjivanje ili odbijanje) validacijom sobe
	//uvid u stanje soba
	//check in i check out gostiju
	//dodavanje gostiju u sistem ako ih vec nema
	//vidi sve podatke o dnevnim dolascima/ odlascima i zauzetosti
	//dodatne usluge za gosta
	//kreira se cijena rezervacije
	
	protected ReservationManager reservations;
	protected RoomManager rooms;
	protected AdditionalServicesManager services;
	protected MaidManager maids;
	
	public ReceptionistManager(ReservationManager reservations, RoomManager rooms, AdditionalServicesManager services, MaidManager maids) {
		super();
		this.reservations = reservations;
		this.rooms = rooms;
		this.services = services;
		this.maids = maids;
	}

	public ReservationManager getReservations() {
		return reservations;
	}

	public void setReservations(ReservationManager reservations) {
		this.reservations = reservations;
	}
	
	
	public RoomManager getRooms() {
		return rooms;
	}

	public void setRooms(RoomManager rooms) {
		this.rooms = rooms;
	}
	
	

	public AdditionalServicesManager getServices() {
		return services;
	}

	public void setServices(AdditionalServicesManager services) {
		this.services = services;
	}

	public void viewReservations() {
		reservations.showReservations();
	}
	
	public Boolean isAvailableTypeOfRoom(Reservation r) {
		if(reservations.isRoomTypeAvailable(r.getType(),r.getStart(), r.getEnd(), rooms)) {
			r.setStatusOfReservation(StatusReservation.POTVRDJENA);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void checkIn(Reservation r, Room room) {
		room.setStatusRoom(StatusRoom.zauzeto);
		r.setR(room);
		rooms.saveRooms();
		reservations.saveReservations();
		
		}
		
	
	
	public void checkOut(Reservation r) {
		r.getR().setStatusRoom(StatusRoom.spremanje);
		maids.someoneClean(r.getR());
		reservations.saveReservations();
		rooms.saveRooms();

	}

	
	
	
}
