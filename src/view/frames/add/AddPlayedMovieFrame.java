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
import controller.Vip;
import view.JComboBoxModels.NotPlayedMovieJComboBox;
import view.frames.list.VipListFrame;

import javax.swing.SwingConstants;

public class AddPlayedMovieFrame extends JFrame {

	private JPanel contentPane;
	private List<String> movies = new ArrayList<>();
	private List<Integer> moviesVisa = new ArrayList<>();
	private NotPlayedMovieJComboBox moviesJComboBox;
	private JLabel moviesValues;

	public AddPlayedMovieFrame(VipListFrame vipListFrame, Vip vip) throws SQLException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddPlayedMovies = new JLabel("Add Played Movies For");
		lblAddPlayedMovies.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPlayedMovies.setBounds(12, 0, 499, 52);
		getContentPane().add(lblAddPlayedMovies);

		JLabel lblSelectedActor = new JLabel(vip.getSurname()[0] + " " + vip.getName());
		lblSelectedActor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedActor.setBounds(12, 53, 499, 52);
		getContentPane().add(lblSelectedActor);

		JLabel lblMovie = new JLabel("Movie");
		lblMovie.setBounds(12, 118, 113, 16);
		getContentPane().add(lblMovie);

		JLabel lblSelectedMovies = new JLabel("Added Movies");
		lblSelectedMovies.setBounds(12, 153, 113, 16);
		getContentPane().add(lblSelectedMovies);

		moviesValues = new JLabel("");
		moviesValues.setBounds(136, 153, 259, 16);
		getContentPane().add(moviesValues);

		JComboBox cbxMovie = new JComboBox();
		moviesJComboBox = new NotPlayedMovieJComboBox(vip);
		cbxMovie.setModel(moviesJComboBox);
		cbxMovie.setBounds(137, 115, 258, 22);
		getContentPane().add(cbxMovie);

		JButton btnAddPlayedMovie = new JButton("Add");
		btnAddPlayedMovie.addActionListener(new ActionListener() {
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
		btnAddPlayedMovie.setBounds(407, 114, 104, 25);
		getContentPane().add(btnAddPlayedMovie);

		JButton btnRemoveMovie = new JButton("<");
		btnRemoveMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastItem(movies, moviesValues);
			}
		});
		btnRemoveMovie.setBounds(407, 149, 51, 25);
		getContentPane().add(btnRemoveMovie);

		JButton btnClearAllMovies = new JButton("<<");
		btnClearAllMovies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems(movies, moviesValues);
			}
		});
		btnClearAllMovies.setBounds(460, 149, 51, 25);
		getContentPane().add(btnClearAllMovies);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (movies.isEmpty()) {
						throw new Exception("No movie selected");
					} else {
						App.getDaoVip().addPlayedMovies(vip, moviesVisa);
						clearInfo();
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		btnSave.setBounds(175, 216, 158, 25);
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
