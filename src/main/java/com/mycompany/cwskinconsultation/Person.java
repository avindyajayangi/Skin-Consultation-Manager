package com.mycompany.cwskinconsultation;

import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String surName;
    private String dateOfBirth;
    private int mobileNumber;
    
    public Person(String name, String surName, String dateOfBirth, int mobileNumber){
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }
    
    // getters

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }
    
    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    
}
