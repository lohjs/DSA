/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.CourseTutor;
import entity.GroupTutorCourse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author hgtan
 */
public class TeachingAssignmentUI {

    Scanner scanner = new Scanner(System.in);
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mma");
    String formattedDateTime = currentDateTime.format(formatter);

    public int getMenuChoice() {
        int choice = 0;
        boolean validInput = false;
        System.out.println("\nTeaching Assignment System Menu");
        System.out.println("1. Assign Tutor To Course ");
        System.out.println("2. Assign Tutorial Groups To Tutor ");
        System.out.println("3. Add Tutor To A Tutorial Group For A Course ");
        System.out.println("4. Search Course Under A Tutor ");
        System.out.println("5. Search Tutor For A Course ");
        System.out.println("6. List Tutor And Tutorial Groups For A Course ");
        System.out.println("7. List Courses For Each Tutor ");
        System.out.println("8. Filter Tutor Based on Criteria");
        System.out.println("9. Summary Report");
        System.out.println("0. Quit");
        do {
            try {
                System.out.print("Please indicate your selection to proceed(1-9): ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                validInput = true;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume invalid input
                MessageUI.displayInputErrorMessage();
            }
        } while (!validInput);
        System.out.println();
        return choice;
    }
    
    public CourseTutor assignTutorToCourse(String tutorID, String courseCode, char[] tutorTypeArr) {
        System.out.print("Assign tutor to course successfully!\n");
        return new CourseTutor(tutorID, courseCode, tutorTypeArr);
    }
    
    public GroupTutorCourse addTutorialGroupToTutor(String groupID, String tutorID, String courseCode) {
        System.out.print("Tutorial group added successfully!\n");
        return new GroupTutorCourse(groupID, tutorID, courseCode);
    }

    public void listAllTutor(String outputStr) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("Tutor Details: ");
            System.out.printf("\n%-120s\n%-15s %-40s %-5s %-10s %-30s %-20s\n%-120s\n",
                    "-".repeat(120), "TutorID", "Tutor Name", "Age", "Gender", "Email", "Phone No", "-".repeat(120));
            System.out.println(outputStr);
        }
    }
    
    public void listTutorCourse(String outputStr) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("Tutor Course Details: ");
            System.out.printf("\n%-55s\n%-20s %-20s %-15s\n%-55s\n",
                    "-".repeat(55), "TutorID", "Course Code", "Tutor Type", "-".repeat(55));
            System.out.println(outputStr);
        }
    }
    
    public void listProgrammeGroupUnderCourse(String outputStr, String courseCode) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("\nProgramme Groups Under: " + courseCode);
            System.out.printf("\n%-45s\n%-15s %-15s %-15s\n%-45s\n",
                    "-".repeat(45), "Programme", "Group ID", "Group Name", "-".repeat(45));
            System.out.println(outputStr);
        }
    }
    
    public void listTutorsUnderCourse(String outputStr, String courseCode) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("Tutor Under: " + courseCode);
            System.out.printf("\n%-120s\n%-15s %-40s %-5s %-10s %-30s %-20s\n%-120s\n",
                    "-".repeat(120), "TutorID", "Tutor Name", "Age", "Gender", "Email", "Phone No", "-".repeat(120));
            System.out.println(outputStr);
        }
    }
    
    public void listCoursesUnderTutor(String outputStr, String tutorID) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("\nCourses Under Tutor ID: " + tutorID);
            System.out.printf("\n%-100s\n%-15s %-40s %-14s %-10s\n%-100s\n",
                    "-".repeat(100), "Course Code", "Course Name", "Credit Hour", "Semester", "-".repeat(100));
            System.out.println(outputStr);
        }
    }
    
    public void listTutorsUnderCourseWithType(String outputStr, String courseCode, char tutorType) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("Tutor Under: " + courseCode);
            System.out.println("Tutor Type: " + tutorType);
            System.out.printf("\n%-120s\n%-15s %-40s %-5s %-10s %-30s %-20s\n%-120s\n",
                    "-".repeat(120), "TutorID", "Tutor Name", "Age", "Gender", "Email", "Phone No", "-".repeat(120));
            System.out.println(outputStr);
        }
    }
    
    public void listTutorAndGroupForCourse(String outputStr, String courseCode) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("Tutors and Tutorial Groups List For: " + courseCode);
            System.out.printf("\n%-60s\n%-20s %-20s %-20s\n%-60s\n",
                    "-".repeat(60), "GroupID", "TutorID", "Course", "-".repeat(60));
            System.out.println(outputStr);
        }
    }
    
    public int getCriteriaChoice() {
        int criteriaChoice = 0;
        System.out.println("\nFilter Tutor Criteria:");
        System.out.println("1. Age Above");
        System.out.println("2. Age Below");
        System.out.println("3. Gender");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        criteriaChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return criteriaChoice;
    }
    
    public String inputTutorID() {
        System.out.print("Enter tutor id to proceed: ");
        String tutorID = scanner.nextLine();
        return tutorID;
    }
    
    public char inputTutorType() {
        String input = "";
        do {
            System.out.print("Enter tutor type (L/T/P): ");
            input = scanner.nextLine();
            input = input.toUpperCase();
            if (input.length() == 1) {
                if (input.charAt(0) == 'L' || input.charAt(0) == 'T' || input.charAt(0) == 'P') {
                    continue;
                } else {
                    System.out.println("Invalid tutor type!");
                }
            } else {
                System.out.println("Error! Please enter only one character.");
            }
        } while (input.length() != 1);
        char tutorType = input.charAt(0);
        return tutorType;
    }
    
    public String inputTutorialGroup() {
        System.out.print("Enter the tutorial group id: ");
        String groupID = scanner.nextLine();
        return groupID;
    }
    
    public String inputTutorGender() {
        System.out.print("Enter tutor gender (Male/Female): ");
        String gender = scanner.nextLine();
        return gender;
    }
    
    public int inputTutorAgeAbove() {
        System.out.print("Filter tutor by age above: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return age;
    }
    
    public int inputTutorAgeBelow() {
        System.out.print("Filter tutor by age below: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return age;
    }
    
    public int inputReportChoice() {
        System.out.println("\nSummary Report Menu");
        System.out.println("1. Tutor Summary Report");
        System.out.println("2. Course Tutor Summary Report");
        System.out.println("0. Back");
        System.out.print("Please select the report you would like to display(1-2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return choice;
    }
    
    public void TutorReport() {
        System.out.println("=".repeat(150));
        System.out.printf("\n%65s", "");
        System.out.println("TUTOR SUMMARY REPORT");
        System.out.printf("%65s\n", "");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-3s %-20s %-78s %-20s %-20s\n",
                " ", "Tutor_ID", "Tutor_Name", "Courses", "Tutorial_Groups");
    }
    
    public void CourseReport() {
        System.out.println("=".repeat(150));
        System.out.printf("\n%65s", "");
        System.out.println("COURSE TUTOR SUMMARY REPORT");
        System.out.printf("%65s\n", "");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-3s %-20s %-78s %-20s %-5s %-5s %-5s\n",
                " ", "Course_Code", "Course_Name", "Total_Tutors", "L", "T", "P");
    }
}
