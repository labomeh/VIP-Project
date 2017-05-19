package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class mainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewVipList = new JButton("View all the VIP");
		btnViewVipList.setBounds(96, 76, 138, 65);
		contentPane.add(btnViewVipList);
		
		JButton btnViewEventList = new JButton("View all the events");
		btnViewEventList.setBounds(246, 76, 138, 65);
		contentPane.add(btnViewEventList);
		
		JButton btnNewVip = new JButton("New VIP");
		btnNewVip.setBounds(67, 286, 97, 25);
		contentPane.add(btnNewVip);
		
		JButton btnNewEvent = new JButton("New Event");
		btnNewEvent.setBounds(186, 286, 97, 25);
		contentPane.add(btnNewEvent);
		
		JButton btnNewPhoto = new JButton("New Photo");
		btnNewPhoto.setBounds(310, 284, 97, 25);
		contentPane.add(btnNewPhoto);
		
		JLabel lblTitle = new JLabel("VIP World");
		lblTitle.setBounds(195, 30, 88, 16);
		contentPane.add(lblTitle);
	}
}
