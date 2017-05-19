/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.Vip;

/**
 * @author Simon
 *
 */
public class DAOVip {

    private final Connection connexion;

    public DAOVip(Connection connexion) {
        this.connexion = connexion;
    }

    public void getVip(List<Vip> vipList) throws SQLException {
        String requete = "select * from VIP";
        Statement stmt = connexion.createStatement();
        ResultSet rset = stmt.executeQuery(requete);
         while (rset.next()) {
        	int idVip = rset.getInt(1);
            String name = rset.getString(2);
            String surname1 = rset.getString(3);
            String surname2 = rset.getString(4);
            String surname[] = {surname1, surname2};
            LocalDate birthdate = rset.getDate(5).toLocalDate();
            String birthplace = rset.getString(6);
            char roleCodeVIP = rset.getString(7).charAt(0);
            int idPartner = rset.getInt(8);
            Vip vip = new Vip(idVip, name, surname, birthdate, birthplace, roleCodeVIP, idPartner);
            vipList.add(vip);
        }        
        rset.close();
        stmt.close();
    } // getVip method
    
    public List<String> getVipNationalities(Vip vip) throws SQLException {
    	int idVip = vip.getIdVip();
    	List<String> vipNationalities = new ArrayList<>();
        String requete = "SELECT country.country FROM country, VIP, nationality WHERE VIP.idVIP=? and nationality.idVIP=? and country.country=nationality.country";
        Statement stmt = connexion.createStatement();
        ResultSet rset = stmt.executeQuery(requete);
         while (rset.next()) {
            String nationality = rset.getString(1);
            vipNationalities.add(nationality);
        }
        rset.close();
        stmt.close();
        return vipNationalities;
    } // getNationalities method
    
} // DAOVip class
