package view.frames;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Movie;
import controller.Photo;
import controller.Vip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.sql.DataSource;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private DataSource dataSource;

	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MainFrame(DataSource dataSource) throws SQLException {
		this.dataSource=dataSource;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewVipList = new JButton("View all the VIP");
		btnViewVipList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VipListFrame vipListFrame = vipListDisplay();
					vipListFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnViewVipList.setBounds(123, 103, 138, 65);
		contentPane.add(btnViewVipList);
		
		JButton btnViewEventList = new JButton("View all the events");
		btnViewEventList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EventListFrame eventListFrame = eventListDisplay();
					eventListFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnViewEventList.setBounds(287, 103, 138, 65);
		contentPane.add(btnViewEventList);
		
		JButton btnNewVip = new JButton("New VIP");
		btnNewVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Vip newVip = new Vip();
					NewVipFrame newVipFrame = newVipDisplay(newVip);
					newVipFrame.setVisible(true);
					
				} catch (SQLException eVipCreation) {
					eVipCreation.printStackTrace();
				}
			}
		});
		btnNewVip.setBounds(31, 286, 97, 25);
		contentPane.add(btnNewVip);
		
		JButton btnNewMovie = new JButton("New Movie");
		btnNewMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Movie newMovie = new Movie();
					NewMovieFrame newMovieFrame = newMovieDisplay(newMovie);
					newMovieFrame.setVisible(true);
					
				} catch (SQLException eMovieCreation) {
					eMovieCreation.printStackTrace();
				}
			}
		});
		btnNewMovie.setBounds(159, 286, 97, 25);
		contentPane.add(btnNewMovie);
		
		JButton btnNewEvent = new JButton("New Event");
		btnNewEvent.setBounds(287, 286, 97, 25);
		contentPane.add(btnNewEvent);
		
		JButton btnNewPhoto = new JButton("New Photo");
		btnNewPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Photo newPhoto = new Photo();
					NewPhotoFrame newPhotoFrame = newPhotoDisplay(newPhoto);
					newPhotoFrame.setVisible(true);
					
				} catch (SQLException ePhotoCreation) {
					ePhotoCreation.printStackTrace();
				}
			}
		});
		btnNewPhoto.setBounds(415, 286, 97, 25);
		contentPane.add(btnNewPhoto);
		
		JLabel lblVipWorld = new JLabel("VIP WORLD");
		lblVipWorld.setFont(new Font("Tahoma", Font.BOLD, 41));
		lblVipWorld.setBounds(143, 11, 251, 65);
		contentPane.add(lblVipWorld);
	}
	
	private VipListFrame vipListDisplay() throws SQLException{
		return new VipListFrame(this);
	}
	
	private EventListFrame eventListDisplay() throws SQLException{
		return new EventListFrame(this);
	}
	
	private NewVipFrame newVipDisplay(Vip newVip) throws SQLException{
		return new NewVipFrame(this, newVip);
	}
	
	private NewMovieFrame newMovieDisplay(Movie newMovie) throws SQLException{
		return new NewMovieFrame(this, newMovie);
	}
	
	private NewPhotoFrame newPhotoDisplay(Photo newPhoto) throws SQLException{
		return new NewPhotoFrame(this, newPhoto);
	}
}
