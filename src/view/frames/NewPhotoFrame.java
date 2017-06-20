package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;
import java.io.File;

import app.App;
import controller.Photo;
import view.JComboBoxModels.VipJComboBox;

public class NewPhotoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewPlace, txtNewDate;
	private List<String> identifiedVip = new ArrayList<>();
	private List<Integer> identifiedVipId = new ArrayList<>();
	private JComboBox cbxVip = new JComboBox();
	private JLabel identifiedVipValues;
	private JFileChooser fileChooser = new JFileChooser();
	private JLabel lblSelectedFile;

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

		JLabel lvlIdentification = new JLabel("Identify Vip");
		lvlIdentification.setHorizontalAlignment(SwingConstants.CENTER);
		lvlIdentification.setAlignmentX(0.5f);
		lvlIdentification.setBounds(12, 209, 552, 42);
		contentPane.add(lvlIdentification);

		JLabel lblImportPhoto = new JLabel("Import photo");
		lblImportPhoto.setBounds(22, 74, 117, 16);
		contentPane.add(lblImportPhoto);

		lblSelectedFile = new JLabel("");
		lblSelectedFile.setBounds(337, 74, 227, 16);
		contentPane.add(lblSelectedFile);

		JLabel lblPhotoPlace = new JLabel("Place");
		lblPhotoPlace.setBounds(22, 107, 117, 16);
		contentPane.add(lblPhotoPlace);

		JLabel lblPhotoDate = new JLabel("Date");
		lblPhotoDate.setBounds(22, 142, 117, 16);
		contentPane.add(lblPhotoDate);

		JLabel lblyyyy = new JLabel("(yyyy-mm-dd)");
		lblyyyy.setBounds(337, 142, 104, 16);
		contentPane.add(lblyyyy);

		JLabel lblNewVip = new JLabel("Vip");
		lblNewVip.setBounds(22, 282, 113, 16);
		contentPane.add(lblNewVip);

		JLabel lblAddedVip = new JLabel("Added Vip");
		lblAddedVip.setBounds(22, 314, 113, 16);
		contentPane.add(lblAddedVip);

		identifiedVipValues = new JLabel("");
		identifiedVipValues.setBounds(147, 314, 274, 16);
		contentPane.add(identifiedVipValues);

		txtNewPlace = new JTextField();
		txtNewPlace.setBounds(147, 104, 417, 22);
		contentPane.add(txtNewPlace);
		txtNewPlace.setColumns(10);

		txtNewDate = new JTextField();
		txtNewDate.setBounds(147, 139, 178, 22);
		contentPane.add(txtNewDate);
		txtNewDate.setColumns(10);

		VipJComboBox vipJComboBox = new VipJComboBox();
		cbxVip.setModel(vipJComboBox);
		cbxVip.setBounds(147, 279, 274, 22);
		contentPane.add(cbxVip);

		JButton btnSelectFile = new JButton("Select a file");
		btnSelectFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				fileChooser = new JFileChooser(new File("."));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
				fileChooser.setDialogTitle("Select a photo");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showDialog(btnSelectFile, "Select");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String fileName = file.getName();
					newPhoto.setFileName(fileName);
					lblSelectedFile.setText(fileName);
				}
			}
		});
		btnSelectFile.setBounds(147, 69, 178, 25);
		contentPane.add(btnSelectFile);

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
					if(lblSelectedFile.getText().equals("")) {
						formError("No file selected");					
					} else if (!App.checkDateFormat(txtNewDate.getText())) {
						formError("The date format is incorrect or the field is empty: it should be yyyy-mm-dd.");
					} else if (!App.checkDateValue(txtNewDate.getText())) {
						formError("The date value is incorrect: it should be before today!");
					} else {
						newPhoto.setPlace(txtNewPlace.getText());
						String[] dateFields = txtNewDate.getText().split("-");
						newPhoto.setDate(Date.valueOf(txtNewDate.getText()));
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

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 264, 552, 2);
		contentPane.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 55, 552, 2);
		contentPane.add(separator);

	} // NewPhotoFrame contructor

	private void clearInfo() {
		txtNewPlace.setText("");
		txtNewDate.setText("");
		cbxVip.setSelectedIndex(-1);
		identifiedVip.clear();
		identifiedVipId.clear();
		identifiedVipValues.setText("");
		lblSelectedFile.setText("");
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
	
	public void formError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Form error", JOptionPane.ERROR_MESSAGE);
	}

}
