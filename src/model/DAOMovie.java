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

/**
 * @author Simon
 *
 */
public class DAOMovie {

	private final Connection connexion;

	public DAOMovie() {
		this.connexion = App.getConnection();
	}

	public void getMovies(List<Movie> movieList) throws SQLException {
		String requete = "select * from movie";
		Statement stmt = connexion.createStatement();
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
	
	/**
	 * Add a movie to the database
	 * 
	 * @param the movie to add to the database
	 * @param a list that contains all the movie's genres
	 *
	 */
	public void addNewMovie(Movie newMovie, List<String> genres) throws SQLException {
		String queryNewVip = "INSERT INTO movie VALUES(?, ?, ?);";
		PreparedStatement pstmtNewVip = connexion.prepareStatement(queryNewVip);
		pstmtNewVip.setInt(1, newMovie.getMovieVisa());
		pstmtNewVip.setString(2, newMovie.getMovieTitle());
		pstmtNewVip.setInt(3, newMovie.getReleaseYear());
		pstmtNewVip.executeUpdate();
		for (String genre : genres) {
			String queryNewGenre = "INSERT INTO movieCategory VALUES(?, ?);";
			PreparedStatement pstmtNewGenre = connexion.prepareStatement(queryNewGenre);
			pstmtNewGenre.setInt(1, newMovie.getMovieVisa());
			pstmtNewGenre.setString(2, genre);
			pstmtNewGenre.executeUpdate();
			pstmtNewGenre.close();
		}
		pstmtNewVip.close();
	} // addNewMovie method

	/**
	 * Add a casting to the database
	 * 
	 * @param the movie concerned
	 * @param a list that contains all the actors' id playing in this movie
	 *
	 */
	public void addMovieCasting(Movie newMovie, List<Integer> actorsId) throws SQLException {
		for (int actorId : actorsId) {
			String queryNewActor = "INSERT INTO casting VALUES(?, ?);";
			PreparedStatement pstmtNewActor = connexion.prepareStatement(queryNewActor);
			pstmtNewActor.setInt(1, actorId);
			pstmtNewActor.setInt(2, newMovie.getMovieVisa());
			pstmtNewActor.executeUpdate();
			pstmtNewActor.close();
		}
	} // addMovieCasting method

	
	/**
	 * Add a movie direction to the database
	 * 
	 * @param the movie concerned
	 * @param a list that contains all the movie directors' id
	 *
	 */
	public void addMovieDirection(Movie newMovie, List<Integer> directorsId) throws SQLException {
		for (int directorId : directorsId) {
			String queryNewDirector = "INSERT INTO directing VALUES(?, ?);";
			PreparedStatement pstmtNewActor = connexion.prepareStatement(queryNewDirector);
			pstmtNewActor.setInt(1, directorId);
			pstmtNewActor.setInt(2, newMovie.getMovieVisa());
			pstmtNewActor.executeUpdate();
			pstmtNewActor.close();
		}
	} // addMovieDirection method

} // DAOVip class
