/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author hgtan
 */
public class CourseTutor implements Serializable {
    private String courseCode;
    private String tutorID;
    private char[] tutorTypeArr;

    public CourseTutor() {
    }

    public CourseTutor(String tutorID, String courseCode, char[] tutorTypeArr) {
        this.tutorID = tutorID;
        this.courseCode = courseCode;
        this.tutorTypeArr = new char[tutorTypeArr.length];
        
        for (int i = 0; i < tutorTypeArr.length; i++) {
            this.tutorTypeArr[i] = tutorTypeArr[i];
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }    

    public char[] getTutorTypeArr() {
        return tutorTypeArr;
    }

    public void setTutorTypeArr(char[] tutorTypeArr) {
        this.tutorTypeArr = tutorTypeArr;
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
        CourseTutor courseTutor = (CourseTutor) obj;
        return courseCode.equals(courseTutor.courseCode);
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-15s", tutorID, courseCode, Arrays.toString(tutorTypeArr));
    }
}
