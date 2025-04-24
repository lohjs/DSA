
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


import adt.*;
import java.util.Objects;
import java.io.Serializable;


/**
 *
 * @author boon xiang
 */
public class TutorialGroup implements Serializable {

    private static MapInterface<String, Integer> lastGroupNumberMap = new CustomMap<>();
    private String programmeCode;
    private String groupID;
    private String groupName;
    private ListInterface<Student> groupStudentsList = new ArrayList<>();
   
    

    public TutorialGroup() {

    }

    public TutorialGroup(String programmeCode) {
        this.programmeCode = programmeCode;
        updateLastGroupNumber(programmeCode);
        int groupNumber = lastGroupNumberMap.getOrDefault(programmeCode, 1)+1;
        String formattedGroupNumber = String.format("%04d", groupNumber);  
        this.groupID = programmeCode + formattedGroupNumber;
        this.groupName = programmeCode + " Group " + groupNumber;
        lastGroupNumberMap.put(programmeCode, groupNumber);
        this.groupStudentsList = new ArrayList<>();

    
    }

    private void updateLastGroupNumber(String programmeCode) {
        lastGroupNumberMap.putIfAbsent(programmeCode, 0); 
    }

    public static MapInterface<String, Integer> getLastGroupNumberMap() {
        return lastGroupNumberMap;
    }
    

    public static void setLastGroupNumberMap(MapInterface<String, Integer> lastGroupNumberMap) {
        TutorialGroup.lastGroupNumberMap = lastGroupNumberMap;
    }


    
    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ListInterface<Student> getGroupStudentsList() {
        return groupStudentsList;
    }

    public void setGroupStudentsList(ListInterface<Student> groupStudentsList) {
        this.groupStudentsList = groupStudentsList;
    }
    
    public void addStudent(Student student) {
        groupStudentsList.add(student);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.groupID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TutorialGroup other = (TutorialGroup) obj;
        return Objects.equals(this.groupID, other.groupID);
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s", programmeCode, groupID,groupName);

    }
    
}