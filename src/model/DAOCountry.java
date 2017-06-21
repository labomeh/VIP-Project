package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import app.App;

/**
 * 
 * @author Mehdi
 *
 */
public class DAOCountry {

	private static Connection connection;

	public DAOCountry() {
		this.connection = App.getConnection();
	}

	public List<String> getCountries() throws SQLException {
		List<String> countryList = new ArrayList<String>();
		String query = "select * from country";
		Statement stmt = connection.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		while (rset.next()) {
			String countryName = rset.getString(1);
			countryList.add(countryName);
		}
		rset.close();
		stmt.close();
		return countryList;
	} // getCountries method

} // DAOCountry class
