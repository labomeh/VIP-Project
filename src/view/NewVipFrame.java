package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Vip;
import model.DAOVip;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;

public class NewVipFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewName;
	private JTextField txtNewSurname1;
	private JTextField txtNewSurname2;
	private JTextField txtNewBirthDate;
	private JTextField txtNewBirthPlace;

	/**
	 * Create the frame.
	 */
	public NewVipFrame(mainFrame mainFrame, DAOVip daoVip, Vip newVip) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVipCreation = new JLabel("VIP Creation");
		lblVipCreation.setBounds(137, 13, 210, 42);
		contentPane.add(lblVipCreation);
		
		JLabel lblNewName = new JLabel("Last Name");
		lblNewName.setBounds(63, 55, 56, 16);
		contentPane.add(lblNewName);
		
		txtNewName = new JTextField();
		txtNewName.setBounds(135, 52, 116, 22);
		contentPane.add(txtNewName);
		txtNewName.setColumns(10);
		
		JLabel lblNewSurname1 = new JLabel("First Name 1");
		lblNewSurname1.setBounds(63, 87, 56, 16);
		contentPane.add(lblNewSurname1);
		
		txtNewSurname1 = new JTextField();
		txtNewSurname1.setBounds(135, 84, 116, 22);
		contentPane.add(txtNewSurname1);
		txtNewSurname1.setColumns(10);
		
		JLabel lblNewSurname2 = new JLabel("First Name 2");
		lblNewSurname2.setBounds(63, 119, 56, 16);
		contentPane.add(lblNewSurname2);
		
		JLabel lblNewSurname2Opt = new JLabel("(optional)");
		lblNewSurname2Opt.setBounds(261, 119, 56, 16);
		contentPane.add(lblNewSurname2Opt);
		
		txtNewSurname2 = new JTextField();
		txtNewSurname2.setBounds(137, 119, 116, 22);
		contentPane.add(txtNewSurname2);
		txtNewSurname2.setColumns(10);
		
		JLabel lblNewBirthdate = new JLabel("Birthdate");
		lblNewBirthdate.setBounds(63, 148, 56, 16);
		contentPane.add(lblNewBirthdate);
		
		txtNewBirthDate = new JTextField();
		txtNewBirthDate.setBounds(137, 145, 116, 22);
		contentPane.add(txtNewBirthDate);
		txtNewBirthDate.setColumns(10);
		
		JLabel lblNewBirthplace = new JLabel("Birthplace");
		lblNewBirthplace.setBounds(63, 183, 56, 16);
		contentPane.add(lblNewBirthplace);
		
		txtNewBirthPlace = new JTextField();
		txtNewBirthPlace.setBounds(135, 180, 116, 22);
		contentPane.add(txtNewBirthPlace);
		txtNewBirthPlace.setColumns(10);
	}
}
