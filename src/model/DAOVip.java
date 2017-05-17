/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import controller.VIP;

/**
 * @author Simon
 *
 */
public class DAOVip {

    private final Connection connexion;

    public DAOVip(Connection connexion) {
        this.connexion = connexion;
    }

    public void recupererDepartements(List<VIP> vipList) throws SQLException {
        String requete = "select * from VIP";
        Statement stmt = connexion.createStatement();
        ResultSet rset = stmt.executeQuery(requete);
         while (rset.next()) {
            String name = rset.getString(2);
            String surname1 = rset.getString(3);
            String surname2 = rset.getString(4);
            String surname[] = {surname1, surname2};
            LocalDate birthdate = rset.getDate(5).toLocalDate();
            String birthplace = rset.getString(6);
            char roleCodeVIP = rset.getString(7).charAt(0);
            int idPartner = rset.getInt(8);
            VIP vip = new VIP(name, surname, birthdate, birthplace, roleCodeVIP, idPartner);
            vipList.add(vip);
        }        
        rset.close();
        stmt.close();
    }
} // DAOVip class
