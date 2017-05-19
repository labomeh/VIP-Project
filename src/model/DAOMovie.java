/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    }
} // DAOVip class
