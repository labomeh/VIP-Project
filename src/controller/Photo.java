/**
 * 
 */
package controller;

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

	public int getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Photo [idPhoto=" + idPhoto + ", place=" + place + ", date=" + date + "]";
	}
	
	
}
