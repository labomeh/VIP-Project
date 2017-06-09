/**
 * 
 */
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
	private char roleCode; // value A for Actor, D for Director, B for Both, N for None
	private int idPartner;
	
	public Vip() {
	}
	
	public Vip(int idVip, String name, String[] surname, LocalDate birthdate, String birthplace, char roleCode, int idPartner) {
		this.idVip = idVip;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.birthplace = birthplace;
		this.roleCode = roleCode;
		this.idPartner = idPartner;
	}

	public int getIdVip() {
		return idVip;
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

	public int getIdPartner() {
		return idPartner;
	}

	public void setIdPartner(int idPartner) {
		this.idPartner = idPartner;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate=birthdate.toLocalDate();
	}
	
	public String getBirthplace() {
		return birthplace;
	}
	
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
}


