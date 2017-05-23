package app;

import java.awt.EventQueue;
import javax.sql.DataSource;
import javax.swing.UIManager;

import model.SourceMariaDB;
import view.MainFrame;

public class App {
	private static DataSource dataSource;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			dataSource = SourceMariaDB.getSource("p1517582","253502");
		}
		catch(Exception ex){
			System.out.println("Echec connexion : "+ex.getMessage());
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
}
