package view.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.App;
import controller.Vip;
import view.JTableModels.VipJTable;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VipListFrame extends JFrame {

	private JPanel contentPane;
	private VipJTable model;
	private boolean outputState;
	private JTable table;
	private Vip currentVip;

	/**
	 * Create the frame.
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
		
		JLabel lblMovieList = new JLabel("Movies");
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
		btnAddDivorce.setBounds(121, 160, 89, 23);
		panel.add(btnAddDivorce);
		
		JButton btnAddWedding = new JButton("Add wedding");
		btnAddWedding.setBounds(10, 160, 95, 23);
		panel.add(btnAddWedding);
		
		
		JLabel lblPartner = new JLabel("Single");
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
		
		JButton btnAddDirectedMovie = new JButton("Add a movie he/she directed");
		btnAddDirectedMovie.setBounds(558, 308, 224, 25);
		contentPane.add(btnAddDirectedMovie);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(table.getSelectedRow()!=-1){
	        		try {
	        			currentVip=model.getVipList().get(table.getSelectedRow());
						if(model.getVipList().get(table.getSelectedRow()).getIdPartner()!=-1){							
							lblPartner.setText("Wed to : " + App.getDaoVip().getPartner(currentVip).getSurname()[0] + " " + App.getDaoVip().getPartner(model.getVipList().get(table.getSelectedRow())).getName());
							lblWeddingDate.setText("On : "+ App.getDaoEvent().getMaritialStatus(currentVip).getWeddingDate());
							if(App.getDaoEvent().getMaritialStatus(currentVip).getDivorceDate()!=null){
								lblDivorceDate.setText("Divorced : "+ App.getDaoEvent().getMaritialStatus(currentVip).getDivorceDate());
							}
						}
						else{
							lblPartner.setText("Single");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	        }
	    });
		
		
		
		
		
	} // Constructor
	
	private AddPlayedMovieFrame addPlayedMovieDisplay() throws SQLException{
		return new AddPlayedMovieFrame(this, currentVip);
	}
}
