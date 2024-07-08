package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.AdditionalServices;
import manage.AdditionalServicesManager;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminAddServices extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	public AdminAddServices(AdditionalServicesManager am, AdminServicesFrame af, AdditionalServices a) {
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
		    	AdminAddServices.this.dispose();
		        af.setVisible(true);
		    }
		});


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(280, 49, 169, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usluga");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(195, 58, 75, 22);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(280, 108, 169, 31);
		contentPane.add(textField_1);
		
		JLabel lblCijena = new JLabel("Cijena");
		lblCijena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCijena.setBounds(195, 117, 75, 22);
		contentPane.add(lblCijena);
		
		JLabel lblDodajteDodatnuUslugu = new JLabel("Dodajte dodatnu uslugu");
		lblDodajteDodatnuUslugu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDodajteDodatnuUslugu.setBounds(263, 10, 356, 22);
		contentPane.add(lblDodajteDodatnuUslugu);
		
		if(!(a.equals(null))) {
			textField.setText(a.getService());
			textField_1.setText(Integer.toString(a.getPrice()));
		}
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String service = textField.getText();
		        String priceText = textField_1.getText();
		        
		        if (service.isEmpty() || priceText.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena.", "Greška", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        Integer price;
		        try {
		            price = Integer.parseInt(priceText);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Cijena mora biti validan broj.", "Greška", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        if(a.equals(null)) {
		        
		        am.addAdditionalService(service, price);
		        }
		        else {
		        	am.changeService(service, price);
		        }
		        JOptionPane.showMessageDialog(null, "Usluga je uspješno dodata.", "", JOptionPane.INFORMATION_MESSAGE);
		        
		        textField.setText("");
		        textField_1.setText("");
		    }
		});

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(307, 173, 111, 37);
		contentPane.add(btnNewButton);
	}

}
