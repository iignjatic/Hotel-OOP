package entity;

import java.time.LocalDate;

import enums.Function;
import enums.Gender;
import enums.LevelOfEducation;


public class Workers extends Person {
	

	//atributi: nivo strucne spreme, staz, plata
	
	protected LevelOfEducation level;
	protected int internship;
	protected float salary;
	protected Function function;
	
	
	public LevelOfEducation getLevel() {
		return level;
	}
	
	public void setLevel(LevelOfEducation level) {
		this.level = level;
	}
	
	public int getInternship() {
		return internship;
	}
	
	public void setInternship(int internship) {
		this.internship = internship;
	}
	
	public float getSalary() {
		return salary;
	}
	
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Workers() {}

	public Workers( String username, String password, String name, String surname, Gender gender,
			LocalDate date, String phoneNumber, Function role, LevelOfEducation level, int internship) {
		super(username, password, name, surname, gender, date, phoneNumber);
		int level_for_salary = 1;
		if(level == LevelOfEducation.I) {
			level_for_salary = 1;
		}if(level == LevelOfEducation.II) {
			level_for_salary = 2;
		}if(level == LevelOfEducation.III) {
			level_for_salary = 3;
		}if(level == LevelOfEducation.IV) {
			level_for_salary = 4;
		}if(level == LevelOfEducation.V) {
			level_for_salary = 5;
		}if(level == LevelOfEducation.VI) {
			level_for_salary = 6;
		}if(level == LevelOfEducation.VII) {
			level_for_salary = 7;
		}if(level == LevelOfEducation.VIII) {
			level_for_salary = 8;
		}
		this.salary = 300 + internship*50 + level_for_salary * 100;
		this.level = level;
		this.internship = internship;
		this.function = role;
	}

	public String toFileString() {		//funkcija za upisivanje u fajl
		return  ","+password+","+name+","+surname+","+gender+","+date+","
			+phoneNumber+","+level+","+internship+","+salary+","+function;
	}
	
	
	
	
}
