package view.frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Frame;

import java.sql.SQLException;

import view.JTableModels.EventJTable;

public class EventListFrame extends JFrame {

	private JPanel contentPane;
	private EventJTable model;
	private JTable table;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public EventListFrame(Frame parent) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 564, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		model = new EventJTable();

		JLabel lblEventList = new JLabel("Event List");
		lblEventList.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEventList.setBounds(225, 24, 91, 25);
		contentPane.add(lblEventList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 528, 365);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);
		scrollPane.setViewportView(table);
	}

}
