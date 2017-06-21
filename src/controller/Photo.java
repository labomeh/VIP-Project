package controller;

import java.sql.Date;
import java.time.LocalDate;

/**
 * 
 * @author Mehdi
 *
 */
public class Photo {
	private int idPhoto;
	private String place;
	private LocalDate date;
	private String fileName;

	public Photo() {
	}

	public Photo(int idPhoto, String place, LocalDate date) {
		this.idPhoto = idPhoto;
		this.place = place;
		this.date = date;
	}

	// Getters
	public int getIdPhoto() {
		return idPhoto;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getPlace() {
		return place;
	}

	public String getFileName() {
		return fileName;
	}

	// Setters
	public void setDate(Date date) {
		this.date = date.toLocalDate();
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
