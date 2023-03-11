package com.mycompany.cwskinconsultation;

import java.util.ArrayList;

public interface SkinConsultationManager {
    //add doctor
    public void addDoctor();
    
    //delete doctor
    public void deleteDoctor();
    
    //Print the list of the doctors
    public void printDoctors();
    
    //save information in to file
    public void saveDoctorsToFile(ArrayList<Doctor> doctors);

    
}
