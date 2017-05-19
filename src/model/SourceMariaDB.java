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
    
    public static DataSource getSource(PasswordAuthentication login) throws Exception {
        String user = login.getUserName();
        String pwd = new String(login.getPassword());
        Properties properties = new Properties();
        FileInputStream propertiesFile = new FileInputStream("src/connection.properties");
        properties.load(propertiesFile);
        MariaDbDataSource mdbDataSource = new MariaDbDataSource();
        mdbDataSource.setPortNumber(Integer.parseInt(properties.getProperty("port")));
        mdbDataSource.setDatabaseName(properties.getProperty("service"));
        mdbDataSource.setServerName(properties.getProperty("serveur"));
        mdbDataSource.setUser(user);
        mdbDataSource.setPassword(pwd);
        return mdbDataSource;
    }
}
