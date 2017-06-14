package view.frames;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Vip;
import view.JComboBoxModels.NotPlayedMovieJComboBox;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddPlayedMovieFrame extends JFrame {
	
	public AddPlayedMovieFrame(VipListFrame vipListFrame, Vip vip) throws SQLException {
		
		getContentPane().setLayout(null);
		
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
		cbxMovie.setModel(new NotPlayedMovieJComboBox(vip));
		cbxMovie.setBounds(135, 124, 158, 22);
		getContentPane().add(cbxMovie);
		
		JButton btnAddPlayedMovie = new JButton("Add");
		btnAddPlayedMovie.setBounds(305, 123, 104, 25);
		getContentPane().add(btnAddPlayedMovie);
		
		JLabel lblSelectedMovies = new JLabel("Added Nationalities");
		lblSelectedMovies.setBounds(10, 162, 113, 16);
		getContentPane().add(lblSelectedMovies);
		
		JLabel moviesValues = new JLabel("");
		moviesValues.setBounds(135, 162, 158, 16);
		getContentPane().add(moviesValues);
		
		JButton btnRemoveMovie = new JButton("<");
		btnRemoveMovie.setBounds(305, 158, 51, 25);
		getContentPane().add(btnRemoveMovie);
		
		JButton btnClearAllMovies = new JButton("<<");
		btnClearAllMovies.setBounds(358, 158, 51, 25);
		getContentPane().add(btnClearAllMovies);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(135, 232, 158, 25);
		getContentPane().add(btnSave);
	}
}
