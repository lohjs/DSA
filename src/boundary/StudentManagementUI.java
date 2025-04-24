package boundary;

import java.util.Scanner;
import entity.Student;
import entity.Enrollment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utility.MessageUI;
import java.util.InputMismatchException;

/**
 *
 * @author Lock Kwan
 */
public class StudentManagementUI {

    Scanner scanner = new Scanner(System.in);
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mma");
    String formattedDateTime = currentDateTime.format(formatter);

    //Display student management menu, get the choice from the user and return the choice
    public int getMenuChoice() {
        int choice = 0;
        boolean validInput = false;

        System.out.println("\n==================================================");
        System.out.println("  Student Management Registration Subystem Menu");
        System.out.println("==================================================");
        System.out.println("1.  Display Student List");
        System.out.println("2.  Add New Student");
        System.out.println("3.  Remove Student");
        System.out.println("4.  Edit Student Details");
        System.out.println("5.  Search Students for Registered Courses");
        System.out.println("6.  Add Student to Courses");
        System.out.println("7.  Remove Student from Courses");
        System.out.println("8.  Calculation of Registration Course Fees");
        System.out.println("9.  Filter Student");
        System.out.println("10. Sort Student");
        System.out.println("11. Summary Report");
        System.out.println("0.  Quit");
        do {
            try {
                System.out.print("Please indicate your selection to proceed(1-11): ");
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

    //To display edit details menu and  get the choice from the user and returns the input as a integer.
    public int getTypeEditChoice() {
        System.out.println("\nEdit Student Details Menu");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Gender");
        System.out.println("4. Phone Number");
        System.out.println("5. Email");
        System.out.println("6. Programme");
        System.out.println("0: Back");
        System.out.print("Please select the details you would like to modify(1-6): ");
        int editChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return editChoice;
    }

    //Display Student List
    public void listAllStudents(String outputStr) {
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-5s %-8s %-35s %-15s %-6s", "Student ID", "Name", "Age", "Gender", "E-mail", "Phone Number", "Programme");
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------");
        System.out.println(outputStr);
    }

    //Prompt user to input a new student's name and returns the input as a string.
    public String inputNewStudentName() {
        System.out.print("\nEnter New Student Name: ");
        String newStudentName = scanner.nextLine();
        return newStudentName;
    }

    //Prompt user to input a new student's age and returns the input as a integer.
    public int inputNewStudentAge() {
        int newStudentAge = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print("Enter New Student Age: ");
                newStudentAge = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                validInput = true;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume invalid input
                MessageUI.displayInputErrorMessage();
            }
        } while (!validInput);
        return newStudentAge;
    }

    //Prompt user to input a new student's gender and returns the input as a string.
    public String inputNewStudentGender() {
        System.out.print("Enter New Student Gender: ");
        String newStudentGender = scanner.nextLine();
        return newStudentGender;
    }

    //Prompt user to input a new student's phone no and returns the input as a string.
    public String inputNewStudentPhoneNo() {
        System.out.print("Enter New Student Phone Number: ");
        String newPhoneNo = scanner.nextLine();
        return newPhoneNo;
    }

    //Prompt user to input a new student's email address and returns the input as a string.
    public String inputNewStudentEmail() {
        System.out.print("Enter New Student Email: ");
        String newStudentEmail = scanner.nextLine();
        return newStudentEmail;
    }

    //Prompt user to input a new student's programme code, referencing the programme 
    //list shown above and returns the input as a string.
    public String inputNewStudentProgrammeCode() {
        System.out.print("**PLEASE REFER PROGRAMME LIST SHOWN ABOVE");
        System.out.print("\nEnter New Student Programme Code: ");
        String newStudentProgrammeCode = scanner.nextLine();
        return newStudentProgrammeCode;
    }

    //Prompt user to decide if they want to input a new student again and returns the input as a string.
    public String inputNewStudentAgain() {
        System.out.print("\nDo you want enter new student again?(Yes/No): ");
        String newStudentAgain = scanner.nextLine();
        System.out.println();
        return newStudentAgain;
    }

    //This method gathers input for a new student's details and 
    //creates a new Student object with the provided information.
    public Student inputNewStudentDetails(String newStudentID, String newStudentName, int newStudentAge, String newStudentGender, String newStudentPhoneNo, String newStudentEmail, String newStudentProgrammeCode) {
        System.out.println();
        System.out.print("New student has been added successfully!\n");
        return new Student(newStudentID, newStudentName, newStudentAge, newStudentGender, newStudentPhoneNo, newStudentEmail, newStudentProgrammeCode);
    }

    //Prompt the user to input the student ID of the student they wish to remove.
    public String inputRemoveStudentID() {
        System.out.print("Please enter the studentID of the student you wish to remove: ");
        String removeStudentID = scanner.nextLine();
        return removeStudentID;
    }

    //Prompt the user to input the student ID of the student they wish to edit and returns the input as a string.
    public String inputEditStudentID() {
        System.out.print("Please enter the Student ID of the student whose details you wish to edit: ");
        String editStudentID = scanner.nextLine();
        return editStudentID;
    }

    //Prompt the user to enter a new name and returns the input as a string.
    public String inputNewName() {
        System.out.print("Please enter NEW name: ");
        String newName = scanner.nextLine();
        return newName;
    }

    //Prompt the user to enter a new age and returns the input as a integer.
    public int inputNewAge() {
        int newAge = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print("Please enter NEW age: ");
                newAge = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                validInput = true;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume invalid input
                MessageUI.displayInputErrorMessage();
            }
        } while (!validInput);
        return newAge;
    }

    //Prompt the user to enter a new gender and returns the input as a String.
    public String inputNewGender() {
        System.out.print("Please enter NEW gender: ");
        String newGender = scanner.nextLine();
        return newGender;
    }

    //Prompt the user to enter a new phone no and returns the input as a String.
    public String inputNewPhoneNo() {
        System.out.print("Please enter NEW Phone Number: ");
        String newPhoneNo = scanner.nextLine();
        return newPhoneNo;
    }

    //Prompt the user to enter a new email and returns the input as a String.
    public String inputNewEmail() {
        System.out.print("Please enter NEW Email: ");
        String newEmail = scanner.nextLine();
        return newEmail;
    }

    //Prompt the user to enter a programme code and returns the input as a String.
    public String inputNewProgrammeCode() {
        System.out.print("Please enter NEW Programme Code: ");
        String newProgrammeCode = scanner.nextLine();
        return newProgrammeCode;
    }

    //Prompt the user to enter a student id and returns the input as a String.
    public String inputStudentID() {
        System.out.print("Please enter Student ID: ");
        String studentID = scanner.nextLine();
        return studentID;
    }

    //Prompt the user to enter a course code and returns the input as a String.
    public String inputCourseCode() {
        System.out.print("Please enter Course Code: ");
        String courseCode = scanner.nextLine();
        return courseCode;
    }

    //Prompt user to decide if they want to search a new student again and returns the input as a string.
    public String inputSearchStudentAgain() {
        System.out.print("\nDo you want search student for registered course again?(Yes/No): ");
        String searchStudentAgain = scanner.nextLine();
        System.out.println();
        return searchStudentAgain;
    }

    //Prompt the user to enter a course code to register for students and returns the input as a String.
    public String inputEnrollmentCourseCode() {
        System.out.print("Please enter the Course Code you want to register for the student: ");
        String enrollmentCourseCode = scanner.nextLine();
        return enrollmentCourseCode;
    }

    //Prompt the user to enter a status to register for students and returns the input as a String.
    public String inputEnrollmentStatus() {
        System.out.print("Please enter Enrollment Status (Main/Resit/Repeat/Elective): ");
        String enrollmentStatus = scanner.nextLine();
        return enrollmentStatus;
    }

    //Prompt the user to input details for adding a student to a course and returns a 
    //new Enrollment object with the provided information.
    public Enrollment inputAddStudentToCourse() {
        String studentID = inputStudentID();
        String courseCode = inputEnrollmentCourseCode();
        String enrollmentStatus = inputEnrollmentStatus();
        System.out.println();
        System.out.print("Student has been added to a new course successfully!\n");
        return new Enrollment(studentID, courseCode, enrollmentStatus);
    }

    //Prompt user to decide if they want to add a new student to course again and returns the input as a string.
    public String inputAddStudentToCourseAgain() {
        System.out.print("\nDo you want add student to a new course again?(Yes/No): ");
        String addStudentToCourseAgain = scanner.nextLine();
        System.out.println();
        return addStudentToCourseAgain;
    }

    //Prompt the user to enter a course code to remove for students and returns the input as a String.
    public String inputRemoveEnrollmentCourseCode() {
        System.out.print("Please enter the Course Code you wish to remove from your student course: ");
        String removeEnrollmentCourseCode = scanner.nextLine();
        return removeEnrollmentCourseCode;
    }
    
    //Prompt the user to enter a status to remove for students and returns the input as a String.
    public String inputRemoveEnrollmentStatus() {
        System.out.print("\nPlease enter Enrollment Status(Main/Resit/Repeat/Elective): ");
        String RemoveEnrollmentStatus = scanner.nextLine();
        return RemoveEnrollmentStatus;
    }

    //Displays a menu for selecting filtering criteria (Age, Gender, Programme Code), 
    //prompts the user to choose one, and returns the selected criteria as an integer.
    public int inputCriteriaType() {
        System.out.println("\nCriteria Menu");
        System.out.println("1. Age");
        System.out.println("2. Gender");
        System.out.println("3. ProgrammeCode");
        System.out.println("0. Back");
        System.out.print("Please select the criteria by which you want to filter students(1-3): ");
        int criteriaType = scanner.nextInt();
        scanner.nextLine();
        return criteriaType;
    }

    //prompts the user to input the maximum age for filtering students and 
    //returns the entered value as an integer.
    public int inputMaxAge() {
        int maxAge = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print("Please enter the maximum age: ");
                maxAge = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                validInput = true;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume invalid input
                MessageUI.displayInputErrorMessage();
            }
        } while (!validInput);
        return maxAge;
    }

    //prompts the user to input the minimum age for filtering students and 
    //returns the entered value as an integer.
    public int inputMinAge() {
        int minAge = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print("Please enter the minimum age: ");
                minAge = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                validInput = true;
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Consume invalid input
                MessageUI.displayInputErrorMessage();
            }
        } while (!validInput);
        return minAge;
    }

    //Prompt the user to enter a gender and returns the input as a String.
    public String inputGender() {
        System.out.print("Please enter the gender: ");
        String studentID = scanner.nextLine();
        return studentID;
    }
    //Prompt the user to enter a programme code and returns the input as a String.
    public String inputProgrammeCode() {
        System.out.print("Please enter the programme code: ");
        String programmeCode = scanner.nextLine();
        return programmeCode;
    }

    //displays a menu for selecting sorting criteria (Student ID, Name, Age, Gender, Phone Number, Email, Programme), 
    //prompts the user to choose one, and returns the selected criteria as an integer.
    public int getSortDetailsChoice() {
        System.out.println("\nDetails Menu");
        System.out.println("1. Student ID");
        System.out.println("2. Name");
        System.out.println("3. Age");
        System.out.println("4. Gender");
        System.out.println("5. Phone Number");
        System.out.println("6. Email");
        System.out.println("7. Programme");
        System.out.println("0. Back");
        System.out.print("Please choose the criteria by which you'd like to sort the details in the student list(1-7):");
        int sortDetails = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return sortDetails;
    }

    //Prompts the user to choose the sorting order (Ascending or Descending) 
    //and returns the selected order type as an integer.
    public int inputSortType() {
        System.out.print("\n1. Ascending Order \n2. Desencing Order");
        System.out.print("\nPlease enter your choice of sort order(1-2): ");
        int orderType = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return orderType;
    }

    //Display Student Bill
    public void StudentBill() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("        TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("                             STUDENT BILL");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("%-47s %-14s %-8s", "Courses", "Status", "Amount(RM)");
        System.out.println("\n---------------------------------------------------------------------------");
    }

    //prompts the user to decide whether they want to calculate course fees for another student 
    //and returns their choice as a string (either "Yes" or "No").
    public String inputCalculateCourseFeeAgain() {
        System.out.print("Do you want calculate course fee for another student?(Yes/No): ");
        String newCalculateAgain = scanner.nextLine();
        System.out.println();
        return newCalculateAgain;
    }
    
    //displays a menu for selecting the type of report (Student Summary Report, Course Summary Report), 
    //prompts the user to choose one, and returns the selected report type as an integer.
    public int inputReportType() {
        System.out.print("\n1. Student Summary Report \n2. Course Enrollment Summary Report \n0. Back");
        System.out.print("\nPlease enter your choice of report(1-2): ");
        int reportType = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.println();
        return reportType;
    }
    
    //Display Student Report
    public void StudentReport(){
        System.out.println("=".repeat(150));
        System.out.printf("%49s", "");
        System.out.println("TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.printf("%62s", "");
        System.out.println("STUDENT MANAGEMENT SUBSYSTEM");
        System.out.printf("\n%65s", "");
        System.out.println("STUDENT SUMMARY REPORT");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-3s %-20s %-78s %-20s %-20s\n",
                " ", "Stdeunt ID", "Name", "Courses Taken", "Credit Hours Obtained");
        System.out.println("-".repeat(150));
    }

    //Display Course Report
    public void CourseReport(){
        System.out.println("=".repeat(150));
        System.out.printf("%49s", "");
        System.out.println("TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.printf("%62s", "");
        System.out.println("STUDENT MANAGEMENT SUBSYSTEM");
        System.out.printf("\n%60s", "");
        System.out.println("COURSE ENROLLMENT SUMMARY REPORT");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-3s %-15s %-49s %-17s %-31s %-23s\n",
                " ", "Course Code", "Course Name", "Credit Hours", "Total Students", "Enrollent Status");
        System.out.println("-".repeat(150));
    }
}
