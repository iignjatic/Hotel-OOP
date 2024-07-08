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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import entity.TypeOfRoom;
import manage.TypeOfRoomManager;

public class AdminTypeOfRoomFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private String type = "";

	
	public AdminTypeOfRoomFrame(TypeOfRoomManager tm, AdminFrame af) {
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
		    	AdminTypeOfRoomFrame.this.dispose();
		        af.setVisible(true);
		    }
		});
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTable table = new JTable(model);
		table.setBounds(10, 42, 695, 155);
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
                         type = (table.getValueAt(selectedRow, 0).toString());
                      }
                  }
              }
          });
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminAddType addType = new AdminAddType(tm,AdminTypeOfRoomFrame.this, null);
				AdminTypeOfRoomFrame.this.dispose();
				addType.setVisible(true);
				fillTable(model, tm);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(128, 244, 97, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obrisi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(type == "") {
		            JOptionPane.showMessageDialog(null, "Morate selektovati sobu.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
				tm.removeTypeOfRoom(type);
                fillTable(model, tm);
	            JOptionPane.showMessageDialog(null, "Tip je obrisan.", "", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(276, 244, 107, 38);
		contentPane.add(btnNewButton_1);
		
		
		
		JButton btnNewButton_2 = new JButton("Izmijeni");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(type.equals("")) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati sobu.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
				TypeOfRoom t = tm.typeRooms.get(type);
				AdminAddType addR = new AdminAddType(tm, AdminTypeOfRoomFrame.this,t);
				addR.setVisible(true);
			}
			
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(428, 244, 107, 38);
		contentPane.add(btnNewButton_2);
		
		model.addColumn("Tip sobe");
		model.addColumn("Cijena");
		
		
		
		fillTable(model, tm);


	}
	
	public void fillTable(DefaultTableModel model,TypeOfRoomManager tm) {
		//r.viewReservations();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		
		
		
		
		for (TypeOfRoom type: tm.typeRooms.values()) {			
	
			model.addRow(new Object[]{type.getType(),type.getPrice()});	
			
			
			
		
	}
}
}