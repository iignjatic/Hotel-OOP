package entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import enums.StatusReservation;
import manage.PriceListManager;



public class Reservation {
	//status atribut: na cekanju, potvrdjena, odbijena, otkazana
	//cijena rezervacije
	//dodatne uskuge: dorucak, rucak, vecera

	private int id; 
	
	protected Guests g;
	protected Room r;
	protected TypeOfRoom type;
	protected LocalDate start;
	protected LocalDate end;
	protected ArrayList<AdditionalServices> addServices;
	protected StatusReservation statusOfReservation;
	protected float totalPrice;
	protected LocalDate processing_date;
	

	
	public Reservation() {
	}
	
	public Reservation(Guests g, TypeOfRoom t, Room r, LocalDate start, LocalDate end, ArrayList<AdditionalServices> addServices
			,StatusReservation status, LocalDate processing_date, Float price) {
		this.g = g;
		this.r = r;
		this.type = t;
		this.start = start;
		this.end = end;
		this.addServices = addServices;
		this.statusOfReservation = status;
		this.processing_date = processing_date;
		this.totalPrice = price;
		
		/*Period period = Period.between(start, end);
		int numOfDays = period.getDays();
		for (AdditionalServices service : addServices){
			priceOfService += service.getPrice();
		}
		this.totalPrice = (t.getPrice() + priceOfService)*numOfDays;*/
	}



	public int getId() {
		return id;
	}
	
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	
	public TypeOfRoom getType() {
		return type;
	}



	public void setType(TypeOfRoom type) {
		this.type = type;
	}
	
	


	public StatusReservation getStatusOfReservation() {
		return statusOfReservation;
	}



	public void setStatusOfReservation(StatusReservation statusOfReservation) {
		this.statusOfReservation = statusOfReservation;
	}



	

	public Guests getG() {
		return g;
	}

	public void setG(Guests g) {
		this.g = g;
	}

	public Room getR() {
		return r;
	}

	public void setR(Room r) {
		this.r = r;
	}
	
	public ArrayList<AdditionalServices> getAddServices() {
		return addServices;
	}
	

	public void setAddServices(ArrayList<AdditionalServices> addServices) {
		this.addServices = addServices;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	public LocalDate getProcessing_date() {
		return processing_date;
	}

	public void setProcessing_date(LocalDate processing_date) {
		this.processing_date = processing_date;
	}

	public String toFileString() {
		String toFile = ","+ g.getUsername() +","+type.getType()+',';
		
		if (r == null) {
			toFile += "-" +","+start+","+end+","+this.addServiceToFileString(addServices)+','+statusOfReservation+','+totalPrice +','+processing_date;
			return toFile;
		}
		else {
			toFile += r.getRoomID()+","+start+","+end+","+this.addServiceToFileString(addServices)+','+statusOfReservation +','+totalPrice+','+processing_date;
			return toFile;
		
		}
	}
	
	public String addServiceToFileString(ArrayList<AdditionalServices> a) {
		String stringServices = "";
		if(a != null) {
			for(AdditionalServices service: a) {
				if(service != null) {
					stringServices += service.getService() + "-";
			
				}

			}
			if(stringServices != "") {
				stringServices = stringServices.substring(0, stringServices.length()-1);

			}

		}
		return stringServices;
	}

	

	
}
