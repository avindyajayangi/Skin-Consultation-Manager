package com.mycompany.cwskinconsultation;
import java.io.Serializable;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
public class Consultation implements Serializable{

    private String date;
    private String timeSlot;
    private double cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;
    private int consultationId;
    private SecretKey key;
    private Cipher cipher;

    static ArrayList<Consultation> consultations = new ArrayList<>();

    public Consultation(String date, String timeSlot,
            double cost, String notes, Doctor doctor, Patient patient) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.cost = cost;
        this.notes = notes;
        this.doctor = doctor;
        this.patient = patient;
    }

    public SecretKey getKey() {
        return key;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public double getCost() {
        return cost;
    }

    public String getNotes() {
        return notes;
    }
    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public static ArrayList<Consultation> getConsultations() {
        return consultations;
    }
    
    
    
    
}
