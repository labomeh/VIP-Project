package model;

/**
 * 
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.Country;
import controller.Genre;

/**
 * 
 * @author Mehdi
 *
 */
public class DAOCountry {
	private static Connection connection;

	public DAOCountry(Connection connection) {
		this.connection = connection;
	}

	public static List<String> getCountries() throws SQLException {
		List<String> countryList = new ArrayList<String>();
		String request = "select * from country";
		Statement stmt = connection.createStatement();
		ResultSet rset = stmt.executeQuery(request);
		while (rset.next()) {
			String countryName = rset.getString(0);
			countryList.add(countryName);
		}
		rset.close();
		stmt.close();
		return countryList;
	}
}