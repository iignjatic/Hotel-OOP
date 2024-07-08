package view;


import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import entity.AdditionalServices;
import entity.Reservation;
import enums.StatusReservation;
import manage.AdditionalServicesManager;
import manage.GuestManager;
import manage.ReceptionistManager;
import manage.ReservationManager;
import manage.RoomManager;
import manage.TypeOfRoomManager;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;


public class ReceptionistFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private DefaultTableModel model = new DefaultTableModel();
	
	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	private DefaultTableModel model_1 = new DefaultTableModel();
	private Reservation r = null;
	private CheckOut checkOut;
	private JTable table_1;


	
	public ReceptionistFrame(ReceptionistManager rec, ReservationManager res, AdditionalServicesManager addS, 
			RoomManager roomM, TypeOfRoomManager tm, login login
			,GuestManager gm) {
		setTitle("Recepcioner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 494);
		getContentPane().setLayout(null);
		
		//meni
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Potvrda rezervacije");
		menuBar.add(mntmNewMenuItem);
		
		

	
        AcceptReservation acceptRes = new AcceptReservation(rec, res, addS, roomM,tm, this);

		
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ReceptionistFrame.this.dispose();
		        acceptRes.setVisible(true);
		    }
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("CheckIn");
		menuBar.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("CheckOut");
		menuBar.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Odjavite se");
		menuBar.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Registrujte gosta");
		menuBar.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ReceptionistFrame.this.dispose();
		        login.setVisible(true);
		    }
		});
		
		GuestRegistration guestR = new GuestRegistration(this,null, gm,null);

		
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ReceptionistFrame.this.dispose();
		        guestR.setVisible(true);
		    }
		});

		 checkOut = new CheckOut(rec, res, addS, roomM, this);

			
			mntmNewMenuItem_2.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	ReceptionistFrame.this.dispose();
			        checkOut.setVisible(true);
			    }
			});

		
		
		//tabela rezervacija
		
		table = new JTable(model);
		table.setBounds(27, 32, 663, 142);
		getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
	     scrollPane.setBounds(10, 20, 695, 164);
	     getContentPane().add(scrollPane);
		
		
		model_1.addColumn("Dodatna usluga");
		model_1.addColumn("Cijena");
		
		
		

		model.addColumn("ID");
		model.addColumn("Korisnicko ime");
		model.addColumn("Broj sobe");
		model.addColumn("Tip sobe");
		model.addColumn("Datum pocetka");
		model.addColumn("Datum kraja");
		model.addColumn("Dodatne usluge");
		model.addColumn("Cijena");
		
		TableColumn column1 = table.getColumnModel().getColumn(0);
        TableColumn column2 = table.getColumnModel().getColumn(2);
        TableColumn column7 = table.getColumnModel().getColumn(7);
		
		
        column1.setPreferredWidth(20); 
        column2.setPreferredWidth(30); 
        column7.setPreferredWidth(50); 

		
		fillTable(model,res);
			
			JComboBox<Integer> comboBox = new JComboBox<Integer>();
			comboBox.setBounds(301, 244, 101, 33);
			getContentPane().add(comboBox);
			
			JLabel lblNewLabel_2 = new JLabel("Slobodne sobe");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_2.setBounds(306, 216, 96, 13);
			getContentPane().add(lblNewLabel_2);
			
			
			
			JButton btnNewButton = new JButton("Potvrdi");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(r != null && r.getStart().equals(LocalDate.now())) {
					rec.checkIn(r, roomM.rooms.get( Integer.parseInt(comboBox.getSelectedItem().toString())));
					checkOut = new CheckOut(rec, res, addS, roomM, ReceptionistFrame.this);

					
					model.getDataVector().removeAllElements();
					model.fireTableDataChanged();
					
					fillTable(model,res);
					}
					else if(r == null) {
	                    JOptionPane.showMessageDialog(null, "Morate selektovati rezervaciju.", "Greška", JOptionPane.ERROR_MESSAGE);
					}
					else {
	                    JOptionPane.showMessageDialog(null, "CheckIn nije moguc za danasnji datum.", "Greška", JOptionPane.ERROR_MESSAGE);

					}
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton.setBounds(576, 386, 110, 33);
			getContentPane().add(btnNewButton);
			
			
			
			
			JLabel lblNewLabel_3 = new JLabel("Dodatne usluge");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_3.setBounds(222, 287, 126, 22);
			getContentPane().add(lblNewLabel_3);
			
					
			btnNewButton.setBackground(UIManager.getColor("CheckBox.darkShadow"));
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
					
			table_1 = new JTable(model_1);
			table_1.setBounds(222, 316, 244, 69);
			getContentPane().add(table_1);
			
			JLabel lblNewLabel = new JLabel("Check In");
			lblNewLabel.setBounds(309, 184, 72, 22);
			getContentPane().add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			
		
				   ListSelectionModel selectionModel = table.getSelectionModel();
		           selectionModel.addListSelectionListener(new ListSelectionListener() {
		               public void valueChanged(ListSelectionEvent e) {
		                   if (!e.getValueIsAdjusting()) {
		                       int selectedRow = table.getSelectedRow();
		                       if (selectedRow != -1) {
		                    	   r = res.reservationsMap.get(table.getValueAt(selectedRow, 0));
			                       ArrayList<Integer> availableRooms = new ArrayList<>();

		                    	   
		                    	   availableRooms = res.availableTypeRooms(r.getStart(), r.getEnd(), r.getType());
		                   		
		                           comboBox.removeAllItems();
		                           for (int room : availableRooms) {
		                               comboBox.addItem(room);
		                           }
		                           
		                           fillServices(model_1,r,addS);
		                       }
		                      
		                       }
		               }
		           });
		
		           
		           ListSelectionModel selectionModel_1 = table_1.getSelectionModel();
		           selectionModel_1.addListSelectionListener(new ListSelectionListener() {
		               public void valueChanged(ListSelectionEvent e) {
		                   if (!e.getValueIsAdjusting()) {
		                       int selectedRow = table_1.getSelectedRow();
		                       if (selectedRow != -1) {
		                    	   String service = table_1.getValueAt(selectedRow, 0).toString();
		                           r.getAddServices().add(addS.services.get(service));
		                           
			                       	Period period = Period.between(r.getStart(), r.getEnd());
			                		int numOfDays = period.getDays();
			                		
		                           r.setTotalPrice(r.getTotalPrice()+addS.services.get(service).getPrice()*numOfDays);
		                       }
		                   }
		               }
		           });
		}
	
			
	


	//funkcija za punjenje tabele rezervacija
	public void fillTable(DefaultTableModel model,ReservationManager res) {
		//r.viewReservations();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		
		
	//	model.addRow(new Object[] {"ID", "Korisnicko ime","Broj sobe","Tip sobe","Datum pocetka","Datum kraja","Dodatne usluge", "Cijena"});
		
		
		for (int id: res.reservationsMap.keySet()) {
			Reservation r = res.reservationsMap.get(id);
			
			if (r.getStatusOfReservation() == StatusReservation.POTVRDJENA) {

			if(r.getR()==null) {
			model.addRow(new Object[]{id, r.getG().getUsername(),"-" ,r.getType().getType(),r.getStart(),r.getEnd()
					,r.addServiceToFileString(r.getAddServices()),r.getTotalPrice()});	
			}
			else {
				model.addRow(new Object[]{id, r.getG().getUsername(), r.getR().getRoomID() ,r.getType().getType(),
						r.getStart(),r.getEnd()
						,r.addServiceToFileString(r.getAddServices()),r.getTotalPrice()});	
			}
			}
			
		}
	}
	
	//funkcija za punjenje tabele dodatnih usluga
	public void fillServices(DefaultTableModel model, Reservation r, AdditionalServicesManager addS) {
		
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		model.addRow(new Object[] {"Usluga","Cijena"});
				
		ArrayList<AdditionalServices> more = new ArrayList<>();
		
		for(AdditionalServices a: addS.services.values()) {
			more.add(a);
		}
       
		for(AdditionalServices a1:r.getAddServices()) {
			for(AdditionalServices a2: more) {
				if (a1.getService().equals(a2.getService())) {
					more.remove(a2);
					break;
				}
			}
		}
        
        for(AdditionalServices a: more) {
        	model.addRow(new Object[] {a.getService(),a.getPrice()});
        }
	}
	}

	
	
	
