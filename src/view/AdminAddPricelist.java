package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import manage.AdditionalServicesManager;
import manage.PriceListManager;
import manage.TypeOfRoomManager;

import com.toedter.calendar.JDateChooser;

import entity.AdditionalServices;
import entity.PriceList;
import entity.TypeOfRoom;

import javax.swing.JTable;

public class AdminAddPricelist extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model1 = new DefaultTableModel();
    private JDateChooser dateChooser;
    private JDateChooser dateChooser_1;

    private JTable table;
    private JTable table_1;

    protected HashMap<String, AdditionalServices> services;
    protected HashMap<String, TypeOfRoom> typeOfRooms;

    public AdminAddPricelist(AdminPricelistFrame af, PriceListManager pm, AdditionalServicesManager addS, TypeOfRoomManager tm, PriceList p) {
        this.services = new HashMap<>();
        for (Map.Entry<String, AdditionalServices> entry : addS.services.entrySet()) {
            this.services.put(entry.getKey(), new AdditionalServices(entry.getValue()));
        }

        this.typeOfRooms = new HashMap<>();
        for (Map.Entry<String, TypeOfRoom> entry : tm.typeRooms.entrySet()) {
            this.typeOfRooms.put(entry.getKey(), new TypeOfRoom(entry.getValue()));
        }

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
                AdminAddPricelist.this.dispose();
                af.setVisible(true);
            }
        });

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Pocetak vazenja");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(139, 58, 131, 22);
        contentPane.add(lblNewLabel);

        JLabel lblCijena = new JLabel("Kraj vazenja");
        lblCijena.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCijena.setBounds(152, 117, 118, 22);
        contentPane.add(lblCijena);

        JLabel lblDodajteDodatnuUslugu = new JLabel("Cjenovnik");
        lblDodajteDodatnuUslugu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDodajteDodatnuUslugu.setBounds(263, 10, 356, 22);
        contentPane.add(lblDodajteDodatnuUslugu);

        JButton btnNewButton = new JButton("Dodaj");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSaveButton(addS, tm, pm, p);
            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(339, 337, 111, 37);
        contentPane.add(btnNewButton);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(280, 58, 170, 32);
        contentPane.add(dateChooser);

        dateChooser_1 = new JDateChooser();
        dateChooser_1.setBounds(280, 117, 170, 32);
        contentPane.add(dateChooser_1);

        table = new JTable(model);
        table.setBounds(139, 202, 184, 104);
        contentPane.add(table);

        table_1 = new JTable(model1);
        table_1.setBounds(377, 202, 184, 104);
        contentPane.add(table_1);

        model.addColumn("Usluga");
        model.addColumn("Cijena");

        fillServices(model, services);

        model1.addColumn("Tip");
        model1.addColumn("Cijena");

        fillTypes(model1, typeOfRooms);

        if (p != null) {
            Date start = Date.from(p.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            dateChooser.setDate(start);

            Date end = Date.from(p.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            dateChooser_1.setDate(end);

            fillServices(model, p.getServices());
            fillTypes(model1, p.getTypeOfRooms());

            model.addTableModelListener(new TableModelListener() {
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        updateServicePrice(row, column, model, p);
                    }
                }
            });

            model1.addTableModelListener(new TableModelListener() {
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        updateTypePrice(row, column, model1, p);
                    }
                }
            });
        }
    }

    // funkcija za punjenje tabele dodatnih usluga
    public void fillServices(DefaultTableModel model, HashMap<String, AdditionalServices> services) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        for (AdditionalServices a : services.values()) {
            model.addRow(new Object[] { a.getService(), a.getPrice() });
        }
    }

    // funkcija za punjenje tabele tipova sobe
    public void fillTypes(DefaultTableModel model, HashMap<String, TypeOfRoom> typeOfRooms) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        for (TypeOfRoom t : typeOfRooms.values()) {
            model.addRow(new Object[] { t.getType(), t.getPrice() });
        }
    }

    // funkcija za azuriranje cijena usluga
    private void updateServicePrice(int row, int column, DefaultTableModel model, PriceList p) {
        if (column == 1) { 
            String service = (String) model.getValueAt(row, 0);
            Integer price = Integer.valueOf(model.getValueAt(row, 1).toString());
            this.services.get(service).setPrice(price);
            p.setServices(services);
        }
    }

    // funkcija za azuriranje cijena tipova sobe
    private void updateTypePrice(int row, int column, DefaultTableModel model, PriceList p) {
        if (column == 1) { 
            String type = (String) model.getValueAt(row, 0);
            Integer price = Integer.valueOf(model.getValueAt(row, 1).toString());
            this.typeOfRooms.get(type).setPrice(price);
            p.setTypeOfRooms(typeOfRooms);
        }
    }

    private void handleSaveButton(AdditionalServicesManager addS, TypeOfRoomManager tm, PriceListManager pm, PriceList p) {
        LocalDate start = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = dateChooser_1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (start == null || end == null) {
            JOptionPane.showMessageDialog(this, "Unesite oba datuma.", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (end.isBefore(start)) {
            JOptionPane.showMessageDialog(this, "Datum kraja mora biti posle datuma pocetka.", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }

        addS.services = new HashMap<>(services);
        tm.typeRooms = new HashMap<>(typeOfRooms);
        
        if (p == null) {
            pm.createPricelist(start, end, typeOfRooms, services);
        } else {
            pm.changePricelist(p.getId(), start, end, typeOfRooms, services);
        }

        JOptionPane.showMessageDialog(this, "Cjenovnik je sacuvan.", "", JOptionPane.INFORMATION_MESSAGE);
    }
}