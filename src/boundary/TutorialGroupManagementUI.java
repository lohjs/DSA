/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;


import java.util.Scanner;
import entity.TutorialGroup;


/**
 *
 * @author boon xiang
 */
public class TutorialGroupManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\nTutorial Group Management System Menu");
        System.out.println("1. Add Tutorial Group to Programme ");
        System.out.println("2. Remove Tutorial Group from Programme");
        System.out.println("3. List All Tutorial Group For A Programme");
        System.out.println("4. Add New Student to Tutorial Group");
        System.out.println("5. Remove Student from Tutorial Group");
        System.out.println("6. Change the Tutorial Group for a Student");
        System.out.println("7. List All Students in a Tutorial Group and A Programme");
        System.out.println("8. Merge Tutorial Group Based on Criteria");
        System.out.println("9. Summary Report");
        System.out.println("0. Quit");
        System.out.print("Please indicate your selection to proceed(1-9): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }
    
    public TutorialGroup addGroupToProgramme(String programmeCode){
        System.out.print("New tutorial group has been added to programme successfully!\n");
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
        System.out.println("");
        return new TutorialGroup(programmeCode);
    
    }
    
    public TutorialGroup addNewStudentToGroup(String groupID,int studentId) {
        System.out.print("New student had been added successfully!\n");
        System.out.print("\nPress enter to continue adding the new student to a tutorial group...");
        scanner.nextLine();
        System.out.println("");
        return new TutorialGroup();
    }
    
    
    //Display TutorialGroup List
    public void listAllTutorialGroup(String outputStr) {
        if (outputStr.isEmpty()) {
            System.out.println("");
        } else {
            System.out.println("\nList of Tutorial Groups for Programme :");
            System.out.printf("\n%-60s\n%-15s %-15s %-15s \n%-60s\n",
                    "-".repeat(100), "Prgramme Code","Group ID","Group Name",
                     "-".repeat(100));
            System.out.println(outputStr);
        }
    }
    
    //Display Group Student List
    public void listGroupStudent(String outputStr) {
        if (outputStr.isEmpty()) {
            System.out.println("");
        } else {
            
            System.out.println("Student Details: ");
            System.out.printf("\n%-100s\n%-20s %-70s\n%-100s\n",
                    "-".repeat(100), "StudentID", "Student Name", "-".repeat(100));
            
            System.out.println(outputStr);
        }
    }
    
    public int reportChoice() {
        System.out.println("\nSummary Report Menu");
        System.out.println("1. Programme Group Summary Report");
        System.out.println("2. Tutorial Group Summary Report");
        System.out.println("0. Back");
        System.out.print("Please select the report you would like to display(1-2): ");
        int Choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return Choice;
    }
    
 
    public String inputGroupID() {
        System.out.print("Enter Tutorial Group ID: ");
        String groupID = scanner.nextLine();
        return groupID;
    }
    
        public String inputGroupName() {
        System.out.print("Enter Tutorial Group Name: ");
        String groupName = scanner.nextLine();
        return groupName;
    }
    
    
}