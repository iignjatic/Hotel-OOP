package manage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import entity.TypeOfRoom;

import entity.AdditionalServices;
import entity.PriceList;




public class PriceListManager {
	
	public HashMap<Integer, PriceList> pricelistMap = new HashMap<>();			//lista cjenovnika
	protected static int id=1;
	public String fileName;
	
	public PriceListManager(String file) {
		this.fileName = file;
	}
	
	public PriceListManager() {
		
	}

	
	public void createPricelist(LocalDate start,LocalDate end,
		HashMap<String, TypeOfRoom> typeOfRooms, HashMap<String,AdditionalServices> addServices) {
		id++;

		PriceList p = new PriceList(start, end, typeOfRooms, addServices);
		pricelistMap.put(id, p);
		this.savePricelist();
	}
	
	public void changePricelist(Integer id,LocalDate start,LocalDate end,
		HashMap<String, TypeOfRoom> typeOfRooms, HashMap<String,AdditionalServices> addServices) {
		PriceList p = pricelistMap.get(id);
		p.setStartDate(start);
		p.setEndDate(end);
		p.setServices(addServices);
		p.setTypeOfRooms(typeOfRooms);
		
		pricelistMap.put(id, p);
		this.savePricelist();
		
	}
		

	public void removePricelist(int id) {
		if(pricelistMap.containsKey(id)){
			pricelistMap.remove(id);
			this.savePricelist();
		}
	}
	

	public void savePricelist() {			//cuvanje u fajl cjenovnika
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, false));
			pw.println("Datum pocetka vazenja, Datum kraja vazenja, Dodatne usluge, Sobe");

			for (Integer id : pricelistMap.keySet()) {
				pw.println(id +"," +pricelistMap.get(id).toFileString());
			}
			pw.close();
		} catch (IOException e) {

		}
	}

	public void loadPriceList() {		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));
			String line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				int id = Integer.parseInt(tokens[0]);
				PriceList p = new PriceList();
				LocalDate start = LocalDate.parse(tokens[1]);
				LocalDate end = LocalDate.parse(tokens[2]);
				p.setId(id);
				p.setStartDate(start);
				p.setEndDate(end);
				
				String[] rooms = tokens[3].split("-");
				HashMap<String, TypeOfRoom> types = new HashMap<>();
				for (String room: rooms) {
					String[] type = room.split(":");
					TypeOfRoom t = new TypeOfRoom(type[0], Float.parseFloat(type[1]));
					types.put(type[0], t);
				}
				
				p.setTypeOfRooms(types);
						
				String[] addServices = tokens[4].split("-");
				HashMap<String, AdditionalServices> services = new HashMap<>();
				for (String service: addServices) {
					String[] addService = service.split(":");
					AdditionalServices a = new AdditionalServices(addService[0], Integer.parseInt(addService[1]));
					services.put(addService[0], a);
				}
					p.setServices(services);
					
					pricelistMap.put(id, p);
					id++;
			}
			br.close();
		} catch (IOException e) {

		}
	}

}
