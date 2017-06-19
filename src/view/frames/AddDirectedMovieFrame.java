package view.frames;

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
import controller.Vip;
import view.JComboBoxModels.NotPlayedMovieJComboBox;

public class AddDirectedMovieFrame extends JFrame {

	private JPanel contentPane;
	private List<String> movies = new ArrayList<>();
	private List<Integer> moviesVisa = new ArrayList<>();
	private NotPlayedMovieJComboBox moviesJComboBox;
	private JLabel moviesValues;

	public AddDirectedMovieFrame(VipListFrame vipListFrame, Vip vip) throws SQLException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddDirectedMovies = new JLabel("Add Directed Movies For");
		lblAddDirectedMovies.setBounds(0, 0, 449, 52);
		getContentPane().add(lblAddDirectedMovies);

		JLabel lblSelectedDirector = new JLabel(vip.getSurname()[0] + vip.getName());
		lblSelectedDirector.setBounds(0, 53, 449, 52);
		getContentPane().add(lblSelectedDirector);

		JLabel lblMovie = new JLabel("Movie");
		lblMovie.setBounds(10, 127, 113, 16);
		getContentPane().add(lblMovie);

		JLabel lblSelectedMovies = new JLabel("Added Movies");
		lblSelectedMovies.setBounds(20, 156, 113, 16);
		getContentPane().add(lblSelectedMovies);

		moviesValues = new JLabel("");
		moviesValues.setBounds(135, 162, 158, 16);
		getContentPane().add(moviesValues);

		JComboBox cbxMovie = new JComboBox();
		moviesJComboBox = new NotPlayedMovieJComboBox(vip);
		cbxMovie.setModel(moviesJComboBox);
		cbxMovie.setBounds(135, 124, 158, 22);
		getContentPane().add(cbxMovie);

		JButton btnAddDirectedMovie = new JButton("Add");
		btnAddDirectedMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String movieTitle = (String) cbxMovie.getSelectedItem();
				if (!movies.contains(movieTitle)) {
					movies.add(movieTitle);
					Integer movieVisa = (Integer) moviesJComboBox.getCurrentId(cbxMovie.getSelectedIndex());
					moviesVisa.add(movieVisa);
					cbxMovie.setSelectedIndex(-1);
				}
				moviesValues.setText(movies.toString());
			}
		});
		btnAddDirectedMovie.setBounds(305, 123, 104, 25);
		getContentPane().add(btnAddDirectedMovie);

		JButton btnRemoveMovie = new JButton("<");
		btnRemoveMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastItem(movies, moviesValues);
			}
		});
		btnRemoveMovie.setBounds(305, 158, 51, 25);
		getContentPane().add(btnRemoveMovie);

		JButton btnClearAllMovies = new JButton("<<");
		btnClearAllMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems(movies, moviesValues);
			}
		});
		btnClearAllMovies.setBounds(358, 158, 51, 25);
		getContentPane().add(btnClearAllMovies);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (movies.isEmpty()) {
						throw new Exception("No movie selected");
					} else {
						App.getDaoVip().addDirectedMovies(vip, moviesVisa);
						clearInfo();
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		btnSave.setBounds(135, 232, 158, 25);
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
		movies.clear();
		moviesValues.setText("");
	}
}
