package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.App;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.Movie;
import javax.swing.JComboBox;
import java.awt.Component;

import javax.swing.SwingConstants;

public class NewMovieFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewVisa;
	private JTextField txtNewTitle;
	private JTextField txtNewReleaseYear;
	private Movie newMovie;
	private List<String> genres = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public NewMovieFrame(MainFrame mainFrame, Movie newMovie) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMovieCreation = new JLabel("Movie Creation");
		lblMovieCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovieCreation.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMovieCreation.setBounds(12, 0, 409, 42);
		contentPane.add(lblMovieCreation);

		JLabel lblNewVisa = new JLabel("Visa");
		lblNewVisa.setBounds(22, 55, 113, 16);
		contentPane.add(lblNewVisa);

		JLabel lblNewTitle = new JLabel("Movie Title");
		lblNewTitle.setBounds(22, 90, 113, 16);
		contentPane.add(lblNewTitle);

		JLabel lblNewReleaseYear = new JLabel("Release Year");
		lblNewReleaseYear.setBounds(22, 125, 113, 16);
		contentPane.add(lblNewReleaseYear);

		JLabel lblNewGenre = new JLabel("Genre");
		lblNewGenre.setBounds(22, 160, 113, 16);
		contentPane.add(lblNewGenre);

		JLabel lblAddedGenres = new JLabel("Added Genres");
		lblAddedGenres.setBounds(22, 192, 113, 16);
		contentPane.add(lblAddedGenres);

		JLabel lblyyyy = new JLabel("(yyyy)");
		lblyyyy.setBounds(317, 125, 104, 16);
		contentPane.add(lblyyyy);

		JLabel genresValues = new JLabel("");
		genresValues.setBounds(147, 192, 274, 16);
		contentPane.add(genresValues);

		txtNewVisa = new JTextField();
		txtNewVisa.setBounds(147, 52, 158, 22);
		contentPane.add(txtNewVisa);
		txtNewVisa.setColumns(10);

		txtNewTitle = new JTextField();
		txtNewTitle.setBounds(147, 87, 274, 22);
		contentPane.add(txtNewTitle);
		txtNewTitle.setColumns(10);

		txtNewReleaseYear = new JTextField();
		txtNewReleaseYear.setBounds(147, 122, 158, 22);
		contentPane.add(txtNewReleaseYear);
		txtNewReleaseYear.setColumns(10);

		JComboBox cbxGenre = new JComboBox();
		cbxGenre.setModel(new GenreJComboBox());
		cbxGenre.setBounds(147, 157, 158, 22);
		contentPane.add(cbxGenre);

		JButton btnErase = new JButton("Erase");
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInfo(cbxGenre, genresValues);
			}
		});
		btnErase.setBounds(147, 331, 158, 25);
		contentPane.add(btnErase);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtNewVisa.getText().isEmpty()) {
						throw new Exception("You must fill the Visa field");
					}
					if (txtNewTitle.getText().isEmpty()) {
						throw new Exception("You must fill the Title field");
					} else {
						newMovie.setMovieVisa(new Integer(txtNewVisa.getText()));
						newMovie.setMovieTitle(txtNewTitle.getText());
						App.getDaoMovie().addNewMovie(newMovie, genres);
						clearInfo(cbxGenre, genresValues);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(147, 369, 158, 25);
		contentPane.add(btnSave);

		JButton btnAddGenres = new JButton("Add");
		btnAddGenres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nationality = (String) cbxGenre.getSelectedItem();
				genres.add(nationality);
				genresValues.setText(genres.toString());
				cbxGenre.setSelectedIndex(-1);
			}
		});
		btnAddGenres.setBounds(317, 156, 104, 25);
		contentPane.add(btnAddGenres);

	}

	private void clearInfo(JComboBox cbxGenre, JLabel nationalitiesValues) {
		txtNewVisa.setText("");
		txtNewTitle.setText("");
		txtNewReleaseYear.setText("");
		cbxGenre.setSelectedIndex(-1);
		genres.clear();
		System.out.println(genres.toString());
		nationalitiesValues.setText("");
	}
}
