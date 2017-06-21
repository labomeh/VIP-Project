package view.frames.add;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.App;
import controller.Movie;
import view.JComboBoxModels.DirectorsJComboBox;
import view.frames.MovieListFrame;

public class AddToDirectionFrame extends JFrame {

	private JPanel contentPane;
	private List<String> directors = new ArrayList<>();
	private List<Integer> directorsId = new ArrayList<>();
	private DirectorsJComboBox directorsJComboBox;
	private JLabel directorsValues;

	public AddToDirectionFrame(MovieListFrame movieListFrame, Movie movie) throws SQLException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddToDirection = new JLabel("Add Director For");
		lblAddToDirection.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddToDirection.setBounds(10, 0, 476, 52);
		getContentPane().add(lblAddToDirection);

		JLabel lblSelectedMovie = new JLabel(movie.getMovieTitle());
		lblSelectedMovie.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedMovie.setBounds(10, 53, 476, 52);
		getContentPane().add(lblSelectedMovie);

		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(11, 127, 113, 16);
		getContentPane().add(lblDirector);

		JLabel lblSelectedDirectors = new JLabel("Added Directors");
		lblSelectedDirectors.setBounds(10, 162, 113, 16);
		getContentPane().add(lblSelectedDirectors);

		directorsValues = new JLabel("");
		directorsValues.setBounds(135, 162, 212, 16);
		getContentPane().add(directorsValues);

		JComboBox cbxDirector = new JComboBox();
		directorsJComboBox = new DirectorsJComboBox(movie);
		cbxDirector.setModel(directorsJComboBox);
		cbxDirector.setBounds(135, 124, 212, 22);
		getContentPane().add(cbxDirector);

		JButton btnAddToDirection = new JButton("Add");
		btnAddToDirection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String director = (String) cbxDirector.getSelectedItem();
				if (!directors.contains(director)) {
					directors.add(director);
					Integer directorId = (Integer) directorsJComboBox.getCurrentId(cbxDirector.getSelectedIndex());
					directorsId.add(directorId);
					cbxDirector.setSelectedIndex(-1);
				}
				directorsValues.setText(directors.toString());
			}
		});
		btnAddToDirection.setBounds(358, 123, 127, 25);
		getContentPane().add(btnAddToDirection);

		JButton btnRemoveDirector = new JButton("<");
		btnRemoveDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastItem(directors, directorsValues);
			}
		});
		btnRemoveDirector.setBounds(359, 158, 61, 25);
		getContentPane().add(btnRemoveDirector);

		JButton btnClearAllDirectors = new JButton("<<");
		btnClearAllDirectors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems(directors, directorsValues);
			}
		});
		btnClearAllDirectors.setBounds(425, 158, 61, 25);
		getContentPane().add(btnClearAllDirectors);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (directors.isEmpty()) {
						throw new Exception("No director selected");
					} else {
						App.getDaoMovie().addMovieDirection(movie, directorsId);
						clearInfo();
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		btnSave.setBounds(135, 214, 212, 25);
		getContentPane().add(btnSave);
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

	private void clearInfo() {
		directors.clear();
		directorsValues.setText("");
	}
}
