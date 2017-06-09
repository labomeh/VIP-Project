/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.App;
import controller.Vip;

/**
 * @author Simon
 *
 */
public class DAOVip {

	private static Connection connexion;

	public DAOVip() {
		this.connexion = App.getConnection();
	}

	public List<Vip> getVip() throws SQLException {
		List<Vip> vipList = new ArrayList<>();
		String query = "select * from VIP";
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		while (rset.next()) {
			int idVip = rset.getInt(1);
			String name = rset.getString(2);
			String surname1 = rset.getString(3);
			String surname2 = rset.getString(4);
			String surname[] = { surname1, surname2 };
			LocalDate birthdate = rset.getDate(5).toLocalDate();
			String birthplace = rset.getString(6);
			char roleCodeVIP = rset.getString(7).charAt(0);
			int idPartner;
			if (rset.getObject(8) == null) {
				idPartner = -1;
			} else {
				idPartner = rset.getInt(8);
			}

			Vip vip = new Vip(idVip, name, surname, birthdate, birthplace, roleCodeVIP, idPartner);
			vipList.add(vip);
		}
		rset.close();
		stmt.close();
		return vipList;

	} // getVip method

	public Vip getPartner(Vip vip) throws SQLException {
		String query = "select * from VIP where idVIP = ? " ;
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, vip.getIdPartner());
		ResultSet rset = pstmt.executeQuery();
		rset.next();
		int idVip = rset.getInt(1);
		String name = rset.getString(2);
		String surname1 = rset.getString(3);
		String surname2 = rset.getString(4);
		String surname[] = { surname1, surname2 };
		LocalDate birthdate = rset.getDate(5).toLocalDate();
		String birthplace = rset.getString(6);
		char roleCodeVIP = rset.getString(7).charAt(0);
		int idPartner = rset.getInt(8);
		Vip partner = new Vip(idVip, name, surname, birthdate, birthplace, roleCodeVIP, idPartner);
		rset.close();
		pstmt.close();
		return partner;
	}

	public List<String> getVipNationalities(Vip vip) throws SQLException {
		int idVip = vip.getIdVip();
		List<String> vipNationalities = new ArrayList<>();
		String query = "SELECT country.country FROM country, VIP, nationality WHERE VIP.idVIP=? and nationality.idVIP=? and country.country=nationality.country";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, idVip);
		pstmt.setInt(2, idVip);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()) {
			String nationality = rset.getString(1);
			vipNationalities.add(nationality);
		}
		rset.close();
		pstmt.close();
		return vipNationalities;

	} // getVipNationalities method

	public List<Integer> getVipPlayedMovies(Vip vip) throws SQLException {
		int idVip = vip.getIdVip();
		List<Integer> vipPlayedMovies = new ArrayList<>();
		String query = "SELECT movieVisa FROM playing WHERE idVIP=?";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, idVip);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()) {
			int playedMovie = rset.getInt(1);
			vipPlayedMovies.add(playedMovie);
		}
		rset.close();
		pstmt.close();
		return vipPlayedMovies;

	} // getVipPlayedMovies method

	public List<Integer> getVipDirectedMovies(Vip vip) throws SQLException {
		int idVip = vip.getIdVip();
		List<Integer> vipDirectedMovies = new ArrayList<>();
		String query = "SELECT movieVisa FROM directing WHERE idVIP=?";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, idVip);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()) {
			int directedMovie = rset.getInt(1);
			vipDirectedMovies.add(directedMovie);
		}
		rset.close();
		pstmt.close();
		return vipDirectedMovies;

	} // getVipDirectedMovies method

	public void addNewVip(Vip vip, List<String> nationalities) throws SQLException {
		String queryNewVip = "INSERT INTO VIP VALUES(idVIP, ?, ?, ?, ?, ?, null, null);";
		PreparedStatement pstmtNewVip = connexion.prepareStatement(queryNewVip);
		pstmtNewVip.setString(1, vip.getName());
		pstmtNewVip.setString(2, vip.getSurname()[0]);
		pstmtNewVip.setString(3, vip.getSurname()[1]);
		pstmtNewVip.setString(4, vip.getBirthdate().toString());
		pstmtNewVip.setString(5, vip.getBirthplace());
		pstmtNewVip.executeUpdate();

		String queryGetCurrentId = "SELECT MAX(idVIP) FROM VIP";
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery(queryGetCurrentId);
		rset.next();
		int idVIP = rset.getInt(1);

		for (String nat : nationalities) {
			String queryNewNat = "INSERT INTO nationality VALUES(?, ?);";
			PreparedStatement pstmtNewNat = connexion.prepareStatement(queryNewNat);
			pstmtNewNat.setInt(1, idVIP);
			pstmtNewNat.setString(2, nat.toString());
			pstmtNewNat.executeUpdate();
			pstmtNewNat.close();
		}

		stmt.close();
		pstmtNewVip.close();
		// connexion.close();

	} // addNewVip method

} // DAOVip class
