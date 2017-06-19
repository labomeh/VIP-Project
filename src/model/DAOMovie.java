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

	public List<String> getMovieGenres(Movie movie) throws SQLException {
		int movieVisa = movie.getMovieVisa();
		List<String> movieGenres = new ArrayList<>();
		String requete = "select genreTitle from movieCategory where movieVisa=?";
		PreparedStatement pstmt = connexion.prepareStatement(requete);
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
		PreparedStatement pstmt = connexion.prepareStatement(requete);
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
		PreparedStatement pstmt = connexion.prepareStatement(requete);
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
