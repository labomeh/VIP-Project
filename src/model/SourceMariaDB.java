package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.sql.DataSource;
import org.mariadb.jdbc.MariaDbDataSource;

/**
 * @author Simon
 *
 */
public class SourceMariaDB {
	public static DataSource getSource(PasswordAuthentication login) throws IOException {
		String user = login.getUserName();
        String password = new String(login.getPassword());
		Properties properties = new Properties();
		FileInputStream propertiesFile = new FileInputStream("src/connection.properties");
		properties.load(propertiesFile);
		MariaDbDataSource mdbDataSource = new MariaDbDataSource();
		mdbDataSource.setPortNumber(Integer.parseInt(properties.getProperty("port")));
		mdbDataSource.setDatabaseName(properties.getProperty("service"));
		mdbDataSource.setServerName(properties.getProperty("server"));
		mdbDataSource.setUser(user);
		mdbDataSource.setPassword(password);
		return mdbDataSource;
	}
}
