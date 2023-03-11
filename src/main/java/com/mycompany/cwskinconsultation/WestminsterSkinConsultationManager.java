//Name: Avindya Jayangi
//UoW No: w1912938
//IIT No: 20210241

package com.mycompany.cwskinconsultation;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;


public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    static Scanner scanner = new Scanner(System.in);
    static File file = new File("doctors.txt");
    
    //main method
    public static void main(String[] args) {

        WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();

        
        System.out.println("Welcome to Skin Consultation Centre!");
        System.out.println("Please choose a number to carry out a specific task.");
        
        // load data from the file when starting the program
        
        if (file.exists()) {
            Doctor.doctors = loadDoctorsFromFile();
            
            while(true){
                System.out.println("""
                                    1 : Add a new doctor
                                    2 : Delete a doctor 
                                    3 : print the list of doctors 
                                    4 : save all the details into a file
                                    5 : Open the GUI
                                    6 : exit the program""");
                System.out.print("Enter your option number: ");
                String userInput = scanner.next();
                switch (userInput) {
                    case "1":
                        w1.addDoctor();
                        break;
                    case "2":
                        if (Doctor.doctors.isEmpty()) {
                            System.out.println("Doctor list is empty");
                        }else{
                            w1.deleteDoctor();
                            break;
                        }
                        break;
                    case "3":
                        if (Doctor.doctors.isEmpty()) {
                            System.out.println("Doctor list is empty");
                        }else{
                            w1.printDoctors();
                            break;
                        }
                        break;
                        //code
                    case "4":
                        w1.saveDoctorsToFile(Doctor.doctors);
                        System.out.println("successfully saved data to a file.");
                        break;

                    case "5":
                        ConsultationGUI consultationGUI = new ConsultationGUI(Doctor.doctors);
                        //code to open gui 
                        break;
                    case "6":


                        //exit the program
                        System.out.print("Would you like to remove the saved data from the file? (Y/N): ");
                        String userOption = scanner.next();
                        userOption = userOption.toUpperCase();
                        if (userOption.equals("Y")) {
                            System.out.println("Deleted doctors.txt file successfully!!!");
                            file.delete();
                        }
                        System.exit(0);
                    default:
                        System.out.println("please enter a valid input!!!");
                        break;
                }
            }
            
        } else {
            while(true){
                System.out.println("""
                                    1 : Add a new doctor 
                                    2 : Delete a doctor 
                                    3 : print the list of doctors 
                                    4 : save all the details into a file
                                    5 : Open the GUI
                                    6 : exit the programme""");
                System.out.print("Enter your option number: ");
                String userInput = scanner.next();
                switch (userInput) {
                    case "1":
                        w1.addDoctor();
                        break;
                    case "2":
                        if (Doctor.doctors.isEmpty()) {
                            System.out.println("Doctor list is empty");
                        }else{
                            w1.deleteDoctor();
                            break;
                        }
                        break;
                    case "3":
                        if (Doctor.doctors.isEmpty()) {
                            System.out.println("Doctor list is empty");
                        }else{
                            w1.printDoctors();
                            break;
                        }
                        break;
                        //code
                    case "4":
                        w1.saveDoctorsToFile(Doctor.doctors);
                        System.out.println("successfully saved data to a file.");
                        break;

                    case "5":
                        //code to open gui
                        ConsultationGUI consultationGUI = new ConsultationGUI(Doctor.doctors);
                        break;

                    case "6":



                        //exit the program
                        if(file.exists()){
                            System.out.print("Would you like to remove the saved data from the file? (Y/N): ");
                            String userOption = scanner.next();
                            userOption = userOption.toUpperCase();
                            if (userOption.equals("Y")) {
                                System.out.println("Deleted doctors.txt file successfully!!!");
                                file.delete();
                            }
                        }
                        
                        System.exit(0);
                        
                    default:
                        System.out.println("please enter a valid input!!!");
                        break;
                }
            }
        }
    }
    
    // convert string into date format(dd-MM-yyyy)
    public String dateFormat(){
        // Create a SimpleDateFormat object
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        // Declare a Date object
        Date date = null;
        String formattedDate;

        // Keep looping until the user enters a valid date
        while (date == null) {
          System.out.print("Enter the date of birth of the Doctor (dd-MM-yyyy): ");
          String input = scanner.next();
          
          try {
            // Try to parse the input string using the SimpleDateFormat object
            date = dateFormat.parse(input);
            
          } catch (ParseException e) {
            // If the string could not be parsed, print an error message and continue the loop
            System.out.println("Invalid date format. Please try again.");
          }
       
        }
        //return the formatted date
        formattedDate = dateFormat.format(date);
        return formattedDate;    
    }
    
    //implementation of add doctor method
    @Override
    public void addDoctor(){
        
        boolean continueLoop = true;
//        boolean stringCheck = true;
        
        while (continueLoop) {

            if(Doctor.doctors.size()<10 && Doctor.doctors.size()>=0){
                
                // define a variable to store string user inputs
                String input;
                //name
                while(true){
                    System.out.print("Enter the name of the Doctor: ");
                    input = scanner.next().toUpperCase();
                    if (input.matches("[a-zA-Z]+")) {
                        break;
                    }else{
                        System.out.println("please enter only strings!!");
                    }
                }
                
                // assigning user inputted value into newly created name variable
                String name = input;
                //sur name
                while(true){
                    System.out.print("Enter the sur name of the Doctor: ");
                    input = scanner.next().toUpperCase();
                    if (input.matches("[a-zA-Z]+")) {
                        break;
                    }else{
                        System.out.println("please enter only strings!!");
                    }
                }
                
                // assigning user inputted value into newly created name variable
                String surName = input;
                //date of birth
                //call the dateFormat method
                String dateOfBirthFinal = dateFormat();
                
                
                //mobile number
                int mobileNumber = 0;
                while(true){
                    System.out.print("Enter the mobile number of the Doctor: ");
                    input = scanner.next();
                    if (input.matches("\\d+")) {
                        mobileNumber = Integer.parseInt(input);
                        break;
                    }else{
                        System.out.println("Mobile number should contain only integers!!!");
                    }
                }
                int mobileNo = mobileNumber;
                
                
                int licenseNumber = 0;

                while (true) {
                    System.out.print("Enter the medical license number of the Doctor: ");
                    input = scanner.next();

                    // Validate that the input is a number
                    if (input.matches("\\d+")) {
                        licenseNumber = Integer.parseInt(input);

                        // Check if the license number is already in use
                        boolean isDuplicate = false;
                        for (Doctor doctor : Doctor.doctors) {
                            if (licenseNumber == doctor.getLicenseNumber()) {
                                isDuplicate = true;
                                break;
                            }
                        }

                        if (!isDuplicate) {
                            // The license number is not a duplicate, so we can exit the loop
                            break;
                        } else {
                            System.out.println("Medical license number cannot be duplicated!!!");
                        }
                    } else {
                        System.out.println("Medical license number should contain only integers!!");
                    }
                }

                
                //specialisation
                while(true){
                    System.out.print("Enter the specialisation of the Doctor: ");
                    input = scanner.next();
                    if (input.matches("[a-zA-Z]+")) {
                        break;
                    }else{
                        System.out.println("please enter only letters!!");
                    }
                }
                // assigning user inputted value into newly created name variable
                String specialisation = input;

                //creating a new doctor object
                Doctor doctor = new Doctor(licenseNumber, specialisation, name, surName, dateOfBirthFinal, mobileNumber);
                
                //add newly added doctor into the doctors array
                Doctor.doctors.add(doctor);

                //validating user input
                while(true){
                    System.out.println("Do you want to continue the process? enter 'Y' for yes and 'N' for no: ");
                    String answer = scanner.next();

                    //convert user inputted string into uppercase
                    answer = answer.toUpperCase();
                    if (answer.equals("N")) {
                        continueLoop=false;
                        break;
                    }else if (answer.equals("Y")) {
                        break;
                    }else{
                        System.out.println("Please enter 'Y' or 'N'!!!");
                    }
                }
                
                
            }else {
                System.out.println("Maximum 10 doctors can be added! You have added " + Doctor.doctors.size() + "Doctors.");
                break;       
            }
                
            
        }
        
        //print doctors sur name
        System.out.println("Names of the Doctors:");
        for (Doctor doctorList : Doctor.doctors) {
            System.out.println(doctorList.getSurName());
            System.out.println(doctorList.getName());
            System.out.println(doctorList.getDateOfBirth());
            System.out.println(doctorList.getLicenseNumber());
            System.out.println("0"+doctorList.getMobileNumber());
            System.out.println(doctorList.getSpecialisation());
        }
    }
    
    
    //delete doctor method
    @Override
    public void deleteDoctor(){
        
        boolean found = false;
        
        System.out.print("enter the medical license number of the doctor, that you want to delete :");
        String medicalLicenseNum = scanner.next();
        int licenseNumber = Integer.parseInt(medicalLicenseNum);
        for (Doctor doctor : Doctor.doctors) {
            if (doctor.getLicenseNumber() == licenseNumber) {
                found = true;
                System.out.println(doctor.getSurName() +" "+ doctor.getName() + " successfully deleted from the list");
                System.out.println("deleted doctor information");
                System.out.println("Medical License Number: " + doctor.getLicenseNumber());
                System.out.println("Date of Birth: " + doctor.getDateOfBirth());
                System.out.println("Mobile Number: " + doctor.getMobileNumber());
                System.out.println("Specialisation: " + doctor.getSpecialisation());
                
                Doctor.doctors.remove(doctor);
                System.out.println("Current number of doctors in the center: " + Doctor.doctors.size());
                break;
            }
        }
        if (!found) {
            System.out.println("A doctor with the license number " + licenseNumber + "does not exist!!!");
        }
    }
    
    //print the list of doctors
    @Override
    public void printDoctors(){
        // Sort the list in alphabetical order by surname
        Collections.sort(Doctor.doctors, new DoctorSurnameComparator());

        // Print the list of doctors
        for (Doctor doctor : Doctor.doctors) {
            System.out.println("Sorted list of doctors");
            System.out.println();  
            System.out.println("Name: " + doctor.getName() + " " + doctor.getSurName());
            System.out.println("Date of Birth: " + doctor.getDateOfBirth());
            System.out.println("Mobile Number: " + doctor.getMobileNumber());
            System.out.println("Medical license Number: " + doctor.getLicenseNumber());
            System.out.println("Specialisation: " + doctor.getSpecialisation());
            System.out.println();
        }
        
        
    }
    
    // Comparator for sorting doctors by surname
    class DoctorSurnameComparator implements Comparator<Doctor> {
        @Override
        public int compare(Doctor d1, Doctor d2) {
          return d1.getSurName().compareTo(d2.getSurName());
    }
}
    
    
    //save doctors information into a file
    @Override
    public void saveDoctorsToFile(ArrayList<Doctor> doctors) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Doctor doctor : Doctor.doctors) {
                    String line = doctor.getLicenseNumber() + "," + doctor.getSpecialisation()+ ","
                            + doctor.getName() + "," + doctor.getSurName() + "," +
                            doctor.getDateOfBirth()+ "," + doctor.getMobileNumber();
                    
                    writer.write(line);
                    writer.newLine();
                } }
        } catch (IOException e) {
            System.out.println("something went wrong"+ e);
        }
    }

    // load saved data from the file
    public static ArrayList<Doctor> loadDoctorsFromFile() {    
      try {
          try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
              String line;
              while ((line = reader.readLine()) != null) {
                  
                  String[] parts = line.split(",");
                  
                  int medicalLicenceNumber = Integer.parseInt(parts[0]);
                  String specialization = parts[1];
                  String name = parts[2];
                  String surname = parts[3];
                  String dateOfBirth = parts[4];
                  int mobNumber = Integer.parseInt(parts[5]);
                  
                  Doctor doctor = new Doctor(medicalLicenceNumber, specialization, name, surname, dateOfBirth, mobNumber);
                  
                  Doctor.doctors.add(doctor);
                  
              } 
          }
      } catch (IOException e) {
          System.out.println("something went wrong!!!");
      }
      return Doctor.doctors;
    }

    
}