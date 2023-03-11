/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cwskinconsultation;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SortDoctorsGui extends JFrame {
    
    private final JPanel headerPanel = new JPanel();
    private JLabel header = new JLabel();
    private final JTable sortedDocTable;
    private final JPanel tablePanel;
    ArrayList<Doctor> listToSort;
    ArrayList<Doctor> sortedDoc;
    
    public SortDoctorsGui(){
        
        //create a instance of WestminsterSkinConsultationManager
        
        listToSort = new ArrayList<>(List.copyOf(Doctor.doctors));
        
        //define column names of the table
        final String[] columnNames = { "Medical License Number", "Specialisation", "Name", "Sur Name", "Date of Birth", "Mobile Number"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        sortedDocTable = new JTable(model);
        //insert doctors info into an object
        sortDoctors();
        
        for (Doctor doctor : listToSort) {
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
        
        tablePanel = new JPanel();
        
        //add the table to the panel
        tablePanel.add(new JScrollPane(sortedDocTable),BorderLayout.CENTER);
        

        this.add(tablePanel, BorderLayout.CENTER);

        
        //frame;
        this.setSize(800, 800);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Skin Consultation Centre");
        this.setVisible(true);
    }
    
   
    //sort doctors
    public void sortDoctors(){
        // Sort the list in alphabetical order by surname
        Collections.sort(listToSort, new DoctorSurnameComparator());
        sortedDoc = new ArrayList<>(listToSort);
        
    }
    
    // Comparator for sorting doctors by surname
    class DoctorSurnameComparator implements Comparator<Doctor> {
        @Override
        public int compare(Doctor d1, Doctor d2) {
          return d1.getSurName().compareTo(d2.getSurName());
        }
    }
}
