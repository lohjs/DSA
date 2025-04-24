package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Lim Hoi Yau
 */
public class AssignmentTeam implements Serializable{
    private String assignmentTeamID;
    private String assignmentTeamName;
    private int assignmentTeamSize;
    private ListInterface<Student> teamMember = new ArrayList<>();
    private Student teamLeader;
    private TutorialGroup tutorialGroup; 
    private int numOfMember;

    public AssignmentTeam() {
         this.teamMember = new ArrayList<>(assignmentTeamSize);
        
    }
    
    public AssignmentTeam(String assignmentTeamID, String assignmentTeamName, int assignmentTeamSize, TutorialGroup tutorialGroup) {
        this.assignmentTeamID = assignmentTeamID;
        this.assignmentTeamName = assignmentTeamName;
        this.teamLeader = null;
        this.teamMember = new ArrayList<>(assignmentTeamSize);
        this.tutorialGroup = tutorialGroup; 
        this.assignmentTeamSize = assignmentTeamSize;
        this.numOfMember = 0;
    }

    public String getAssignmentTeamID() {
        return assignmentTeamID;
    }

    public void setAssignmentTeamID(String assignmentTeamID) {
        this.assignmentTeamID = assignmentTeamID;
    }

    public String getAssignmentTeamName() {
        return assignmentTeamName;
    }

    public void setAssignmentTeamName(String assignmentTeamName) {
        this.assignmentTeamName = assignmentTeamName;
    }

    public int getAssignmentTeamSize() {
        return assignmentTeamSize;
    }

    public void setAssignmentTeamSize(int assignmentTeamSize) {
        this.assignmentTeamSize = assignmentTeamSize;
         this.teamMember = new ArrayList<>(assignmentTeamSize);
    }
        
    public ListInterface<Student> getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(ListInterface<Student> teamMember) {
        this.teamMember = teamMember;
    }

    public Student getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(Student teamLeader) {
        this.teamLeader = teamLeader;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public int getNumOfMember() {
        return numOfMember;
    }

    public void setNumOfMember(int numOfMember) {
        this.numOfMember = numOfMember;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.assignmentTeamID);
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
        final AssignmentTeam other = (AssignmentTeam) obj;
        return Objects.equals(this.assignmentTeamID, other.assignmentTeamID);
        
    }

    @Override
    public String toString() {
        String teamLeaderName = (teamLeader != null) ? teamLeader.getName() : "-";
        String teamSize = (assignmentTeamSize != 0) ? String.valueOf(assignmentTeamSize) : "-";
        return String.format("%-30s %-30s %-15s %-15s %-20s %-10s", assignmentTeamID, assignmentTeamName, teamSize,teamLeaderName,  numOfMember,tutorialGroup.getGroupID());
    }

}
