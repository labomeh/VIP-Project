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
public class Photo {
	private int year;
	private String place;
	
	public Photo(int year, String place) {
		this.year = year;
		this.place = place;
	}
	
	public int getYear() {
		return year;
	}
	public String getPlace() {
		return place;
	}
}
