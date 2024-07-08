package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.CleanedRooms;
import entity.Maid;

public class CleanedRoomsManager {
	public ArrayList<CleanedRooms> cleanedRooms = new ArrayList<>();

	String fileName = "./data/datumiCiscenja.csv";
	
	public void saveDatesOfCleaning() {			
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, false));
			pw.println("Sobarica, Datum");
	
			for (CleanedRooms cleaned : cleanedRooms) {
				pw.println(cleaned.toFileString());
			}
			pw.close();
		} catch (IOException e) {
	
		}
	}
	
	public void loadDatesOfCleaning(WorkerManager wm) {	
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));
			String line = br.readLine();
	
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				String username = tokens[0];
				LocalDate date = LocalDate.parse(tokens[1]);
				CleanedRooms cr = new CleanedRooms((Maid)wm.workersMap.get(username), date);
				cleanedRooms.add(cr);
			}
			br.close();
		} catch (IOException e) {
	
		}
	}
	}

	
	
	

