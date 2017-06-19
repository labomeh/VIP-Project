package app;

import java.awt.EventQueue;

import javax.swing.UIManager;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import model.*;

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

	public static void setConnection(Connection connection) {
		App.connection = connection;
	}

	public static DAOVip getDaoVip() {
		return daoVip;
	}

	public static void setDaoVip(DAOVip daoVip) {
		App.daoVip = daoVip;
	}

	public static DAOCountry getDaoCountry() {
		return daoCountry;
	}

	public static void setDaoCountry(DAOCountry daoCountry) {
		App.daoCountry = daoCountry;
	}

	public static DAOMovie getDaoMovie() {
		return daoMovie;
	}

	public static void setDaoMovie(DAOMovie daoMovie) {
		App.daoMovie = daoMovie;
	}

	public static DAOGenre getDaoGenre() {
		return daoGenre;
	}

	public static void setDaoGenre(DAOGenre daoGenre) {
		App.daoGenre = daoGenre;
	}

	public static DAOPhoto getDaoPhoto() {
		return daoPhoto;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dataSource = SourceMariaDB.getSource("p1517582", "253502");
			connection = dataSource.getConnection();
			daoCountry = new DAOCountry();
			daoVip = new DAOVip();
			daoMovie = new DAOMovie();
			daoGenre = new DAOGenre();
			daoPhoto = new DAOPhoto();
			daoEvent = new DAOEvent();
		} catch (Exception ex) {
			System.out.println("Echec connexion : " + ex.getMessage());
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame(dataSource);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static boolean checkDateFormat(String date) {
		Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
	}

	public static boolean checkDateValue(String date) {
		LocalDate localDate = Date.valueOf(date).toLocalDate();
		if(localDate.compareTo(LocalDate.now())>=0) {
			return false;
		}
		return true;
	}
}
