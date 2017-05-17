/**
 * 
 */
package controller;
import java.time.*;

/**
 * 
 * @author Mehdi
 *
 */

public class Vip {
	private String name;
	private String[] surname;
	private LocalDate birthdate;
	private String birthplace;
	private char roleCode; // value A for Actor, R for Realisator, B for Both, N for None
	private Vip partner;
	
	public Vip(String name, String[] surname, LocalDate birthdate, String birthplace, char roleCode, int idPartner) {
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.birthplace = birthplace;
		this.roleCode = roleCode;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getSurname() {
		return surname;
	}

	public void setSurname(String[] surname) {
		this.surname = surname;
	}

	public char getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(char roleCode) {
		this.roleCode = roleCode;
	}

	public Vip getPartner() {
		return partner;
	}

	public void setPartner(Vip partner) {
		this.partner = partner;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public String getBirthplace() {
		return birthplace;
	}
}


