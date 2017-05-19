/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import controller.Genre;

/**
 * @author Simon
 *
 */
public class DAOGenre {

    private final Connection connexion;

    public DAOGenre(Connection connexion) {
        this.connexion = connexion;
    }

    public void getGenres(List<Genre> genreList) throws SQLException {
        String requete = "select * from movie";
        Statement stmt = connexion.createStatement();
        ResultSet rset = stmt.executeQuery(requete);
         while (rset.next()) {
            String genreTitle = rset.getString(1);
            Genre genre = new Genre(genreTitle);
            genreList.add(genre);
        }        
        rset.close();
        stmt.close();
    }
} // DAOVip class
