package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import entity.Guests;
import manage.GuestManager;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class AdminGuestFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private String username = "";
	
	public AdminGuestFrame(GuestManager gm, AdminFrame af) {
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
		    	AdminGuestFrame.this.dispose();
		        af.setVisible(true);
		    }
		});
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable(model);
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
                         username = table.getValueAt(selectedRow, 0).toString();
                      }
                  }
              }
          });
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuestRegistration gReg = new GuestRegistration(null, AdminGuestFrame.this, gm, null);
				AdminGuestFrame.this.dispose();
				gReg.setVisible(true);
				fillTable(model, gm);
			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(128, 244, 97, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obrisi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username.equals("")) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati korisnika.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
				gm.removeGuest(username);
                fillTable(model, gm);
	            JOptionPane.showMessageDialog(null, "Korisnik je obrisan.", "Greška", JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(276, 244, 107, 38);
		contentPane.add(btnNewButton_1);
		
		
		
		JButton btnNewButton_2 = new JButton("Izmijeni");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username.equals("")) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati korisnika.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
					Guests g = gm.guestsMap.get(username);
					GuestRegistration addF = new GuestRegistration(null,AdminGuestFrame.this, gm, g);
					AdminGuestFrame.this.dispose();
					addF.setVisible(true);
				}

			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(428, 244, 107, 38);
		contentPane.add(btnNewButton_2);
		
		model.addColumn("Korisnicko ime");
		model.addColumn("Lozinka");
		model.addColumn("Ime");
		model.addColumn("Prezime");
		model.addColumn("Pol");
		model.addColumn("Datum rodjenja");
		model.addColumn("Broj telefona");
		
		fillTable(model, gm);


	}
	
	public void fillTable(DefaultTableModel model,GuestManager gm) {
		//r.viewReservations();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		
		
		
		
		for (String username: gm.guestsMap.keySet()) {
			Guests g = gm.guestsMap.get(username);
			
	
			model.addRow(new Object[]{username, g.getPassword(),g.getName(),g.getSurname(),g.getGender(),g.getDate(),g.getPhoneNumber()});	
			
			
			
		
	}
}
	
}
