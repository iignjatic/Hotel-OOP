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

import entity.Workers;
import manage.WorkerManager;

public class AdminWorkerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private String username = "";

	
	public AdminWorkerFrame(WorkerManager wm, AdminFrame af) {
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
		    	AdminWorkerFrame.this.dispose();
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
                         username = table.getValueAt(selectedRow, 0).toString();
                      }
                  }
              }
          });
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerRegistrationFrame wReg = new WorkerRegistrationFrame( AdminWorkerFrame.this, wm, null);
				AdminWorkerFrame.this.dispose();
				wReg.setVisible(true);
				fillTable(model, wm);

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
				wm.removeWorker(username);
                fillTable(model, wm);
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
					Workers w = wm.workersMap.get(username);
					WorkerRegistrationFrame addF = new WorkerRegistrationFrame(AdminWorkerFrame.this, wm, w);
					AdminWorkerFrame.this.dispose();
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
		
		fillTable(model, wm);


	}
	
	public void fillTable(DefaultTableModel model,WorkerManager wm) {
		//r.viewReservations();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		
		
		//model.addRow(new Object[] { "Korisnicko ime","Lozinka","Ime","Prezime","Pol","Datum rodjenja", "Broj telefona"});
		
		
		for (String username: wm.workersMap.keySet()) {
			Workers w = wm.workersMap.get(username);
			
	
			model.addRow(new Object[]{username, w.getPassword(),w.getName(),w.getSurname(),w.getGender(),w.getDate(),w.getPhoneNumber()});	
			
			
			
		
	}
}
}