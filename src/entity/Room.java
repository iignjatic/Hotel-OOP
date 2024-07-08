package entity;


import java.util.List;

import enums.StatusRoom;

public class Room{
	//atributi tip sobe, status sobe: zauzeto / slobodno/ spremanje
	
	protected TypeOfRoom typeRoom; 
	protected StatusRoom statusRoom;
	protected int roomID;
	protected List<String> criteria;
	
	
	public Room() {
	}
	
	public Room( TypeOfRoom typeRoom, StatusRoom statusRoom, int roomID, List<String> criteria) {
		this.typeRoom = typeRoom;
		this.statusRoom = statusRoom;
		this.roomID = roomID;
		this.criteria = criteria;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	
	public int getRoomID() {
		
		
		return roomID;
	}

	public TypeOfRoom getTypeRoom() {
		return typeRoom;
	}

	public void setTypeRoom(TypeOfRoom typeRoom) {
		this.typeRoom = typeRoom;
	}

	public StatusRoom getStatusRoom() {
		return statusRoom;
	}
	
	public void setStatusRoom(StatusRoom status) {
		this.statusRoom = status;
	}
	
	public List<String> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<String> criteria) {
		this.criteria = criteria;
	}

	public String listToString(List<String> criteria) {
		String result = "";
		for(String s: criteria) {
			result += s + ",";
		}
		result = result.substring(0, result.length()-1);
		return result;
	}
	
	

	public String toFileString() {
		return roomID+","+typeRoom.getType()+","+statusRoom+","+listToString(this.criteria);
	}

	

	
	
	
	
	
	
	
	

	
}
