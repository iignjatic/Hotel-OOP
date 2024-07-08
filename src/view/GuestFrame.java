package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import entity.AdditionalServices;
import entity.Guests;
import entity.Room;
import entity.TypeOfRoom;
import enums.StatusReservation;
import manage.AdditionalServicesManager;
import manage.PriceListManager;
import manage.ReservationManager;
import manage.RoomManager;
import manage.TypeOfRoomManager;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.JCheckBox;



public class GuestFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private ArrayList<AdditionalServices> services = new ArrayList<>();
	private GuestAllReservations allRes;
	private JButton btnNewButton_1;
	



	public DefaultTableModel getModel() {
		return model;
	}





	public GuestFrame(TypeOfRoomManager tp, RoomManager rm, AdditionalServicesManager addS, ReservationManager res, 
			Guests g,login login, PriceListManager pm) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 494);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Kreiraj rezervaciju");
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Sve rezervacije");
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Odjavite se");
		menuBar.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		
        allRes = new GuestAllReservations(g,res, this);

		
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	GuestFrame.this.dispose();
		        allRes.setVisible(true);
		    }
		});
		
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	GuestFrame.this.dispose();
		        login.setVisible(true);
		    }
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(114, 46, 124, 32);
		getContentPane().add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(269, 46, 124, 32);
		contentPane.add(dateChooser_1);
		
		JLabel lblNewLabel = new JLabel("Dolazak");
		lblNewLabel.setBounds(115, 23, 80, 13);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		
		JLabel lblOdlazak = new JLabel("Odlazak");
		lblOdlazak.setBounds(269, 23, 80, 13);
		lblOdlazak.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblOdlazak);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(227, 111, 166, 32);
		contentPane.add(comboBox);
		
		
		JLabel lblTipSobe = new JLabel("Tip sobe");
		lblTipSobe.setBounds(133, 109, 84, 30);
		lblTipSobe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblTipSobe);
		
		table = new JTable(model);
		table.setBounds(1, 1, 146, 0);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(116, 219, 148, 84);
	     contentPane.add(scrollPane);
		
		model.addColumn("Usluga");
		model.addColumn("Cijena");
		
		for (TypeOfRoom type: tp.typeRooms.values()) {
			comboBox.addItem(type.toFileString());
			
		}
		
        
        for(AdditionalServices a: addS.services.values()) {
        	model.addRow(new Object[] {a.getService(),a.getPrice()});
        }
		
		JLabel lblNewLabel_1 = new JLabel("Dodatne usluge");
		lblNewLabel_1.setBounds(119, 171, 98, 45);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	services.removeAll(services);
                    int[] selectedRow = table.getSelectedRows();
                    for(int selected: selectedRow) {
                    if (selected != -1) {
                    	services.add(addS.services.get(table.getValueAt(selected, 0).toString()));
                    }
                    }
                }
            }
        });
		
        JButton btnNewButton = new JButton("Potvrdi");
        btnNewButton.setBounds(285, 271, 108, 32);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dateChooser.getDate() == null || dateChooser_1.getDate() == null) {
                        JOptionPane.showMessageDialog(null, "Molimo vas da odaberete oba datuma.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    LocalDate start = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate end = dateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    allRes = new GuestAllReservations(g,res, GuestFrame.this);


                    if (start.isAfter(end)) {
                        JOptionPane.showMessageDialog(null, "Datum početka ne može biti posle datuma završetka.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String[] type = ((String) comboBox.getSelectedItem()).split(",");
                    TypeOfRoom typeRoom = tp.typeRooms.get(type[0]);


                    res.createReservation(g, typeRoom, null, addS, start, end, services, StatusReservation.NA_CEKANJU, null,pm);

                    JOptionPane.showMessageDialog(null, "Rezervacija uspješno kreirana.", "", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Došlo je do greške: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnNewButton);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(60, 0, 388, 324);
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(horizontalBox);
		
		JLabel lblKriterijumi = new JLabel("Kriterijumi:");
		lblKriterijumi.setBounds(513, 23, 84, 30);
		lblKriterijumi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblKriterijumi);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Klima");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxNewCheckBox.setBounds(504, 59, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxTv = new JCheckBox("TV");
		chckbxTv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxTv.setBounds(504, 97, 93, 21);
		contentPane.add(chckbxTv);
		
		JCheckBox chckbxBalkon = new JCheckBox("Balkon");
		chckbxBalkon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBalkon.setBounds(504, 132, 93, 21);
		contentPane.add(chckbxBalkon);
		
		JCheckBox chckbxInternet = new JCheckBox("Internet");
		chckbxInternet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxInternet.setBounds(504, 171, 93, 21);
		contentPane.add(chckbxInternet);
		
		btnNewButton_1 = new JButton("Prikazi tipove soba");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> selected = new ArrayList<>();
				if (chckbxNewCheckBox.isSelected()) {
					selected.add(chckbxNewCheckBox.getText());
				}
				if (chckbxTv.isSelected()) {
					selected.add(chckbxTv.getText());
				}
				if (chckbxBalkon.isSelected()) {
					selected.add(chckbxBalkon.getText());
					
				}if (chckbxInternet.isSelected()) {
					selected.add(chckbxInternet.getText());
				}
				
				comboBox.removeAllItems();
				
				for(Room r: rm.rooms.values()) {
				    if(r.getCriteria().containsAll(selected)) {
				        String type = r.getTypeRoom().getType();
				        boolean exists = false;
				        
				        for (int i = 0; i < comboBox.getItemCount(); i++) {
				            if (comboBox.getItemAt(i).equals(type)) {
				                exists = true;
				                break;
				            }
				        }
				        
				        if (!exists) {
				            comboBox.addItem(type);
				        }
				    }
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(592, 217, 85, 21);
		contentPane.add(btnNewButton_1);

		
	}
}
