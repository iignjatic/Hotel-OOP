package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import entity.Reservation;
import manage.AdditionalServicesManager;
import manage.ReceptionistManager;
import manage.ReservationManager;
import manage.RoomManager;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class CheckOut extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private Reservation reservation = null;


	public CheckOut(ReceptionistManager rec, ReservationManager res, AdditionalServicesManager addS,RoomManager rm, ReceptionistFrame rf) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 494);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nazad na meni");
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		


		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	CheckOut.this.dispose();
		    	rf.fillTable(rf.getModel(), res);
		        rf.setVisible(true);
		    }
		});
				contentPane.setLayout(null);
				
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1.setBounds(233, 270, 317, 42);
				contentPane.add(lblNewLabel_1);
				
				lblNewLabel_1.setVisible(false);
		
				
				JTable table = new JTable(model);
				table.setBounds(10, 20, 695, 147);
				contentPane.add(table);
				
				JScrollPane scrollPane = new JScrollPane(table);
			     scrollPane.setBounds(10, 20, 695, 164);
			     contentPane.add(scrollPane);
				

				 ListSelectionModel selectionModel = table.getSelectionModel();
		           selectionModel.addListSelectionListener(new ListSelectionListener() {
		               public void valueChanged(ListSelectionEvent e) {
		                   if (!e.getValueIsAdjusting()) {
		                	   int selectedRow = table.getSelectedRow();
		                      // reservation = null;
		                       if (selectedRow != -1) {
		                    	   reservation = res.reservationsMap.get( Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
		                    	   
		                       }
		                   }
		               }
		           });
				
				JButton btnNewButton = new JButton("CheckOut");
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(reservation != null) {
						rec.checkOut(reservation);
						fillTable(model, res);
						lblNewLabel_1.setText("Soba " + reservation.getR().getRoomID()+" je u statusu: SPREMANJE");
						lblNewLabel_1.setVisible(true);
						}
						else {
		                    JOptionPane.showMessageDialog(null, "Morate selektovati rezervaciju.", "Greška", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnNewButton.setBounds(280, 204, 125, 42);
				contentPane.add(btnNewButton);
				
				
		           

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

					
					
				//	model.addRow(new Object[] {"ID", "Korisnicko ime","Broj sobe","Tip sobe","Datum pocetka","Datum kraja","Dodatne usluge", 
				//			"Cijena","Status"});
					
					
					for (int id: res.reservationsMap.keySet()) {
						Reservation r = res.reservationsMap.get(id);

						if(r.getR()!=null) {
							if(r.getEnd().isAfter(LocalDate.now())) {
						
							model.addRow(new Object[]{id, r.getG().getUsername(), r.getR().getRoomID() ,r.getType().getType(),
									r.getStart(),r.getEnd()
									,r.addServiceToFileString(r.getAddServices()),r.getTotalPrice(),r.getStatusOfReservation()});	
						
							}
					}
				}
				}
}
	
	
	
