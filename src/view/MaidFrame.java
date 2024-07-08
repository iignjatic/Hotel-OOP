package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import entity.CleanedRooms;
import entity.Maid;
import entity.Room;
import enums.StatusRoom;
import manage.CleanedRoomsManager;
import manage.RoomManager;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MaidFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private Room room = null;
	


	public MaidFrame(Maid maid,RoomManager rm, login login, CleanedRoomsManager cr) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 494);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Odjavite se");
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MaidFrame.this.dispose();
		        login.setVisible(true);
		    }
		});


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable(model);
		table.setBounds(10, 32, 695, 166);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
	     scrollPane.setBounds(10, 20, 695, 164);
	     contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Pospremi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(room != null) {
				room.setStatusRoom(StatusRoom.slobodno);
				CleanedRooms cleaned = new CleanedRooms(maid, LocalDate.now());
				cr.cleanedRooms.add(cleaned);
				cr.saveDatesOfCleaning();
				fillTable(maid,model, rm);
				rm.saveRooms();
				}
				else {
                    JOptionPane.showMessageDialog(null, "Morate selektovati sobu.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		
		 ListSelectionModel selectionModel = table.getSelectionModel();
         selectionModel.addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent e) {
                 if (!e.getValueIsAdjusting()) {
                     int selectedRow = table.getSelectedRow();
                     if (selectedRow != -1) {
                    	 room = rm.rooms.get( Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
                  	   
                     }
                 }
             }
         });
         
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(289, 235, 103, 30);
		contentPane.add(btnNewButton);
		
		model.addColumn("Broj sobe");
		model.addColumn("Tip sobe");
		model.addColumn("Status sobe");
		
		fillTable(maid,model, rm);
		
		}
	
	public void fillTable(Maid m,DefaultTableModel model, RoomManager rm) {
		
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		
		//model.addRow(new Object[] {"Broj sobe","Tip sobe","Status sobe"});
	
		for(Room r: m.getRoomsToClean()) {
			if(r.getStatusRoom()==StatusRoom.spremanje) {
				model.addRow(new Object[] {r.getRoomID(),r.getTypeRoom().getType(),r.getStatusRoom()});
			}
		}
	
	}
}


