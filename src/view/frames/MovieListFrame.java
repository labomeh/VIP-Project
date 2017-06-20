package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.SQLException;

import app.App;
import controller.Movie;
import view.JTableModels.MovieJTable;

public class MovieListFrame extends JFrame {

	private JPanel contentPane;
	private MovieJTable model;
	private JTable table;
	private Movie currentMovie;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public MovieListFrame(Frame parent) throws SQLException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		model = new MovieJTable(App.getDaoMovie());

		JLabel lblMovieList = new JLabel("Movies");
		lblMovieList.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblMovieList.setBounds(225, 24, 91, 25);
		contentPane.add(lblMovieList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 528, 365);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);
		scrollPane.setViewportView(table);

		JButton btnAddActor = new JButton("Add actors to casting");
		btnAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddToCastingFrame addToCastingFrame = addToCastingDisplay();
					addToCastingFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddActor.setBounds(21, 439, 242, 25);
		contentPane.add(btnAddActor);

		JButton btnAddDirector = new JButton("Add a director");
		btnAddDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddToDirectionFrame addToDirectionFrame = addToDirectionDisplay();
					addToDirectionFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddDirector.setBounds(284, 439, 242, 25);
		contentPane.add(btnAddDirector);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() != -1) {
					try {
						currentMovie = model.getMovieList().get(table.getSelectedRow());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

	} // Constructor

	private AddToCastingFrame addToCastingDisplay() throws SQLException {
		return new AddToCastingFrame(this, currentMovie);
	}

	private AddToDirectionFrame addToDirectionDisplay() throws SQLException {
		return new AddToDirectionFrame(this, currentMovie);
	}
}
