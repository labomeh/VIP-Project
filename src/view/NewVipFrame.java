package view;

import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.App;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.Vip;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.sql.Date;

public class NewVipFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewName;
	private JTextField txtNewSurname1;
	private JTextField txtNewSurname2;
	private JTextField txtNewBirthdate;
	private JTextField txtNewBirthplace;
	private Vip newVip;
	

	/**
	 * Create the frame.
	 */
	public NewVipFrame(MainFrame mainFrame, Vip newVip) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVipCreation = new JLabel("VIP Creation");
		lblVipCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblVipCreation.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblVipCreation.setBounds(12, 0, 374, 42);
		contentPane.add(lblVipCreation);

		JLabel lblNewName = new JLabel("Last Name");
		lblNewName.setBounds(22, 55, 113, 16);
		contentPane.add(lblNewName);
		
		JLabel lblNewSurname1 = new JLabel("First Name 1");
		lblNewSurname1.setBounds(22, 90, 113, 16);
		contentPane.add(lblNewSurname1);
		
		JLabel lblNewSurname2 = new JLabel("First Name 2");
		lblNewSurname2.setBounds(22, 125, 113, 16);
		contentPane.add(lblNewSurname2);
				
		JLabel lblNewBirthdate = new JLabel("Birthdate");
		lblNewBirthdate.setBounds(22, 160, 113, 16);
		contentPane.add(lblNewBirthdate);
				
		JLabel lblNewBirthplace = new JLabel("Birthplace");
		lblNewBirthplace.setBounds(22, 195, 113, 16);
		contentPane.add(lblNewBirthplace);
				
		JLabel lblNewNationality1 = new JLabel("Nationality 1");
		lblNewNationality1.setBounds(22, 264, 113, 16);
		contentPane.add(lblNewNationality1);
		
		JLabel lblNewNationality2 = new JLabel("Nationality 2");
		lblNewNationality2.setBounds(22, 299, 113, 16);
		contentPane.add(lblNewNationality2);

		JLabel lblNewSurname2Opt = new JLabel("(optional)");
		lblNewSurname2Opt.setBounds(317, 125, 104, 16);
		contentPane.add(lblNewSurname2Opt);
		
		JLabel lblyyyymmdd = new JLabel("(yyyy-mm-dd)");
		lblyyyymmdd.setBounds(317, 160, 104, 16);
		contentPane.add(lblyyyymmdd);
		
		JLabel label = new JLabel("(optional)");
		label.setBounds(317, 299, 104, 16);
		contentPane.add(label);

		txtNewName = new JTextField();
		txtNewName.setBounds(147, 52, 158, 22);
		contentPane.add(txtNewName);
		txtNewName.setColumns(10);

		txtNewSurname1 = new JTextField();
		txtNewSurname1.setBounds(147, 87, 158, 22);
		contentPane.add(txtNewSurname1);
		txtNewSurname1.setColumns(10);

		txtNewSurname2 = new JTextField();
		txtNewSurname2.setBounds(147, 122, 158, 22);
		contentPane.add(txtNewSurname2);
		txtNewSurname2.setColumns(10);

		txtNewBirthdate = new JTextField();
		txtNewBirthdate.setBounds(147, 157, 158, 22);
		contentPane.add(txtNewBirthdate);
		txtNewBirthdate.setColumns(10);

		txtNewBirthplace = new JTextField();
		txtNewBirthplace.setBounds(147, 192, 158, 56);
		contentPane.add(txtNewBirthplace);
		txtNewBirthplace.setColumns(10);

		JComboBox cbxNationality1 = new JComboBox();
		cbxNationality1.setModel(new CountryJComboBox());
		cbxNationality1.setBounds(147, 261, 158, 22);
		contentPane.add(cbxNationality1);
		
		JComboBox cbxNationality2 = new JComboBox();
		cbxNationality2.setModel(new CountryJComboBox());
		cbxNationality2.setBounds(147, 296, 158, 22);
		contentPane.add(cbxNationality2);
		
		JButton btnErase = new JButton("Erase");
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNewName.setText("");
				txtNewSurname1.setText("");
				txtNewSurname2.setText("");
				txtNewBirthdate.setText("");
				txtNewBirthplace.setText("");
				cbxNationality1.setSelectedIndex(-1);
				cbxNationality2.setSelectedIndex(-1);
			}
		});
		btnErase.setBounds(147, 331, 158, 25);
		contentPane.add(btnErase);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtNewName.getText().isEmpty()) {
						throw new Exception("You must fill the name field");
					}
					if (txtNewSurname1.getText().isEmpty()) {
						throw new Exception("You must fill the surname field");
					} else {
						newVip.setName(txtNewName.getText());
						String[] surnames = {txtNewSurname1.getText(), txtNewSurname2.getText()};
						newVip.setSurname(surnames);
						newVip.setBirthdate(Date.valueOf(txtNewBirthdate.getText()));
						newVip.setBirthplace(txtNewBirthplace.getText());
						String nationality1 = (String) cbxNationality1.getSelectedItem();
						String nationality2 = (String) cbxNationality2.getSelectedItem();
						App.getDaoVip().addNewVip(newVip, nationality1, nationality2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(147, 369, 158, 25);
		contentPane.add(btnSave);
	}
}
