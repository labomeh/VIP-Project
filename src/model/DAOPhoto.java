package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;

import app.App;
import controller.Photo;

/**
 * @author Simon
 *
 */
public class DAOPhoto {

	private static Connection connexion;

	public DAOPhoto() {
		this.connexion = App.getConnection();
	}

	/**
	 * 
	 * @param newPhoto the photo to transfer
	 * @param identifiedVipId a list of vip's id that appear on the photo
	 * @param photoPath photo local path
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addNewPhoto(Photo newPhoto, List<Integer> identifiedVipId, String photoPath) throws SQLException, IOException {
		// Transferring the file via FTP
		Properties properties = new Properties();
		FileInputStream fichier = new FileInputStream("src/ftp.properties");
		properties.load(fichier);
		FTPSClient ftp = new FTPSClient();
		ftp.connect(properties.getProperty("host"),990);
		ftp.login(properties.getProperty("login"), properties.getProperty("password"));
		ftp.enterLocalPassiveMode();
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		
		InputStream input = new FileInputStream(photoPath);
		boolean bool = false;
		while(bool!=true){
			bool = ftp.storeFile("/public_html/image/" + newPhoto.getFileName(), input);
			System.out.println("Upload Ok");
		}
		
		// Adding the photo details to the database
		String queryNewPhoto = "INSERT INTO photo VALUES(idPhoto, ?, ?, ?);";
		PreparedStatement pstmtNewPhoto = connexion.prepareStatement(queryNewPhoto);
		pstmtNewPhoto.setString(1, newPhoto.getPlace());
		pstmtNewPhoto.setString(2, newPhoto.getDate().toString());
		pstmtNewPhoto.setString(3, newPhoto.getFileName());
		pstmtNewPhoto.executeUpdate();
		String queryGetCurrentPhotoId = "SELECT MAX(idPhoto) FROM photo";
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery(queryGetCurrentPhotoId);
		rset.next();
		int idPhoto = rset.getInt(1);
		for (int id : identifiedVipId) {
			String queryIdentification = "INSERT INTO shows VALUES(?, ?);";
			PreparedStatement pstmtNewIdentification = connexion.prepareStatement(queryIdentification);
			pstmtNewIdentification.setInt(1, id);
			pstmtNewIdentification.setInt(2, idPhoto);
			pstmtNewIdentification.executeUpdate();
			pstmtNewIdentification.close();
		}
		pstmtNewPhoto.close();
	} // addNewPhoto method

} // DAOVip class
