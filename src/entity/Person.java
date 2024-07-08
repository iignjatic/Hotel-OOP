package entity;
import java.time.LocalDate;

import enums.Gender;

public abstract class Person {
	protected String username;
	protected String password;
	protected String name;
	protected String surname;
	protected Gender gender;
	protected LocalDate date;
	protected String phoneNumber;
	

	//atributi:ime, prezime, pol, datum rodj, telefon, adresa, k ime, lozinka = broj pasosa
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Person() {}

	public Person( String username, String password, String name, String surname, Gender gender,
			LocalDate date, String phoneNumber) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.date = date;
		this.phoneNumber = phoneNumber;
	}
	
	

}
