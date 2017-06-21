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

	// Getters
	public int getIdVip1() {
		return idVip1;
	}

	public int getIdVip2() {
		return idVip2;
	}

	public LocalDate getWeddingDate() {
		return weddingDate;
	}

	public String getWeddingPlace() {
		return weddingPlace;
	}

	public LocalDate getDivorceDate() {
		return divorceDate;
	}
}
