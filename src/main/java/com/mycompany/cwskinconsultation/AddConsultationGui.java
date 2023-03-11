package com.mycompany.cwskinconsultation;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.*;

public class AddConsultationGui extends JFrame implements Serializable {
    
    JLabel name, surName, dob, phoneNum, selectDoc, selectTime, cost, notes, consultationDate;
    JTextField nameField, surNameField, dateOfBirthField, mobileNumField, costField ,consultationDateField;
    JTextArea notesTextArea;
    JComboBox<String> selectDoctor, selectTimeSlot;
    
    //encrypt decrypt
    byte []encryptedData;
    SecretKey key;
    Cipher cipher;
    
    JPanel panel1;
    ArrayList<String> timeSlots = new ArrayList<>();

    double sessionCost = 15;
    String uuidStr = "";
    boolean alreadyExist = false;

    //declare variables
    String userEnteredName;
    String userEnteredSurName;
    String userEnteredDateOfBirth;
    int userEnteredMobileNum;
    Doctor userSelectedDoctor;
    String userSelectedTimeSlot;
    String userEnteredConsultDate;
    String userEnteredNotes;

    //constructor
    public AddConsultationGui(){

        
        //initialize time slots
        //assume that each patient can book a consultation for maximum of 30 mins long
        //a single day will have 10 consultations sessions for each doctor
        timeSlots.add("8.00-9.00");
        timeSlots.add("9.00-10.00");
        timeSlots.add("11.00-12.00");
        timeSlots.add("12.00-13.00");
        timeSlots.add("13.00-14.00");
        timeSlots.add("14.00-15.00");
        timeSlots.add("15.00-16.00");
        timeSlots.add("16.00-17.00");
        timeSlots.add("17.00-18.00");
        timeSlots.add("18.00-19.00");
        
        //this.setLayout(null);
        
        panel1 = new JPanel();
        
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = (gbc.gridx == 0) ? GridBagConstraints.EAST : GridBagConstraints.WEST;
        
        //components
        name = new JLabel("Name: ");
        surName = new JLabel("Sur Name: ");
        dob = new JLabel("date Of Birth: ");
        phoneNum = new JLabel("Mobile Number: ");
        selectDoc = new JLabel("Select a Doctor: ");
        selectTime = new JLabel("Select a timeslot: ");
        
        consultationDate = new JLabel("Enter a Date: ");
        
        notes = new JLabel("notes: ");
        
        nameField = new JTextField(30);
        surNameField = new JTextField(30);
        dateOfBirthField = new JTextField(30);
        mobileNumField = new JTextField(30);
        consultationDateField = new JTextField(30);
        notesTextArea = new JTextArea(10, 30);
        
        selectDoctor = new JComboBox<>();
        selectTimeSlot = new JComboBox<>();
        
        
        //add components into the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(name, gbc);
        gbc.gridx = 1;
        panel1.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(surName, gbc);
        gbc.gridx = 1;
        panel1.add(surNameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(dob, gbc);
        gbc.gridx = 1;
        panel1.add(dateOfBirthField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel1.add(phoneNum, gbc);
        gbc.gridx = 1;
        panel1.add(mobileNumField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel1.add(selectDoc, gbc);
        gbc.gridx = 1;
        panel1.add(selectDoctor, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel1.add(selectTime, gbc);
        gbc.gridx = 1;
        panel1.add(selectTimeSlot, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel1.add(consultationDate, gbc);
        gbc.gridx = 1;
        panel1.add(consultationDateField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel1.add(notes, gbc);
        gbc.gridx = 1;
        panel1.add(notesTextArea, gbc);

        JButton submitButton = new JButton("Add Consultation");


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                setVisible(false);
                checkAvailability();
            }
        });
        
                
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel1.add(submitButton, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        
        //add doctors into the combo box
        for (Doctor doctor : Doctor.doctors) {
            selectDoctor.addItem(doctor.getName() + " " + doctor.getSurName());
        }
        
        //add timeslots into the combo box
        for(String timeslot : timeSlots){
            selectTimeSlot.addItem(timeslot);
        }
        
        //add panel to JFrame
        this.add(panel1, BorderLayout.CENTER);

        
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setTitle("Add new Consultation");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
                
    }

    //check availability method
    public void checkAvailability(){
        //get entered data
        userEnteredName = nameField.getText().toUpperCase();

        
        userEnteredSurName = surNameField.getText().toUpperCase();

        userEnteredDateOfBirth = dateOfBirthField.getText();
        if (userEnteredDateOfBirth.isEmpty()) {
            infoBox("date cannot be empty", "warning"); 
        }else{
            
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
            sdfrmt.setLenient(false);
            try
            {
                Date dob = sdfrmt.parse(userEnteredDateOfBirth);
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                infoBox(userEnteredDateOfBirth + "is not a vallid date format", "warning");
            }
        }
        
        userEnteredMobileNum = Integer.parseInt(mobileNumField.getText());

        //selected doctor
        userSelectedDoctor = selectedDoctor();
        
        int selectedTimeIndex = selectTimeSlot.getSelectedIndex();
        userSelectedTimeSlot = timeSlots.get(selectedTimeIndex);

        userEnteredConsultDate = consultationDateField.getText();
        if (userEnteredConsultDate.isEmpty()) {
            infoBox("date cannot be empty", "warning"); 
        }else{
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
            sdfrmt.setLenient(false);
            try
            {
                Date dob = sdfrmt.parse(userEnteredConsultDate);
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                infoBox(userEnteredConsultDate + "is not a vallid date format", "warning");
            }
        }
        
        userEnteredNotes = notesTextArea.getText().toUpperCase();

        
        
        for (Consultation consultation : Consultation.consultations) {
            if ((((consultation.getPatient()).getName()).equals(userEnteredName)) && (((consultation.getPatient()).getSurName()).equals(userEnteredSurName))) {
                sessionCost = 25;
                alreadyExist = true;
                break;
            }
        }
        
        if (alreadyExist) {
            for (Consultation consultation : Consultation.consultations) {
                if ((((consultation.getPatient()).getName()).equals(userEnteredName)) && (((consultation.getPatient()).getSurName()).equals(userEnteredSurName))) {
                    uuidStr = consultation.getPatient().getPatientUniqueId();
                    break;
                }
            }
        }else{
            UUID uuid=UUID.randomUUID();
            uuidStr = uuid.toString();
            sessionCost = 15;
        }
        //create patient object
        Patient patient = new Patient(uuidStr, userEnteredName, userEnteredSurName, userEnteredDateOfBirth, userEnteredMobileNum);
        Patient.patients.add(patient);

        if (Consultation.consultations.size() == 0){

            WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();
            
            Consultation consultation = new Consultation(userEnteredConsultDate, userSelectedTimeSlot, sessionCost, userEnteredNotes, userSelectedDoctor, patient);
            Consultation.consultations.add(consultation);
            infoBox("consultation Booked", "success");
                
            
        }else{
            Doctor.tempDoctors.addAll(Doctor.doctors);
            doctorAvailability(userSelectedDoctor, userEnteredConsultDate, userSelectedTimeSlot, patient);
        }


    }

    public void doctorAvailability(Doctor doctor, String consultationSelectedDate, String selectedTimeSlot, Patient patient){
        Random random = new Random();
        boolean selectedDocHasConsultation = false;
        boolean selectedDocHasConsultationSelectedDateTime = false;
        Doctor randomDoctor;

        //check selected doctor has a consultation
        for (Consultation consultation : Consultation.consultations){
            if (consultation.getDoctor().getLicenseNumber() == doctor.getLicenseNumber()){
                selectedDocHasConsultation = true;
                break;
            }
        }

        if (selectedDocHasConsultation){
            for (Consultation consultation : Consultation.consultations){
                if (consultation.getDate().equals(consultationSelectedDate) &&
                        consultation.getTimeSlot().equals(selectedTimeSlot) &&
                        consultation.getDoctor().getLicenseNumber() == doctor.getLicenseNumber()){
                    selectedDocHasConsultationSelectedDateTime = true;
                    infoBox("consultation has been booked already! we will assign you a another doctor!!!", "success");
                    break;
                }
            }
        }

        if (selectedDocHasConsultationSelectedDateTime){
            Doctor.tempDoctors.remove(doctor);
            if (Doctor.tempDoctors.size()==0){
                infoBox("No doctors available at selected time and date", "warning");
            }else{
                int randomDoctorIndex = random.nextInt(Doctor.tempDoctors.size());
                randomDoctor = Doctor.tempDoctors.get(randomDoctorIndex);
                doctorAvailability(randomDoctor, userEnteredConsultDate, userSelectedTimeSlot, patient);
            }
        }else{
            WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();
            Consultation consultation = new Consultation(userEnteredConsultDate, userSelectedTimeSlot, sessionCost, userEnteredNotes, doctor, patient);
            Consultation.consultations.add(consultation);
            infoBox("consultation Booked", "success");
            
        }

    }
    //get doctor information
    public Doctor selectedDoctor(){
        int indexNum = selectDoctor.getSelectedIndex();
        return Doctor.doctors.get(indexNum);
    }
    
    //notification box
    public void infoBox(String infoMessage, String titleBar){
        
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
        
    }
}
