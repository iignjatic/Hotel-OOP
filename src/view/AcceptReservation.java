package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

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
import javax.swing.table.TableColumn;

import entity.AdditionalServices;
import entity.Reservation;
import entity.Room;
import entity.TypeOfRoom;
import enums.StatusReservation;
import manage.AdditionalServicesManager;
import manage.ReceptionistManager;
import manage.ReservationManager;
import manage.RoomManager;
import manage.TypeOfRoomManager;

import javax.swing.JButton;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class AcceptReservation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private Reservation reservation = null;
	private JTextField textField;
	private JTextField textField_1;

	
		
			
	public AcceptReservation(ReceptionistManager rec, ReservationManager res, AdditionalServicesManager addS,
						RoomManager roomM,TypeOfRoomManager tm, ReceptionistFrame rf) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
				
		
		//meni
		
				JMenuBar menuBar = new JMenuBar();
				setJMenuBar(menuBar);
				
				JMenuItem mntmNewMenuItem = new JMenuItem("Nazad na meni");
				menuBar.add(mntmNewMenuItem);

				


				mntmNewMenuItem.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	AcceptReservation.this.dispose();
				    	rf.fillTable(rf.getModel(), res);
				        rf.setVisible(true);
				    }
				});
			contentPane.setLayout(null);


	
	
	//tabela rezervacija
			JTable table = new JTable(model);
			table.setBounds(10, 20, 695, 166);
			contentPane.add(table);
			
			
			 JScrollPane scrollPane = new JScrollPane(table);
		     scrollPane.setBounds(10, 20, 695, 164);
		     contentPane.add(scrollPane);

			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(216, 319, 368, 29);
			contentPane.add(lblNewLabel);
			
			lblNewLabel.setVisible(false);
			
			 ListSelectionModel selectionModel = table.getSelectionModel();
	           selectionModel.addListSelectionListener(new ListSelectionListener() {
	               public void valueChanged(ListSelectionEvent e) {
	                   if (!e.getValueIsAdjusting()) {
	                       int selectedRow = table.getSelectedRow();
	                       if (selectedRow != -1) {
	                    	   reservation = res.reservationsMap.get( Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
	                    	   
	                       }
	                   }
	               }
	           });
	           
	           JButton btnNewButton_1 = new JButton("Odbijte rezervaciju");
     			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
     			btnNewButton_1.setBackground(UIManager.getColor("ToolBar.dockingForeground"));
     			btnNewButton_1.setBounds(264, 369, 153, 21);
      			contentPane.add(btnNewButton_1);
      			btnNewButton_1.setVisible(false);

	
			
			JButton btnNewButton = new JButton("Potvrdi rezervaciju");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(reservation != null) {
					if(rec.isAvailableTypeOfRoom(reservation)) {
						reservation.setProcessing_date(LocalDate.now());
						res.saveReservations();
             		   fillTable(model,res);
             			btnNewButton_1.setVisible(false);
             		   lblNewLabel.setText("Uspjesno ste potvrdili rezervaciju");
             		   lblNewLabel.setVisible(true);
             		   
             	   }
             	   else {
             		   lblNewLabel.setText("Nema slobodnih soba tog tipa");
             		   lblNewLabel.setVisible(true);
             		   
             		  
	          			btnNewButton_1.setVisible(true);
          			
	          			btnNewButton_1.addActionListener(new ActionListener() {
	        				public void actionPerformed(ActionEvent e) {
	        						reservation.setStatusOfReservation(StatusReservation.ODBIJENA);
	        						reservation.setTotalPrice(0);
	        						res.saveReservations();
	        	             		   fillTable(model,res);

	        					
	        				}
	          			});


             	   }
					res.saveReservations();

				}
				
				else {
                    JOptionPane.showMessageDialog(null, "Morate selektovati rezervaciju.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				}
				
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewButton.setBounds(233, 276, 219, 29);
			contentPane.add(btnNewButton);
			
			JLabel lblNewLabel_1 = new JLabel("Selektujte rezervaciju");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(264, -3, 181, 21);
			contentPane.add(lblNewLabel_1);
			
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setBounds(20, 223, 121, 29);
			contentPane.add(comboBox);
			
			comboBox.addItem("");
			for(TypeOfRoom type: tm.typeRooms.values()) {
				comboBox.addItem(type.getType());
			}
			
			JComboBox<Integer>comboBox_1 = new JComboBox<Integer>();
			comboBox_1.setBounds(151, 223, 121, 29);
			contentPane.add(comboBox_1);
			
			comboBox_1.addItem(null);
			for(Room r: roomM.rooms.values()) {
				comboBox_1.addItem(r.getRoomID());
			}
			
			JComboBox<String> comboBox_1_1 = new JComboBox<String>();
			comboBox_1_1.setBounds(282, 223, 121, 29);
			contentPane.add(comboBox_1_1);
			
			comboBox_1_1.addItem("");
			for(AdditionalServices service: addS.services.values()) {
				comboBox_1_1.addItem(service.getService());
			}
			
			textField = new JTextField();
			textField.setBounds(441, 223, 63, 29);
			contentPane.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(530, 223, 63, 29);
			contentPane.add(textField_1);
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Tip sobe");
			lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1_1_1.setBounds(35, 197, 106, 21);
			contentPane.add(lblNewLabel_1_1_1);
			
			JLabel lblNewLabel_1_1_1_1 = new JLabel("Soba");
			lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1_1_1_1.setBounds(160, 197, 106, 21);
			contentPane.add(lblNewLabel_1_1_1_1);
			
			JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Dodatna usluga");
			lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1_1_1_1_1.setBounds(282, 197, 127, 21);
			contentPane.add(lblNewLabel_1_1_1_1_1);
			
			JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Min cijena");
			lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1_1_1_1_2.setBounds(435, 197, 106, 21);
			contentPane.add(lblNewLabel_1_1_1_1_2);
			
			JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("Max cijena");
			lblNewLabel_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1_1_1_1_2_1.setBounds(523, 197, 106, 21);
			contentPane.add(lblNewLabel_1_1_1_1_2_1);
			
			JButton btnNewButton_2 = new JButton("Filtriraj");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ArrayList<Integer> toDelete = new ArrayList<>();
					fillTable(model,res);
					int numOfRows = model.getRowCount();
					//za tipove soba
					for(int i = 0; i< numOfRows; i++) {
						if(comboBox.getSelectedItem() != "") {
							if(model.getValueAt(i,3) != comboBox.getSelectedItem())
								toDelete.add( (Integer) model.getValueAt(i, 0));
						}
						
					}
					//za sobe
					for(int i = 0; i< numOfRows; i++) {
						if(comboBox_1.getSelectedItem() != null) {
							if(!(model.getValueAt(i,2).toString().equals(comboBox_1.getSelectedItem().toString())))
								toDelete.add( (Integer) model.getValueAt(i, 0));
						}
						
					}
					//za usluge
					for(int i = 0; i< numOfRows; i++) {
						if(comboBox_1_1.getSelectedItem() != "") {
							if(!(model.getValueAt(i,6).toString().contains(comboBox_1_1.getSelectedItem().toString())))
								toDelete.add( (Integer) model.getValueAt(i, 0));
						}
						
					}
					//za cijenu
					for(int i = 0; i< numOfRows; i++) {
						if(!textField.getText().equals("")) {
							if((Float)(model.getValueAt(i,7))<Float.parseFloat(textField.getText()))
								toDelete.add( (Integer) model.getValueAt(i, 0));
						}
						if(!textField_1.getText().equals("")) {
							if((Float)(model.getValueAt(i,7))>Float.parseFloat(textField_1.getText()))
								toDelete.add( (Integer) model.getValueAt(i, 0));
						}
						
					}
					//brisanje
					for(int delete: toDelete) {
						for(int i = 0; i< numOfRows; i++) {
							if((Integer)model.getValueAt(i, 0) == delete) {
								model.removeRow(i);
								numOfRows --;
							}
							
					}
					}
				}	
			});
			btnNewButton_2.setBackground(SystemColor.activeCaption);
			btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewButton_2.setBounds(608, 207, 97, 57);
			contentPane.add(btnNewButton_2);
			
				
			

			model.addColumn("ID");
			model.addColumn("Korisnicko ime");
			model.addColumn("Broj sobe");
			model.addColumn("Tip sobe");
			model.addColumn("Datum pocetka");
			model.addColumn("Datum kraja");
			model.addColumn("Dodatne usluge");
			model.addColumn("Cijena");
			model.addColumn("Status");
			
			TableColumn column1 = table.getColumnModel().getColumn(0);
	        TableColumn column2 = table.getColumnModel().getColumn(2);
	        TableColumn column7 = table.getColumnModel().getColumn(7);
			
			
	        column1.setPreferredWidth(20); 
	        column2.setPreferredWidth(30); 
	        column7.setPreferredWidth(50); 

			
			fillTable(model,res);
			
			
	}
	
	//funkcija za punjenje tabele rezervacija
		public void fillTable(DefaultTableModel model,ReservationManager res) {
			//r.viewReservations();
			model.getDataVector().removeAllElements();
			model.fireTableDataChanged();
			
			LocalDate now = LocalDate.now();

			for (int id: res.reservationsMap.keySet()) {
				Reservation r = res.reservationsMap.get(id);
				
				if(r.getStart().isBefore(now) && r.getStatusOfReservation() == StatusReservation.NA_CEKANJU) {
					r.setStatusOfReservation(StatusReservation.ODBIJENA);
					r.setTotalPrice(0);
					res.saveReservations();
				}


				if(r.getR()==null) {
				model.addRow(new Object[]{id, r.getG().getUsername(),"-" ,r.getType().getType(),r.getStart(),r.getEnd()
						,r.addServiceToFileString(r.getAddServices()),r.getTotalPrice(),r.getStatusOfReservation()});	
				}
				else {
					model.addRow(new Object[]{id, r.getG().getUsername(), r.getR().getRoomID() ,r.getType().getType(),
							r.getStart(),r.getEnd()
							,r.addServiceToFileString(r.getAddServices()),r.getTotalPrice(),r.getStatusOfReservation()});	
				}
				
			}
		}
}
