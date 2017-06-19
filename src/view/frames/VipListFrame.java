package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
<<<<<<< HEAD
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.sql.Date;
import java.sql.SQLException;
=======
>>>>>>> origin/master
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.SQLException;

import app.App;
import controller.Vip;
import view.JTableModels.VipJTable;

public class VipListFrame extends JFrame {

	private JPanel contentPane;
	private VipJTable model;
	private JTable table;
	private Vip currentVip;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public VipListFrame(Frame parent) throws SQLException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		model = new VipJTable();
<<<<<<< HEAD
		
		JLabel lblMovieList = new JLabel("VIP");
=======

		JLabel lblMovieList = new JLabel("Movies");
>>>>>>> origin/master
		lblMovieList.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblMovieList.setBounds(225, 24, 91, 25);
		contentPane.add(lblMovieList);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 61, 528, 365);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		table.setModel(model);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Marital status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(548, 61, 238, 194);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnAddDivorce = new JButton("Add divorce");
		btnAddDivorce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = DialogDivorceDate();
				if(Date.valueOf(s) != null)
					try {
						App.getDaoEvent().addDivorce(Date.valueOf(s), currentVip.getIdVip(), currentVip.getIdPartner());
						model = new VipJTable();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnAddDivorce.setBounds(121, 160, 89, 23);
		panel.add(btnAddDivorce);
<<<<<<< HEAD
		btnAddDivorce.setEnabled(false);
		
=======

>>>>>>> origin/master
		JButton btnAddWedding = new JButton("Add wedding");
		btnAddWedding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddWeddingFrame addWeddingFrame = addWeddingDisplay();
					addWeddingFrame.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnAddWedding.setBounds(10, 160, 95, 23);
		panel.add(btnAddWedding);
<<<<<<< HEAD
		btnAddWedding.setEnabled(false);
		
		
		JLabel lblPartner = new JLabel("");
=======

		JLabel lblPartner = new JLabel("Single");
>>>>>>> origin/master
		lblPartner.setBounds(10, 51, 218, 14);
		panel.add(lblPartner);

		JLabel lblWeddingDate = new JLabel("");
		lblWeddingDate.setBounds(10, 96, 218, 14);
		panel.add(lblWeddingDate);

		JLabel lblDivorceDate = new JLabel("");
		lblDivorceDate.setBounds(10, 135, 218, 14);
		panel.add(lblDivorceDate);

		JButton btnAddPlayedMovie = new JButton("Add a movie he/she played in");
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
		btnAddPlayedMovie.setBounds(558, 270, 224, 25);
		contentPane.add(btnAddPlayedMovie);
<<<<<<< HEAD
		btnAddPlayedMovie.setEnabled(false);
		
=======

>>>>>>> origin/master
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
		btnAddDirectedMovie.setBounds(558, 308, 224, 25);
		contentPane.add(btnAddDirectedMovie);
<<<<<<< HEAD
		btnAddDirectedMovie.setEnabled(false);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(table.getSelectedRow()!=-1){
	        		btnAddPlayedMovie.setEnabled(true);
	        		btnAddDirectedMovie.setEnabled(true);
	        		try {
	        			currentVip=model.getVipList().get(table.getSelectedRow());
						if(model.getVipList().get(table.getSelectedRow()).getIdPartner()!=-1){							
							lblPartner.setText("Wed to : " + App.getDaoVip().getPartner(currentVip).getSurname()[0] + " " + App.getDaoVip().getPartner(model.getVipList().get(table.getSelectedRow())).getName());
							lblWeddingDate.setText("On : "+ App.getDaoEvent().getMaritialStatus(currentVip).getWeddingDate());
							btnAddWedding.setEnabled(false);
						}
						else{
=======

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() != -1) {
					try {
						currentVip = model.getVipList().get(table.getSelectedRow());
						if (model.getVipList().get(table.getSelectedRow()).getIdPartner() != -1) {
							lblPartner.setText("Wed to : " + App.getDaoVip().getPartner(currentVip).getSurname()[0]
									+ " " + App.getDaoVip().getPartner(model.getVipList().get(table.getSelectedRow()))
											.getName());
							lblWeddingDate.setText(
									"On : " + App.getDaoEvent().getMaritialStatus(currentVip).getWeddingDate());
							if (App.getDaoEvent().getMaritialStatus(currentVip).getDivorceDate() != null) {
								lblDivorceDate.setText("Divorced : "
										+ App.getDaoEvent().getMaritialStatus(currentVip).getDivorceDate());
							}
						} else {
>>>>>>> origin/master
							lblPartner.setText("Single");
							lblWeddingDate.setText("");
							btnAddWedding.setEnabled(true);
						}
						if(App.getDaoEvent().getMaritialStatus(currentVip)!=null&&App.getDaoEvent().getMaritialStatus(currentVip).getDivorceDate()!=null){
							lblDivorceDate.setText("Divorced : "+ App.getDaoEvent().getMaritialStatus(currentVip).getDivorceDate());
							btnAddDivorce.setEnabled(false);
						}
						else{
							lblDivorceDate.setText("");
							btnAddDivorce.setEnabled(true);
						}
						if(btnAddWedding.isEnabled()){
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
	
<<<<<<< HEAD
	private AddWeddingFrame addWeddingDisplay() throws SQLException{
		return new AddWeddingFrame(this, currentVip);
	}
	
	private String DialogDivorceDate(){
		return (String)JOptionPane.showInputDialog(this,"Divorce date (yyyy-mm-dd) :","Add Divorce",JOptionPane.PLAIN_MESSAGE);
=======
	private AddDirectedMovieFrame addDirectedMovieDisplay() throws SQLException {
		return new AddDirectedMovieFrame(this, currentVip);
>>>>>>> origin/master
	}
}
