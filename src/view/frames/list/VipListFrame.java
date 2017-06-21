package view.frames.list;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.App;
import controller.Vip;
import view.JTableModels.VipJTable;
import view.frames.add.AddDirectedMovieFrame;
import view.frames.add.AddPlayedMovieFrame;
import view.frames.add.AddWeddingFrame;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;

public class VipListFrame extends JFrame {

	private JPanel contentPane;
	private VipJTable model;
	private JTable table;
	private Vip currentVip;
	private JTextField txtNewDivorceDate;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public VipListFrame(Frame parent) throws SQLException {

		model = new VipJTable();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMovieList = new JLabel("VIP");
		lblMovieList.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovieList.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblMovieList.setBounds(10, 13, 528, 36);
		contentPane.add(lblMovieList);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 528, 396);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		table.setModel(model);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Marital status",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(548, 61, 238, 243);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblPartner = new JLabel("");
		lblPartner.setBounds(10, 51, 218, 14);
		panel.add(lblPartner);

		JLabel lblWeddingDate = new JLabel("");
		lblWeddingDate.setBounds(10, 96, 218, 14);
		panel.add(lblWeddingDate);

		JLabel lblDivorceDate = new JLabel("");
		lblDivorceDate.setBounds(12, 108, 218, 14);
		panel.add(lblDivorceDate);

		//Button to open the wedding with the selected vip creation frame
		JButton btnAddWedding = new JButton("Add wedding");
		btnAddWedding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddWeddingFrame addWeddingFrame = addWeddingDisplay();
					addWeddingFrame.setVisible(true);
				} catch (SQLException weddingException) {
					weddingException.printStackTrace();
				}
			}
		});
		btnAddWedding.setBounds(12, 135, 216, 23);
		panel.add(btnAddWedding);
		btnAddWedding.setEnabled(false);

		//Button to create a divorce with the selected vip using the associated textfield
		JButton btnAddDivorce = new JButton("Add divorce");
		btnAddDivorce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!App.checkDateFormat(txtNewDivorceDate.getText())) {
					formError("The date format is incorrect: it should be yyyy-mm-dd.");
				} else if (!App.checkDateValue(txtNewDivorceDate.getText())) {
					formError("The date value is incorrect: it should be before today!");
				} else {
					try {
						LocalDate weddingDate = App.getDaoEvent().getMaritialStatus(currentVip).getWeddingDate();
						LocalDate divorceDate = Date.valueOf(txtNewDivorceDate.getText()).toLocalDate();
						if (divorceDate.compareTo(weddingDate) <= 0) {
							formError("The divorce date can't be before the wedding date!");
						} else {
							App.getDaoEvent().addDivorce(Date.valueOf(divorceDate), currentVip.getIdVip(),
									currentVip.getIdPartner());
							clearInfo();
						}
					} catch (Exception divorceException) {
						divorceException.printStackTrace();
					}
				}
			}
		});
		btnAddDivorce.setBounds(12, 160, 216, 23);
		panel.add(btnAddDivorce);
		btnAddDivorce.setEnabled(false);

		JLabel lblNewDivorceDate = new JLabel("Divorce date (yyyy-mm-dd)");
		lblNewDivorceDate.setBounds(12, 185, 216, 23);
		panel.add(lblNewDivorceDate);
		lblNewDivorceDate.setEnabled(false);

		txtNewDivorceDate = new JTextField();
		txtNewDivorceDate.setBounds(12, 209, 214, 23);
		panel.add(txtNewDivorceDate);
		txtNewDivorceDate.setColumns(10);
		txtNewDivorceDate.setEnabled(false);

		//Button to open the frame to add movies to the selected vip
		JButton btnAddPlayedMovie = new JButton("Add a movie he/she played in");
		btnAddPlayedMovie.setBounds(550, 317, 232, 25);
		contentPane.add(btnAddPlayedMovie);
		btnAddPlayedMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddPlayedMovieFrame addPlayedMovieFrame = addPlayedMovieDisplay();
					addPlayedMovieFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddPlayedMovie.setEnabled(false);

		JButton btnAddDirectedMovie = new JButton("Add a movie he/she directed");
		btnAddDirectedMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddDirectedMovieFrame addDirectedMovieFrame = addDirectedMovieDisplay();
					addDirectedMovieFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddDirectedMovie.setBounds(550, 355, 232, 25);
		contentPane.add(btnAddDirectedMovie);
		btnAddDirectedMovie.setEnabled(false);

		//Event handling to know which buttons can be pressed depending on the selected vip, also displays the marital status
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() != -1) {
					btnAddPlayedMovie.setEnabled(true);
					btnAddDirectedMovie.setEnabled(true);
					try {
						currentVip = model.getVipList().get(table.getSelectedRow());
						if (model.getVipList().get(table.getSelectedRow()).getIdPartner() != -1) {
							lblPartner.setText("Wed to : " + App.getDaoVip().getPartner(currentVip).getSurname()[0]
									+ " " + App.getDaoVip().getPartner(model.getVipList().get(table.getSelectedRow()))
											.getName());
							lblWeddingDate.setText(
									"On : " + App.getDaoEvent().getMaritialStatus(currentVip).getWeddingDate());
							btnAddWedding.setEnabled(false);
							lblNewDivorceDate.setEnabled(true);
							txtNewDivorceDate.setEnabled(true);
						} else {
							lblPartner.setText("Single");
							lblWeddingDate.setText("");
							btnAddWedding.setEnabled(true);
							lblNewDivorceDate.setEnabled(false);
							txtNewDivorceDate.setEnabled(false);
						}
						if (App.getDaoEvent().getMaritialStatus(currentVip) != null
								&& App.getDaoEvent().getMaritialStatus(currentVip).getDivorceDate() != null) {
							lblDivorceDate.setText(
									"Divorced : " + App.getDaoEvent().getMaritialStatus(currentVip).getDivorceDate());
							btnAddDivorce.setEnabled(false);
						} else {
							lblDivorceDate.setText("");
							btnAddDivorce.setEnabled(true);
						}
						if (btnAddWedding.isEnabled()) {
							btnAddDivorce.setEnabled(false);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

	} // Constructor

	private AddPlayedMovieFrame addPlayedMovieDisplay() throws SQLException {
		return new AddPlayedMovieFrame(this, currentVip);
	}

	private AddDirectedMovieFrame addDirectedMovieDisplay() throws SQLException {
		return new AddDirectedMovieFrame(this, currentVip);
	}

	private AddWeddingFrame addWeddingDisplay() throws SQLException {
		return new AddWeddingFrame(this, currentVip);
	}

	public void formError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Form error", JOptionPane.ERROR_MESSAGE);
	}

	private void clearInfo() {
		txtNewDivorceDate.setText("");
	}
}
