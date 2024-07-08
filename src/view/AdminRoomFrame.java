package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import entity.Room;
import manage.RoomManager;
import manage.TypeOfRoomManager;
import javax.swing.JScrollPane;

public class AdminRoomFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private int id = -1;

	
	public AdminRoomFrame(TypeOfRoomManager tm,RoomManager rm, AdminFrame af) {
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
		    	AdminRoomFrame.this.dispose();
		        af.setVisible(true);
		    }
		});
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTable table = new JTable(model);
		table.setBounds(10, 42, 695, 149);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
	     scrollPane.setBounds(10, 20, 695, 164);
	     contentPane.add(scrollPane);
		
		  ListSelectionModel selectionModel = table.getSelectionModel();
          selectionModel.addListSelectionListener(new ListSelectionListener() {
              public void valueChanged(ListSelectionEvent e) {
                  if (!e.getValueIsAdjusting()) {
                      int selectedRow = table.getSelectedRow();
                      if (selectedRow != -1) {
                         id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                      }
                  }
              }
          });
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*rm.addRoom(null, null); wReg = new WorkerRegistrationFrame( AdminWorkerFrame.this, wm);
				AdminWorkerFrame.this.dispose();
				wReg.setVisible(true);*/
				AdminAddRoom addRoom = new AdminAddRoom(tm,rm,AdminRoomFrame.this, null);
				AdminRoomFrame.this.dispose();
				addRoom.setVisible(true);
				fillTable(model, rm);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(128, 244, 97, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obrisi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == -1) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati sobu.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
				else {
				rm.removeRoom(id);
                fillTable(model, rm);
	            JOptionPane.showMessageDialog(null, "Soba je obrisana.", "", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(276, 244, 107, 38);
		contentPane.add(btnNewButton_1);
		
		
		
		JButton btnNewButton_2 = new JButton("Izmijeni");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == -1) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati sobu.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
					Room r = rm.rooms.get(id);
				AdminAddRoom addR = new AdminAddRoom(tm,rm, AdminRoomFrame.this,r);
				addR.setVisible(true);
			}
			}
			
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(428, 244, 107, 38);
		contentPane.add(btnNewButton_2);
		
		
		
		model.addColumn("Broj sobe");
		model.addColumn("Tip sobe");
		model.addColumn("Status");
		
		
		fillTable(model, rm);


	}
	
	public void fillTable(DefaultTableModel model,RoomManager rm) {
		//r.viewReservations();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		
		
		
		
		for (Integer id: rm.rooms.keySet()) {
			Room r = rm.rooms.get(id);
			
	
			model.addRow(new Object[]{id,r.getTypeRoom().getType(),r.getStatusRoom()});	
			
			
			
		
	}
}
}