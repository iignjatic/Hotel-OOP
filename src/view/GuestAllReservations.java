package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import entity.Guests;
import entity.Reservation;
import enums.StatusReservation;
import manage.ReservationManager;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuestAllReservations extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private JButton btnNewButton;
	private Reservation reservation;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField;

	
	public GuestAllReservations(Guests g,ReservationManager res, GuestFrame gf) {
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
		    	GuestAllReservations.this.dispose();
		    	fillTable(g,model,res);
		        gf.setVisible(true);
		    }
		});
		

		table = new JTable(model);
		table.setBounds(10, 31, 695, 150);
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
                  	   reservation = res.reservationsMap.get( Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
                  	   
                     }
                 }
             }
         });
	contentPane.setLayout(null);


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnNewButton = new JButton("Otkazi rezervaciju");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reservation.getStatusOfReservation() == StatusReservation.NA_CEKANJU) {
				reservation.setStatusOfReservation(StatusReservation.OTKAZANA);
				lblNewLabel.setText("Otkazali ste rezervaciju");
				res.saveReservations();
				fillTable(g,model,res);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(258, 212, 177, 41);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(260, 286, 300, 50);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Ukupan trosak");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(587, 212, 106, 24);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(597, 246, 96, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		float total = 0;
		for(Reservation reservation: res.reservationsMap.values()) {
			if(reservation.getG().getUsername().equals(g.getUsername())) {
				total += reservation.getTotalPrice();
			}
		}
		
		textField.setText(String.valueOf(total));
		

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

		
		fillTable(g,model,res);
	}
	


	//funkcija za punjenje tabele rezervacija
	public void fillTable(Guests g,DefaultTableModel model,ReservationManager res) {
		//r.viewReservations();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		
		
	//	model.addRow(new Object[] {"ID", "Korisnicko ime","Broj sobe","Tip sobe","Datum pocetka","Datum kraja","Dodatne usluge", "Cijena"});
		
		
		for (int id: res.reservationsMap.keySet()) {
			Reservation r = res.reservationsMap.get(id);
			
				if (r.getG().getUsername().equals(g.getUsername() )) {

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
}
