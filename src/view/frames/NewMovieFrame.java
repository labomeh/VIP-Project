package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;

import app.App;
import controller.Movie;
import view.JComboBoxModels.GenreJComboBox;
import view.JComboBoxModels.VipJComboBox;

public class NewMovieFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewVisa, txtNewTitle, txtNewReleaseYear;
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
		setBounds(100, 100, 594, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMovieCreation = new JLabel("Movie Creation");
		lblMovieCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovieCreation.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMovieCreation.setBounds(12, 0, 552, 42);
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
		lblNewGenre.setBounds(22, 176, 113, 16);
		contentPane.add(lblNewGenre);

		JLabel lblAddedGenres = new JLabel("Added Genres");
		lblAddedGenres.setBounds(22, 208, 113, 16);
		contentPane.add(lblAddedGenres);

		JLabel lblNewActor = new JLabel("Actor");
		lblNewActor.setBounds(22, 255, 113, 16);
		contentPane.add(lblNewActor);

		JLabel lblAddedActors = new JLabel("Added Actors");
		lblAddedActors.setBounds(22, 287, 113, 16);
		contentPane.add(lblAddedActors);

		JLabel lblNewDirector = new JLabel("Director");
		lblNewDirector.setBounds(22, 335, 113, 16);
		contentPane.add(lblNewDirector);

		JLabel lblAddedDirectors = new JLabel("Added Directors");
		lblAddedDirectors.setBounds(22, 369, 113, 16);
		contentPane.add(lblAddedDirectors);

		JLabel lblyyyy = new JLabel("(yyyy)");
		lblyyyy.setBounds(317, 125, 104, 16);
		contentPane.add(lblyyyy);

		genresValues = new JLabel("");
		genresValues.setBounds(147, 208, 274, 16);
		contentPane.add(genresValues);

		actorsValues = new JLabel("");
		actorsValues.setBounds(147, 287, 274, 16);
		contentPane.add(actorsValues);

		directorsValues = new JLabel("");
		directorsValues.setBounds(147, 369, 274, 16);
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
		cbxGenre.setBounds(147, 173, 274, 22);
		contentPane.add(cbxGenre);

		VipJComboBox actorJComboBox = new VipJComboBox();
		cbxActor.setModel(actorJComboBox);
		cbxActor.setBounds(147, 252, 274, 22);
		contentPane.add(cbxActor);

		VipJComboBox directorJComboBox = new VipJComboBox();
		cbxDirector.setModel(directorJComboBox);
		cbxDirector.setBounds(147, 332, 274, 22);
		contentPane.add(cbxDirector);

		JButton btnErase = new JButton("Erase");
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInfo();
			}
		});
		btnErase.setBounds(86, 464, 158, 25);
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
						newMovie.setReleaseYear(Integer.valueOf(txtNewReleaseYear.getText()));
						App.getDaoMovie().addNewMovie(newMovie, genres);
						App.getDaoMovie().addMovieCasting(newMovie, actorsId);
						App.getDaoMovie().addMovieDirection(newMovie, directorsId);
						clearInfo();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(330, 464, 158, 25);
		contentPane.add(btnSave);

		JButton btnAddGenre = new JButton("Add");
		btnAddGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String genre = (String) cbxGenre.getSelectedItem();
				if (!genres.contains(genre)) {
					genres.add(genre);
					genresValues.setText(genres.toString());
				}
				cbxGenre.setSelectedIndex(-1);
			}
		});
		btnAddGenre.setBounds(433, 172, 133, 25);
		contentPane.add(btnAddGenre);

		JButton btnAddActor = new JButton("Add");
		btnAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String actor = (String) cbxActor.getSelectedItem();
				if (!actors.contains(actor)) {
					actors.add(actor);
					Integer actorId = (Integer) actorJComboBox.getCurrentId(cbxActor.getSelectedIndex());
					actorsId.add(actorId);
					cbxActor.setSelectedIndex(-1);
				}
				actorsValues.setText(actors.toString());
			}
		});

		JButton btnRemoveGenre = new JButton("<");
		btnRemoveGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeLastItem(genres, genresValues);
			}
		});
		btnRemoveGenre.setBounds(431, 204, 63, 25);
		contentPane.add(btnRemoveGenre);

		JButton btnClearAllGenres = new JButton("<<");
		btnClearAllGenres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems(genres, genresValues);
			}
		});
		btnClearAllGenres.setBounds(501, 204, 63, 25);
		contentPane.add(btnClearAllGenres);
		btnAddActor.setBounds(431, 251, 133, 25);
		contentPane.add(btnAddActor);

		JButton btnAddDirector = new JButton("Add");
		btnAddDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String director = (String) cbxDirector.getSelectedItem();
				if (!directors.contains(director)) {
					directors.add(director);
					Integer directorId = (Integer) directorJComboBox.getCurrentId(cbxDirector.getSelectedIndex());
					directorsId.add(directorId);
					cbxDirector.setSelectedIndex(-1);
				}
				directorsValues.setText(directors.toString());
			}
		});

		JButton btnRemoveActor = new JButton("<");
		btnRemoveActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastItem(actors, actorsValues);
			}
		});
		btnRemoveActor.setBounds(431, 283, 63, 25);
		contentPane.add(btnRemoveActor);

		JButton btnClearAllActors = new JButton("<<");
		btnClearAllActors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems(actors, actorsValues);
			}
		});
		btnClearAllActors.setBounds(501, 283, 63, 25);
		contentPane.add(btnClearAllActors);

		btnAddDirector.setBounds(431, 331, 133, 25);
		contentPane.add(btnAddDirector);

		JButton btnRemoveDirector = new JButton("<");
		btnRemoveDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastItem(directors, directorsValues);
			}
		});
		btnRemoveDirector.setBounds(431, 365, 63, 25);
		contentPane.add(btnRemoveDirector);

		JButton btnClearAllDirectors = new JButton("<<");
		btnClearAllDirectors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems(directors, directorsValues);
			}
		});
		btnClearAllDirectors.setBounds(501, 365, 63, 25);
		contentPane.add(btnClearAllDirectors);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 157, 552, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 237, 552, 2);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 316, 552, 2);
		contentPane.add(separator_2);

	} // Constructor

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

	private void removeLastItem(List list, JLabel jLabel) {
		int nbItems = list.size();
		list.remove(nbItems - 1);
		jLabel.setText(list.toString());
	}

	private void clearAllItems(List list, JLabel jLabel) {
		list.clear();
		jLabel.setText(list.toString());
	}
}
