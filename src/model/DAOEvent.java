/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import controller.Event;
import controller.Genre;

/**
 * @author Simon
 *
 */
public class DAOEvent {

	private final Connection connexion;

	public DAOEvent(Connection connexion) {
		this.connexion = connexion;
	}

	public void getEvents(List<Event> eventList) throws SQLException {
		String requete = "select * from event";
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery(requete);
		while (rset.next()) {
			int idVip1 = rset.getInt(1);
			LocalDate weddingDate = rset.getDate(2).toLocalDate();
			int idVip2 = rset.getInt(3);
			String weddingPlace = rset.getString(4);
			LocalDate divorceDate = rset.getDate(5).toLocalDate();
			Event event = new Event(idVip1, weddingDate, idVip2, weddingPlace, divorceDate);
			eventList.add(event);
		}
		rset.close();
		stmt.close();
	} // getEvents method

} // DAOVip class
