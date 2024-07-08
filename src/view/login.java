package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Guests;
import entity.Maid;
import entity.Workers;
import enums.Function;
import manage.AdditionalServicesManager;
import manage.AdministratorManager;
import manage.CleanedRoomsManager;
import manage.GuestManager;
import manage.PriceListManager;
import manage.ReceptionistManager;
import manage.ReservationManager;
import manage.RoomManager;
import manage.TypeOfRoomManager;
import manage.WorkerManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private Workers w;
	private Guests g;

	
	public login(GuestManager gm, WorkerManager wm, TypeOfRoomManager tm,AdditionalServicesManager am, ReservationManager rm
			,RoomManager roomM, ReceptionistManager rec,PriceListManager pm, CleanedRoomsManager cr, AdministratorManager admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[154.00][309.00][56.00,grow 20]", "[][][][][][][][][][][][]"));
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_2, "cell 1 0,alignx center");
		
		JLabel lblNewLabel = new JLabel("Korisnicko ime");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel, "cell 1 2");
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textField, "cell 1 3,growx");
		textField.setColumns(2);
		
		JLabel lblNewLabel_1 = new JLabel("Lozinka");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1, "cell 1 6");
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(passwordField, "cell 1 7,growx");
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				
				if (gm.guestsMap.containsKey(username)) {
					g = gm.guestsMap.get(username);
					if(g.getPassword().equals(password)) {
					GuestFrame gf = new GuestFrame(tm,roomM,am,rm,g,login.this,pm);
					login.this.dispose();
					gf.setVisible(true);
				}
					else {
						LoginFailedDialog failed = new LoginFailedDialog();
						failed.setVisible(true);
					}
				}
				if(wm.workersMap.containsKey(username)){
					w = wm.workersMap.get(username);
					if(w.getPassword().equals(password)) {

					if(w.getFunction() == Function.maid) {
						MaidFrame mf = new MaidFrame((Maid)w,roomM,login.this, cr);
						login.this.dispose();
						mf.setVisible(true);
					}
					
					if(w.getFunction() == Function.receptionist) {

						ReceptionistFrame rf = new ReceptionistFrame(rec,rm,am,roomM,tm,login.this, gm);
						login.this.dispose();
						rf.setVisible(true);
					}
					if(w.getFunction() == Function.admin) {
						 AdminFrame af = new AdminFrame(gm, wm, tm, am, rm, roomM,pm, login.this, cr, admin);
							login.this.dispose();

						 af.setVisible(true);
					}
					}
					else {
						LoginFailedDialog failed = new LoginFailedDialog();
						failed.setVisible(true);
					}
					
			
		}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnNewButton, "cell 1 11,alignx right");
	}

}
