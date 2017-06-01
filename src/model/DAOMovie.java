/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.App;
import controller.Movie;
import controller.Vip;

/**
 * @author Simon
 *
 */
public class DAOMovie {

	private final Connection connection;

	public DAOMovie() {
		this.connection = App.getConnection();
	}

	public void getMovies(List<Movie> movieList) throws SQLException {
		String requete = "select * from movie";
		Statement stmt = connection.createStatement();
		ResultSet rset = stmt.executeQuery(requete);
		while (rset.next()) {
			int movieVisa = rset.getInt(1);
			String movieTitle = rset.getString(2);
			int releaseYear = rset.getInt(3);
			Movie movie = new Movie(movieVisa, movieTitle, releaseYear);
			movieList.add(movie);
		}
		rset.close();
		stmt.close();
	} // getMovies method
	
	public List<String> getMoviegenres(Movie movie) throws SQLException {
		int movieVisa = movie.getMovieVisa();
		List<String> movieGenres = new ArrayList<>();
		String requete = "select genreTitle from movieCategory where movieVisa=?";
		PreparedStatement pstmt = connection.prepareStatement(requete);
		ResultSet rset = pstmt.executeQuery(requete);
		pstmt.setInt(1, movieVisa);
		while (rset.next()) {
			String genreTitle = rset.getString(1);
			movieGenres.add(genreTitle);
		}
		rset.close();
		pstmt.close();
		return movieGenres;
	} // getMovieGenre method
	
	public List<Integer> getCasting(Movie movie) throws SQLException {
		int movieVisa = movie.getMovieVisa();
		List<Integer> movieCasting = new ArrayList<>();
		String requete = "SELECT idVIP FROM playing WHERE movieVisa=?";
		PreparedStatement pstmt = connection.prepareStatement(requete);
		pstmt.setInt(1, movieVisa);
		ResultSet rset = pstmt.executeQuery(requete);
		while (rset.next()) {
			int playingActor = rset.getInt(1);
			movieCasting.add(playingActor);
		}
		rset.close();
		pstmt.close();
		return movieCasting;
	} // getCasting method
	
	public List<Integer> getDirector(Movie movie) throws SQLException {
		int movieVisa = movie.getMovieVisa();
		List<Integer> movieDirectors = new ArrayList<>();
		String requete = "SELECT idVIP FROM directing WHERE movieVisa=?";
		PreparedStatement pstmt = connection.prepareStatement(requete);
		pstmt.setInt(1, movieVisa);
		ResultSet rset = pstmt.executeQuery(requete);
		while (rset.next()) {
			int idDirector = rset.getInt(1);
			movieDirectors.add(idDirector);
		}
		rset.close();
		pstmt.close();
		return movieDirectors;
	} // getDirector method
	
	
	
} // DAOVip class
