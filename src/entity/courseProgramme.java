/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;
import java.io.Serializable;

/**
 *
 * @author jia shou
 */
public class courseProgramme implements Serializable {
    private String courseCode;
    private String programmeCode;
    
    public courseProgramme() {
        
    }
    
    public courseProgramme(String courseCode, String programmeCode) {
        this.courseCode = courseCode;
        this.programmeCode = programmeCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }
    
        @Override
    public int hashCode() {
        int hashKey = 5;
        return 14 * hashKey + Objects.hashCode(this.courseCode);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        courseProgramme courseProgramme = (courseProgramme) obj;
        return courseCode.equals(courseProgramme.courseCode);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s", courseCode, programmeCode);
    }
}
