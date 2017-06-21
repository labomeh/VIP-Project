package view.frames.add;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import view.JComboBoxModels.ActorsJComboBox;
import view.frames.list.MovieListFrame;

import java.awt.Dimension;
import javax.swing.SwingConstants;

public class AddToCastingFrame extends JFrame {

	private JPanel contentPane;
	private List<String> actors = new ArrayList<>();
	private List<Integer> actorsId = new ArrayList<>();
	private ActorsJComboBox actorsJComboBox;
	private JLabel actorsValues;

	public AddToCastingFrame(MovieListFrame movieListFrame, Movie movie) throws SQLException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddToCasting = new JLabel("Add Actors For");
		lblAddToCasting.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddToCasting.setBounds(10, 0, 476, 52);
		getContentPane().add(lblAddToCasting);

		JLabel lblSelectedMovie = new JLabel(movie.getMovieTitle());
		lblSelectedMovie.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedMovie.setBounds(10, 53, 476, 52);
		getContentPane().add(lblSelectedMovie);

		JLabel lblActor = new JLabel("Actor");
		lblActor.setBounds(11, 127, 113, 16);
		getContentPane().add(lblActor);

		JLabel lblSelectedActors = new JLabel("Added Actors");
		lblSelectedActors.setBounds(10, 162, 113, 16);
		getContentPane().add(lblSelectedActors);

		actorsValues = new JLabel("");
		actorsValues.setBounds(135, 162, 212, 16);
		getContentPane().add(actorsValues);

		JComboBox cbxActor = new JComboBox();
		actorsJComboBox = new ActorsJComboBox(movie);
		cbxActor.setModel(actorsJComboBox);
		cbxActor.setBounds(135, 124, 212, 22);
		getContentPane().add(cbxActor);

		JButton btnAddToCasting = new JButton("Add");
		btnAddToCasting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String actor = (String) cbxActor.getSelectedItem();
				if (!actors.contains(actor)) {
					actors.add(actor);
					Integer actorId = (Integer) actorsJComboBox.getCurrentId(cbxActor.getSelectedIndex());
					actorsId.add(actorId);
					cbxActor.setSelectedIndex(-1);
				}
				actorsValues.setText(actors.toString());
			}
		});
		btnAddToCasting.setBounds(358, 123, 127, 25);
		getContentPane().add(btnAddToCasting);

		JButton btnRemoveActor = new JButton("<");
		btnRemoveActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastItem(actors, actorsValues);
			}
		});
		btnRemoveActor.setBounds(359, 158, 61, 25);
		getContentPane().add(btnRemoveActor);

		JButton btnClearAllActors = new JButton("<<");
		btnClearAllActors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems(actors, actorsValues);
			}
		});
		btnClearAllActors.setBounds(425, 158, 61, 25);
		getContentPane().add(btnClearAllActors);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (actors.isEmpty()) {
						throw new Exception("No actor selected");
					} else {
						App.getDaoMovie().addMovieCasting(movie, actorsId);
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
		actors.clear();
		actorsValues.setText("");
	}
}
