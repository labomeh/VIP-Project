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
import java.sql.SQLException;

public class VipListFrame extends JFrame {

	private JPanel contentPane;
	private VipJTable table;
	private boolean outputState;
	private ;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public VipListFrame(mainFrame frame) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new VipJTable(connection);
		table.setBounds(45, 58, 463, 368);
		contentPane.add(table);
		
		JLabel lblVipList = new JLabel("Vip List");
		lblVipList.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblVipList.setBounds(225, 24, 91, 25);
		contentPane.add(lblVipList);
	}
}
