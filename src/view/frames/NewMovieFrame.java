package view.frames;

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
import view.JComboBoxModels.GenreJComboBox;
import view.JComboBoxModels.VipJComboBox;

import javax.swing.JComboBox;
import java.awt.Component;

import javax.swing.SwingConstants;

public class NewMovieFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewVisa, txtNewTitle, txtNewReleaseYear;
	private Movie newMovie;
	private List<String> genres = new ArrayList<>();
	private List<String> actors = new ArrayList<>();
	private List<Integer> actorsId = new ArrayList<>();
	private List<String> directors = new ArrayList<>();
	private List<Integer> directorsId = new ArrayList<>();
	private JComboBox cbxGenre = new JComboBox(), cbxActor = new JComboBox(), cbxDirector = new JComboBox();
	private JLabel genresValues, actorsValues, directorsValues;

	/**
	 * Create the frame.
	 */
	public NewMovieFrame(MainFrame mainFrame, Movie newMovie) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 594, 491);
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
		
		JLabel lblNewActor = new JLabel("Actor");
		lblNewActor.setBounds(22, 224, 113, 16);
		contentPane.add(lblNewActor);
		
		JLabel lblAddedActors = new JLabel("Added Actors");
		lblAddedActors.setBounds(22, 256, 113, 16);
		contentPane.add(lblAddedActors);
		
		JLabel lblNewDirector = new JLabel("Director");
		lblNewDirector.setBounds(22, 288, 113, 16);
		contentPane.add(lblNewDirector);
		
		JLabel lblAddedDirectors = new JLabel("Added Directors");
		lblAddedDirectors.setBounds(22, 322, 113, 16);
		contentPane.add(lblAddedDirectors);

		JLabel lblyyyy = new JLabel("(yyyy)");
		lblyyyy.setBounds(317, 125, 104, 16);
		contentPane.add(lblyyyy);

		genresValues = new JLabel("");
		genresValues.setBounds(147, 192, 274, 16);
		contentPane.add(genresValues);
		
		actorsValues = new JLabel("");
		actorsValues.setBounds(147, 256, 417, 16);
		contentPane.add(actorsValues);
		
		directorsValues = new JLabel("");
		directorsValues.setBounds(147, 322, 417, 16);
		contentPane.add(directorsValues);

		txtNewVisa = new JTextField();
		txtNewVisa.setBounds(147, 52, 158, 22);
		contentPane.add(txtNewVisa);
		txtNewVisa.setColumns(10);

		txtNewTitle = new JTextField();
		txtNewTitle.setBounds(147, 87, 417, 22);
		contentPane.add(txtNewTitle);
		txtNewTitle.setColumns(10);

		txtNewReleaseYear = new JTextField();
		txtNewReleaseYear.setBounds(147, 122, 158, 22);
		contentPane.add(txtNewReleaseYear);
		txtNewReleaseYear.setColumns(10);
		
		cbxGenre.setModel(new GenreJComboBox());
		cbxGenre.setBounds(147, 157, 158, 22);
		contentPane.add(cbxGenre);

		VipJComboBox actorJComboBox = new VipJComboBox();
		cbxActor.setModel(actorJComboBox);
		cbxActor.setBounds(147, 221, 274, 22);
		contentPane.add(cbxActor);
		
		VipJComboBox directorJComboBox = new VipJComboBox();
		cbxDirector.setModel(directorJComboBox);
		cbxDirector.setBounds(147, 285, 274, 22);
		contentPane.add(cbxDirector);
		
		JButton btnErase = new JButton("Erase");
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInfo();
			}
		});
		btnErase.setBounds(147, 386, 158, 25);
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
						App.getDaoMovie().addNewMovieCategory(newMovie, genres);
						App.getDaoMovie().addNewMovieCasting(newMovie, actors);
						App.getDaoMovie().addNewMovieDirection(newMovie, directors);
						clearInfo();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(350, 386, 158, 25);
		contentPane.add(btnSave);

		JButton btnAddGenre = new JButton("Add");
		btnAddGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String genre = (String) cbxGenre.getSelectedItem();
				genres.add(genre);
				genresValues.setText(genres.toString());
				cbxGenre.setSelectedIndex(-1);
			}
		});
		btnAddGenre.setBounds(317, 156, 104, 25);
		contentPane.add(btnAddGenre);
		
		JButton btnAddActor = new JButton("Add");
		btnAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String actor = (String) cbxActor.getSelectedItem();
				actors.add(actor);
				Integer actorId = (Integer) actorJComboBox.getCurrentId(cbxActor.getSelectedIndex());
				actorsId.add(actorId);
				actorsValues.setText(actors.toString());
				cbxActor.setSelectedIndex(-1);
			}
		});
		btnAddActor.setBounds(431, 220, 133, 25);
		contentPane.add(btnAddActor);
		
		JButton btnAddDirector = new JButton("Add");
		btnAddDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String director = (String) cbxDirector.getSelectedItem();
				directors.add(director);
				Integer directorId = (Integer) directorJComboBox.getCurrentId(cbxDirector.getSelectedIndex());
				directorsId.add(directorId);
				directorsValues.setText(directors.toString());
				cbxDirector.setSelectedIndex(-1);
			}
		});
		btnAddDirector.setBounds(431, 284, 133, 25);
		contentPane.add(btnAddDirector);
		
		

	}

	private void clearInfo() {
		txtNewVisa.setText("");
		txtNewTitle.setText("");
		txtNewReleaseYear.setText("");
		cbxGenre.setSelectedIndex(-1);
		cbxActor.setSelectedIndex(-1);
		cbxDirector.setSelectedIndex(-1);
		genres.clear();
		actors.clear();
		directors.clear();
		genresValues.setText("");
		actorsValues.setText("");
		directorsValues.setText("");
		System.out.println(genres.toString());
	}
}
