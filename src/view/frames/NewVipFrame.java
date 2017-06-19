package view.frames;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.App;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.Vip;
import view.JComboBoxModels.CountryJComboBox;

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
	private List<String> nationalities = new ArrayList<>();

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
		lblVipCreation.setBounds(12, 0, 409, 42);
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

		JLabel lblNewNationality1 = new JLabel("Nationality");
		lblNewNationality1.setBounds(22, 264, 113, 16);
		contentPane.add(lblNewNationality1);

		JLabel lblAddedNationalities = new JLabel("Added Nationalities");
		lblAddedNationalities.setBounds(22, 299, 113, 16);
		contentPane.add(lblAddedNationalities);

		JLabel lblNewSurname2Opt = new JLabel("(optional)");
		lblNewSurname2Opt.setBounds(317, 125, 104, 16);
		contentPane.add(lblNewSurname2Opt);

		JLabel lblyyyymmdd = new JLabel("(yyyy-mm-dd)");
		lblyyyymmdd.setBounds(317, 160, 104, 16);
		contentPane.add(lblyyyymmdd);

		JLabel nationalitiesValues = new JLabel("");
		nationalitiesValues.setBounds(147, 299, 158, 16);
		contentPane.add(nationalitiesValues);

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

		JComboBox cbxNationality = new JComboBox();
		cbxNationality.setModel(new CountryJComboBox());
		cbxNationality.setBounds(147, 261, 158, 22);
		contentPane.add(cbxNationality);

		JButton btnErase = new JButton("Erase");
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInfo(cbxNationality, nationalitiesValues);
			}
		});
		btnErase.setBounds(147, 331, 158, 25);
		contentPane.add(btnErase);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtNewName.getText().isEmpty()) {
						formError("You must fill the name field");
					}
					else if (txtNewSurname1.getText().isEmpty()) {
						formError("You must fill the surname field");
					} else {
						newVip.setName(txtNewName.getText());
						String[] surnames = { txtNewSurname1.getText(), txtNewSurname2.getText() };
						newVip.setSurname(surnames);
						newVip.setBirthdate(Date.valueOf(txtNewBirthdate.getText()));
						newVip.setBirthplace(txtNewBirthplace.getText());
						App.getDaoVip().addNewVip(newVip, nationalities);
						clearInfo(cbxNationality, nationalitiesValues);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(147, 369, 158, 25);
		contentPane.add(btnSave);

		JButton btnAddNationality = new JButton("Add");
		btnAddNationality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nationality = (String) cbxNationality.getSelectedItem();
				if(!nationalities.contains(nationality)) {
					nationalities.add(nationality);
					nationalitiesValues.setText(nationalities.toString());
					cbxNationality.setSelectedIndex(-1);
				}
			}
		});
		btnAddNationality.setBounds(317, 260, 104, 25);
		contentPane.add(btnAddNationality);
		
		JButton btnRemoveNationality = new JButton("<");
		btnRemoveNationality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int nbNationalities = nationalities.size();
				nationalities.remove(nbNationalities-1);
				nationalitiesValues.setText(nationalities.toString());
			}
		});
		btnRemoveNationality.setBounds(317, 295, 51, 25);
		contentPane.add(btnRemoveNationality);
		
		JButton btnClearAllNat = new JButton("<<");
		btnClearAllNat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nationalities.clear();
				nationalitiesValues.setText(nationalities.toString());
			}
		});
		btnClearAllNat.setBounds(370, 295, 51, 25);
		contentPane.add(btnClearAllNat);

	}

	private void clearInfo(JComboBox cbxNationality, JLabel nationalitiesValues) {
		txtNewName.setText("");
		txtNewSurname1.setText("");
		txtNewSurname2.setText("");
		txtNewBirthdate.setText("");
		txtNewBirthplace.setText("");
		cbxNationality.setSelectedIndex(-1);
		nationalities.clear();
		System.out.println(nationalities.toString());
		nationalitiesValues.setText("");
	}
	
	public void formError(String msg){
		JOptionPane.showMessageDialog(this, msg, "Form error", JOptionPane.ERROR_MESSAGE);
	}
}
