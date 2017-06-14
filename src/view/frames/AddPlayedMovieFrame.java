package view.frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Vip;
import view.JComboBoxModels.NotPlayedMovieJComboBox;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPlayedMovieFrame extends JFrame {
	
	private JPanel contentPane;
	private List<String> movies = new ArrayList<>();
	private List<Integer> moviesVisa = new ArrayList<>();
	private NotPlayedMovieJComboBox moviesJComboBox;
	private JLabel moviesValues;
	
	public AddPlayedMovieFrame(VipListFrame vipListFrame, Vip vip) throws SQLException {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddPlayedMovies = new JLabel("Add Played Movies For");
		lblAddPlayedMovies.setBounds(0, 0, 449, 52);
		getContentPane().add(lblAddPlayedMovies);
		
		JLabel lblSelectedActor = new JLabel("Add Played Movies For");
		lblSelectedActor.setBounds(0, 53, 449, 52);
		getContentPane().add(lblSelectedActor);
		
		JLabel lblMovie = new JLabel("Movie");
		lblMovie.setBounds(10, 127, 113, 16);
		getContentPane().add(lblMovie);
		
		JComboBox cbxMovie = new JComboBox();
		moviesJComboBox = new NotPlayedMovieJComboBox(vip);
		cbxMovie.setModel(moviesJComboBox);
		cbxMovie.setBounds(135, 124, 158, 22);
		getContentPane().add(cbxMovie);
		
		JButton btnAddPlayedMovie = new JButton("Add");
		btnAddPlayedMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String movieTitle = (String) cbxMovie.getSelectedItem();
				if(!movies.contains(movieTitle)) {
					movies.add(movieTitle);
					Integer movieVisa = (Integer) moviesJComboBox.getCurrentId(cbxMovie.getSelectedIndex());
					moviesVisa.add(movieVisa);
					cbxMovie.setSelectedIndex(-1);
				}
				moviesValues.setText(movies.toString());
			}
		});
		btnAddPlayedMovie.setBounds(305, 123, 104, 25);
		getContentPane().add(btnAddPlayedMovie);
		
		JLabel lblSelectedMovies = new JLabel("Added Nationalities");
		lblSelectedMovies.setBounds(10, 162, 113, 16);
		getContentPane().add(lblSelectedMovies);
		
		moviesValues = new JLabel("");
		moviesValues.setBounds(135, 162, 158, 16);
		getContentPane().add(moviesValues);
		
		JButton btnRemoveMovie = new JButton("<");
		btnRemoveMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveMovie.setBounds(305, 158, 51, 25);
		getContentPane().add(btnRemoveMovie);
		
		JButton btnClearAllMovies = new JButton("<<");
		btnClearAllMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClearAllMovies.setBounds(358, 158, 51, 25);
		getContentPane().add(btnClearAllMovies);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(135, 232, 158, 25);
		getContentPane().add(btnSave);
	}
}
