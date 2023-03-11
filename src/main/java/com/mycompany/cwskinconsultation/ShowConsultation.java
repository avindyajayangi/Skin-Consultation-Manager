/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cwskinconsultation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ShowConsultation extends JFrame {
    JPanel tablePanel = new JPanel();


    public ShowConsultation(){
        WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();
        

        final String[] columnNames = {"Date", "Time", "Cost", "Notes", "Doctor", "Patient", "ID"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable consultationTable = new JTable(model);

        //insert doctors info into an object
        for (Consultation consultations : Consultation.consultations) {
            Object [] consultationInfo = {
                consultations.getDate(),
                consultations.getTimeSlot(),
                consultations.getCost(),
                consultations.getNotes(),
                consultations.getDoctor().getName(),
                consultations.getPatient().getName(),
                consultations.getPatient().getPatientUniqueId()
            };
            model.addRow(consultationInfo);
        }
        
        //add the table to the panel
        tablePanel.add(new JScrollPane(consultationTable),BorderLayout.CENTER);
        this.add(tablePanel, BorderLayout.CENTER);

        //frame;
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Skin Consultation Centre");
        this.setVisible(true);
    }
}
