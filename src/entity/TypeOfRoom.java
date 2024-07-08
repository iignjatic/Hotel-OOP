package entity;


public class TypeOfRoom {
	protected String type;
	protected float price;
	
	public TypeOfRoom(String type, float price){
		this.type = type;
		this.price = price;
	}
	
	public TypeOfRoom() {
		
	}

	public TypeOfRoom(TypeOfRoom value) {
		this.type = value.type;
		this.price = value.price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
	
	public String toFileString() {
		return type+','+price;
	}

	
}
