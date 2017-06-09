package view.frames;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.App;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.Photo;
import view.JComboBoxModels.VipJComboBox;

import javax.swing.JComboBox;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class NewPhotoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewPlace, txtNewDate;
	private Photo newPhoto;
	private List<String> identifiedVip = new ArrayList<>();
	private List<Integer> identifiedVipId = new ArrayList<>();
	private JComboBox cbxVip = new JComboBox();
	private JLabel identifiedVipValues;

	/**
	 * Create the frame.
	 */
	public NewPhotoFrame(MainFrame mainFrame, Photo newPhoto) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 594, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPhotoImport = new JLabel("Import a new photo");
		lblPhotoImport.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhotoImport.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPhotoImport.setBounds(12, 0, 552, 42);
		contentPane.add(lblPhotoImport);

		JLabel lblPhotoPlace = new JLabel("Place");
		lblPhotoPlace.setBounds(22, 99, 117, 16);
		contentPane.add(lblPhotoPlace);

		JLabel lblPhotoDate = new JLabel("Date");
		lblPhotoDate.setBounds(22, 129, 117, 16);
		contentPane.add(lblPhotoDate);
		
		JLabel lblNewVip = new JLabel("Vip");
		lblNewVip.setBounds(22, 282, 113, 16);
		contentPane.add(lblNewVip);
		
		JLabel lblAddedVip = new JLabel("Added Vip");
		lblAddedVip.setBounds(22, 314, 113, 16);
		contentPane.add(lblAddedVip);

		JLabel lblyyyy = new JLabel("(yyyy-mm-dd)");
		lblyyyy.setBounds(317, 129, 104, 16);
		contentPane.add(lblyyyy);
		
		identifiedVipValues = new JLabel("");
		identifiedVipValues.setBounds(147, 314, 274, 16);
		contentPane.add(identifiedVipValues);

		txtNewPlace = new JTextField();
		txtNewPlace.setBounds(129, 96, 417, 22);
		contentPane.add(txtNewPlace);
		txtNewPlace.setColumns(10);

		txtNewDate = new JTextField();
		txtNewDate.setBounds(129, 126, 178, 22);
		contentPane.add(txtNewDate);
		txtNewDate.setColumns(10);

		VipJComboBox vipJComboBox = new VipJComboBox();
		cbxVip.setModel(vipJComboBox);
		cbxVip.setBounds(147, 279, 274, 22);
		contentPane.add(cbxVip);
		
		JButton btnErase = new JButton("Erase");
		btnErase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInfo();
			}
		});
		btnErase.setBounds(86, 464, 158, 25);
		contentPane.add(btnErase);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtNewPlace.getText().isEmpty()) {
						throw new Exception("The place of the photo must be filled.");
					}
					else {
						newPhoto.setPlace(txtNewPlace.getText());
						String[] dateFields = txtNewDate.getText().split("-");
						for(String field : dateFields) {
							System.out.println(field.toString());
							System.out.println(Integer.parseInt(field));
						}
						try {
							
			                LocalDate newDate = LocalDate.of(
			                        Integer.parseInt(dateFields[0]),
			                        Integer.parseInt(dateFields[1]),
			                        Integer.parseInt(dateFields[2])
			                );
			                LocalDate today = LocalDate.now();
			                System.out.println(today.toString());
			                System.out.println(newDate.toString());
			                if (newDate.isAfter(today)) {
			                    throw new Exception("The date must be earlier than today.");
			                }
			                newPhoto.setDate(newDate);
			            } catch (DateTimeException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			                throw new Exception("Invalid Date Format");
			            }
						App.getDaoPhoto().addNewPhoto(newPhoto, identifiedVipId);
						clearInfo();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(330, 464, 158, 25);
		contentPane.add(btnSave);
		
		JButton btnAddVip = new JButton("Add");
		btnAddVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vip = (String) cbxVip.getSelectedItem();
				identifiedVip.add(vip);
				Integer vipId = (Integer) vipJComboBox.getCurrentId(cbxVip.getSelectedIndex());
				identifiedVipId.add(vipId);
				identifiedVipValues.setText(identifiedVip.toString());
				cbxVip.setSelectedIndex(-1);
			}
		});
		btnAddVip.setBounds(431, 278, 133, 25);
		contentPane.add(btnAddVip);
		
		JButton btnRemoveVip = new JButton("<");
		btnRemoveVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLastItem(identifiedVip, identifiedVipValues);
			}
		});
		btnRemoveVip.setBounds(431, 310, 63, 25);
		contentPane.add(btnRemoveVip);
		
		JButton btnClearAllVip = new JButton("<<");
		btnClearAllVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllItems(identifiedVip, identifiedVipValues);
			}
		});
		btnClearAllVip.setBounds(501, 310, 63, 25);
		contentPane.add(btnClearAllVip);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 264, 552, 2);
		contentPane.add(separator_1);
		
		JLabel lvlIdentification = new JLabel("Identify Vip");
		lvlIdentification.setHorizontalAlignment(SwingConstants.CENTER);
		lvlIdentification.setAlignmentX(0.5f);
		lvlIdentification.setBounds(12, 209, 552, 42);
		contentPane.add(lvlIdentification);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 55, 552, 2);
		contentPane.add(separator);
		
		JLabel lblImportPhoto = new JLabel("Import photo");
		lblImportPhoto.setBounds(22, 70, 117, 16);
		contentPane.add(lblImportPhoto);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(129, 66, 148, 25);
		contentPane.add(btnNewButton);
		
		

	}

	private void clearInfo() {
		txtNewPlace.setText("");
		txtNewDate.setText("");
		cbxVip.setSelectedIndex(-1);
		identifiedVip.clear();
		identifiedVipValues.setText("");
	}
	
	private void removeLastItem(List list, JLabel jLabel){
		int nbItems = list.size();
		list.remove(nbItems-1);
		jLabel.setText(list.toString());
	}
	
	private void clearAllItems(List list, JLabel jLabel) {
		list.clear();
		jLabel.setText(list.toString());
	}
}
