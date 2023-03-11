package com.mycompany.cwskinconsultation;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ConsultationGUI extends JFrame{

    ArrayList<Doctor> doctors = new ArrayList<>();
    
    //buttons
    private JButton bookConsultationButton = new JButton();
    private JButton showConsultationButton = new JButton();
    private JButton sortDoctorsButton = new JButton();
    
    public ConsultationGUI(ArrayList<Doctor> doctors){
        //retrieve doctors array list
        this.doctors = doctors;
        
        //define column names of the table
        final String[] columnNames = { "Medical License Number", "Specialisation", "Name", "Sur Name", "Date of Birth", "Mobile Number"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("poppins", Font.PLAIN, 13));
        table.setRowHeight(30);
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();

        //insert doctors info into an object
        for (Doctor doctor : doctors) {
            Object [] doctorInfo = {
                doctor.getLicenseNumber(),
                doctor.getSpecialisation(),
                doctor.getName(),
                doctor.getSurName(),
                doctor.getDateOfBirth(),
                doctor.getMobileNumber()
            };
            model.addRow(doctorInfo);
        }
        
        //add the table to the panel
        JPanel tablePanel = new JPanel();
        tablePanel.add(new JScrollPane(table),BorderLayout.CENTER);

        
        //event 
        EventHandler newEvent = new EventHandler();
        bookConsultationButton.addActionListener(newEvent);
        showConsultationButton.addActionListener(newEvent);
        sortDoctorsButton.addActionListener(newEvent);
        
        //button panel
        bookConsultationButton.setText("Book Consultation");
        showConsultationButton.setText("show consultations");
        sortDoctorsButton.setText("Sort Doctors");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPanel.add(bookConsultationButton);
        buttonPanel.add(showConsultationButton);
        buttonPanel.add(sortDoctorsButton);

        //add panels into the frame
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

       
        //frame;
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Skin Consultation Centre");
        this.setVisible(true);
        
    }
    
    private class EventHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()== bookConsultationButton) {
                AddConsultationGui bookConsultation = new AddConsultationGui();
            }else if (e.getSource() == showConsultationButton) {
                ShowConsultation showConsultation = new ShowConsultation();
            }else if(e.getSource() == sortDoctorsButton){
                SortDoctorsGui sortedGui = new SortDoctorsGui();
            }
        }
    
    }
}

