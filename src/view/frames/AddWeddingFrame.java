package view.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.App;
import controller.Event;
import controller.Vip;
import view.JTableModels.VipJTable;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddWeddingFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JTable table;
	private VipJTable model;
	private JTextField txtPlace;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public AddWeddingFrame(VipListFrame vipListFrame, Vip vip) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		model = new VipJTable();

		JLabel lblDate = new JLabel("Wedding Date");
		lblDate.setBounds(10, 336, 80, 14);
		contentPane.add(lblDate);

		txtDate = new JTextField();
		txtDate.setBounds(139, 333, 109, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);

		JLabel label = new JLabel("");
		label.setBounds(10, 34, 399, 14);
		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 315);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		table.setModel(model);
		scrollPane.setViewportView(table);

		txtPlace = new JTextField();
		txtPlace.setBounds(139, 364, 270, 20);
		contentPane.add(txtPlace);
		txtPlace.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtDate.getText().isEmpty()) {
					formError("You must fill the date field");
				} else if (!App.checkDateFormat(txtDate.getText())) {
					formError("The date format is incorrect: it should be yyyy-mm-dd.");
				} else if (!App.checkDateValue(txtDate.getText())) {
					formError("The date value is incorrect: it should be before today!");
				} else if (txtPlace.getText().isEmpty()) {
					formError("You must fill the place field");
				} else {
					Event event = new Event(vip.getIdVip(), Date.valueOf(txtDate.getText()).toLocalDate(),
							model.getVipList().get(table.getSelectedRow()).getIdVip(), txtPlace.getText(), null);

					try {
						App.getDaoEvent().addWedding(event);
						clearInfo();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnAdd.setBounds(203, 399, 59, 23);
		contentPane.add(btnAdd);
		btnAdd.setEnabled(false);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() != -1) {
					if (model.getVipList().get(table.getSelectedRow()).getIdPartner() == -1) {
						btnAdd.setEnabled(true);
					} else {
						btnAdd.setEnabled(false);
					}
				}
			}
		});

		JLabel lblWeddingPlace = new JLabel("Wedding Place");
		lblWeddingPlace.setBounds(10, 367, 80, 14);
		contentPane.add(lblWeddingPlace);

		JLabel lblYyyymmdd = new JLabel("(yyyy-mm-dd)");
		lblYyyymmdd.setBounds(258, 336, 93, 14);
		contentPane.add(lblYyyymmdd);
	}

	public void formError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Form error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void clearInfo() {
		txtDate.setText("");
		txtPlace.setText("");
	}
}
