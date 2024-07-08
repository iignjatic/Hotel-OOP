package manage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import entity.Guests;
import entity.Reservation;
import enums.Gender;
import enums.StatusReservation;

public class GuestManager {
	//bira sobu i salje zahtjev za rezervaciju sobe koji se salje recepcioneru
	//inicijalno je na cekanju
	//vidi svoje rezervacije
	//moze otkazati rezervaciju gubi novac
	//ako recepcioner odbije rezervaciju refundira mu se novac
	//bira dodatne usluge
	
	public HashMap<String, Guests> guestsMap = new HashMap<>();
	protected ReservationManager reservations;
	protected RoomManager rooms;
	String fileName;
	
	public GuestManager(String file) {
		this.fileName = file;
	}
	
	public GuestManager() {
		
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
	
	

	public GuestManager(ReservationManager reservations, RoomManager rooms, String file) {
		super();
		this.reservations = reservations;
		this.rooms = rooms;
		this.fileName = file;
	}
	
	public void cancelReservation(Reservation r) {
		r.setStatusOfReservation(StatusReservation.OTKAZANA);
		
	}

	public void addGuests( String username, String password, String name, String surname,
			Gender gender, LocalDate date, String phone) {
		Guests g = new Guests(username, password, name, surname, gender, date, phone);
		
		guestsMap.put(username, g);
		
	}
	
	public void changeGuests( String username, String password, String name, String surname,
			Gender gender, LocalDate date, String phone) {
		if(guestsMap.containsKey(username)) {
			Guests g = guestsMap.get(username);
			g.setPassword(password);
			g.setName(name);
			g.setSurname(surname);
			g.setGender(gender);
			g.setDate(date);
			g.setPhoneNumber(phone);
			
			guestsMap.put(username, g);
			this.saveGuests();
		}
		
	}
	
	public void removeGuest(String username) {
		try {
			guestsMap.remove(username);
			this.saveGuests();
		}catch (Exception e){
			System.out.println("Ne postoji korisnik sa tim id-jem");
		}
	}
		

	public void showGuests() {
		System.out.println("Trenutna lista gostiju: ");
		System.out.println("-----------------------");
		for (String username : guestsMap.keySet()) {
			Guests guest = guestsMap.get(username);
			System.out.println(guest.getName() + " " + guest.getSurname());
			
		}
		System.out.println("________________________");

	}
	
	
	
	public void loadGuests() {							//ucitavanje iz fajla
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));
			String line = null;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				Guests g = new Guests();
				g.setUsername(tokens[0]);
				g.setPassword(tokens[1]);
				g.setName(tokens[2]);
				g.setSurname(tokens[3]);
				g.setGender(Gender.valueOf(tokens[4]));
				g.setDate(LocalDate.parse(tokens[5]));
				g.setPhoneNumber(tokens[6]);
				
	
				guestsMap.put(tokens[0], g);
				
			}
			br.close();
	
		} catch (IOException e) {
	
		}
	
	}
	
	public void saveGuests() {		//ucitavanje u fajl iz workera
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, false));
			pw.println("Username,Lozinka,Ime,Prezime,Pol,Datum Rodj,Telefon");
			//header 
			for(String username: guestsMap.keySet()) {
				pw.print(username);
				pw.println(guestsMap.get(username).toFileString());
			}
			
			pw.close();
		}catch(IOException e) {
			
		}
	}
	}
