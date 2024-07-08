package manage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import entity.AdditionalServices;

public class AdditionalServicesManager {
	
	String fileName;
	
	public AdditionalServicesManager(String file) {
		this.fileName = file;
	}
	
	public AdditionalServicesManager() {
		
	}
	
	public HashMap<String, AdditionalServices> services = new HashMap<>();

	public void addAdditionalService(String service, int price) {
		AdditionalServices a1 = new AdditionalServices(service, price);
		services.put(service, a1);
		this.saveServices();
	}
	
	public void removeAdditionalService(String service) {
		if (services.containsKey(service)) {
			services.remove(service);
			this.saveServices();
		}
	}
	
	public void changeService(String service, int price) {
		try {
		if (services.containsKey(service)) {
			AdditionalServices a = services.get(service);
			a.setPrice(price);
			services.put(service, a);
			this.saveServices();
			}
		}catch(Exception e) {
			
		}
	}
	
	

	public void saveServices() {			//cuvanje u fajl rezervacija
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(this.fileName, false));
			pw.println("Dodatna usluga, Cijena");

			for (String service : services.keySet()) {
				pw.println(services.get(service).toFileString());
			}
			pw.close();
		} catch (IOException e) {

		}
	}

	public void loadServices() {			//citanje iz fajla rezervacija
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));
			String line = br.readLine();

			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				String service = tokens[0];
				AdditionalServices a = new AdditionalServices();
				a.setService(tokens[0]);
				a.setPrice(Integer.parseInt(tokens[1]));

				services.put(service, a);
			}
			br.close();
		} catch (IOException e) {

		}
	}

	
	
	


}
