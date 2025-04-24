/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import adt.*;
import boundary.CourseManagementUI;
import boundary.TutorialGroupManagementUI;
import dao.*;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import java.util.Scanner;
import utility.MessageUI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author boonxiang
 */
public class TutorialGroupManagement {

    Scanner scanner = new Scanner(System.in);
    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<Programme> ProgrammeList = new ArrayList<>();
    private ListInterface<TutorialGroup> TutorialGroupList = new ArrayList<>();
    private StudentDAO StudentDAO = new StudentDAO();
    private StudentInitializer StudentInitializer = new StudentInitializer();
    private ProgrammeDAO ProgrammeDAO = new ProgrammeDAO();
    private ProgrammeInitializer ProgrammeInitializer = new ProgrammeInitializer();
    private TutorialGroupDAO TutorialGroupDAO =  new TutorialGroupDAO();
    private TutorialGroupInitializer TutorialGroupInitializer= new TutorialGroupInitializer();
    private CourseManagementUI CourseManagementUI = new CourseManagementUI();
    private TutorialGroupManagementUI GroupManagementUI = new TutorialGroupManagementUI();
    
    
    public TutorialGroupManagement(){
    
        studentList = StudentDAO.retrieveFromFile();
        ProgrammeList = ProgrammeDAO.retrieveFromFile();
        TutorialGroupList = TutorialGroupDAO.retrieveFromFile();
    
    }
    
    public static void main(String[] args) {
        TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();
        tutorialGroupManagement.runTutorialGroupManagement();
    }
    
