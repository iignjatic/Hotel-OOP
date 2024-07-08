package entity;
import java.time.LocalDate;
import java.util.HashMap;



public class PriceList {
	//atributi: pocetak i kraj vazenja, cijene za tip sobe i dodatne usluge
	protected int id;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	protected LocalDate startDate;
	protected LocalDate endDate;
	protected HashMap<String, AdditionalServices> services;
	protected HashMap<String,TypeOfRoom> typeOfRooms;
	

	
	
	public PriceList(LocalDate startDate, LocalDate endDate, HashMap<String, TypeOfRoom> typeOfRooms,
			HashMap<String, AdditionalServices> service) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.services = service;
		this.typeOfRooms = typeOfRooms;
	}
	
	public PriceList() {
		
	}
	
	public HashMap<String, AdditionalServices> getServices() {
		return services;
	}
	public void setServices(HashMap<String,AdditionalServices> service) {
		this.services = service;
	}
	public HashMap<String,TypeOfRoom> getTypeOfRooms() {
		return typeOfRooms;
	}
	public void setTypeOfRooms(HashMap<String,TypeOfRoom> typeOfRooms) {
		this.typeOfRooms = typeOfRooms;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String toFileString() {
		String toFile = startDate.toString()+','+endDate.toString()+",";
		
		for(String type: typeOfRooms.keySet()) {
			toFile += type + ':' + typeOfRooms.get(type).getPrice()+'-';
		}
		toFile = toFile.substring(0, toFile.length()-1)+',';
		
		for(String service: services.keySet()) {
			toFile += service+':'+services.get(service).getPrice()+'-';
		}
		toFile = toFile.substring(0, toFile.length()-1);

		
		
		return toFile;
	}
	
	
	

}
