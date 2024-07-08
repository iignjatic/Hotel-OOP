package entity;

import java.time.LocalDate;

import enums.Gender;

public class Guests extends Person {

	public String toFileString() {
		return ","+password+","+name+","+surname+","+gender+","+date+","+phoneNumber;
	}

	public Guests() {
		super();
	}

	public Guests(String username, String password, String name, String surname, Gender gender, LocalDate date,
			String phoneNumber) {
		super(username, password, name, surname, gender, date, phoneNumber);
	}
	
	

}
