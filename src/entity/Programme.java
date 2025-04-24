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
public class Programme implements Serializable {
    
    private String programmeCode;
    private String programmeName;
    private String facultyCode;
    
    public Programme() {
        
    }
    
    public Programme(String programmeCode, String programmeName, String facultyCode) {
        this.programmeCode = programmeCode;
        this.programmeName = programmeName;
        this.facultyCode = facultyCode;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }
    
    @Override
    public int hashCode() {
        int hashKey = 5;
        return 14 * hashKey + Objects.hashCode(this.programmeCode);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Programme programme = (Programme) obj;
        return programmeCode.equals(programme.programmeCode);
    }

    @Override
    public String toString() {
        return String.format("%-20s %-80s", 
                programmeCode, programmeName);
    }
}