    public void runTutorialGroupManagement(){
        int choice = 0;
        do {
            choice = GroupManagementUI.getMenuChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    addGroupToProgramme(null,'N');
                    break;
                case 2:
                    removeGroupFromProgramme();
                    break;
                case 3:
                    listAllTutorialGroup();
                    break;
                case 4:
                    addNewStudentToGroup();
                    break;
                case 5:
                    removeStudentFromGroup();
                    break;
                case 6:
                    studentChangeGroup();
                    break;
                case 7:
                    listGroupStudent();
                    break;
                case 8:
                    mergeGroup();
                    break;
                case 9:
                    report();
                    break;
                case 10:
                    TutorialGroupReport();
                    break;
                   
               default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }
        

    //add new tutorial to programme
    public void addGroupToProgramme(String programmeCode,char check){

        displayProgramme(null);
        System.out.println("Please enter programme code that you want to add tutorial group");
        
        if (programmeCode == null || !findProgramme(programmeCode)) {
            do {
                programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
                if (!findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }

        
        System.out.println();
 
        String newGroupID = generateUniqueGroupID(programmeCode); // Generate a unique group ID
        String newGroupName = programmeCode + " Group " + getGroupNumber(newGroupID); // Generate the group name

        TutorialGroup newTutorialGroup = new TutorialGroup();
        newTutorialGroup.setProgrammeCode(programmeCode);
        newTutorialGroup.setGroupID(newGroupID);
        newTutorialGroup.setGroupName(newGroupName);

        TutorialGroupList.add(newTutorialGroup);
        TutorialGroupDAO.saveToFile(TutorialGroupList);
        
        System.out.println("New tutorial group added successfully:");
        System.out.println(newTutorialGroup);
        
    }

    //remove the tutorial group from programme
    public void removeGroupFromProgramme() {
        char check = 'N';
        String programmeCode;
        String groupID = "";
        
        do {
            displayProgramme(null);
            programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
            if (!findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');
        
        displayProgrammeGroup(programmeCode);
        System.out.print("Continue removing tutorial group from programme (Y/N): ");
        check = scanner.next().charAt(0);
        scanner.nextLine();
        
        while (check == 'Y') {
            groupID = GroupManagementUI.inputGroupID();
            if (findGroup(groupID)) {
                check = 'N';
            } else {
                System.out.println("Invalid group ID!");
                
            }
            System.out.println();
        }
        
        int indexToRemove = -1;
        for (int i = 0; i < TutorialGroupList.getNumberOfEntries() +1; i++) {
            if (TutorialGroupList.getEntry(i) != null) {
                if (TutorialGroupList.getEntry(i).getProgrammeCode().equals(programmeCode)
                        && TutorialGroupList.getEntry(i).getGroupID().equals(groupID)) {
                    indexToRemove = i;
                    break;
                }
            }
        }
        
        if (indexToRemove != -1) {
            TutorialGroupList.remove(indexToRemove);
            TutorialGroupDAO.saveToFile(TutorialGroupList);
            System.out.println(groupID + " has been removed from " + programmeCode + " successfully!");
        } else {
            System.out.println("Failed to remove " + groupID + " from " + programmeCode + " !");
        }
        
        System.out.println("\nPress enter to continue...");
        scanner.nextLine();
    
 
    }

    //list all tutorial group of a programme
    public void listAllTutorialGroup(){
    
        String programmeCode = null;
        char check = 'N';
        
        displayProgramme(null);
        System.out.print("Please enter programme code that you want to see");
        
        if (programmeCode == null || !findProgramme(programmeCode)) {
            do {
                programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
                if (!findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }

        displayProgrammeGroup(programmeCode);
    
    }
    
    //add new stduent in a tutorial group
    public void addNewStudentToGroup() {
        String programmeCode = null;
        char check = 'N';
        
        displayProgramme(null);
        System.out.print("Please enter programme code to add students to a group");
        
        if (programmeCode == null || !findProgramme(programmeCode)) {
            do {
                programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
                if (!findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }

        displayProgrammeGroup(programmeCode);
        System.out.println("");
        
        System.out.print("Enter group ID to add students:");
        String groupID = scanner.nextLine();

        // Find the group by its ID
        int groupIndex = findGroupIndex(groupID);
        if (groupIndex == -1) {
            System.out.println("Group not found!");
            return;
        }

        // Get the group and its students list
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);
        ListInterface<Student> groupStudentsList = group.getGroupStudentsList();

        // Ask the user to input student IDs to add to the group
        System.out.println("Enter student IDs to add to the group (separated by comma):");
        String inputStudentIDs = scanner.nextLine();
        String[] studentIDs = inputStudentIDs.split(",");

        // Add each student to the group if they exist and their programme code matches
        for (String studentID : studentIDs) {
            Student student = findStudent(studentID);
            if (student != null && student.getProgrammeCode().equals(programmeCode)) {
                boolean isStudentAlreadyInOtherGroup = false;
                // Iterate through all tutorial groups except the current group
                for (int i = 1; i <= TutorialGroupList.getNumberOfEntries(); i++) {
                    TutorialGroup otherGroup = TutorialGroupList.getEntry(i);
                    if (!otherGroup.getGroupID().equals(groupID) && otherGroup.getGroupStudentsList().contains(student)) {
                        isStudentAlreadyInOtherGroup = true;
                        break;
                    }
                }
                if (!isStudentAlreadyInOtherGroup) {
                    if (!groupStudentsList.contains(student)) { // Check if student is not already in the group then add
                        groupStudentsList.add(student);
                        // Save the updated tutorial group list to file
                        TutorialGroupDAO.saveToFile(TutorialGroupList);
                        System.out.println("Student " + studentID + " added to group successfully!");
                    } else {
                        System.out.println("Student " + studentID + " is already in the group!");
                    }
                } else {
                    System.out.println("Student " + studentID + " is already in another tutorial group!");
                }
            } else {
                System.out.println("Student " + studentID + " not found or not enrolled in the programme!");
            }
        }

        
        // Ask if the user wants to view detailed information about the group
        System.out.println("Do you want to view detailed information about this group? (Y/N)");
        char choice = scanner.next().charAt(0);
        scanner.nextLine(); // Consume newline character

        if (Character.toUpperCase(choice) == 'Y') {
            displayGroupDetail(group);
        }

    }
        
        
    public void removeStudentFromGroup(){
    
        String programmeCode = null;
        String groupID;
        char check = 'N';

        // Display available programmes and prompt user to input programme code
        displayProgramme(null);
        System.out.println("Please enter the programme code:");
        
        if (programmeCode == null || !findProgramme(programmeCode)) {
            do {
                programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
                if (!findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }

        // Display tutorial groups for the specified programme code and prompt user to input group ID
        displayProgrammeGroup(programmeCode);
        System.out.println("Enter the group ID to remove a student:");
        groupID = scanner.nextLine();

        // Find the group by its ID
        int groupIndex = findGroupIndex(groupID);
        if (groupIndex == -1) {
            System.out.println("Group not found!");
            return;
        }

        // Get the group and its students list
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);
        ListInterface<Student> groupStudentsList = group.getGroupStudentsList();

        // Display group details and prompt user to input student ID to remove
        displayGroupDetail(group);
        System.out.println("Enter the student ID to remove from the group:");
        String studentIDToRemove = scanner.nextLine();

        // Check if the student exists in the group
        boolean studentExistsInGroup = false;
        for (int i = 1; i <= groupStudentsList.getNumberOfEntries(); i++) {
            Student student = groupStudentsList.getEntry(i);
            if (student.getStudentID().equals(studentIDToRemove)) {
                groupStudentsList.remove(i); // Remove the student from the group
                TutorialGroupDAO.saveToFile(TutorialGroupList); // Save the updated group list to file
                System.out.println("Student removed from the group successfully!");
                studentExistsInGroup = true;
                break;
            }
        }

        if (!studentExistsInGroup) {
            System.out.println("Student not found in the group or already removed!");
        }

    
    }
    
    public void studentChangeGroup(){
        String programmeCode = null;
        String currentGroupID;
        char check = 'N';

        // Display available programmes and prompt user to input programme code
        displayProgramme(null);
        System.out.println("Please enter the programme code:");
        if (programmeCode == null || !findProgramme(programmeCode)) {
            do {
                programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
                if (!findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }

        // Display tutorial groups for the specified programme code and prompt user to input group ID
        displayProgrammeGroup(programmeCode);
        System.out.println("Enter the current group ID of the student:");
        currentGroupID = scanner.nextLine();

        // Find the current group by its ID
        int currentGroupIndex = findGroupIndex(currentGroupID);
        if (currentGroupIndex == -1) {
            System.out.println("Current group not found!");
            return;
        }

        // Get the current group and its students list
        TutorialGroup currentGroup = TutorialGroupList.getEntry(currentGroupIndex);
        ListInterface<Student> currentGroupStudentsList = currentGroup.getGroupStudentsList();

        // Display group details and prompt user to input student ID to change group
        displayGroupDetail(currentGroup);
        System.out.println("Enter the student ID to change group:");
        String studentIDToChange = scanner.nextLine();

        // Find the student by ID in the current group
        Student studentToChange = null;
        for (int i = 1; i <= currentGroupStudentsList.getNumberOfEntries(); i++) {
            Student student = currentGroupStudentsList.getEntry(i);
            if (student.getStudentID().equals(studentIDToChange)) {
                studentToChange = student;
                break;
            }
        }

        if (studentToChange == null) {
            System.out.println("Student not found in the current group!");
            return;
        }

        // Display available groups for the specified programme code
        displayProgrammeGroup(programmeCode);
        System.out.println("Enter the new group ID to move the student:");
        String newGroupID = scanner.nextLine();

        // Validate new group ID
        int newGroupIndex = findGroupIndex(newGroupID);
        if (newGroupIndex == -1 || newGroupID.equals(currentGroupID)) {
            System.out.println("Invalid new group ID!");
            return;
        }

        // Check if the new group belongs to the same programme code
        TutorialGroup newGroup = TutorialGroupList.getEntry(newGroupIndex);
        if (!newGroup.getProgrammeCode().equals(programmeCode)) {
            System.out.println("New group does not belong to the same programme!");
            return;
        }

        // Add the student to the new group and remove from the current group
        newGroup.getGroupStudentsList().add(studentToChange);
        
        int studentIndexToRemove = -1;
        for (int i = 1; i <= currentGroupStudentsList.getNumberOfEntries(); i++) {
             Student student = currentGroupStudentsList.getEntry(i);
             if (student.getStudentID().equals(studentToChange.getStudentID())) {
                 studentIndexToRemove = i;
                 break;
             }
         }

         if (studentIndexToRemove != -1) {
             currentGroupStudentsList.remove(studentIndexToRemove);
             System.out.println("Student moved to the new group successfully!");
         } else {
             System.out.println("Failed to move the student to the new group!");
         }
        TutorialGroupDAO.saveToFile(TutorialGroupList); // Save the updated group list to file

        System.out.println("Press enter to continue...");
        scanner.nextLine();

    }
    
    
    public void listGroupStudent(){
        String programmeCode = null;
        char check = 'N';

        displayProgramme(null);
        System.out.println("Please enter programme code to view student or tutorial group:");
        if (programmeCode == null || !findProgramme(programmeCode)) {
            do {
                programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
                if (!findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }

        System.out.println("Do you want to view all students under " + programmeCode + "? (Y/N)");
        check = scanner.next().charAt(0);
        scanner.nextLine(); // Consume newline character

        if (Character.toUpperCase(check) == 'Y') {
            // Display all students under the specified programme code
            System.out.println("List of Student Under " + programmeCode + ":");
            System.out.println(String.format("%-10s %-20s %-4s %-8s %-35s %-15s",
                    "StudentID", "Student Name", "Age", "Gender", "Email", "PhoneNo"));
            for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                Student student = studentList.getEntry(i);
                if (student.getProgrammeCode().equals(programmeCode)) {
                    System.out.println(String.format("%-10s %-20s %-4s %-8s %-35s %-15s",
                            student.getStudentID(), student.getName(), student.getAge(),
                            student.getGender(), student.getEmail(), student.getPhoneNo()));
                }
            }

            // Display tutorial groups under the specified programme code
            displayProgrammeGroup(programmeCode);
            System.out.println("Enter the group ID to view students:");
            String groupID = scanner.nextLine();

            // Find the group by its ID
            int groupIndex = findGroupIndex(groupID);
            if (groupIndex == -1) {
                System.out.println("Group not found!");
                return;
            }

            // Get the group and display its students list
            TutorialGroup group = TutorialGroupList.getEntry(groupIndex);
            displayGroupDetail(group);
        } else {
            // Display tutorial groups under the specified programme code
            displayProgrammeGroup(programmeCode);
            System.out.println("Enter the group ID to view students:");
            String groupID = scanner.nextLine();

            // Find the group by its ID
            int groupIndex = findGroupIndex(groupID);
            if (groupIndex == -1) {
                System.out.println("Group not found!");
                return;
            }

            // Get the group and display its students list
            TutorialGroup group = TutorialGroupList.getEntry(groupIndex);
            displayGroupDetail(group);
        }

    
    }
    
    public void mergeGroup(){
        String programmeCode = null;
        char check = 'N';
        int totalStudents = 0;

        // Display available programmes
        displayProgramme(null);
        System.out.println("Please enter programme code to merge tutorial groups:");
        if (programmeCode == null || !findProgramme(programmeCode)) {
            do {
                programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
                if (!findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }

        // Display tutorial groups for the specified programme code
        displayProgrammeGroup(programmeCode);
        System.out.println("Enter two tutorial group IDs to merge (separated by comma):");

        // Get user input for group IDs
        String inputGroupIDs = scanner.nextLine();
        String[] groupIDs = inputGroupIDs.split(",");

        // Validate input group IDs
        if (groupIDs.length != 2 || groupIDs[0].equals(groupIDs[1])) {
            System.out.println("Please provide exactly two different group IDs separated by comma.");
            return;
        }

        // Check if both groups exist and if they can be merged
        boolean group1Exists = findGroup(groupIDs[0]);
        boolean group2Exists = findGroup(groupIDs[1]);

        if (!group1Exists || !group2Exists) {
            System.out.println("Invalid group IDs!");
            return;
        }

        // Check if either group has more than 5 students
        for (String groupID : groupIDs) {
            int groupIndex = findGroupIndex(groupID);
            if (groupIndex != -1) {
                TutorialGroup group = TutorialGroupList.getEntry(groupIndex);
                totalStudents += group.getGroupStudentsList().getNumberOfEntries();
            }
        }

        if (totalStudents > 5) {
            System.out.println("Cannot merge groups with more than 5 students total.");
            return;
        }

        // Merge groups
        mergeTwoGroups(groupIDs[0], groupIDs[1]);
        System.out.println("Tutorial groups merged successfully!");
        
        //Show the group Student List to make sure student add inside
        displayProgrammeGroup(programmeCode);
        System.out.println("Enter the group ID to view students:");
        String groupID = scanner.nextLine();

        int groupIndex = findGroupIndex(groupID);
        if (groupIndex == -1) {
            System.out.println("Group not found!");
            return;
        }
        
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);
        displayGroupDetail(group);


    }
    
    public void report(){
        int choice = -1;
            do {
                choice = GroupManagementUI.reportChoice();
                switch (choice) {
                    case 0:
                        MessageUI.displayExitMessage();
                        break;
                    case 1:
                        programmeGroupReport();
                        break;
                    case 2:
                        TutorialGroupReport();
                        break;
                }
            } while (choice != 0);
       
    
    
    
    
    }
    
    public void programmeGroupReport(){
        System.out.println("=".repeat(150));
        System.out.printf("\n%57s", "");
        System.out.println("TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("=".repeat(150));
        System.out.printf("\n%65s", "");
        System.out.println("Tutorial Group Management Subsystem");
        System.out.printf("\n%66s", "");
        System.out.println("Programme Group Summary Report");
        System.out.printf("\n%61s", "");
        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("Generated at: " + getCurrentDateTime());
        System.out.println();
        System.out.format("%-10s %-80s %-30s %-30s", "Programme_Code", "\tProgramme_Name", "\tTutorial_Group_Offer", "\tTotal_Student");
        System.out.println("");
        

        int totalStudentsAllProgrammes = 0;
        String mostTutorialGroupOfferProgrammeCode = "";
        int mostTutorialGroupOfferCount = 0;
        ListInterface<String> fewestTutorialGroupOfferProgrammeCodes = new ArrayList<>();
        int fewestTutorialGroupOfferCount = Integer.MAX_VALUE;
        String mostStudentsProgrammeCode = "";
        int mostStudentsCount = 0;
        String fewestStudentsProgrammeCode = "";
        int fewestStudentsCount = Integer.MAX_VALUE;

        for (int i = 1; i <= ProgrammeList.getNumberOfEntries(); i++) {
            Programme programme = ProgrammeList.getEntry(i);
            String programmeCode = programme.getProgrammeCode();
            String programmeName = programme.getProgrammeName();

            int tutorialGroupOffer = 0;
            int totalStudents = 0;

            for (int j = 1; j <= TutorialGroupList.getNumberOfEntries(); j++) {
                TutorialGroup group = TutorialGroupList.getEntry(j);
                if (group.getProgrammeCode().equals(programmeCode)) {
                    tutorialGroupOffer++;
                    
                }
            }
            
            for (int k = 1; k <= studentList.getNumberOfEntries(); k++) {
                Student student = studentList.getEntry(k);
                if (student.getProgrammeCode().equals(programmeCode)) {
                    totalStudents++; 
                }
            }
            
            totalStudentsAllProgrammes += totalStudents;
            if (tutorialGroupOffer > mostTutorialGroupOfferCount && tutorialGroupOffer > 0) {
                mostTutorialGroupOfferCount = tutorialGroupOffer;
                mostTutorialGroupOfferProgrammeCode = programmeCode;
            }
            if (tutorialGroupOffer < fewestTutorialGroupOfferCount && tutorialGroupOffer > 0) {
                fewestTutorialGroupOfferCount = tutorialGroupOffer;
                fewestTutorialGroupOfferProgrammeCodes.clear();
                fewestTutorialGroupOfferProgrammeCodes.add(programmeCode); 
            }if (tutorialGroupOffer == fewestTutorialGroupOfferCount && tutorialGroupOffer > 0) {
                fewestTutorialGroupOfferProgrammeCodes.add(programmeCode);
            }
            if (totalStudents > mostStudentsCount) {
                mostStudentsCount = totalStudents;
                mostStudentsProgrammeCode = programmeCode;
            }
            if (totalStudents < fewestStudentsCount && totalStudents > 0) {
                fewestStudentsCount = totalStudents;
                fewestStudentsProgrammeCode = programmeCode;
            }

            
            System.out.println("");
            System.out.format("%-15s %-90s %-27d %-20d%n", programmeCode, programmeName, tutorialGroupOffer, totalStudents);
            }
                
        
        // Calculate total tutorial group offers across all programmes
        int totalTutorialGroupOffers = 0;
        for (int j = 1; j <= TutorialGroupList.getNumberOfEntries(); j++) {
            TutorialGroup group = TutorialGroupList.getEntry(j);
            totalTutorialGroupOffers++;
        }
        
        System.out.println("");
        System.out.println("-".repeat(150));
 

        //Display total tutorial group offer of all programme
        System.out.println("Total Tutorial Group Offers of All Programmes: " + totalTutorialGroupOffers);
        
        // Display programme with the most tutorial group offers
        System.out.println("Programme Offer Most Tutorial Group  :  " + mostTutorialGroupOfferProgrammeCode +
                " [" + mostTutorialGroupOfferCount + " tutorial group(s)]");
        
        // Display programme with the fewest tutorial group offers
        System.out.println("Programme Offer Fewest Tutorial Group : ");
        if (!fewestTutorialGroupOfferProgrammeCodes.isEmpty()) {
            System.out.print(fewestTutorialGroupOfferProgrammeCodes.getEntry(1));
            for (int i = 2; i < fewestTutorialGroupOfferProgrammeCodes.getNumberOfEntries(); i++) {
                System.out.print(" , " + fewestTutorialGroupOfferProgrammeCodes.getEntry(i)); // Print subsequent programs with comma separation
            }
            System.out.println(" [" + fewestTutorialGroupOfferCount + " tutorial group(s)]");
        } else {
            System.out.println("No programs found with the fewest tutorial groups.");
        }
        System.out.println("Note: [0] tutorial group offered is not counted");

        System.out.println("*".repeat(150));
        
        // Display total student count for all programmes
        System.out.println("Total Student of All Programme  :  " + totalStudentsAllProgrammes);

        // Display programme with the most students
        System.out.println("Programme with the most student    :  " + mostStudentsProgrammeCode +
                " [" + mostStudentsCount + " student(s)]");

        // Display programme with the fewest students
        System.out.println("Programme with the fewest student :  ");
        if (!fewestTutorialGroupOfferProgrammeCodes.isEmpty()) {
            System.out.print(fewestTutorialGroupOfferProgrammeCodes.getEntry(1));
            for (int i = 2; i <= fewestTutorialGroupOfferProgrammeCodes.getNumberOfEntries(); i++) {
                System.out.print(" , " + fewestTutorialGroupOfferProgrammeCodes.getEntry(i));
            }
            System.out.println(" [" + fewestStudentsCount + " student(s)]");
        } else {
            System.out.println("No programmes found with the fewest students.");
        }
        System.out.println("Note : [0] student(s) is not counted");

        System.out.println("*".repeat(150));

        System.out.printf("\n%63s", "");
        System.out.println("End of the Programme Group Summary Report\n");

        System.out.println("*".repeat(150));

        System.out.println("Press <ENTER> Key To Continue ...");
        scanner.nextLine(); 
        }
    
    public void TutorialGroupReport(){
     
        String programmeCode = null;
        char check = 'N';
        
        System.out.println("Please enter the programme code to view tutorial groups:");
        if (programmeCode == null || !findProgramme(programmeCode)) {
            do {
                programmeCode = CourseManagementUI.inputProgrammeCode().toUpperCase();
                if (!findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        };


        
        System.out.println("===============================================================================================");
        System.out.println("                   TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("===============================================================================================\n");
        System.out.println("                             Tutorial Group Management Subsystem");
        System.out.println("                                Tutorial Group Summary Report");
        System.out.println("                         ------------------------------------------");
        System.out.println();
        System.out.println("Generated at: " + getCurrentDateTime());
        System.out.println();
    
    
    
        System.out.println("Summary Report of " + programmeCode);


        System.out.format("%-15s %-30s %-40s%n", "Group_ID", "Group_Name", "GroupStudent");
        System.out.println("-----------------------------------------------------------------------------------------------");

        int totalStudents = 0;
        int totalStudentsInGroups = 0;
        int totalGroups = 0;
        int maxStudentsInGroup = 0;
        String groupWithMaxStudents = "";
        int idleGroupsCount = 0;
        StringBuilder idleGroupsStringBuilder = new StringBuilder();
        
        for (int k = 1; k <= studentList.getNumberOfEntries(); k++) {
            Student student = studentList.getEntry(k);
            if (student.getProgrammeCode().equals(programmeCode)) {
                totalStudents++; 
            }
        }

        for (int i = 1; i <= TutorialGroupList.getNumberOfEntries(); i++) {
            TutorialGroup group = TutorialGroupList.getEntry(i);
            if (group.getProgrammeCode().equals(programmeCode)) {
                ListInterface<Student> students = group.getGroupStudentsList();
                totalStudentsInGroups += students.getNumberOfEntries();
                totalGroups++;

                if (students.isEmpty()) {
                    idleGroupsCount++;
                    idleGroupsStringBuilder.append(group.getGroupID());
                    idleGroupsStringBuilder.append(", ");
                } else if (students.getNumberOfEntries() > maxStudentsInGroup) {
                    maxStudentsInGroup = students.getNumberOfEntries();
                    groupWithMaxStudents = group.getGroupID();
                }

                StringBuilder studentIDsBuilder = new StringBuilder();
                for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                    studentIDsBuilder.append(students.getEntry(j).getStudentID());
                    if (j < students.getNumberOfEntries()) {
                        studentIDsBuilder.append(", ");
                    }
                }
                String studentIDs = studentIDsBuilder.toString();
                if (studentIDs.isEmpty()) {
                    studentIDs = "NULL";
                }
                System.out.format("%-15s %-30s [%s]%n", group.getGroupID(), group.getGroupName(), studentIDs);
            }
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Total Student of Programme  :  " + totalStudents);
        System.out.println("Number of student already assigned in a tutorial group: " + totalStudentsInGroups);
        System.out.println("Number of student waiting for assigned in a tutorial group: " + (totalStudents - totalStudentsInGroups));
        System.out.println("************************************************************************************************");
        System.out.println("Tutorial Group with the most students: " + groupWithMaxStudents + " --> [" + maxStudentsInGroup + "] students");
        System.out.println("Idle Tutorial Groups:  [ " + idleGroupsCount + " ]  --->" + idleGroupsStringBuilder.toString());


        
    
        System.out.println("\n************************************************************************************************");

        System.out.println("\n                           End of the Programme Group Summary Report\n");

        System.out.println("************************************************************************************************");

        System.out.println("Press <ENTER> Key To Continue ...");
        scanner.nextLine(); 
    
    }
    

    
    
    public boolean findProgramme(String programmeCode) {
        for (int i = 0; i < ProgrammeList.getNumberOfEntries()+1; i++) {
            if (ProgrammeList.getEntry(i) != null && ProgrammeList.getEntry(i).getProgrammeCode().equals(programmeCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean findGroup(String groupID) {
        for (int i = 0; i < TutorialGroupList.getNumberOfEntries() +1; i++) {
            if (TutorialGroupList.getEntry(i) != null && TutorialGroupList.getEntry(i).getGroupID().equals(groupID)) {
                return true;
            }
        }
        return false;
    }
    
    private Student findStudent(String studentID) {
        if (studentID == null || studentID.isEmpty()) {
            // Handle invalid studentID (e.g., return null or throw an exception)
            return null;
        }

        // Assuming studentList is not null
        for (int i = 0; i < studentList.getNumberOfEntries()+1; i++) {
            Student student = studentList.getEntry(i);
            if (student != null && student.getStudentID() != null && student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null; // Student not found
    }

    private void displayGroupDetail(TutorialGroup group) {
        System.out.println("Student details for " + group.getGroupName() + ":");
        System.out.println(String.format("%-10s %-25s %-5s %-8s %-35s %-15s", "studentID", "studentName", "age", "gender", "email", "phoneNo"));
        ListInterface<Student> students = group.getGroupStudentsList();
        for (int i = 1; i <= students.getNumberOfEntries(); i++) {
            Student student = students.getEntry(i);
            System.out.println(String.format("%-10s %-25s %-5d %-8s %-35s %-15s",
                    student.getStudentID(), student.getName(), student.getAge(),
                    student.getGender(), student.getEmail(), student.getPhoneNo()));
        }
    }
       
    private void mergeTwoGroups(String groupID1, String groupID2) {
        // Find the indices of the groups in the list
        int index1 = findGroupIndex(groupID1);
        int index2 = findGroupIndex(groupID2);

        if (index1 == -1 || index2 == -1) {
            System.out.println("Error: One or both groups not found.");
            return;
        }

        // Merge group2 into group1
        TutorialGroup group1 = TutorialGroupList.getEntry(index1);
        TutorialGroup group2 = TutorialGroupList.getEntry(index2);

        // Check if the total number of students in both groups is more than 5
        int totalStudents = group2.getGroupStudentsList().getNumberOfEntries() + group1.getGroupStudentsList().getNumberOfEntries();
        if (totalStudents > 5) {
            System.out.println("Cannot merge groups with more than 5 students total.");
            return;
        }

        // Add students from group2 to group1
        ListInterface<Student> group1Students = group1.getGroupStudentsList();
        ListInterface<Student> group2Students = group2.getGroupStudentsList();

        for (int i = 1; i <= group2Students.getNumberOfEntries(); i++) {
            group1Students.add(group2Students.getEntry(i));
        }

        // Remove group2 from the list
        TutorialGroupList.remove(index2);
        TutorialGroupDAO.saveToFile(TutorialGroupList); // Save the updated group list to file
    }
    
    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    
    public int findGroupIndex(String groupID) {
        for (int i = 1; i <= TutorialGroupList.getNumberOfEntries(); i++) {
            if (TutorialGroupList.getEntry(i) != null && TutorialGroupList.getEntry(i).getGroupID().equals(groupID)) {
                return i;
            }
        }
        return -1;
    }
    
    public String getAllProgramme(String programmeCode) {
        String outputStr = "";
        
        if (programmeCode == null) {
            for (int i = 1; i <= ProgrammeList.getNumberOfEntries(); i++) {
                outputStr += ProgrammeList.getEntry(i) + "\n";
            }
        } else {
            for (int i = 1; i <= ProgrammeList.getNumberOfEntries(); i++) {
                if (programmeCode.equals(ProgrammeList.getEntry(i).getProgrammeCode())) {
                    outputStr += ProgrammeList.getEntry(i) + "\n";
                }
            }
        }
        if (outputStr.isEmpty()) {
            return "There are no programme taking this course!\n";
        }
        return outputStr;

    }

    public String getAllProgrammeGroup(String programmeCode) {
        String outputStr = "";
        
        if (programmeCode == null) {
            for (int i = 1; i <= TutorialGroupList.getNumberOfEntries(); i++) {
                outputStr += TutorialGroupList.getEntry(i) + "\n";
            }
        } else {
            for (int i = 1; i <= TutorialGroupList.getNumberOfEntries(); i++) {
             TutorialGroup group = TutorialGroupList.getEntry(i);
             if (group.getProgrammeCode().equals(programmeCode)) {    
                 outputStr += group + "\n";
                }
            }
        }
        if (outputStr.isEmpty()) {
            return "There are no group taking this programme!\n";
        }
        return outputStr;
        
    }
    
    private String generateUniqueGroupID(String programmeCode) {
        int nextGroupNumber = getNextGroupNumber(programmeCode); // Get the next available group number
        String formattedGroupNumber = String.format("%04d", nextGroupNumber);
        String newGroupID = programmeCode + formattedGroupNumber;

        // Check if the generated group ID already exists, generate a new one if needed
        while (findGroup(newGroupID)) {
            nextGroupNumber++;
            formattedGroupNumber = String.format("%04d", nextGroupNumber);
            newGroupID = programmeCode + formattedGroupNumber;
        }

        return newGroupID;
    }

    private int getGroupNumber(String groupID) {
        return Integer.parseInt(groupID.substring(groupID.length() - 4)); // Extract the group number from the group ID
    }
    
    private int getNextGroupNumber(String programmeCode) {
        int lastGroupNumber = TutorialGroup.getLastGroupNumberMap().getOrDefault(programmeCode, 0);
        return lastGroupNumber + 1;
    }
    
    public void displayProgramme(String programmeCode) {
        CourseManagementUI.listAllProgramme(getAllProgramme(programmeCode));
    }

    public void displayProgrammeGroup(String programmeCode) {
        GroupManagementUI.listAllTutorialGroup(getAllProgrammeGroup(programmeCode));
    }
    
}


