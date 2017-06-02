/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.App;

/**
 * @author Simon
 *
 */
public class DAOGenre {

	private static Connection connexion;

	public DAOGenre() {
		this.connexion = App.getConnection();
	}

	public static List<String> getGenres() throws SQLException {
		List<String> genreList = new ArrayList<String>();
		String query = "select * from genre";
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		while (rset.next()) {
			String genreName = rset.getString(1);
			genreList.add(genreName);
		}
		rset.close();
		stmt.close();
		return genreList;
	} // getGenres method

} // DAOVip class
