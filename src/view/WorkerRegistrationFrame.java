package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import enums.Function;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import entity.Workers;
import enums.Gender;
import enums.LevelOfEducation;
import manage.WorkerManager;

public class WorkerRegistrationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_4;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;


	public WorkerRegistrationFrame(AdminWorkerFrame af, WorkerManager wm, Workers w) {
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
		    	WorkerRegistrationFrame.this.dispose();
		    		  af.setVisible(true);
		    }

		});

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(155, 59, 170, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 112, 170, 32);
		contentPane.add(passwordField);
		
		JComboBox<Gender> comboBox = new JComboBox<Gender>();
		comboBox.setBounds(155, 276, 170, 33);
		contentPane.add(comboBox);
		
		comboBox.addItem(Gender.M);
		comboBox.addItem(Gender.Ž);
		
		JComboBox<LevelOfEducation> comboBox_1 = new JComboBox<LevelOfEducation>();
		comboBox_1.setBounds(519, 104, 170, 33);
		contentPane.add(comboBox_1);
		
		comboBox_1.addItem(LevelOfEducation.I);
		comboBox_1.addItem(LevelOfEducation.II);
		comboBox_1.addItem(LevelOfEducation.III);
		comboBox_1.addItem(LevelOfEducation.IV);
		comboBox_1.addItem(LevelOfEducation.V);
		comboBox_1.addItem(LevelOfEducation.VI);
		comboBox_1.addItem(LevelOfEducation.VII);
		comboBox_1.addItem(LevelOfEducation.VIII);

		
		JComboBox<Function> comboBox_2 = new JComboBox<Function>();
		comboBox_2.setBounds(519, 167, 170, 33);
		contentPane.add(comboBox_2);
		
		comboBox_2.addItem(Function.admin);
		comboBox_2.addItem(Function.receptionist);
		comboBox_2.addItem(Function.maid);


		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(155, 336, 170, 32);
		getContentPane().add(dateChooser);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(155, 168, 170, 32);
		contentPane.add(textField_4);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(155, 223, 170, 32);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(155, 393, 170, 32);
		contentPane.add(textField_2);
		
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(519, 52, 170, 32);
		contentPane.add(textField_3);
		
		
		JLabel lblNewLabel = new JLabel("Korisnicko ime");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(41, 61, 106, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLozinka.setBounds(62, 114, 74, 23);
		contentPane.add(lblLozinka);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(85, 170, 51, 23);
		contentPane.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(71, 223, 74, 23);
		contentPane.add(lblPrezime);
		
		JLabel lblPol = new JLabel("Pol");
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPol.setBounds(85, 276, 51, 23);
		contentPane.add(lblPol);
		
		JLabel lblDatumRodjenja = new JLabel("Datum rodjenja");
		lblDatumRodjenja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDatumRodjenja.setBounds(39, 336, 106, 23);
		contentPane.add(lblDatumRodjenja);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefon.setBounds(62, 393, 74, 23);
		contentPane.add(lblTelefon);
		
		JLabel lblNewLabel_1 = new JLabel("Registracija zaposlenog");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(261, 10, 205, 32);
		contentPane.add(lblNewLabel_1);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		horizontalBox.setBounds(10, 36, 398, 393);
		contentPane.add(horizontalBox);
		
		if(!(w == null)) {
			textField.setText(w.getUsername());
			passwordField.setText(w.getPassword());
			textField_4.setText(w.getName());
			textField_1.setText(w.getSurname());
			comboBox.setSelectedItem(w.getGender());

	        Date date = Date.from(w.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			dateChooser.setDate(date);
			textField_2.setText(w.getPhoneNumber());
			
			String internship = Integer.toString(w.getInternship());
			
			textField_3.setText(internship);
			comboBox_1.setSelectedItem(w.getLevel());
			comboBox_2.setSelectedItem(w.getFunction());
			
		
	}
		
		JButton btnNewButton = new JButton("Potvrdi");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = textField.getText();
		        String password = new String(passwordField.getPassword());
		        String name = textField_4.getText();
		        String surname = textField_1.getText();
		        String genderText = comboBox.getSelectedItem() != null ? comboBox.getSelectedItem().toString() : "";
		        LocalDate date = dateChooser.getDate() != null ? dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
		        String phone = textField_2.getText();
		        String internshipText = textField_3.getText(); 
		        String levelText = comboBox_1.getSelectedItem() != null ? comboBox_1.getSelectedItem().toString() : "";
		        String functionText = comboBox_2.getSelectedItem() != null ? comboBox_2.getSelectedItem().toString() : "";
		        
		        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty() || 
		            genderText.isEmpty() || date == null || phone.isEmpty() || 
		            internshipText.isEmpty() || levelText.isEmpty() || functionText.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena.", "Greška", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		        Integer internship;
		        try {
		            internship = Integer.parseInt(internshipText);
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Internship mora biti validan broj.", "Greška", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        
		     
		        Gender gender = Gender.valueOf(genderText);
		        LevelOfEducation level = LevelOfEducation.valueOf(levelText);
		        Function function = Function.valueOf(functionText);
		        
		        if(w == null) {
					wm.addWorker(username, password, name, surname, gender, date, phone, level, internship, function);
				}
				else {
					wm.changeWorker(username, password, name, surname, gender, date, phone, level, internship, function);
				}
		        
		        PersonAddedDialog pa = new PersonAddedDialog();
		        pa.setVisible(true);
		    }
		});

		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(583, 367, 106, 49);
		contentPane.add(btnNewButton);

		
		
		JLabel lblGodineStaza = new JLabel("Godine staza");
		lblGodineStaza.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGodineStaza.setBounds(423, 59, 86, 23);
		contentPane.add(lblGodineStaza);
		
		JLabel lblStrucnaSprema = new JLabel("Strucna sprema");
		lblStrucnaSprema.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStrucnaSprema.setBounds(414, 114, 106, 23);
		contentPane.add(lblStrucnaSprema);
		
		JLabel lblUloga = new JLabel("Uloga");
		lblUloga.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUloga.setBounds(446, 170, 74, 23);
		contentPane.add(lblUloga);
			
	}
}