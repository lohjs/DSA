/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package dao;

import adt.*;
import entity.AssignmentTeam;
import entity.TutorialGroup;

/**
 *
 * @author Lim Hoi Yau
 */
public class AssignmentTeamInitializer {

    public ListInterface<AssignmentTeam> initializeAssignmentTeam() {
        
        ListInterface<AssignmentTeam> assignmentTList = new ArrayList<>();
        
        TutorialGroup RDS0001 = new TutorialGroup("RDS");
        TutorialGroup RDS0002 = new TutorialGroup("RDS");
        TutorialGroup RDS0003 = new TutorialGroup("RDS");
        TutorialGroup RDS0004 = new TutorialGroup("RDS");
        
        TutorialGroup RSW0001 = new TutorialGroup("RSW");
        
        TutorialGroup RAN0001 = new TutorialGroup("RAN");
        TutorialGroup RAN0002 = new TutorialGroup("RAN");
        
        TutorialGroup RBS0001 = new TutorialGroup("RBS");
        
        TutorialGroup REE0001 = new TutorialGroup("REE");
        
        TutorialGroup REI0001 = new TutorialGroup("REI");
        
        TutorialGroup RFN0001 = new TutorialGroup("RFN");
        
        TutorialGroup RIS0001 = new TutorialGroup("RIS");
        
        TutorialGroup RME0001 = new TutorialGroup("RME");
        
        TutorialGroup RMM0001 = new TutorialGroup("RMM");
        
        TutorialGroup RNR0001 = new TutorialGroup("RNR");
        
        TutorialGroup RSE0001 = new TutorialGroup("RSE");
        
        TutorialGroup RST0001 = new TutorialGroup("RST");
        
        TutorialGroup RSD0001 = new TutorialGroup("RSD");
        
        TutorialGroup RMT0001 = new TutorialGroup("RMT");
        TutorialGroup RMT0002 = new TutorialGroup("RMT");
        TutorialGroup RMT0003 = new TutorialGroup("RMT");
        
        assignmentTList.add(new AssignmentTeam("RDS0001T1","RDS0001 Assignment Team 1",2, RDS0001));
        assignmentTList.add(new AssignmentTeam("RDS0001T2","RDS0001 Assignment Team 2", 4, RDS0001));
        assignmentTList.add(new AssignmentTeam("RDS0001T3","RDS0001 Assignment Team 3",4, RDS0001));
        assignmentTList.add(new AssignmentTeam("RDS0001T4","RDS0001 Assignment Team 4", 5,RDS0001));
        
        assignmentTList.add(new AssignmentTeam("RDS0002T1","RDS0002 Assignment Team 1",4, RDS0002));
        assignmentTList.add(new AssignmentTeam("RDS0002T2","RDS0002 Assignment Team 2", 5,RDS0002));
        
        assignmentTList.add(new AssignmentTeam("RDS0003T1","RDS0003 Assignment Team 1",4, RDS0003));
        assignmentTList.add(new AssignmentTeam("RDS0003T2","RDS0003 Assignment Team 2", 5,RDS0003));
        
        assignmentTList.add(new AssignmentTeam("RDS0004T1","RDS0004 Assignment Team 1",4, RDS0004));
        assignmentTList.add(new AssignmentTeam("RDS0004T2","RDS0004 Assignment Team 2", 5,RDS0004));
        
        assignmentTList.add(new AssignmentTeam("RBS0001T1","RBS0001 Assignment Team 1",4, RBS0001));
        assignmentTList.add(new AssignmentTeam("RBS0001T2","RBS0001 Assignment Team 2", 4, RBS0001));
        
        
        assignmentTList.add(new AssignmentTeam("RAN0001T1","RAN0001 Assignment Team 1",4, RAN0001));
        assignmentTList.add(new AssignmentTeam("RAN0001T2","RAN0001 Assignment Team 2", 4, RAN0001));
        
        assignmentTList.add(new AssignmentTeam("RAN0002T1","RAN0002 Assignment Team 1",4, RAN0002));
        assignmentTList.add(new AssignmentTeam("RAN0002T2","RAN0002 Assignment Team 2", 4, RAN0002));
        
        assignmentTList.add(new AssignmentTeam("REE0001T1","REE0001 Assignment Team 1",4, REE0001));
        assignmentTList.add(new AssignmentTeam("REE0001T2","REE0001 Assignment Team 2", 4, REE0001));
        
        assignmentTList.add(new AssignmentTeam("REI0001T1","REI0001 Assignment Team 1",4, REI0001));
        assignmentTList.add(new AssignmentTeam("REI0001T2","REI0001 Assignment Team 2", 4, REI0001));

        assignmentTList.add(new AssignmentTeam("RFN0001T1","RFN0001 Assignment Team 1",4, RFN0001));
        assignmentTList.add(new AssignmentTeam("RFN0001T2","RFN0001 Assignment Team 2", 4, RFN0001));
        
        assignmentTList.add(new AssignmentTeam("RIS0001T1","RIS0001 Assignment Team 1",4, RIS0001));
        assignmentTList.add(new AssignmentTeam("RIS0001T2","RIS0001 Assignment Team 2", 4, RIS0001));
        
        assignmentTList.add(new AssignmentTeam("RME0001T1","RME0001 Assignment Team 1",4, RME0001));
        assignmentTList.add(new AssignmentTeam("RME0001T2","RME0001 Assignment Team 2", 4, RME0001));
        
        assignmentTList.add(new AssignmentTeam("RMM0001T1","RMM0001 Assignment Team 1",4, RMM0001));
        assignmentTList.add(new AssignmentTeam("RMM0001T2","RMM0001 Assignment Team 2", 4, RMM0001));
        
        assignmentTList.add(new AssignmentTeam("RNR0001T1","RNR0001 Assignment Team 1",4, RNR0001));
        assignmentTList.add(new AssignmentTeam("RNR0001T2","RNR0001 Assignment Team 2", 4, RNR0001));
        
        assignmentTList.add(new AssignmentTeam("RSE0001T1","RSE0001 Assignment Team 1",4, RSE0001));
        assignmentTList.add(new AssignmentTeam("RSE0001T2","RSE0001 Assignment Team 2", 4, RSE0001));
        
        assignmentTList.add(new AssignmentTeam("RSD0001T1","RSD0001 Assignment Team 1",4, RSD0001));
        assignmentTList.add(new AssignmentTeam("RSD0001T2","RSD0001 Assignment Team 2", 4, RSD0001));

        assignmentTList.add(new AssignmentTeam("RST0001T1","RST0001 Assignment Team 1",4, RST0001));
        assignmentTList.add(new AssignmentTeam("RST0001T2","RST0001 Assignment Team 2", 4, RST0001));

        assignmentTList.add(new AssignmentTeam("RMT0001T1","RMT0001 Assignment Team 1",4, RMT0001));
        assignmentTList.add(new AssignmentTeam("RMT0001T2","RMT0001 Assignment Team 2", 4, RMT0001));   
        
        assignmentTList.add(new AssignmentTeam("RMT0002T1","RMT0002 Assignment Team 1",4, RMT0002));
        assignmentTList.add(new AssignmentTeam("RMT0002T2","RMT0002 Assignment Team 2", 4, RMT0002));
        
        assignmentTList.add(new AssignmentTeam("RMT0003T1","RMT0003 Assignment Team 1",4, RMT0003));
        assignmentTList.add(new AssignmentTeam("RMT0003T2","RMT0003 Assignment Team 2", 4, RMT0003));
        
        assignmentTList.add(new AssignmentTeam("RSW0001T1","RSW0001 Assignment Team 1", 4,RSW0001));
        assignmentTList.add(new AssignmentTeam("RSW0001T2","RSW0001 Assignment Team 2", 5, RSW0001));
        
        return assignmentTList;
    }

    public static void main(String[] args) {
        AssignmentTeamInitializer a = new AssignmentTeamInitializer();
        ListInterface<AssignmentTeam> assignmentTList = a.initializeAssignmentTeam();
        AssignmentTeamDAO assignmentTeamDAO = new AssignmentTeamDAO();
        assignmentTeamDAO.saveToFile(assignmentTList);
        System.out.println("\nAssignmentTeam:\n" + assignmentTList);
    }
}

