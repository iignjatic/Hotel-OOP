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

import entity.AdditionalServices;
import manage.AdditionalServicesManager;

public class AdminServicesFrame extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private String service = "";

	
	public AdminServicesFrame(AdditionalServicesManager am, AdminFrame af) {
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
		    	AdminServicesFrame.this.dispose();
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
                         service = (table.getValueAt(selectedRow, 0).toString());
                      }
                  }
              }
          });
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminAddServices addService = new AdminAddServices(am,AdminServicesFrame.this,null);
				AdminServicesFrame.this.dispose();
				addService.setVisible(true);
				fillTable(model, am);

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(128, 244, 97, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obrisi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(service .equals("")) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati uslugu.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
				am.removeAdditionalService(service);
                fillTable(model, am);
	            JOptionPane.showMessageDialog(null, "Usluga je obrisana.", "Greška", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(276, 244, 107, 38);
		contentPane.add(btnNewButton_1);
		
		
		
		JButton btnNewButton_2 = new JButton("Izmijeni");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(service.equals("")) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati uslugu.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
				AdditionalServices a = am.services.get(service);
				AdminAddServices addR = new AdminAddServices(am, AdminServicesFrame.this,a);
				addR.setVisible(true);
			}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(428, 244, 107, 38);
		contentPane.add(btnNewButton_2);
		
		model.addColumn("Usluga");
		model.addColumn("Cijena");
		
		
		
		fillTable(model, am);


	}
	
	public void fillTable(DefaultTableModel model,AdditionalServicesManager am) {
		//r.viewReservations();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		
		
		
		
		for (AdditionalServices service: am.services.values()) {			
	
			model.addRow(new Object[]{service.getService(),service.getPrice()});	
			
			
			
		
	}
}
}