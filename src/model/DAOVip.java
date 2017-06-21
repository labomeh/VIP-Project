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
import controller.Movie;
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

	
	/**
	 * Get the vip knowing his id
	 * 
	 * @param idVIP
	 * @return vip corresponding to the id parameter
	 * @throws SQLException
	 */
	public Vip getVip(int idVIP) throws SQLException {
		Vip vip;
		String query = "SELECT * FROM VIP WHERE idVIP=?;";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, idVIP);
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
		int idPartner;
		if (rset.getObject(8) == null) {
			idPartner = -1;
		} else {
			idPartner = rset.getInt(8);
		}
		vip = new Vip(idVip, name, surname, birthdate, birthplace, roleCodeVIP, idPartner);
		rset.close();
		pstmt.close();
		return vip;
	} // getVip method

	/**
	 * Get the vip's partner knowing his id
	 * 
	 * @param idVIP
	 * @return vip corresponding to the id parameter
	 * @throws SQLException
	 */
	public Vip getPartner(Vip vip) throws SQLException {
		String query = "select * from VIP where idVIP = ? ";
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
	} // getPartner method

	/**
	 * Add a vip to the database and add all hisnationalities
	 * 
	 * @param vip
	 * @param nationalities the list of nationalities of this vip
	 * @throws SQLException
	 */
	public void addNewVip(Vip vip, List<String> nationalities) throws SQLException {
		String queryNewVip = "INSERT INTO VIP VALUES(idVIP, ?, ?, ?, ?, ?, DEFAULT, DEFAULT);";
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
	} // addNewVip method

	/**
	 * Add movie casting to the database
	 * 
	 * @param vip
	 * @param moviesVisa the visa list of the movies the vip played in
	 * @throws SQLException
	 */
	public void addPlayedMovies(Vip vip, List<Integer> moviesVisa) throws SQLException {
		String query = "INSERT INTO casting VALUES(?, ?);";
		for (int movieVisa : moviesVisa) {
			PreparedStatement pstmt = connexion.prepareStatement(query);
			pstmt.setInt(1, vip.getIdVip());
			pstmt.setInt(2, movieVisa);
			pstmt.executeUpdate();
			pstmt.close();
		}
	} // addPlayedMovie method

	/**
	 * Add movie direction to the database
	 * 
	 * @param vip
	 * @param moviesVisa the visa list of the movies the vip directed
	 * @throws SQLException
	 */
	public void addDirectedMovies(Vip vip, List<Integer> moviesVisa) throws SQLException {
		String query = "INSERT INTO directing VALUES(?, ?);";
		for (int movieVisa : moviesVisa) {
			PreparedStatement pstmt = connexion.prepareStatement(query);
			pstmt.setInt(1, vip.getIdVip());
			pstmt.setInt(2, movieVisa);
			pstmt.executeUpdate();
			pstmt.close();
		}
	} // addDirectedMovies method

	/**
	 * Return the list of movies in which the vip is not playing
	 * 
	 * @param vip
	 * @return
	 * @throws SQLException
	 */
	public List<Movie> getNotPlayedMovies(Vip vip) throws SQLException {
		int idVip = vip.getIdVip();
		List<Movie> vipNotPlayedMovies = new ArrayList<>();
		String query = "SELECT * FROM movie WHERE movieVisa NOT IN (SELECT DISTINCT movieVisa FROM casting WHERE idVIP=?);";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, idVip);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()) {
			Movie movie = new Movie();
			movie.setMovieVisa(rset.getInt(1));
			movie.setMovieTitle(rset.getString(2));
			movie.setReleaseYear(rset.getInt(3));
			vipNotPlayedMovies.add(movie);
		}
		rset.close();
		pstmt.close();
		return vipNotPlayedMovies;
	} // getVipNotPlayedMovies method

	/**
	 * Return the list of movies the vip has not directed
	 * 
	 * @param vip
	 * @return
	 * @throws SQLException
	 */
	public List<Movie> getNotDirectedMovies(Vip vip) throws SQLException {
		int idVip = vip.getIdVip();
		List<Movie> vipNotDirectedMovies = new ArrayList<>();
		String query = "SELECT * FROM movie WHERE movieVisa NOT IN (SELECT DISTINCT movieVisa FROM directing WHERE idVIP=?);";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, idVip);
		ResultSet rset = pstmt.executeQuery();
		while (rset.next()) {
			Movie movie = new Movie();
			movie.setMovieVisa(rset.getInt(1));
			movie.setMovieTitle(rset.getString(2));
			movie.setReleaseYear(rset.getInt(3));
			vipNotDirectedMovies.add(movie);
		}
		rset.close();
		pstmt.close();
		return vipNotDirectedMovies;
	} // getNotDirectedMovies method

	/**
	 * Returns the list of vip who are not playing in the movie parameter
	 * 
	 * @param movie
	 * @return
	 * @throws SQLException
	 */
	public List<Vip> getActorsToAdd(Movie movie) throws SQLException {
		List<Vip> vipList = new ArrayList<>();
		String query = "SELECT * FROM VIP WHERE idVIP NOT IN (SELECT idVIP FROM casting WHERE movieVisa=?);";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, movie.getMovieVisa());
		ResultSet rset = pstmt.executeQuery();
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
		pstmt.close();
		return vipList;
	} // getActorsToAdd method

	/**
	 * Returns the list of vip who are not directing the movie parameter
	 * 
	 * @param movie
	 * @return
	 * @throws SQLException
	 */
	public List<Vip> getDirectorsToAdd(Movie movie) throws SQLException {
		List<Vip> vipList = new ArrayList<>();
		String query = "SELECT * FROM VIP WHERE idVIP NOT IN (SELECT idVIP FROM directing WHERE movieVisa=?);";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, movie.getMovieVisa());
		ResultSet rset = pstmt.executeQuery();
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
		pstmt.close();
		return vipList;
	} // getDirectorsToAdd method

} // DAOVip class
