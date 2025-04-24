package boundary;

import java.util.Scanner;
import entity.AssignmentTeam;
import entity.Student;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Lim Hoi Yau
 */
public class AssignmentTeamManagementUI {

    Scanner scanner = new Scanner(System.in);
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mma");
    String formattedDateTime = currentDateTime.format(formatter);

    public int getMenuChoice() {
        System.out.println("\n==================================================");
        System.out.println("  Assignment Team Management");
        System.out.println("==================================================");
        System.out.println("1. Create Assignment Team for Tutorial Group");
        System.out.println("2. Remove Assignment Team from Tutorial Group");
        System.out.println("3. Ammend Assignment Team Details");
        System.out.println("4. Add Students to Assignment Team");
        System.out.println("5. Remove Students from Assignment Team");
        System.out.println("6. Merge Assignment Team based on criteria");
        System.out.println("7. List Assignment Teams for a Tutorial Group");
        System.out.println("8. List Students Under an Assignment Team");
        System.out.println("9. Summary Report");
        System.out.println("0. Quit");
        System.out.print("Please indicate your selection to proceed(1-9): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void CreateAssignmentTeam() {
        System.out.print("Assignment Team has created successfully!\n");

    }

    public AssignmentTeam addNewStudentToAssignmentTeam(String assignmentTeamID, Student studentId) {
        System.out.print("New student had been added successfully!\n");
        System.out.print("\nPress enter to continue adding the new student to the Assignment Team...");
        scanner.nextLine();
        System.out.println("");
        return new AssignmentTeam();
    }

    public void listAssignmentTeam(String outputStr) {
        if (outputStr.isBlank()) {
            System.out.println("Assignment Team haven't created");
        } else {
            System.out.println("\nAssignment Teams Details:");
            System.out.printf("\n%-100s\n%-30s %-30s %-15s %-15s %-20s %-10s\n%-100s\n",
                    "-".repeat(160), "Assignment Team ID", "Assignment Team Name", "Team Size", "Team Leader", "Number of Member", "Tutorial Group", "-".repeat(160));
            System.out.println(outputStr);
        }
    }

    public void listAssignmentTeamStudent(String outputStr) {
        if (outputStr.isBlank()) {
            System.out.println("The assignment team is empty\n");
        } else {
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-12s %-25s %-5s %-8s %-35s %-15s %-6s", "Student ID", "Name", "Age", "Gender", "E-mail", "Phone Number", "Programme");
            System.out.println("\n----------------------------------------------------------------------------------------------------------------------");
            System.out.println(outputStr);
        }
    }

    public int getTypeEditChoice() {
        System.out.println("\nEdit Assignment Team Details Menu");
        System.out.println("1. Team Name");
        System.out.println("2. Team Size");
        System.out.println("3. Team Leader");
        System.out.println("0. Back");
        System.out.print("Please select the details you would like to modify(1-4): ");
        int editChoice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return editChoice;
    }

    public int reportChoice() {
        System.out.println("\nSummary Report Menu");
        System.out.println("1. Programme Summary Report");
        System.out.println("2. Tutorial Group Summary Report");
        System.out.println("0. Back");
        System.out.print("Please select the report you would like to display(1-2): ");
        int Choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return Choice;
    }

    public void asgTeamOfProgrammeReport(String outputStr) {
        System.out.println("=".repeat(150));
        System.out.printf("\n%65s", "");
        System.out.println("PROGRAMME (Assignment Team) SUMMARY REPORT");
        System.out.printf("%65s\n", "");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-3s %-20s %-82s %-20s %-20s\n",
                " ", "Programme Code", "Programme Name", "Tutorial Group", "Assignment Team");
        System.out.println(outputStr);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void asgTeamOfTutorialGroupReport(String outputStr) {
        System.out.println("=".repeat(150));
        System.out.printf("\n%65s", "");
        System.out.println("Tutorial Group( (Assignment Team) SUMMARY REPORT");
        System.out.printf("%65s\n", "");
        System.out.println("=".repeat(150));
        System.out.println("\nReport generated at: " + formattedDateTime + "\n");
        System.out.println("-".repeat(150));
        System.out.printf("%-3s %-38s %-50s %-20s %-20s\n",
                " ", "Assignment Team ID", "Assignment Team Name", "Current Member", "Space Left");
        System.out.println(outputStr);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public String inputAssignmentTeamID() {
        System.out.print("Enter Assignment Team ID (Enter -1 to exit): ");
        String assignmentTeamID = scanner.nextLine();
        return assignmentTeamID;
    }

    public String inputAssignmentTeamName() {
        System.out.print("Enter Assignment Team Name (Enter -1 to exit): ");
        String assignmentTeamName = scanner.nextLine();
        return assignmentTeamName;
    }

    public int inputAssignmentTeamSize() {
        int assignmentTeamSize;
        do {
            System.out.print("Enter Assignment Team Size (Enter -1 to exit): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number.");
                System.out.print("Enter Assignment Team Size (Enter -1 to exit): ");
                scanner.next();
            }
            assignmentTeamSize = scanner.nextInt();
            if (assignmentTeamSize == 0) {
                System.out.println("Assignment Team Size cannot be 0. Please enter a valid size.");
            }
        } while (assignmentTeamSize == 0);
        return assignmentTeamSize;
    }

    public int inputCreateTeamNumber() {
        System.out.print("Enter Number of Assignment Team to Create (Enter -1 to exit): ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid number.");
            System.out.print("Enter Number of Assignment Team to Create (Enter -1 to exit): ");
            scanner.next();
        }
        int assignmentTeamNumber = scanner.nextInt();
        return assignmentTeamNumber;
    }

    public String inputStudentID() {
        System.out.print("Enter Student ID (Enter -1 to exit): ");
        String assignmentTask = scanner.nextLine();
        return assignmentTask;

    }
}
