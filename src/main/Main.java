package main;
import manage.*;
import view.login;


public class Main {

	public static void main(String[] args) {
		//menadzeri
		WorkerManager workerManager1 = new WorkerManager("./data/zaposleni.csv");
		RoomManager roomManager1 = new RoomManager("./data/sobe.csv");
		AdditionalServicesManager addServicesManager1 = new AdditionalServicesManager("./data/dodatneUsluge.csv");
		PriceListManager priceListManager1 = new PriceListManager("./data/cjenovnik.csv");
		ReservationManager reservationManager = new ReservationManager(roomManager1, "./data/rezervacije.csv");
		TypeOfRoomManager typeOfRoomManager = new TypeOfRoomManager("./data/tipoviSoba.csv");
		MaidManager maidManager = new MaidManager(workerManager1);
		AdministratorManager admin = new AdministratorManager();
		GuestManager guestManager1 = new GuestManager(reservationManager, roomManager1,"./data/gosti.csv");
		ReceptionistManager receptionist = new ReceptionistManager(reservationManager, roomManager1, addServicesManager1, maidManager);
		CleanedRoomsManager cleanedRooms = new CleanedRoomsManager();
		
		
		//ucitavanje
		workerManager1.loadUsers();
		guestManager1.loadGuests();
		typeOfRoomManager.loadTypeOfRoom();
		roomManager1.loadRooms(maidManager, typeOfRoomManager);
		reservationManager.loadReservations(guestManager1, roomManager1, typeOfRoomManager);
		addServicesManager1.loadServices();
		priceListManager1.loadPriceList();
		cleanedRooms.loadDatesOfCleaning(workerManager1);
		
		login login = new login(guestManager1, workerManager1, typeOfRoomManager, addServicesManager1, 
        		reservationManager, roomManager1, receptionist,priceListManager1, cleanedRooms, admin);
        login.setVisible(true);


		
		//cuvanje u fajlove
		addServicesManager1.saveServices();
		priceListManager1.savePricelist();
		typeOfRoomManager.saveTypeOfRooms();
		workerManager1.saveUsers();
		guestManager1.saveGuests();
		roomManager1.saveRooms();
		reservationManager.saveReservations();
		cleanedRooms.saveDatesOfCleaning();
		

	}

}
