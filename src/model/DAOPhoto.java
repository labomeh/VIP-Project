/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.App;
import controller.Photo;
import controller.Vip;

/**
 * @author Simon
 *
 */
public class DAOPhoto {

	private static Connection connexion;

	public DAOPhoto() {
		this.connexion = App.getConnection();
	}

	public List<Photo> getPhoto() throws SQLException {
		List<Photo> photoList = new ArrayList<>();
		String query = "select * from photo";
		Statement stmt = connexion.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		while (rset.next()) {
			int idPhoto = rset.getInt(1);
			String place = rset.getString(2);
			LocalDate date = rset.getDate(3).toLocalDate();
			Photo photo = new Photo(idPhoto, place, date);
			photoList.add(photo);
		}
		rset.close();
		stmt.close();
		return photoList;
		
	} // getPhoto method

	public List<Vip> getIdentifiedVip(Photo photo) throws SQLException {
		int idPhoto = photo.getIdPhoto();
		List<Integer> identifiedVipId = new ArrayList<>();
		List<Vip> identifiedVip = new ArrayList<>();
		String query = "SELECT idVip FROM photo WHERE idPhoto=?";
		PreparedStatement pstmt = connexion.prepareStatement(query);
		pstmt.setInt(1, idPhoto);
		ResultSet rset = pstmt.executeQuery(query);
		while (rset.next()) {
			identifiedVipId.add(rset.getInt(1));
		}
		for(int idVip : identifiedVipId) {
			identifiedVip.add(App.getDaoVip().getVip(idVip));
		}
		rset.close();
		pstmt.close();
		return identifiedVip;
		
	} // getIdentifiedVip method
	
	public void addNewPhoto(Photo newPhoto, List<Integer> identifiedVipId) throws SQLException {
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
		
		for(int id : identifiedVipId) {
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
