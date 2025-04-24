/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;
import entity.Course;
import entity.courseProgramme;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jia shou
 */
public class CourseManagementUI {

    Scanner scanner = new Scanner(System.in);
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mma");
    String formattedDateTime = currentDateTime.format(formatter);

    public int getMenuChoice() {
        System.out.println("\nCourse Management System Menu");
        System.out.println("1. Add Programme to Course");
        System.out.println("2. Remove Programme from Course");
        System.out.println("3. Add New Course to Programme");
        System.out.println("4. Remove Course from Programme");
        System.out.println("5. Search Courses Offered in a Semester");
        System.out.println("6. Modify Course Details");
        System.out.println("7. List All Courses Taken By Faculty");
        System.out.println("8. List All Course For a Programme");
        System.out.println("9. Summary Report");
        System.out.println("0. Quit");
        System.out.print("Please indicate your selection to proceed(1-9): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    public courseProgramme addProgrammetoCourse(String programmeCode, String courseCode) {
        System.out.print("New programme has been added to course successfully!\n");
        return new courseProgramme(courseCode, programmeCode);
    }

    public Course addNewCourse(String courseCode, String courseName, double creditHour, String academicYear) {
        System.out.print("New course had been added successfully!\n");
        System.out.print("\nPress enter to continue adding the new course to a programme...");
        scanner.nextLine();
        System.out.println("");
        return new Course(courseCode, courseName, creditHour, academicYear);
    }

    //Display Course List
    public void listAllCourse(String outputStr) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("\nCourse Details:");
            System.out.printf("\n%-100s\n%-15s %-40s %-14s %-10s\n%-100s\n",
                    "-".repeat(100), "Course Code", "Course Name", "Credit Hour", "Semester", "-".repeat(100));
            System.out.println(outputStr);
        }
    }

    //Display Programme
    public void listAllProgramme(String outputStr) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("Programme Details: ");
            System.out.printf("\n%-100s\n%-20s %-70s\n%-100s\n",
                    "-".repeat(100), "Programme Code", "Programme Name", "-".repeat(100));
            System.out.println(outputStr);
        }
    }

    //Display Course Programme List
    public void listAllCourseProgramme(String outputStr) {
        if (outputStr.isEmpty()) {
            System.out.println("\nThere is no record!");
        } else {
            System.out.println("\nList of Course Programmes:");
            System.out.printf("\n%-100s\n%-15s %-15s\n%-100s\n",
                    "-".repeat(100), "Course Code", "Programme Code", "-".repeat(100));
            System.out.println(outputStr);
        }
    }

    //Display Faculty List
    public void listAllFaculty(String outputStr) {
        if (outputStr.isEmpty()) {
            System.out.println("");
        } else {
            System.out.println("Faculty Details: ");
            System.out.printf("\n%-100s\n%-20s %-70s\n%-100s\n",
                    "-".repeat(100), "Faculty Code", "Faculty Name", "-".repeat(100));
            System.out.println(outputStr);
        }
    }

    public int reportChoice() {
        System.out.println("\nSummary Report Menu");
        System.out.println("1. Faculty Summary Report");
        System.out.println("2. Programme Summary Report");
        System.out.println("3. Course Summary Report");
        System.out.println("0. Back");
        System.out.print("Please select the report you would like to display(1-3): ");
        int Choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return Choice;
    }

    public int getTypeEditChoice() {
        System.out.println("\nEdit Course Details Menu");
        System.out.println("1. Course Name");
        System.out.println("2. Credit Hour");
        System.out.println("3. Academic Year");
        System.out.println("0. Back");
        System.out.print("Please select the details you would like to modify(1-3): ");
        int editChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return editChoice;
    }

    public void FacultyReport(String outputStr) {
        System.out.println("=".repeat(150));
        System.out.printf("\n%65s", "");
        System.out.println("FACULTY SUMMARY REPORT");
        System.out.printf("%65s\n", "");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-2s %-20s %-60s %-20s %-30s\n",
                " ", " Faculty Code", "Faculty Name", "Total Programme", "Total Course Offered");
        System.out.println("-".repeat(150));
        System.out.println(outputStr);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void CourseReport(String outputStr) {
        System.out.println("=".repeat(150));
        System.out.printf("\n%65s", "");
        System.out.println("COURSE SUMMARY REPORT");
        System.out.printf("%65s\n", "");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-3s %-15s %-36s %-18s %-15s %-20s %-20s\n",
                " ", "Course Code", "Course Name", "Credit Hour", "Semester", "Programmes Offered", "Faculty Offered");
        System.out.println(outputStr);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void ProgrammeReport(String outputStr) {
        System.out.println("=".repeat(150));
        System.out.printf("\n%65s", "");
        System.out.println("PROGRAMME SUMMARY REPORT");
        System.out.printf("%65s\n", "");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-3s %-20s %-82s %-20s %-20s\n",
                " ", "Programme Code", "Programme Name", "Courses Offered", "Faculty Offered");
        System.out.println(outputStr);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public int getFacultySortDetailsChoice() {
        System.out.println("\nSorting Menu");
        System.out.println("1. Faculty Code");
        System.out.println("2. Faculty Name");
        System.out.print("Please choose the criteria by which you'd like to sort the details in the faculty list(1-2):");
        int sortDetails = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return sortDetails;
    }

    public int getCourseSortDetailsChoice() {
        System.out.println("\nSorting Menu");
        System.out.println("1. Course Code");
        System.out.println("2. Course Name");
        System.out.print("Please choose the criteria by which you'd like to sort the details in the faculty list(1-2):");
        int sortDetails = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return sortDetails;
    }

    public int getProgrammeSortDetailsChoice() {
        System.out.println("\nSorting Menu");
        System.out.println("1. Programme Code");
        System.out.println("2. Programme Name");
        System.out.print("Please choose the criteria by which you'd like to sort the details in the faculty list(1-2):");
        int sortDetails = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return sortDetails;
    }

    public int inputSortType() {
        System.out.print("\n1. Ascending Order \n2. Desencing Order");
        System.out.print("\nPlease enter your choice of sort order(1-2): ");
        int orderType = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return orderType;
    }

    public String inputProgrammeCode() {
        System.out.print("Enter Programme Code: ");
        String programmeCode = scanner.nextLine();
        return programmeCode;
    }

    public String inputCourseCode() {
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        return courseCode;
    }

    public String inputCourseName() {
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        return courseName;
    }

    public double inputCreditHour() {
        System.out.print("Enter Credit Hour: ");
        double creditHour = scanner.nextDouble();
        scanner.nextLine();
        return creditHour;
    }

    public String inputAcademicYear() {
        System.out.print("Enter Academic Year: ");
        String academicYear = scanner.nextLine();
        return academicYear;
    }

    public String inputFacultyCode() {
        System.out.print("Enter Faculty Code: ");
        String facultyCode = scanner.nextLine();
        return facultyCode;
    }
}
