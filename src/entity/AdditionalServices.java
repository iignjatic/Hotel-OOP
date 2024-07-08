package entity;


public class AdditionalServices {
	
	protected String service;
	protected int price;
	
	public AdditionalServices(String service, int price){
		this.service = service;
		this.price = price;
	}
	




	public AdditionalServices(String service){
		this.service = service;
	}

	public AdditionalServices() {
	}
	
	public AdditionalServices(AdditionalServices value) {
		this.service = value.service;
		this.price = value.price;
	}





	public String getService() {
		return this.service;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	public String toFileString() {
		return service+','+price;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdditionalServices other = (AdditionalServices) obj;
		return price == other.price && service == other.service;
	}
	
	

	

	
}
