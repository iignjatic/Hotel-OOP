package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entity.TypeOfRoom;
import manage.TypeOfRoomManager;

public class AdminAddType extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	public AdminAddType(TypeOfRoomManager tm, AdminTypeOfRoomFrame tf, TypeOfRoom t) {
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
		    	AdminAddType.this.dispose();
		        tf.setVisible(true);
		    }
		});


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(280, 49, 169, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tip sobe");
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
		
		if(!(t.equals(null))) {
			textField.setText(t.getType());
			textField_1.setText(Float.toString(t.getPrice()));
		}
		
		JLabel lblDodajteDodatnuUslugu = new JLabel("Dodajte tip sobe");
		lblDodajteDodatnuUslugu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDodajteDodatnuUslugu.setBounds(263, 10, 356, 22);
		contentPane.add(lblDodajteDodatnuUslugu);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String type = textField.getText();
		        String priceText = textField_1.getText();
		        
		        if (type.isEmpty() || priceText.isEmpty()) {
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
		        
		        if(t.equals(null)) {
		        tm.addTypeOfRoom(type, price);
		        }
		        else {
		        	tm.changeTypeOfRoom(type, price);
		        }
		        JOptionPane.showMessageDialog(null, "Tip sobe je uspješno dodat.", "Informacija", JOptionPane.INFORMATION_MESSAGE);
		        
		        textField.setText("");
		        textField_1.setText("");
		    }
		});

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(307, 173, 111, 37);
		contentPane.add(btnNewButton);
	}

}
