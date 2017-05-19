package app;

import java.awt.EventQueue;
import java.net.PasswordAuthentication;

import javax.sql.DataSource;
import javax.swing.UIManager;


import model.SourceMariaDB;
import view.mainFrame;

public class App {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		char[] pwd = {'2','5','3','5','0','2'};
		DataSource dataSource;
		try{
			dataSource = SourceMariaDB.getSource(new PasswordAuthentication("p1517582",pwd));
		}
		catch(Exception ex){
			System.out.println("Echec connexion : "+ex.getMessage());
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					mainFrame frame = new mainFrame(dataSource);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
