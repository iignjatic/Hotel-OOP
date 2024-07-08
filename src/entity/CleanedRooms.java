package entity;

import java.time.LocalDate;

public class CleanedRooms {
	private Maid maid;
	private LocalDate date_of_cleaning;
	
	public CleanedRooms(Maid maid, LocalDate date_of_cleaning) {
		super();
		this.maid = maid;
		this.date_of_cleaning = date_of_cleaning;
	}

	public Maid getMaid() {
		return maid;
	}

	public void setMaid(Maid maid) {
		this.maid = maid;
	}

	public LocalDate getDate_of_cleaning() {
		return date_of_cleaning;
	}

	public void setDate_of_cleaning(LocalDate date_of_cleaning) {
		this.date_of_cleaning = date_of_cleaning;
	}
	
	public String toFileString() {
		return maid.getUsername() + "," + date_of_cleaning;
	}
	
}
