/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jia shou
 */
public class Faculty implements Serializable {

    private String facultyCode;
    private String facultyName;

    public Faculty() {

    }

    public Faculty(String facultyCode, String facultyName) {
        this.facultyCode = facultyCode;
        this.facultyName = facultyName;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public int hashCode() {
        int hashKey = 5;
        return 14 * hashKey + Objects.hashCode(this.facultyCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Faculty faculty = (Faculty) obj;
        return facultyCode.equals(faculty.facultyCode);
    }

    @Override
    public String toString() {
        return String.format("%-20s %-68s",
                facultyCode, facultyName);
    }
}
