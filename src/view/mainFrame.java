package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		btnViewVipList.setBounds(33, 103, 138, 65);
		contentPane.add(btnViewVipList);
		
		JButton btnViewMovieList = new JButton("View all the movies");
		btnViewMovieList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MovieListFrame movieListFrame = movieListDisplay();
					movieListFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnViewMovieList.setBounds(204, 103, 138, 65);
		contentPane.add(btnViewMovieList);
		
		JButton btnViewEventList = new JButton("View all the events");
		btnViewEventList.setBounds(375, 103, 138, 65);
		contentPane.add(btnViewEventList);
		
		JButton btnNewVip = new JButton("New VIP");
		btnNewVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Vip newVip = new Vip();
					NewVipFrame newVipFrame = newVipDisplay(newVip);
					newVipFrame.setVisible(true);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewVip.setBounds(67, 286, 97, 25);
		contentPane.add(btnNewVip);
		
		JButton btnNewEvent = new JButton("New Event");
		btnNewEvent.setBounds(220, 286, 97, 25);
		contentPane.add(btnNewEvent);
		
		JButton btnNewPhoto = new JButton("New Photo");
		btnNewPhoto.setBounds(367, 286, 97, 25);
		contentPane.add(btnNewPhoto);
		
		JLabel lblVipWorld = new JLabel("VIP WORLD");
		lblVipWorld.setFont(new Font("Tahoma", Font.BOLD, 41));
		lblVipWorld.setBounds(143, 11, 251, 65);
		contentPane.add(lblVipWorld);
	}
	
	private VipListFrame vipListDisplay() throws SQLException{
		return new VipListFrame(this);
	}
	
	private NewVipFrame newVipDisplay(Vip newVip) throws SQLException{
		return new NewVipFrame(this, newVip);
	}
	
	private MovieListFrame movieListDisplay() throws SQLException{
		return new MovieListFrame(this);
	}
	
	private NewMovieFrame newMovieDisplay(Vip newVip) throws SQLException{
		return new NewMovieFrame(this, newVip);
	}
}
