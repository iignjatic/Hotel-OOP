package entity;

import java.util.ArrayList;

public class Maid extends Workers{
	
	protected int numOfroomsToClean;
	protected ArrayList<Room> roomsToClean;

	
	public Maid(){
		super();
		this.numOfroomsToClean = 0;
		this.roomsToClean = new ArrayList<>();
	}
	
	public ArrayList<Room> getRoomsToClean() {
		return roomsToClean;
	}

	public void setRoomsToClean(ArrayList<Room> roomsToClean) {
		this.roomsToClean = roomsToClean;
	}

	public int getNumOfRoomsToClean() {
		return numOfroomsToClean;
	}

	public void setNumOfRoomsToClean(int roomsToClean) {
		this.numOfroomsToClean = roomsToClean;
	}


	
	
	
}
