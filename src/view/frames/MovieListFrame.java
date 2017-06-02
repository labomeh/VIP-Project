package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.App;
import view.JTableModels.MovieJTable;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JScrollPane;

public class MovieListFrame extends JFrame {

	private JPanel contentPane;
	private MovieJTable model;
	private boolean outputState;
	private JTable table;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public MovieListFrame(Frame parent) throws SQLException {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 564, 476);
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
		
	} // Constructor
	
}
