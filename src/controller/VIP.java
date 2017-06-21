package controller;

import java.sql.Date;
import java.time.*;

/**
 * 
 * @author Mehdi
 *
 */

public class Vip {
	private int idVip;
	private String name;
	private String[] surname;
	private LocalDate birthdate;
	private String birthplace;
	private char roleCode;
	// roleCode has the value A for Actor, D for Director, B for Both, N for
	// None
	private int idPartner;

	public Vip() {
	}

	public Vip(int idVip, String name, String[] surname, LocalDate birthdate, String birthplace, char roleCode,
			int idPartner) {
		this.idVip = idVip;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.birthplace = birthplace;
		this.roleCode = roleCode;
		this.idPartner = idPartner;
	}

	// Getters
	public int getIdVip() {
		return idVip;
	}

	public String getName() {
		return name;
	}

	public String[] getSurname() {
		return surname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public char getRoleCode() {
		return roleCode;
	}

	public int getIdPartner() {
		return idPartner;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String[] surname) {
		this.surname = surname;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate.toLocalDate();
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
}
