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
public class GroupTutorCourse implements Serializable {
    
    private String groupID;
    private String tutorID;
    private String courseCode;

    public GroupTutorCourse() {
    }

    public GroupTutorCourse(String groupID, String tutorID, String courseCode) {
        this.groupID = groupID;
        this.tutorID = tutorID;
        this.courseCode = courseCode;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public int hashCode() {
        int hashKey = 5;
        return 14 * hashKey + Objects.hashCode(this.groupID);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        GroupTutorCourse groupTutorCourse = (GroupTutorCourse) obj;
        return groupID.equals(groupTutorCourse.groupID);
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s",
                groupID, tutorID, courseCode);
    }
    
}
