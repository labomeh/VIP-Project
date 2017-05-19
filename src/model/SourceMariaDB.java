/**
 * 
 */
package model;

import java.io.FileInputStream;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

/**
 * @author Simon
 *
 */
public class SourceMariaDB {
    //TODO utiliser un objet PasswordAuthentication
    public static DataSource getSource(String login,String password) throws Exception {
        Properties properties = new Properties();
        FileInputStream propertiesFile = new FileInputStream("src/connection.properties");
        properties.load(propertiesFile);
        MariaDbDataSource mdbDataSource = new MariaDbDataSource();
        mdbDataSource.setPortNumber(Integer.parseInt(properties.getProperty("port")));
        mdbDataSource.setDatabaseName(properties.getProperty("service"));
        mdbDataSource.setServerName(properties.getProperty("server"));
        mdbDataSource.setUser(login);
        mdbDataSource.setPassword(password);
        return mdbDataSource;
    }
}
