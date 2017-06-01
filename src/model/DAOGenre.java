/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import app.App;
import controller.Genre;

/**
 * @author Simon
 *
 */
public class DAOGenre {

	private final Connection connection;

	public DAOGenre() {
		this.connection = App.getConnection();
	}

	public void getGenres(List<Genre> genreList) throws SQLException {
		String requete = "select * from movie";
		Statement stmt = connection.createStatement();
		ResultSet rset = stmt.executeQuery(requete);
		while (rset.next()) {
			String genreTitle = rset.getString(1);
			Genre genre = new Genre(genreTitle);
			genreList.add(genre);
		}
		rset.close();
		stmt.close();
	} // getGenres method

} // DAOVip class
