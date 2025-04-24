package entity;
import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author lock kwan
 */

public class Student implements Serializable {    
    private String studentID;
    private String name;
    private int age;
    private String gender;
    private String phoneNo;
    private String email;
    private String programmeCode;

    public Student(String studentID, String name, int age, String gender, String phoneNo, String email, String programmeCode) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.email = email;
        this.programmeCode = programmeCode;
    }

    // Additional getters and setters
    
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
    
    public String getPhoneNo() {
        return phoneNo;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getProgrammeCode() {
        return programmeCode;
    }
   
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
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
        Student student = (Student) obj;
        return Objects.equals(studentID, student.studentID);
    }

    @Override
    public String toString() {
        return String.format("%-12s %-25s %-5s %-8s %-35s %-15s %-6s",
                studentID, name, age,gender,email, phoneNo, programmeCode);
    }
}
