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

import entity.PriceList;
import manage.AdditionalServicesManager;
import manage.PriceListManager;
import manage.TypeOfRoomManager;

public class AdminPricelistFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private int id = -1;
	
	public AdminPricelistFrame(PriceListManager pm, AdditionalServicesManager am,TypeOfRoomManager tm, AdminFrame af) {
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
		    	AdminPricelistFrame.this.dispose();
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
                         id = Integer.parseInt( table.getValueAt(selectedRow, 0).toString());
                      }
                  }
              }
          });
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAddPricelist listAdd = new AdminAddPricelist( AdminPricelistFrame.this, pm,am,tm,null);
				AdminPricelistFrame.this.dispose();
				listAdd.setVisible(true);
				fillTable(model, pm);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(128, 244, 97, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obrisi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == 0) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati cjenovnik.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
				pm.removePricelist(id); 
                fillTable(model, pm);
	            JOptionPane.showMessageDialog(null, "Cjenovnik je obrisan.", "Greška", JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(276, 244, 107, 38);
		contentPane.add(btnNewButton_1);
		
		
		
		JButton btnNewButton_2 = new JButton("Izmijeni");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == -1) {
		            JOptionPane.showMessageDialog(null, "Morate selektovati cjenovnik.", "Greška", JOptionPane.ERROR_MESSAGE);

				}
				else {
				PriceList p = pm.pricelistMap.get(id);
				AdminAddPricelist addR = new AdminAddPricelist(AdminPricelistFrame.this,pm,am,tm,p);
				addR.setVisible(true);
			}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(428, 244, 107, 38);
		contentPane.add(btnNewButton_2);
		
		model.addColumn("ID");
		model.addColumn("Pocetak vazenja");
		model.addColumn("Kraj vazenja");
		
		
		fillTable(model, pm);


	}
	
	public void fillTable(DefaultTableModel model,PriceListManager pm) {
		//r.viewReservations();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();

		
		for (PriceList p : pm.pricelistMap.values()) {
			
	
			model.addRow(new Object[]{p.getId(),p.getStartDate(),p.getEndDate()});	
			
			
			
		
	}
}
}
