package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import entity.Guests;
import enums.Gender;
import manage.GuestManager;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GuestRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_4;
	private JTextField textField_1;
	private JTextField textField_2;


	public GuestRegistration(ReceptionistFrame rf, AdminGuestFrame af, GuestManager gm, Guests guest) {
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
		    	GuestRegistration.this.dispose();
		    	if (rf != null) {
		    		rf.setVisible(true);
		    	}
		    	else {
		    		af.setVisible(true);
		    	}
		    	
		    }
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(284, 59, 170, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(284, 112, 170, 32);
		contentPane.add(passwordField);
		
		JComboBox<Gender> comboBox = new JComboBox<Gender>();
		comboBox.setBounds(284, 276, 170, 33);
		contentPane.add(comboBox);
		
		comboBox.addItem(Gender.M);
		comboBox.addItem(Gender.Ž);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(284, 336, 170, 32);
		getContentPane().add(dateChooser);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(284, 168, 170, 32);
		contentPane.add(textField_4);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(284, 223, 170, 32);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(284, 393, 170, 32);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("Korisnicko ime");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(170, 61, 106, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLozinka.setBounds(191, 114, 74, 23);
		contentPane.add(lblLozinka);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(214, 170, 51, 23);
		contentPane.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(200, 223, 74, 23);
		contentPane.add(lblPrezime);
		
		JLabel lblPol = new JLabel("Pol");
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPol.setBounds(214, 276, 51, 23);
		contentPane.add(lblPol);
		
		JLabel lblDatumRodjenja = new JLabel("Datum rodjenja");
		lblDatumRodjenja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDatumRodjenja.setBounds(168, 336, 106, 23);
		contentPane.add(lblDatumRodjenja);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefon.setBounds(191, 393, 74, 23);
		contentPane.add(lblTelefon);
		
		if(!(guest==null)) {
			textField.setText(guest.getUsername());
			passwordField.setText(guest.getPassword());
			textField_4.setText(guest.getName());
			textField_1.setText(guest.getSurname());
			comboBox.setSelectedItem(guest.getGender());

	        Date date = Date.from(guest.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateChooser.setDate(date);
			textField_2.setText(guest.getPhoneNumber());
			
		
	}

		
		JLabel lblNewLabel_1 = new JLabel("Registracija gosta");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(261, 10, 205, 32);
		contentPane.add(lblNewLabel_1);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		horizontalBox.setBounds(139, 36, 398, 393);
		contentPane.add(horizontalBox);
		
		JButton btnNewButton = new JButton("Potvrdi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				String name = textField_4.getText();
				String surname = textField_1.getText();
				Gender gender = Gender.valueOf(comboBox.getSelectedItem().toString());
		        LocalDate date = dateChooser.getDate() != null ? dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
				String phone = textField_2.getText();
				
				
			if (username.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty() || 
				     date == null || phone.isEmpty() ) {
				            JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena.", "Greška", JOptionPane.ERROR_MESSAGE);
				            return;
			}
			if(guest == null) {
				gm.addGuests(username, password, name, surname, gender, date, phone);
			}
			else {
				gm.changeGuests(username, password, name, surname, gender, date, phone);
			}
				gm.saveGuests();
				
				PersonAddedDialog pa = new PersonAddedDialog();
				pa.setVisible(true);
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(583, 367, 106, 49);
		contentPane.add(btnNewButton);
		
	}
}
