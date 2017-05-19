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

import controller.Movie;

/**
 * @author Simon
 *
 */
public class DAOMovie {

	private final Connection connexion;

	public DAOMovie(Connection connexion) {
		this.connexion = connexion;
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
	
	public List<String> getMoviegenres(Movie movie) throws SQLException {
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
	
} // DAOVip class
