package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import com.toedter.calendar.JDateChooser;

import entity.CleanedRooms;
import entity.Reservation;
import entity.Room;
import entity.TypeOfRoom;
import entity.Workers;
import enums.Function;
import enums.StatusReservation;
import manage.AdditionalServicesManager;
import manage.AdministratorManager;
import manage.CleanedRoomsManager;
import manage.GuestManager;
import manage.PriceListManager;
import manage.ReservationManager;
import manage.RoomManager;
import manage.TypeOfRoomManager;
import manage.WorkerManager;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model1 = new DefaultTableModel();
	private DefaultTableModel model2 = new DefaultTableModel();
	private DefaultTableModel model3 = new DefaultTableModel();
	private DefaultTableModel model4 = new DefaultTableModel();


	public AdminFrame(GuestManager guestManager1, WorkerManager workerManager1, TypeOfRoomManager typeOfRoomManager, 
			AdditionalServicesManager addServicesManager1, ReservationManager reservationManager, RoomManager roomManager1, 
			PriceListManager pm, login login, CleanedRoomsManager cr, AdministratorManager admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 494);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Gosti");
		menuBar.add(mntmNewMenuItem);
		
		AdminGuestFrame gf = new AdminGuestFrame(guestManager1,this);
		
		mntmNewMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AdminFrame.this.dispose();
		        gf.setVisible(true);
		    }
		});
		
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Zaposleni");
		menuBar.add(mntmNewMenuItem_1);
		
		AdminWorkerFrame wf = new AdminWorkerFrame(workerManager1, this);
		
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AdminFrame.this.dispose();
		        wf.setVisible(true);
		    }
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sobe");
		menuBar.add(mntmNewMenuItem_2);
		
		AdminRoomFrame rf = new AdminRoomFrame(typeOfRoomManager,roomManager1,this);
		
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AdminFrame.this.dispose();
		        rf.setVisible(true);
		    }
		});
		
		AdminTypeOfRoomFrame tf = new AdminTypeOfRoomFrame(typeOfRoomManager, AdminFrame.this);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Tipovi sobe");
		menuBar.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AdminFrame.this.dispose();
		        tf.setVisible(true);
		    }
		});
		
		
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Dodatne usluge");
		menuBar.add(mntmNewMenuItem_4);
		
		AdminServicesFrame af = new AdminServicesFrame(addServicesManager1, AdminFrame.this);
		
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AdminFrame.this.dispose();
		        af.setVisible(true);
		    }
		});
		
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Cjenovnici");
		menuBar.add(mntmNewMenuItem_5);
		
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Odjavite se");
		menuBar.add(mntmNewMenuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AdminFrame.this.dispose();
		        login.setVisible(true);
		    }
		});


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		AdminPricelistFrame pf = new AdminPricelistFrame(pm,addServicesManager1,typeOfRoomManager,AdminFrame.this);
		
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	AdminFrame.this.dispose();
		        pf.setVisible(true);
		    }
		});
		
		
		
		//sve za izvjestaje
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(161, 100, 170, 32);
		getContentPane().add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(395, 100, 170, 32);
		contentPane.add(dateChooser_1);
		
		JLabel lblNewLabel = new JLabel("Pocetak");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(161, 77, 145, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblKraj = new JLabel("Kraj");
		lblKraj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKraj.setBounds(398, 67, 145, 32);
		contentPane.add(lblKraj);
		
		model1.addColumn("Status");
		model1.addColumn("Broj rezervacija");
			
		model2.addColumn("Sobarica");
		model2.addColumn("Broj ociscenih");
		
		model3.addColumn("Broj sobe");
		model3.addColumn("Tip sobe");
		model3.addColumn("Broj nocenja");
		model3.addColumn("Ukupan prihod");
		
		model4.addColumn("Prihodi");
		model4.addColumn("Rashodi");
		model4.addColumn("Profit");
		
		
		JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(161, 236, 404, 118);
	    contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Obradjeni zahtjevi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate start = dateChooser.getDate() != null ? dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
		        LocalDate end = dateChooser_1.getDate() != null ? dateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

		        if (start == null || end == null || start.isAfter(end)) {
		            JOptionPane.showMessageDialog(contentPane, "Molimo unesite ispravne datume.", "Greška", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        int counter_waitlist = 0, counter_accepted = 0, counter_denied = 0, counter_canceled = 0;
		        model1.setRowCount(0); 

		        table = new JTable(model1);
				table.setBounds(161, 236, 404, 118);
				contentPane.add(table);
				
				
		     
				
		        
		        for (Reservation r : reservationManager.reservationsMap.values()) {
		        	if(r.getProcessing_date().isAfter(start) && r.getProcessing_date().isBefore(end)){
		        		if(r.getStatusOfReservation() == StatusReservation.NA_CEKANJU) {
		        			counter_waitlist += 1;
		        		}
		        		if(r.getStatusOfReservation() == StatusReservation.POTVRDJENA) {
		        			counter_accepted += 1;
		        		}
		        		if(r.getStatusOfReservation() == StatusReservation.ODBIJENA) {
		        			counter_denied += 1;
		        		}
		        		if(r.getStatusOfReservation() == StatusReservation.OTKAZANA) {
		        			counter_canceled += 1;
		        		}
		}
		        }
		        
		     				
				model1.addRow(new Object[]{"NA CEKANJU", counter_waitlist});	
				model1.addRow(new Object[]{"POTVRDJENA", counter_accepted});	
				model1.addRow(new Object[]{"ODBIJENA", counter_denied});	
				model1.addRow(new Object[]{"OTKAZANA", counter_canceled});	

				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 160, 153, 45);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Izvjestaji");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(311, 10, 170, 57);
		contentPane.add(lblNewLabel_1);
		
		JButton btnSo = new JButton("Broj spremljenih soba");
		btnSo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        model2.setRowCount(0); 

				table = new JTable(model2);
				table.setBounds(161, 236, 404, 118);
				contentPane.add(table);
				
				
				LocalDate start = dateChooser.getDate() != null ? dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
		        LocalDate end = dateChooser_1.getDate() != null ? dateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

		        if (start == null || end == null || start.isAfter(end)) {
		            JOptionPane.showMessageDialog(contentPane, "Molimo unesite ispravne datume.", "Greška", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
				
				for(Workers w: workerManager1.workersMap.values()) {
					int count = 0;
					if(w.getFunction() == Function.maid) {
						for(CleanedRooms cleaned: cr.cleanedRooms) {
							if(cleaned.getDate_of_cleaning().isAfter(start) && cleaned.getDate_of_cleaning().isBefore(end)) {
							if(cleaned.getMaid().getUsername().equals(w.getUsername())) {
								count += 1;
							}
							}					
						}
						model2.addRow(new Object[]{w.getUsername(), count});
						count = 0;
					}
				}
				scrollPane.setViewportView(table);

			}

		});
		btnSo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSo.setBounds(173, 160, 189, 45);
		contentPane.add(btnSo);
		
		JButton btnPrikazSoba = new JButton("Prikaz soba");
		btnPrikazSoba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        model3.setRowCount(0); 


				table = new JTable(model3);
				table.setBounds(161, 236, 404, 118);
				contentPane.add(table);
				
				LocalDate start = dateChooser.getDate() != null ? dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
		        LocalDate end = dateChooser_1.getDate() != null ? dateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

		        
		        if (start == null || end == null || start.isAfter(end)) {
		            JOptionPane.showMessageDialog(contentPane, "Molimo unesite ispravne datume.", "Greška", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        int count = 0;
				float price = 0;
				LocalDate add_day;
		        
				
				for(Room r: roomManager1.rooms.values()) {
					for(Reservation res: reservationManager.reservationsMap.values()) {
						if(res.getR() != null) {
							if(res.getR().getRoomID() == r.getRoomID()) {
								if((start.isBefore(res.getEnd()) || start.isEqual(res.getEnd())) && (res.getStart().isBefore(end) || res.getStart().isEqual(end))) {
									if(start.isAfter(res.getStart()) || start.isEqual(res.getStart())) {
										add_day = start;
									}
									else {
										add_day = res.getStart();
									}
										while(true) {
											count += 1;
											price += r.getTypeRoom().getPrice();
											
											if(add_day.isEqual(end) || add_day.isEqual(res.getEnd())) {
												break;
											}
											
											add_day = add_day.plusDays(1);
										}
						
									}
								
								}
							  }
					}
					model3.addRow(new Object[]{r.getRoomID(),r.getTypeRoom().getType(),count, price});
					count = 0;
					price = 0;
				}
				scrollPane.setViewportView(table);

				
			}
		});
		btnPrikazSoba.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPrikazSoba.setBounds(372, 160, 135, 45);
		contentPane.add(btnPrikazSoba);
		
		JButton btnPrihodirashodi = new JButton("Prihodi/Rashodi");
		btnPrihodirashodi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        model4.setRowCount(0); 
				table = new JTable(model4);
				table.setBounds(161, 236, 404, 118);
				contentPane.add(table);
				
				JScrollPane scrollPane = new JScrollPane(table);
			    scrollPane.setBounds(161, 236, 404, 118);
			    contentPane.add(scrollPane);
			    
		        float income = 0, outcome = 0, profit = 0;
		        
		        for(Reservation r: reservationManager.reservationsMap.values()) {
		        	income += r.getTotalPrice();
		        }
		        for(Workers w: workerManager1.workersMap.values()) {
		        	outcome += w.getSalary();
		        }
		        profit = income - outcome;
				model4.addRow(new Object[]{income, outcome, profit});

				scrollPane.setViewportView(table);

			}
		});
		btnPrihodirashodi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPrihodirashodi.setBounds(517, 160, 189, 45);
		contentPane.add(btnPrihodirashodi);
		
		JButton btnIzvjestaj = new JButton("Izvjestaj 1");
		btnIzvjestaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		        int counter_waitlist = 0, counter_accepted = 0, counter_denied = 0, counter_canceled = 0;
		        
		        LocalDate now = LocalDate.now();
		        LocalDate start = now.minusDays(30);
		        		        
		       
		        for (Reservation r : reservationManager.reservationsMap.values()) {
		        	if(r.getProcessing_date().isAfter(start)) {
		        		if(r.getStatusOfReservation() == StatusReservation.NA_CEKANJU) {
		        			counter_waitlist += 1;
		        		}
		        		if(r.getStatusOfReservation() == StatusReservation.POTVRDJENA) {
		        			counter_accepted += 1;
		        		}
		        		if(r.getStatusOfReservation() == StatusReservation.ODBIJENA) {
		        			counter_denied += 1;
		        		}
		        		if(r.getStatusOfReservation() == StatusReservation.OTKAZANA) {
		        			counter_canceled += 1;
		        		}
		        	}
		        }
		        
		     		admin.report1(counter_waitlist, counter_accepted, counter_denied, counter_canceled);	
			}
		});
		btnIzvjestaj.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIzvjestaj.setBounds(135, 374, 153, 45);
		contentPane.add(btnIzvjestaj);
		
		JButton btnIzvjestaj_1 = new JButton("Izvjestaj 2");
		btnIzvjestaj_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LocalDate now = LocalDate.now();
				LocalDate start = now.minusDays(30);
				HashMap<String, Integer> result = new HashMap<>();

				for(Workers w: workerManager1.workersMap.values()) {
					int count = 0;
					if(w.getFunction() == Function.maid) {
						for(CleanedRooms cleaned: cr.cleanedRooms) {
							if(cleaned.getDate_of_cleaning().isAfter(start)) {
							if(cleaned.getMaid().getUsername().equals(w.getUsername())) {
								count += 1;
							}
							}					
						}
						result.put(w.getUsername(), count);

						count = 0;
					}
					

				
				}
				admin.report2(result);
				}
		});
		btnIzvjestaj_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIzvjestaj_1.setBounds(298, 374, 153, 45);
		contentPane.add(btnIzvjestaj_1);
		
		JButton btnIzvjestaj_2 = new JButton("Izvjestaj 3");
		btnIzvjestaj_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LocalDate add_day;
				LocalDate now = LocalDate.now();
				LocalDate start = now.minusDays(365);
				int month = start.getMonthValue();
				start = LocalDate.of(start.getYear(), month, 1);
				HashMap<String, double[]> result = new HashMap<>();
				
				for(TypeOfRoom type: typeOfRoomManager.typeRooms.values()) {
					double[] values = {0.0, 0.0,0.0, 0.0,0.0, 0.0,0.0, 0.0,0.0, 0.0,0.0, 0.0};
					result.put(type.getType(), values);
				}
		        
				int count = 12;
				
				while(count > 0) {
					count --;
						for(Reservation res: reservationManager.reservationsMap.values()) {
							if(res.getR() != null) {
								if(res.getStart().isAfter(start)) {
									add_day = res.getStart();
								}else {
									break;
								}
									while(add_day.getMonthValue() == month && !(add_day.isAfter(res.getEnd()))) {
										double[] add = result.get(res.getR().getTypeRoom().getType());
										add[month] = result.get(res.getR().getTypeRoom().getType())[month] + res.getR().getTypeRoom().getPrice();
										result.put(res.getR().getTypeRoom().getType(),add);
 
									
										add_day = add_day.plusDays(1);	
									}

							}

				}
						month += 1;
						if(month == 13) {
							month = 1;
						}
				}
				List<Double> lista = new ArrayList<Double>();
				HashMap<String, List<Double>> finalResult = new HashMap<>();
				for(String key: result.keySet()) {
					for(Double d:result.get(key)) {
						lista.add(d);
					}
					
					finalResult.put(key, lista);
					lista = new ArrayList<Double>();
					
				}
				admin.report3(finalResult);
			}
		});
		btnIzvjestaj_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIzvjestaj_2.setBounds(465, 374, 153, 45);
		contentPane.add(btnIzvjestaj_2);
		
		
		
	}
	
	
}
