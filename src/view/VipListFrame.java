package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAOVip;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JScrollPane;

public class VipListFrame extends JFrame {

	private JPanel contentPane;
	private VipJTable model;
	private boolean outputState;
	private JTable table;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VipListFrame(Frame parent,DAOVip dao) throws SQLException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		model = new VipJTable(dao);
		
		JLabel lblVipList = new JLabel("Vip List");
		lblVipList.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblVipList.setBounds(225, 24, 91, 25);
		contentPane.add(lblVipList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 528, 365);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);
		scrollPane.setViewportView(table);
	}
}
