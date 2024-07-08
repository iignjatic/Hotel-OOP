package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Admin;
import entity.Maid;
import entity.Receptionist;
import entity.Workers;
import enums.Function;
import enums.Gender;
import enums.LevelOfEducation;

public class WorkerManager {
	String fileName;
	
	public WorkerManager(String fileName) {
		this.fileName = fileName;
	}
	public WorkerManager() {
		
	}
	
	public HashMap<String, Workers> workersMap = new HashMap<>();			//lista zaposlenih

	public void addWorker(String username, String password, String name, String surname,
			Gender gender, LocalDate date, String phone, LevelOfEducation level, int internship,
			Function role) {
		Workers w = new Workers(username, password, name, surname, gender, date, phone, role, level, internship);

		workersMap.put(username,w);
		this.saveUsers();

	}
	
	public void changeWorker( String username, String password, String name, String surname,
			Gender gender, LocalDate date, String phone, LevelOfEducation level, int internship,
			Function role) {
		if(workersMap.containsKey(username)) {
			Workers w = workersMap.get(username);
			w.setPassword(password);
			w.setName(name);
			w.setSurname(surname);
			w.setGender(gender);
			w.setDate(date);
			w.setPhoneNumber(phone);
			w.setLevel(level);
			w.setInternship(internship);
			w.setFunction(role);
			
	
			workersMap.put(username,w);
			this.saveUsers();
		}

	}
	
	public void removeWorker(String username) {
			try {
				workersMap.remove(username);
				this.saveUsers();
			}catch (Exception e){
				System.out.println("Ne postoji korisnik sa tim id-jem");
			}
		}
			
	
	public List<Workers> showWorkers() {
		List<Workers> workers = new ArrayList<Workers>();
		for (Workers w : workersMap.values()) {
			workers.add(w);
		}
		return workers;
	}
	
	
	
	public void loadUsers() {							//ucitavanje iz fajla
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));
			String line = null;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				if (Function.valueOf(tokens[10]) == Function.receptionist) {
					Receptionist r = new Receptionist();
				r.setUsername(tokens[0]);
				r.setPassword(tokens[1]);
				r.setName(tokens[2]);
				r.setSurname(tokens[3]);
				r.setGender(Gender.valueOf(tokens[4]));
				r.setDate(LocalDate.parse(tokens[5]));
				r.setPhoneNumber(tokens[6]);
				r.setLevel(LevelOfEducation.valueOf(tokens[7]));
				r.setInternship(Integer.parseInt(tokens[8]));
				r.setSalary(Float.parseFloat(tokens[9]));
				r.setFunction(Function.valueOf(tokens[10]));

				workersMap.put(tokens[0], r);
				
				}
				if (Function.valueOf(tokens[10]) == Function.maid) {
					Maid m = new Maid();
					m.setUsername(tokens[0]);
					m.setPassword(tokens[1]);
					m.setName(tokens[2]);
					m.setSurname(tokens[3]);
					m.setGender(Gender.valueOf(tokens[4]));
					m.setDate(LocalDate.parse(tokens[5]));
					m.setPhoneNumber(tokens[6]);
					m.setLevel(LevelOfEducation.valueOf(tokens[7]));
					m.setInternship(Integer.parseInt(tokens[8]));
					m.setSalary(Float.parseFloat(tokens[9]));
					m.setFunction(Function.valueOf(tokens[10]));

					workersMap.put(tokens[0], m);
				}
				else {
					Admin a = new Admin();
					a.setUsername(tokens[0]);
					a.setPassword(tokens[1]);
					a.setName(tokens[2]);
					a.setSurname(tokens[3]);
					a.setGender(Gender.valueOf(tokens[4]));
					a.setDate(LocalDate.parse(tokens[5]));
					a.setPhoneNumber(tokens[6]);
					a.setLevel(LevelOfEducation.valueOf(tokens[7]));
					a.setInternship(Integer.parseInt(tokens[8]));
					a.setSalary(Float.parseFloat(tokens[9]));
					a.setFunction(Function.valueOf(tokens[10]));

					workersMap.put(tokens[0], a);
				}
			}
			br.close();

		} catch (IOException e) {

		}

	}
	
	public void saveUsers() {		//ucitavanje u fajl iz workera
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, false));
			pw.println("Username,Lozinka,Ime,Prezime,Pol,Datum Rodj,Telefon,Stepen spreme,GodineStaza,Plata,Uloga");
			for(String username: workersMap.keySet()) {
				pw.print(username);
				pw.println(workersMap.get(username).toFileString());
			}
			
			pw.close();
		}catch(IOException e) {
			
		}
	}
}
