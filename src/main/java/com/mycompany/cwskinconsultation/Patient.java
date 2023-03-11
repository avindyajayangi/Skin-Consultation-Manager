
package com.mycompany.cwskinconsultation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Patient extends Person {
    static ArrayList<Patient> patients = new ArrayList<>();
    private String patientUniqueId;

    public Patient(String patientUniqueId, String name, String surName,
            String dateOfBirth, int mobileNumber) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.patientUniqueId = patientUniqueId;
    }
    
    //getter

    public String getPatientUniqueId() {
        return patientUniqueId;
    }
    
    //setter

    public void setPatientUniqueId(String patientUniqueId) {
        this.patientUniqueId = patientUniqueId;
    }
    
    
    
}
