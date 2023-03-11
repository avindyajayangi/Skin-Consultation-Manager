
package com.mycompany.cwskinconsultation;
import java.util.ArrayList;

public class Doctor extends Person {
     static ArrayList<Doctor> doctors = new ArrayList<>();

     static ArrayList<Doctor> tempDoctors = new ArrayList<>();
    
    private int licenseNumber;
    private String specialisation;

    public Doctor(int medicalLicenceNumber, String specialisation,
            String name, String surName, String dateOfBirth, int mobileNumber) {
        
        //calling the parent's constructor
        super(name, surName, dateOfBirth, mobileNumber);
        this.licenseNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
    }
    
    //getters

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }
    
    //setters

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
    
    
    
}

