package control;

import java.util.InputMismatchException;
import java.util.Scanner;
import utility.MessageUI;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();
        CourseManagement courseManagement = new CourseManagement();
        TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();
        TeachingAssignment teachingAssignment = new TeachingAssignment();
        AssignmentTeamManagement assignmentTeamManagement = new AssignmentTeamManagement();

        int choice = 0;
        do {
            boolean validInput = false;
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("        TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
            System.out.println("                           MANAGEMENT SYSTEM");
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("1. Student Registration Management Subsystem");
            System.out.println("2. Course Management Subsystem");
            System.out.println("3. Tutorial Group Management Subsystem");
            System.out.println("4. Teaching Assignment Subsystem");
            System.out.println("5. Assignment Team Management Subsystem");
            System.out.println("0. Quit");
            do {
                try {
                    System.out.print("Please indicate your selection to proceed(1-5): ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    validInput = true;
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // Consume invalid input
                    MessageUI.displayInputErrorMessage();
                }
            } while (!validInput);
            System.out.println();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    studentManagement.runStudentManagement();
                    break;
                case 2:
                    courseManagement.runCourseManagement();
                    break;
                case 3:
                    tutorialGroupManagement.runTutorialGroupManagement();
                    break;
                case 4:
                    teachingAssignment.runTeachingAssignment();
                    break;
                case 5:
                    assignmentTeamManagement.runAssignmentTeamManagement();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);

    }
}
