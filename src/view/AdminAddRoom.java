package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Room;
import entity.TypeOfRoom;
import enums.StatusRoom;
import manage.RoomManager;
import manage.TypeOfRoomManager;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminAddRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public AdminAddRoom(TypeOfRoomManager tm, RoomManager rm,AdminRoomFrame af, Room r) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 494);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nazad na meni");
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AdminAddRoom.this.dispose();
		        af.setVisible(true);
		    }
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(252, 68, 153, 35);
		contentPane.add(comboBox);
		
		for(TypeOfRoom t: tm.typeRooms.values()) {
			comboBox.addItem(t.getType());
		}
		
		if(!(r.equals(null))) {
			comboBox.setSelectedItem(r.getTypeRoom().getType());
		}
		
		JLabel lblNewLabel = new JLabel("Dodaj sobu tipa:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(269, 22, 187, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rm.addRoom(tm.typeRooms.get(comboBox.getSelectedItem()), StatusRoom.slobodno, null);
				
			}
		});
		btnNewButton.setBounds(285, 156, 92, 35);
		contentPane.add(btnNewButton);
	}

}
