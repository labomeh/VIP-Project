/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.App;
import controller.Event;
import controller.Vip;

/**
 * @author Simon
 *
 */
public class DAOEvent {

	private final Connection connection;

	public DAOEvent() {
		this.connection = App.getConnection();
	}

	public List<Event> getEvents() throws SQLException {
		List<Event> eventList = new ArrayList<>();
		String requete = "select * from event";
		Statement stmt = connection.createStatement();
		ResultSet rset = stmt.executeQuery(requete);
		while (rset.next()) {
			int idVip1 = rset.getInt(1);
			LocalDate weddingDate = rset.getDate(2).toLocalDate();
			int idVip2 = rset.getInt(3);
			String weddingPlace = rset.getString(4);
			LocalDate divorceDate;
			if(rset.getDate(5)==null){
				divorceDate= null;
			}
			else{
				divorceDate = rset.getDate(5).toLocalDate();
			}
			 
			Event event = new Event(idVip1, weddingDate, idVip2, weddingPlace, divorceDate);
			eventList.add(event);
		}
		rset.close();
		stmt.close();
		return eventList;
	} // getEvents method
	
	public Event getMaritialStatus(Vip vip) throws SQLException{
		String query = "select * from event where (idVIP1=? or idVIP2=?) and weddingDate=(select max(weddingDate) from event where idVIP1=? or idVIP2=?)";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, vip.getIdVip());
		pstmt.setInt(2, vip.getIdVip());
		pstmt.setInt(3, vip.getIdVip());
		pstmt.setInt(4, vip.getIdVip());
		ResultSet rset=pstmt.executeQuery();
		if(rset.next()){
			int idVip1 = rset.getInt(1);
			LocalDate weddingDate = rset.getDate(2).toLocalDate();
			int idVip2 = rset.getInt(3);
			String weddingPlace = rset.getString(4);
			LocalDate divorceDate;
			if(rset.getDate(5)==null){
				divorceDate= null;
			}
			else{
				divorceDate = rset.getDate(5).toLocalDate();
			}
			 
			Event event = new Event(idVip1, weddingDate, idVip2, weddingPlace, divorceDate);
			return event;
		}
		return null;
	}
	
	public void addWedding(Event event) throws SQLException{
		String requete = "INSERT INTO event VALUES(?, ?, ?, ?, ?);";
		PreparedStatement pstmt = connection.prepareStatement(requete);
		pstmt.setInt(1, event.getIdVip1());
		pstmt.setDate(2, Date.valueOf(event.getWeddingDate()));
		pstmt.setInt(3, event.getIdVip2());
		pstmt.setString(4, event.getWeddingPlace());
		pstmt.setDate(5, null);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void addDivorce(Date divorceDate, int idVip1, int idVip2) throws SQLException{
		String requete = "UPDATE event SET divorceDate=? WHERE (idVip1=? AND idVip2=?) or (idVip2=? AND idVip1=?);";
		PreparedStatement pstmt = connection.prepareStatement(requete);
		pstmt.setDate(1, divorceDate);
		pstmt.setInt(2, idVip1);
		pstmt.setInt(3, idVip2);
		pstmt.setInt(4, idVip1);
		pstmt.setInt(5, idVip2);
		pstmt.executeUpdate();
		pstmt.close();
	}

} // DAOEvent class
