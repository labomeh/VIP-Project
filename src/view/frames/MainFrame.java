package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.sql.DataSource;
import java.sql.SQLException;

import controller.*;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private DataSource dataSource;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public MainFrame(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVipWorld = new JLabel("VIP WORLD");
		lblVipWorld.setFont(new Font("Tahoma", Font.BOLD, 41));
		lblVipWorld.setBounds(143, 11, 251, 65);
		contentPane.add(lblVipWorld);

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
		btnViewMovieList.setBounds(202, 181, 138, 65);
		contentPane.add(btnViewMovieList);

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
	}

	private VipListFrame vipListDisplay() throws SQLException {
		return new VipListFrame(this);
	}

	private EventListFrame eventListDisplay() throws SQLException {
		return new EventListFrame(this);
	}

	protected MovieListFrame movieListDisplay() throws SQLException {
		return new MovieListFrame(this);
	}

	private NewVipFrame newVipDisplay(Vip newVip) throws SQLException {
		return new NewVipFrame(this, newVip);
	}

	private NewMovieFrame newMovieDisplay(Movie newMovie) throws SQLException {
		return new NewMovieFrame(this, newMovie);
	}

	private NewPhotoFrame newPhotoDisplay(Photo newPhoto) throws SQLException {
		return new NewPhotoFrame(this, newPhoto);
	}
}
