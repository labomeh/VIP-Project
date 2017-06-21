package app;

import java.awt.EventQueue;
import java.net.PasswordAuthentication;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import model.*;
import view.frames.IdentificationFrame;
import view.frames.MainFrame;

public class App {
	private static DataSource dataSource;
	private static Connection connection;
	private static DAOVip daoVip;
	private static DAOCountry daoCountry;
	private static DAOMovie daoMovie;
	private static DAOGenre daoGenre;
	private static DAOEvent daoEvent;
	private static DAOPhoto daoPhoto;

	public static DAOEvent getDaoEvent() {
		return daoEvent;
	}

	public static void setDaoEvent(DAOEvent daoEvent) {
		App.daoEvent = daoEvent;
	}

	public static Connection getConnection() {
		return connection;
	}

	public static DAOVip getDaoVip() {
		return daoVip;
	}

	public static DAOCountry getDaoCountry() {
		return daoCountry;
	}

	public static DAOMovie getDaoMovie() {
		return daoMovie;
	}

	public static DAOPhoto getDaoPhoto() {
		return daoPhoto;
	}

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		boolean etat = false;
        do {
            IdentificationFrame identificationFrame = new IdentificationFrame(null);
            PasswordAuthentication login = identificationFrame.identifier();
            try {
                dataSource = SourceMariaDB.getSource(login);
                connection = dataSource.getConnection();
                etat = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "login incorrect : " + ex.getMessage(),
                        "avertissement", JOptionPane.WARNING_MESSAGE);
            }
        } while (etat == false); // tant que la saisie n'est pas correcte
		daoCountry = new DAOCountry();
		daoVip = new DAOVip();
		daoMovie = new DAOMovie();
		daoGenre = new DAOGenre();
		daoPhoto = new DAOPhoto();
		daoEvent = new DAOEvent();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(dataSource);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Check the string's format
	 * Return true if the string's format is yyyy-m-d or yyyy-mm-dd
	 * 
	 * @param string
	 */
	public static boolean checkDateFormat(String string) {
		Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
	}
	
	/**
	 * Check if the string is an earlier date than today
	 * 
	 * @param string
	 */
	public static boolean checkDateValue(String date) {
		LocalDate localDate = Date.valueOf(date).toLocalDate();
		if(localDate.compareTo(LocalDate.now())>=0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check the string's format
	 * Return true if the string's format is yyyy
	 * 
	 * @param string
	 */
	public static boolean checkYearFormat(String year) {
		Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(year);
        return matcher.matches();
	}
	
	/**
	 * Check the string's format
	 * Return true if the string is only composed of integers
	 * 
	 * @param string
	 */
	public static boolean checkVisaFormat(String visa) {
		Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(visa);
        return matcher.matches();
	}
}
