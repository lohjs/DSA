/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author hgtan
 */
public class Tutor implements Serializable {
    private String tutorID;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String phoneNo;
    private static int nextTutorID = 1000;
    
    public Tutor() {
        
    }

    public Tutor(String name, int age, String gender, String email, String phoneNo) {
        this.tutorID = "T" + nextTutorID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNo = phoneNo;
        nextTutorID++;
    }

    public String getTutorID() {
        return tutorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-40s %-5s %-10s %-30s %-20s",
                tutorID, name, age, gender, email, phoneNo);
    }

    @Override
    public int hashCode() {
        int hashKey = 5;
        return 14 * hashKey + Objects.hashCode(this.tutorID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tutor tutor = (Tutor) obj;
        return tutorID.equals(tutor.tutorID);
    }
}
