package entity;
import java.io.Serializable;
import java.util.Objects;
import adt.*;
import java.io.Serializable;

/**
 *
 * @author lock kwan
 */
public class Enrollment implements Serializable {

    private String studentID; // Reference to the Student object
    private String courseCode;
    private String enrollmentStatus; // Main, Repeat, etc.

    public Enrollment(String studentID, String courseCode, String enrollmentStatus) {
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.enrollmentStatus = enrollmentStatus;
    }

    // Getters for student, courseCode, enrollmentType, and paymentStatus
    public String getStudentID() {
        return studentID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
    
    @Override
    public int hashCode() {
        int hashKey = 5;
        return 14 * hashKey + Objects.hashCode(this.studentID);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Enrollment enrollment = (Enrollment) obj;
        return Objects.equals(studentID, enrollment.studentID);
    }

    @Override
    public String toString() {
        return String.format("%-12s %-12s %-10s ",
                studentID, courseCode, enrollmentStatus);
    }
}
