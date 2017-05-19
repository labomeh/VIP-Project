/**
 * 
 */
package controller;

import java.time.LocalDate;

/**
 * @author Simon
 *
 */
public class Event {

	private int idVip1;
	private int idVip2;
	private LocalDate weddingDate;
	private String weddingPlace;
	private LocalDate divorceDate;

	public Event(int idVip1, LocalDate weddingDate, int idVip2, String weddingPlace, LocalDate divorceDate) {
		this.idVip1 = idVip1;
		this.idVip2 = idVip2;
		this.weddingDate = weddingDate;
		this.weddingPlace = weddingPlace;
		this.divorceDate = divorceDate;
	}
}
