package control;

import adt.*;
import boundary.*;
import dao.*;
import entity.AssignmentTeam;
import entity.Student;
import entity.TutorialGroup;
import entity.Programme;
import entity.courseProgramme;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Lim Hoi Yau
 */
public class AssignmentTeamManagement {

    Scanner scanner = new Scanner(System.in);

    private ListInterface<AssignmentTeam> AssignmentTeamList = new ArrayList<>();
    private AssignmentTeamDAO AssignmentTeamDAO = new AssignmentTeamDAO();
    private AssignmentTeamInitializer AssignmentTeamInitializer = new AssignmentTeamInitializer();
    private AssignmentTeamManagementUI AssignmentTeamManagementUI = new AssignmentTeamManagementUI();

    private StudentDAO StudentDAO = new StudentDAO();
    private StudentInitializer StudentInitializer = new StudentInitializer();
    private ListInterface<Student> studentList = new ArrayList<>();

    private ListInterface<TutorialGroup> TutorialGroupList = new ArrayList<>();
    private TutorialGroupDAO TutorialGroupDAO = new TutorialGroupDAO();
    private TutorialGroupInitializer TutorialGroupInitializer = new TutorialGroupInitializer();
    private TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();
    private TutorialGroupManagementUI tutorialGroupManagementUI = new TutorialGroupManagementUI();

    private CourseManagement courseManagement = new CourseManagement();
    private CourseManagementUI courseManagementUI = new CourseManagementUI();

    private ProgrammeDAO ProgrammeDAO = new ProgrammeDAO();

    ListInterface<Programme> ProgrammeList = new ArrayList<>();
    private ListInterface<courseProgramme> CourseProgrammeList = new ArrayList<>();
    ListInterface<Programme> programmeSortedList = new ArrayList<>();

    public AssignmentTeamManagement() {
        studentList = StudentDAO.retrieveFromFile();
        TutorialGroupList = TutorialGroupDAO.retrieveFromFile();
        AssignmentTeamList = AssignmentTeamDAO.retrieveFromFile();
        ProgrammeList = ProgrammeDAO.retrieveFromFile();
    }

    public static void main(String[] args) {
        AssignmentTeamManagement assignmentTeamManagement = new AssignmentTeamManagement();
        assignmentTeamManagement.runAssignmentTeamManagement();
    }

    public void runAssignmentTeamManagement() {
        studentList = StudentDAO.retrieveFromFile();
        TutorialGroupList = TutorialGroupDAO.retrieveFromFile();
        AssignmentTeamList = AssignmentTeamDAO.retrieveFromFile();
        ProgrammeList = ProgrammeDAO.retrieveFromFile();
        int choice = 0;
        do {
            choice = AssignmentTeamManagementUI.getMenuChoice();
            switch (choice) {
                case 0:

                    break;
                case 1:
                    createAssignmentTeam(null, null, 'N');
                    break;
                case 2:
                    removeAssignmentTeam();
                    break;
                case 3:
                    editAssignmentTeam();
                    break;
                case 4:
                    addStudentToAssignmentTeam();
                    break;
                case 5:
                    removeStudentFromAssignmentTeam();
                    break;
                case 6:
                    mergeAsgTeams();
                    break;
                case 7:
                    listAssignmentTeamOfGroup();
                    break;
                case 8:
                    listStudentOfAsgTeam();
                    break;
                case 9:
                    Report();
                    break;
            }
        } while (choice != 0);
    }

    /*Create Assignment Team*/
    public void createAssignmentTeam(String programmeCode, String tutorialGroupID, char check) {
        courseManagement.displayAllProgramme(null);

        if (programmeCode == null || !courseManagement.findProgramme(programmeCode)) {
            do {
                programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
                if (!courseManagement.findProgramme(programmeCode)) {
                    System.out.println("Invalid programme!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }

        System.out.println();

        tutorialGroupManagement.displayProgrammeGroup(programmeCode);
        check = 'N';

        if (tutorialGroupID == null || !courseManagement.findProgramme(programmeCode)) {
            do {
                tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
                if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                    System.out.println("Invalid Tutorial Group ID!");

                } else {
                    check = 'Y';

                }
                System.out.println();
            } while (check != 'Y');
        }

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);
        displayAssignmentTeam(group);
        int createTeamNumber = AssignmentTeamManagementUI.inputCreateTeamNumber();
        int asgTeamSize = 0;
        if (createTeamNumber != -1) {
            asgTeamSize = AssignmentTeamManagementUI.inputAssignmentTeamSize();
        }
        int currentAsgTeamIndex = getTutorialGroupAsgTeamNum(group);
        for (int i = 1; i <= AssignmentTeamList.getNumberOfEntries(); ++i) {
            if ((tutorialGroupID + "T" + (currentAsgTeamIndex + 1)).equals(AssignmentTeamList.getEntry(i).getAssignmentTeamID())) {
                currentAsgTeamIndex++;
            }
        }
        if (createTeamNumber != -1 && asgTeamSize != -1) {
            for (int i = 1; i <= createTeamNumber && createTeamNumber != -1; ++i) {
                AssignmentTeam newAssignmentTeam = new AssignmentTeam();
                newAssignmentTeam.setAssignmentTeamID(tutorialGroupID + "T" + (i + currentAsgTeamIndex));
                newAssignmentTeam.setAssignmentTeamName(tutorialGroupID + " Assignment Team " + (i + currentAsgTeamIndex));
                newAssignmentTeam.setTutorialGroup(group);
                newAssignmentTeam.setAssignmentTeamSize(asgTeamSize);
                AssignmentTeamList.add(newAssignmentTeam);
            }
            AssignmentTeamDAO.saveToFile(AssignmentTeamList);
            displayAssignmentTeam(group);
            AssignmentTeamManagementUI.CreateAssignmentTeam();

            System.out.println("\nPress enter to continue...");
            scanner.nextLine();
        }
    }

    /*Remove Assignment Team*/
    public void removeAssignmentTeam() {
        char check = 'N';
        String programmeCode;
        String assignmentTeamID = "";
        String tutorialGroupID;

        do {
            courseManagement.displayAllProgramme(null);
            programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
            if (!courseManagement.findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        do {
            tutorialGroupManagement.displayProgrammeGroup(programmeCode);
            /*System.out.println("Please enter tutorial group ID that you want to remove assignment team");*/
            tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
            if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                System.out.println("Invalid Tutorial Group ID!");

            } else {
                check = 'Y';

            }
            System.out.println();
        } while (check == 'N');

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);

        displayAssignmentTeam(group);

        check = 'N';

        while (check == 'N') {
            assignmentTeamID = AssignmentTeamManagementUI.inputAssignmentTeamID().toUpperCase();
            if (checkAsgTeam(group, assignmentTeamID) || assignmentTeamID.equals("-1")) {
                check = 'Y';
            } else {
                System.out.println("Invalid assignment team ID!");

            }
            System.out.println();
        }

        if (!assignmentTeamID.equals("-1")) {
            int indexToRemove = -1;
            for (int i = 0; i < AssignmentTeamList.getNumberOfEntries() + 1; i++) {
                if (AssignmentTeamList.getEntry(i) != null) {
                    if (AssignmentTeamList.getEntry(i).getTutorialGroup().equals(group)
                            && AssignmentTeamList.getEntry(i).getAssignmentTeamID().equals(assignmentTeamID)) {
                        indexToRemove = i;
                        break;
                    }
                }
            }

            do {
                System.out.print("Continue removing assignment team from tutorial group (Y/N): ");
                check = Character.toUpperCase(scanner.next().charAt(0));
                if (check != 'Y' && check != 'N') {
                    System.out.println("Invalid Input, Please Try Again");
                }
                scanner.nextLine();
            } while (check != 'Y' && check != 'N');

            if (check == 'Y') {
                if (indexToRemove != -1) {
                    AssignmentTeamList.remove(indexToRemove);
                    AssignmentTeamDAO.saveToFile(AssignmentTeamList);
                    
                    displayAssignmentTeam(group);
                    System.out.println(assignmentTeamID + " has been removed from tutorial group: " + tutorialGroupID + " successfully!");
                    
                } else {
                    System.out.println("Failed to remove " + assignmentTeamID + " from " + tutorialGroupID + " !");
                }
            } else {
                System.out.println("Assignment Team remove canceled!");
            }

            System.out.println("\nPress enter to continue...");
            scanner.nextLine();

        }
    }

    /* Amend Assignment Team Details */
    public void editAssignmentTeam() {
        char check = 'N';
        String assignmentTeam = "";
        String programmeCode;
        String tutorialGroupID;

        do {
            courseManagement.displayAllProgramme(null);
            programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
            if (!courseManagement.findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        do {
            tutorialGroupManagement.displayProgrammeGroup(programmeCode);
            tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
            if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                System.out.println("Invalid Tutorial Group ID!");

            } else {
                check = 'Y';

            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);

        displayAssignmentTeam(group);

        while (check == 'N') {
            assignmentTeam = AssignmentTeamManagementUI.inputAssignmentTeamID().toUpperCase();
            if (checkAsgTeam(group, assignmentTeam) || assignmentTeam.equals("-1")) {
                check = 'Y';
            } else {
                System.out.println("Invalid assignment team ID!");

            }
            System.out.println();
        }

        if (!assignmentTeam.equals("-1")) {
            int indexToEdit = -1;
            for (int i = 1; i < AssignmentTeamList.getNumberOfEntries() + 1; i++) {
                if (AssignmentTeamList.getEntry(i).getAssignmentTeamID().equals(assignmentTeam)) {
                    indexToEdit = i;
                    break;
                }
            }
            if (indexToEdit != -1) {
                int typeEditChoice = 0;
                AssignmentTeam editAsgTeam = AssignmentTeamList.getEntry(indexToEdit);
                do {
                    typeEditChoice = AssignmentTeamManagementUI.getTypeEditChoice();
                    switch (typeEditChoice) {
                        case 0:
                            MessageUI.displayExitMessage();
                            break;
                        case 1:
                            String oldTeamName = editAsgTeam.getAssignmentTeamName();
                            System.out.println("Old Team Name for " + assignmentTeam + ": " + oldTeamName);
                            String newTeamName = AssignmentTeamManagementUI.inputAssignmentTeamName();
                            if (newTeamName.equals("-1")) {
                                break;
                            }
                            if (newTeamName.equals(oldTeamName)) {
                                System.out.println("Invalid input, you have enter an old team name!");
                                System.out.println("\nPress enter to continue...");
                                scanner.nextLine();
                                break;
                            }
                            editAsgTeam.setAssignmentTeamName(newTeamName);
                            System.out.println("Team name had been changed to " + editAsgTeam.getAssignmentTeamName());
                            break;

                        case 2:
                            int oldTeamSize = editAsgTeam.getAssignmentTeamSize();
                            System.out.println("Old Team Size for " + assignmentTeam + ": " + oldTeamSize);
                            int newTeamSize = AssignmentTeamManagementUI.inputAssignmentTeamSize();
                            if (newTeamSize == -1) {
                                break;
                            }
                            if (newTeamSize == oldTeamSize) {
                                System.out.println("Invalid input, you have enter an old team size!");
                                System.out.println("\nPress enter to continue...");
                                scanner.nextLine();
                                break;
                            }
                            if (newTeamSize < editAsgTeam.getTeamMember().getNumberOfEntries()) {
                                System.out.println("Current Member is Exceeding New Team Size, Please Remove Member First");
                                System.out.println("\nPress enter to continue...");
                                scanner.nextLine();
                                break;
                            }
                            ListInterface<Student> newTeamMember = new ArrayList<>(newTeamSize);
                            ListInterface<Student> teamMemberCopy = editAsgTeam.getTeamMember();
                            for (int i = 1; i <= teamMemberCopy.getNumberOfEntries(); i++) {
                                newTeamMember.add(teamMemberCopy.getEntry(i));
                            }
                            editAsgTeam.setAssignmentTeamSize(newTeamSize);
                            editAsgTeam.setTeamMember(newTeamMember);
                            System.out.println("Team size had been changed to " + editAsgTeam.getAssignmentTeamSize());
                            break;

                        case 3:
                            String oldTeamLeader;
                            if (editAsgTeam.getTeamLeader() != null) {
                                oldTeamLeader = editAsgTeam.getTeamLeader().getName();
                                System.out.println("Old Team Leader for " + assignmentTeam + ": " + oldTeamLeader);
                            }
                            displayStudentForAsgTeam(editAsgTeam);
                            if (editAsgTeam.getTeamMember().isEmpty()) {
                                System.out.println("No Student Can Choose to be the Team Leader");
                                System.out.println("\nPress enter to continue...");
                                scanner.nextLine();
                                break;
                            }
                            String newTeamLeaderID = AssignmentTeamManagementUI.inputStudentID().toUpperCase();
                            if (newTeamLeaderID.equals("-1")) {
                                break;
                            }
                            Student newTeamLeader = retrieveStudent(newTeamLeaderID);
                            if (checkStudentExistsInTeam(editAsgTeam, newTeamLeader)) {
                                editAsgTeam.setTeamLeader(newTeamLeader);
                                if (editAsgTeam.getTeamLeader() != null) {
                                    System.out.println("Team Leader had been assigned to " + editAsgTeam.getTeamLeader().getName());
                                }
                                break;
                            } else {
                                System.out.println("Target Student doesn't exists in this group");
                                break;
                            }

                    }
                } while (typeEditChoice != 0);
                AssignmentTeamList.replace(indexToEdit, editAsgTeam);
                AssignmentTeamDAO.saveToFile(AssignmentTeamList);
            } else {
                System.out.println("Assignment Team with ID " + assignmentTeam + " is not found in the list!\n");
            }
            System.out.print("\nPress enter to continue...");
            scanner.nextLine();
        }
    }

    /* Add Student To Assignment Team*/
    public void addStudentToAssignmentTeam() {
        char check = 'N';
        String assignmentTeam = "";
        String programmeCode;
        String tutorialGroupID;
        String studentID;

        do {
            courseManagement.displayAllProgramme(null);
            programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
            if (!courseManagement.findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        do {
            tutorialGroupManagement.displayProgrammeGroup(programmeCode);
            tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
            if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                System.out.println("Invalid Tutorial Group ID!");

            } else {
                check = 'Y';

            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);

        displayAssignmentTeam(group);

        while (check == 'N') {
            assignmentTeam = AssignmentTeamManagementUI.inputAssignmentTeamID().toUpperCase();
            if (checkAsgTeam(group, assignmentTeam) || assignmentTeam.equals("-1")) {
                check = 'Y';
            } else {
                System.out.println("Invalid assignment team ID!");

            }
            System.out.println();
        }

        check = 'N';

        if (!assignmentTeam.equals("-1")) {
            int indexToAddStudent = -1;
            for (int i = 1; i < AssignmentTeamList.getNumberOfEntries() + 1; i++) {
                if (AssignmentTeamList.getEntry(i).getAssignmentTeamID().equals(assignmentTeam)) {
                    indexToAddStudent = i;
                    break;
                }
            }

            AssignmentTeam addStudentAsgTeam = AssignmentTeamList.getEntry(indexToAddStudent);
            ListInterface<Student> asgTeamStudentsList = addStudentAsgTeam.getTeamMember();
            Programme programme = new Programme();
            programme.setProgrammeCode(programmeCode);
            displayStudentForAsgTeam(addStudentAsgTeam);
            while (check == 'N') {

                if (asgTeamStudentsList.isFull()) {

                    System.out.println("Cannot add more student to the group because the group is full");
                    check = 'Y';
                    System.out.print("\nPress enter to continue...");
                    scanner.nextLine();
                } else {

                    System.out.println("Enter 0 to view student in the tutorial group\n");
                    studentID = AssignmentTeamManagementUI.inputStudentID().toUpperCase();
                    if (studentID.equals("0")) {
                        displayStudentForTutorialGroup(group);
                    } else if (studentID.equals("-1")) {
                        check = 'Y';
                    } else {
                        if (!checkStudentExists(group, studentID)) {
                            System.out.println("Student Does Not Exists, Please Try Again.");

                        } else {
                            Student student = retrieveStudent(studentID);
                            if (student != null && student.getProgrammeCode().equals(programmeCode)) {
                                if (!checkStudentAsgTeamStatus(addStudentAsgTeam, student)) {

                                    asgTeamStudentsList.add(student);
                                    addStudentAsgTeam.setNumOfMember(asgTeamStudentsList.getNumberOfEntries());
                                    AssignmentTeamList.replace(indexToAddStudent, addStudentAsgTeam);
                                    AssignmentTeamDAO.saveToFile(AssignmentTeamList);
                                    System.out.println("Students added to assignment team successfully!");
                                    displayStudentForAsgTeam(addStudentAsgTeam);
                                } else {
                                    System.out.print("Cannot assign student to this group since student already assign in ");
                                    System.out.println(findStudentCurrentTeam(addStudentAsgTeam, student));
                                    displayStudentForAsgTeam(addStudentAsgTeam);
                                }
                            } else {
                                System.out.println("Student " + studentID + " not found in this group!");
                                displayStudentForAsgTeam(addStudentAsgTeam);
                            }

                        }
                        System.out.println();
                    }
                }
            }
        }
    }

    /* Remove Student From Assignment Team */
    public void removeStudentFromAssignmentTeam() {
        char check = 'N';
        String assignmentTeam = "";
        String programmeCode;
        String tutorialGroupID;
        String studentID;

        do {
            courseManagement.displayAllProgramme(null);
            programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
            if (!courseManagement.findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        do {
            tutorialGroupManagement.displayProgrammeGroup(programmeCode);
            tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
            if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                System.out.println("Invalid Tutorial Group ID!");

            } else {
                check = 'Y';

            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);

        displayAssignmentTeam(group);

        while (check == 'N') {
            assignmentTeam = AssignmentTeamManagementUI.inputAssignmentTeamID().toUpperCase();
            if (checkAsgTeam(group, assignmentTeam) || assignmentTeam.equals("-1")) {
                check = 'Y';
            } else {
                System.out.println("Invalid assignment team ID!");

            }
            System.out.println();
        }

        check = 'N';

        if (!assignmentTeam.equals("-1")) {
            int indexOfAsgTeam = -1;
            for (int i = 1; i < AssignmentTeamList.getNumberOfEntries() + 1; i++) {
                if (AssignmentTeamList.getEntry(i).getAssignmentTeamID().equals(assignmentTeam)) {
                    indexOfAsgTeam = i;
                    break;
                }
            }

            AssignmentTeam removeStudentAsgTeam = AssignmentTeamList.getEntry(indexOfAsgTeam);
            ListInterface<Student> asgTeamStudentsList = removeStudentAsgTeam.getTeamMember();
            Programme programme = new Programme();
            programme.setProgrammeCode(programmeCode);
            displayStudentForAsgTeam(removeStudentAsgTeam);
            while (check == 'N') {

                if (asgTeamStudentsList.isEmpty()) {

                    System.out.println("Cannot remove student from the group because the group is Empty");
                    check = 'Y';
                    System.out.print("\nPress enter to continue...");
                    scanner.nextLine();
                } else {

                    studentID = AssignmentTeamManagementUI.inputStudentID().toUpperCase();

                    if (studentID.equals("-1")) {
                        check = 'Y';
                    } else {
                        Student removeStudent = retrieveStudent(studentID);
                        if (!checkStudentExistsInTeam(removeStudentAsgTeam, removeStudent)) {
                            System.out.println("Member Does Not Exists, Please Try Again.");

                        } else {
                            int indexToRemove = -1;
                            for (int i = 0; i < asgTeamStudentsList.getNumberOfEntries() + 1; i++) {
                                if (asgTeamStudentsList.getEntry(i) != null) {
                                    if (asgTeamStudentsList.getEntry(i).getStudentID().equals(removeStudent.getStudentID())) {
                                        indexToRemove = i;
                                        break;
                                    }
                                }
                            }
                            if (removeStudentAsgTeam.getTeamLeader() != null && asgTeamStudentsList.getEntry(indexToRemove).getStudentID().equals(removeStudentAsgTeam.getTeamLeader().getStudentID())) {
                                removeStudentAsgTeam.setTeamLeader(null);
                            }
                            removeStudentAsgTeam.setNumOfMember(asgTeamStudentsList.getNumberOfEntries() - 1);
                            AssignmentTeamList.replace(indexOfAsgTeam, removeStudentAsgTeam);
                            asgTeamStudentsList.remove(indexToRemove);

                            AssignmentTeamDAO.saveToFile(AssignmentTeamList);
                            System.out.println("Students remove from assignment team successfully!");
                            displayStudentForAsgTeam(removeStudentAsgTeam);

                        }
                        System.out.println();
                    }
                }
            }
        }
    }

    /* Merge Assignment Team */
    public void mergeAsgTeams() {
        char check = 'N';
        String firstAssignmentTeam = "";
        String secondAssignmentTeam = "";
        String programmeCode;
        String tutorialGroupID;

        do {
            courseManagement.displayAllProgramme(null);
            programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
            if (!courseManagement.findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        do {
            tutorialGroupManagement.displayProgrammeGroup(programmeCode);
            tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
            if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                System.out.println("Invalid Tutorial Group ID!");

            } else {
                check = 'Y';

            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);

        displayAssignmentTeam(group);
        System.out.println("Enter assignment team ID you want to merge.\n");

        while (check == 'N') {
            firstAssignmentTeam = AssignmentTeamManagementUI.inputAssignmentTeamID().toUpperCase();
            if (checkAsgTeam(group, firstAssignmentTeam) || firstAssignmentTeam.equals("-1")) {
                check = 'Y';
            } else {
                System.out.println("Invalid assignment team ID!");

            }
            System.out.println();
        }

        check = 'N';

        if (!firstAssignmentTeam.equals("-1")) {
            int indexOfFirstAsgTeam = -1;
            for (int i = 1; i < AssignmentTeamList.getNumberOfEntries() + 1; i++) {
                if (AssignmentTeamList.getEntry(i).getAssignmentTeamID().equals(firstAssignmentTeam)) {
                    indexOfFirstAsgTeam = i;
                    break;
                }
            }

            AssignmentTeam firstAsgTeam = AssignmentTeamList.getEntry(indexOfFirstAsgTeam);
            ListInterface<Student> firstAsgTeamStudentsList = firstAsgTeam.getTeamMember();
            ListInterface<AssignmentTeam> suitableTeam = retrieveSuitableMergeTeam(firstAsgTeam);

            System.out.println("Suitable Team For Merging:\n");
            if (!suitableTeam.isEmpty()) {
                System.out.println(suitableTeam);
                System.out.println("Enter assignment team ID you want to merge with first team selected");
            } else {
                System.out.println("No suitable team to merge.");
                System.out.print("\nPress enter to continue...");
                scanner.nextLine();
                check = 'Y';
                secondAssignmentTeam = "-1";
            }

            while (check == 'N') {
                secondAssignmentTeam = AssignmentTeamManagementUI.inputAssignmentTeamID().toUpperCase();
                if (checkAsgTeam(suitableTeam, secondAssignmentTeam) || secondAssignmentTeam.equals("-1")) {
                    check = 'Y';
                } else {
                    System.out.println("Invalid assignment team ID!");

                }
                System.out.println();
            }

            check = 'N';
            if (!secondAssignmentTeam.equals("-1")) {
                int indexOfSecondAsgTeam = -1;
                for (int i = 1; i < AssignmentTeamList.getNumberOfEntries(); i++) {
                    if (AssignmentTeamList.getEntry(i).getAssignmentTeamID().equals(secondAssignmentTeam)) {
                        indexOfSecondAsgTeam = i;
                        break;
                    }
                }
                AssignmentTeam secondAsgTeam = AssignmentTeamList.getEntry(indexOfSecondAsgTeam);
                ListInterface<Student> secondAsgTeamStudentsList = secondAsgTeam.getTeamMember();
                firstAsgTeamStudentsList = mergeAssignmentTeam(firstAsgTeamStudentsList, secondAsgTeamStudentsList);
                firstAsgTeam.setNumOfMember(firstAsgTeam.getNumOfMember() + secondAsgTeam.getNumOfMember());
                AssignmentTeamList.replace(indexOfFirstAsgTeam, firstAsgTeam);
                AssignmentTeamList.remove(indexOfSecondAsgTeam);

                AssignmentTeamDAO.saveToFile(AssignmentTeamList);
                System.out.println("Assignment Group Merge Successfully!");
                displayAssignmentTeam(group);
                System.out.print("\nPress enter to continue...");
                scanner.nextLine();
            }

        }
    }

    /* List Assignment Team For A TutorialGroup */
    public void listAssignmentTeamOfGroup() {
        char check = 'N';
        String programmeCode;
        String tutorialGroupID;

        do {
            courseManagement.displayAllProgramme(null);
            programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
            if (!courseManagement.findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        do {
            tutorialGroupManagement.displayProgrammeGroup(programmeCode);
            tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
            if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                System.out.println("Invalid Tutorial Group ID!");

            } else {
                check = 'Y';

            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);

        displayAssignmentTeam(group);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();

    }

    /* List Students Under An Assignment Team */
    public void listStudentOfAsgTeam() {
        char check = 'N';
        String assignmentTeam = "";
        String programmeCode;
        String tutorialGroupID;

        do {
            courseManagement.displayAllProgramme(null);
            programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
            if (!courseManagement.findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        do {
            tutorialGroupManagement.displayProgrammeGroup(programmeCode);
            tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
            if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                System.out.println("Invalid Tutorial Group ID!");

            } else {
                check = 'Y';

            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);

        displayAssignmentTeam(group);

        while (check == 'N') {
            assignmentTeam = AssignmentTeamManagementUI.inputAssignmentTeamID().toUpperCase();
            if (checkAsgTeam(group, assignmentTeam) || assignmentTeam.equals("-1")) {
                check = 'Y';
            } else {
                System.out.println("Invalid assignment team ID!");

            }
            System.out.println();
        }

        if (!assignmentTeam.equals("-1")) {
            int indexToViewStudent = -1;
            for (int i = 1; i < AssignmentTeamList.getNumberOfEntries(); i++) {
                if (AssignmentTeamList.getEntry(i).getAssignmentTeamID().equals(assignmentTeam)) {
                    indexToViewStudent = i;
                    break;
                }
            }

            AssignmentTeam viewStudentAsgTeam = AssignmentTeamList.getEntry(indexToViewStudent);
            displayStudentForAsgTeam(viewStudentAsgTeam);
            System.out.print("\nPress enter to continue...");
            scanner.nextLine();

        }

    }

    /* Summary Report */
    public void Report() {
        int choice = -1;
        do {
            choice = AssignmentTeamManagementUI.reportChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    displayProgrammeAsgTeamReport();
                    break;
                case 2:
                    displayTutorialGroupAsgTeamReport();
                    break;
            }
        } while (choice != 0);
    }

    /* Report Of Assignment Team For Each Programme */
    public String ProgrammeReport() {
        sortProgrammeForAsgTeam();
        ListInterface<String> outputStr = new ArrayList<>();
        ListInterface<AssignmentTeam> TEMPAssignmentTeam = new ArrayList<>();
        ListInterface<String> TEMPProgramme = new ArrayList<>();
        ListInterface<TutorialGroup> TEMPTutorialGroup = new ArrayList<>();
        ListInterface<Integer> programmeAssignmentTeamCount = new ArrayList<>();
        ListInterface<Integer> programmeTutorialGroupCount = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();
        int max = 0;
        int min = 999;

        for (int i = 1; i <= programmeSortedList.getNumberOfEntries(); i++) {
            TEMPProgramme.add(programmeSortedList.getEntry(i).getProgrammeCode());
        }

        for (int i = 1; i <= TEMPProgramme.getNumberOfEntries(); i++) {
            int count = 0;
            for (int j = 1; j <= TutorialGroupList.getNumberOfEntries(); j++) {
                if (TEMPProgramme.getEntry(i).equals(TutorialGroupList.getEntry(j).getProgrammeCode())) {
                    TEMPTutorialGroup.add(TutorialGroupList.getEntry(j));
                    count++;
                }
            }
            programmeTutorialGroupCount.add(count);
        }

        for (int i = 1; i <= TEMPTutorialGroup.getNumberOfEntries(); i++) {
            for (int j = 1; j <= AssignmentTeamList.getNumberOfEntries(); j++) {
                if (TEMPTutorialGroup.getEntry(i).equals(AssignmentTeamList.getEntry(j).getTutorialGroup())) {
                    TEMPAssignmentTeam.add(AssignmentTeamList.getEntry(j));
                }
            }

        }

        for (int i = 1; i <= TEMPProgramme.getNumberOfEntries(); i++) {
            int count = 0;
            for (int j = 1; j <= TEMPAssignmentTeam.getNumberOfEntries(); j++) {
                if (TEMPAssignmentTeam.getEntry(j).getTutorialGroup().getProgrammeCode().equals(TEMPProgramme.getEntry(i))) {
                    count++;
                }
            }
            programmeAssignmentTeamCount.add(count);
        }

        for (int i = 1; i <= programmeSortedList.getNumberOfEntries(); i++) {
            if (i >= 10) {
                outputStr.add(i + ". " + programmeSortedList.getEntry(i) + " ".repeat(10) + programmeTutorialGroupCount.getEntry(i) + " ".repeat(20) + programmeAssignmentTeamCount.getEntry(i) + "\n");
            } else {
                outputStr.add(" " + i + ". " + programmeSortedList.getEntry(i) + " ".repeat(10) + programmeTutorialGroupCount.getEntry(i) + " ".repeat(20) + programmeAssignmentTeamCount.getEntry(i) + "\n");
            }
        }

        outputStr.add("-".repeat(150)
                + "\nTotal " + TEMPProgramme.getNumberOfEntries() + " Programme(s)");

        for (int i = 1; i <= programmeTutorialGroupCount.getNumberOfEntries(); i++) {
            if (programmeAssignmentTeamCount.getEntry(i) != 0) {
                if (programmeAssignmentTeamCount.getEntry(i) > max) {
                    max = programmeAssignmentTeamCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (programmeAssignmentTeamCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (programmeAssignmentTeamCount.getEntry(i) < min) {
                    min = programmeAssignmentTeamCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (programmeAssignmentTeamCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        outputStr.add("-".repeat(150) + "\n" + "HIGHEST Number OF Assignment Teams: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + max + " Assignment Team] " + "<" + programmeSortedList.getEntry(maxIndex.getEntry(i)).getProgrammeCode() + "> " + programmeSortedList.getEntry(maxIndex.getEntry(i)).getProgrammeName());
        }

        outputStr.add("\nLOWEST Number OF Assignment Teams:");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + min + " Assignment Team] " + "<" + programmeSortedList.getEntry(minIndex.getEntry(i)).getProgrammeCode() + "> " + programmeSortedList.getEntry(minIndex.getEntry(i)).getProgrammeName());
        }

        outputStr.add("\n[NOTE: 0 Assignment Team IS NOT COUNTED]\n" + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF PROGRAMME (Assignment Team) SUMMARY REPORT\n"
                + "=".repeat(150));

        return outputStr.toString();
    }

    /* Report Of Assignment Team Of A Tutorial Group */
    public String TutorialGroupReport() {
        char check = 'N';
        String programmeCode;
        String tutorialGroupID;
        ListInterface<String> outputStr = new ArrayList<>();
        ListInterface<AssignmentTeam> assignmentTeam = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();
        int max = 0;
        int min = 999;

        do {
            courseManagement.displayAllProgramme(null);
            programmeCode = courseManagementUI.inputProgrammeCode().toUpperCase();
            if (!courseManagement.findProgramme(programmeCode)) {
                System.out.println("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check == 'N');

        check = 'N';

        do {
            tutorialGroupManagement.displayProgrammeGroup(programmeCode);
            tutorialGroupID = tutorialGroupManagementUI.inputGroupID().toUpperCase();
            if (tutorialGroupManagement.findGroupIndex(tutorialGroupID) == -1) {
                System.out.println("Invalid Tutorial Group ID!");

            } else {
                check = 'Y';

            }
            System.out.println();
        } while (check == 'N');

        int groupIndex = tutorialGroupManagement.findGroupIndex(tutorialGroupID);
        TutorialGroup group = TutorialGroupList.getEntry(groupIndex);
        int count = 1;
        for (int i = 1; i <= AssignmentTeamList.getNumberOfEntries(); i++) {

            if (AssignmentTeamList.getEntry(i).getTutorialGroup().equals(group)) {
                if (count >= 10) {
                    outputStr.add(count + ". " + AssignmentTeamList.getEntry(i).getAssignmentTeamID() + " ".repeat(30) + AssignmentTeamList.getEntry(i).getAssignmentTeamName() + " ".repeat(32)
                            + AssignmentTeamList.getEntry(i).getNumOfMember() + " ".repeat(19) + (AssignmentTeamList.getEntry(i).getAssignmentTeamSize() - AssignmentTeamList.getEntry(i).getNumOfMember()) + "\n");
                    assignmentTeam.add(AssignmentTeamList.getEntry(i));

                } else {
                    outputStr.add(" " + count + ". " + AssignmentTeamList.getEntry(i).getAssignmentTeamID() + " ".repeat(30) + AssignmentTeamList.getEntry(i).getAssignmentTeamName() + " ".repeat(32)
                            + AssignmentTeamList.getEntry(i).getNumOfMember() + " ".repeat(19) + (AssignmentTeamList.getEntry(i).getAssignmentTeamSize() - AssignmentTeamList.getEntry(i).getNumOfMember()) + "\n");
                    assignmentTeam.add(AssignmentTeamList.getEntry(i));
                }
                count++;
            }

        }

        outputStr.add("-".repeat(150)
                + "\nTotal " + (count - 1) + " Assignment Team(s)");

        for (int i = 1; i <= assignmentTeam.getNumberOfEntries(); i++) {
            if (assignmentTeam.getEntry(i).getNumOfMember() != 0) {
                if (assignmentTeam.getEntry(i).getNumOfMember() > max) {
                    max = assignmentTeam.getEntry(i).getNumOfMember();
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (assignmentTeam.getEntry(i).getNumOfMember() == max) {
                    maxIndex.add(i);
                }
                if (assignmentTeam.getEntry(i).getNumOfMember() < min) {
                    min = assignmentTeam.getEntry(i).getNumOfMember();
                    minIndex.clear();
                    minIndex.add(i);
                } else if (assignmentTeam.getEntry(i).getNumOfMember() == min) {
                    minIndex.add(i);
                }
            }
        }

        outputStr.add("-".repeat(150) + "\n" + "HIGHEST Number OF Members: ");

        check = 'N';
        if (max == 0) {
            outputStr.add("None");
        } else {
            for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
                outputStr.add("-> [" + max + " Member(s)] " + "<" + assignmentTeam.getEntry(maxIndex.getEntry(i)).getAssignmentTeamID() + "> ");
            }
        }

        outputStr.add("\nLOWEST Number OF Members:");

        if (min == 999) {
            outputStr.add("None");
        } else {
            for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
                outputStr.add("-> [" + min + " Member(s)] " + "<" + assignmentTeam.getEntry(maxIndex.getEntry(i)).getAssignmentTeamID() + "> ");
            }
        }

        outputStr.add("\n[NOTE: 0 Member IS NOT COUNTED]" + "\n" + "-".repeat(150));

        outputStr.add("\nFull Of Member:");

        check = 'N';

        for (int i = 1; i <= assignmentTeam.getNumberOfEntries(); i++) {
            if (assignmentTeam.getEntry(i).getAssignmentTeamSize() == assignmentTeam.getEntry(i).getNumOfMember()) {
                outputStr.add("-> [" + assignmentTeam.getEntry(i).getNumOfMember() + " Member] " + "<" + assignmentTeam.getEntry(i).getAssignmentTeamID() + "> ");
                check = 'Y';
            }
        }

        if (check == 'N') {
            outputStr.add("None");
        }
        check = 'N';

        outputStr.add("-".repeat(150) + "\n" + "Empty Assignment Team: ");
        for (int i = 1; i <= assignmentTeam.getNumberOfEntries(); i++) {
            if (assignmentTeam.getEntry(i).getNumOfMember() == 0) {
                outputStr.add("-> [" + assignmentTeam.getEntry(i).getNumOfMember() + " Member] " + "<" + assignmentTeam.getEntry(i).getAssignmentTeamID() + "> ");
                check = 'Y';
            }
        }

        if (check == 'N') {
            outputStr.add("None");
        }

        outputStr.add("\n" + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF Tutorial Group( (Assignment Team) SUMMARY REPORT\n"
                + "=".repeat(150));

        return outputStr.toString();
    }

    /* Check Assignment Team Exists in The Group */
    public boolean checkAsgTeam(TutorialGroup group, String asgTeamID) {
        for (int i = 0; i < AssignmentTeamList.getNumberOfEntries() + 1; i++) {
            if (AssignmentTeamList.getEntry(i) != null && AssignmentTeamList.getEntry(i).getAssignmentTeamID().equals(asgTeamID) && AssignmentTeamList.getEntry(i).getTutorialGroup().equals(group)) {
                return true;
            }
        }
        return false;
    }

    /* Validate The Target Assignment Team ID is In the Assignment Team List*/
    public boolean checkAsgTeam(ListInterface<AssignmentTeam> asgTeam, String targetAsgTeamID) {
        for (int i = 1; i <= asgTeam.getNumberOfEntries(); i++) {
            if (targetAsgTeamID != null && asgTeam.getEntry(i).getAssignmentTeamID().equals(targetAsgTeamID)) {
                return true;
            }
        }
        return false;
    }

    /* Check Student Already Assign In A Assignment Team */
    public boolean checkStudentAsgTeamStatus(AssignmentTeam asgTeam, Student student) {
        for (int i = 1; i <= AssignmentTeamList.getNumberOfEntries(); i++) {
            ListInterface<Student> teamMemberList = AssignmentTeamList.getEntry(i).getTeamMember();
            if (teamMemberList != null && teamMemberList.contains(student)) {
                return true;
            }
        }
        return false;
    }

    /* Check Student Already In This Team */
    public boolean checkStudentExistsInTeam(AssignmentTeam asgTeam, Student student) {
        ListInterface<Student> teamMemberList = asgTeam.getTeamMember();
        return teamMemberList.contains(student);
    }

    /* Check Student Assign In This Tutorial Group */
    public boolean checkStudentExists(TutorialGroup group, String studentID) {
        for (int i = 0; i < studentList.getNumberOfEntries() + 1; i++) {
            if (studentList.getEntry(i) != null && group.getGroupStudentsList().contains(studentList.getEntry(i))) {
                return true;
            }
        }
        return false;
    }

    /* Find Student Current Assignment Team */
    public String findStudentCurrentTeam(AssignmentTeam asgTeam, Student student) {
        for (int i = 0; i < AssignmentTeamList.getNumberOfEntries() + 1; i++) {
            if (AssignmentTeamList.getEntry(i) != null && AssignmentTeamList.getEntry(i).getTeamMember().contains(student)) {
                if (AssignmentTeamList.getEntry(i).equals(asgTeam)) {
                    return "This Group";
                }
                return ":\nTutorial Group: " + AssignmentTeamList.getEntry(i).getTutorialGroup().getGroupID() + " \nAssignment Team: " + AssignmentTeamList.getEntry(i).getAssignmentTeamID() + "\n";
            }
        }
        return null;
    }

    /* Get The List Of Assignment Team That Can Merge With Target Team */
    public ListInterface<AssignmentTeam> retrieveSuitableMergeTeam(AssignmentTeam asgTeam) {
        ListInterface<AssignmentTeam> suitableTeam = new ArrayList<>();
        for (int i = 0; i < AssignmentTeamList.getNumberOfEntries() + 1; i++) {
            if (AssignmentTeamList.getEntry(i) != null && AssignmentTeamList.getEntry(i).getTutorialGroup().equals(asgTeam.getTutorialGroup()) && AssignmentTeamList.getEntry(i).getNumOfMember() + asgTeam.getNumOfMember() <= asgTeam.getAssignmentTeamSize() && !AssignmentTeamList.getEntry(i).equals(asgTeam)) {
                suitableTeam.add(AssignmentTeamList.getEntry(i));
            }
        }
        return suitableTeam;
    }

    /* Merge The Two Assignment Team*/
    public ListInterface<Student> mergeAssignmentTeam(ListInterface<Student> asgTeamA, ListInterface<Student> asgTeamB) {
        for (int i = 0; i < asgTeamB.getNumberOfEntries() + 1; i++) {
            if (asgTeamB.getEntry(i) != null) {
                asgTeamA.add(asgTeamB.getEntry(i));
            }
        }
        return asgTeamA;
    }

    /* Get The Student Object By Its ID */
    public Student retrieveStudent(String studentID) {
        if (studentID == null || studentID.isEmpty()) {
            return null;
        }

        for (int i = 0; i < studentList.getNumberOfEntries() + 1; i++) {
            if (studentList.getEntry(i) != null && studentList.getEntry(i).getStudentID().equals(studentID)) {
                Student student = studentList.getEntry(i);
                return student;
            }
        }
        return null;
    }

    /* Get The Number Of Assignment Team For A Group */
    public int getTutorialGroupAsgTeamNum(TutorialGroup group) {
        int count = 0;

        for (int i = 1; i <= AssignmentTeamList.getNumberOfEntries(); i++) {
            AssignmentTeam team = AssignmentTeamList.getEntry(i);

            if (team.getTutorialGroup().equals(group)) {
                count++;
            }
        }

        return count;
    }

    /* Get String OF Assignment Team Member */
    public String getAllTeamMember(AssignmentTeam asgTeam) {
        String outputStr = "";

        if (asgTeam == null) {
            for (int i = 1; i <= AssignmentTeamList.getNumberOfEntries(); i++) {
                outputStr += AssignmentTeamList.getEntry(i) + "\n";
            }
        } else {
            for (int i = 1; i <= AssignmentTeamList.getNumberOfEntries(); i++) {
                AssignmentTeam team = AssignmentTeamList.getEntry(i);
                if (team.getAssignmentTeamID().equals(asgTeam.getAssignmentTeamID())) {
                    outputStr += team.getTeamMember() + "\n";
                }
            }
        }
        if (outputStr.isEmpty()) {
            return "There are no student in this assignment team\n";
        }
        return outputStr;

    }

    /* Get String Of Assignment Team */
    public String getAllAssignmentTeam(TutorialGroup group) {
        String outputStr = "";

        if (group == null) {
            for (int i = 1; i <= AssignmentTeamList.getNumberOfEntries(); i++) {
                outputStr += AssignmentTeamList.getEntry(i) + "\n";
            }
        } else {
            for (int i = 1; i <= AssignmentTeamList.getNumberOfEntries(); i++) {
                AssignmentTeam team = AssignmentTeamList.getEntry(i);
                if (team.getTutorialGroup().equals(group)) {
                    outputStr += team + "\n";
                }
            }
        }
        if (outputStr.isEmpty()) {
            return "There are no assignment team in this group\n";
        }
        return outputStr;

    }

    /* Get String Of Student In The Group */
    public String getStudentInTutorialGroup(TutorialGroup group) {
        String outputStr = "";

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Student student = studentList.getEntry(i);
            if (group.getGroupStudentsList().contains(student)) {
                outputStr += student + "\n";
            }

        }
        if (outputStr.isEmpty()) {
            return "There are no student in this Tutorial Group\n";
        }
        return outputStr;

    }

    /* Sort The Programme Code */
    public void sortProgrammeForAsgTeam() {
        programmeSortedList.clear();
        for (int i = 1; i <= ProgrammeList.getNumberOfEntries(); i++) {
            programmeSortedList.add(ProgrammeList.getEntry(i));
        }
        String criteria = null;
        String sortOrder = null;
        int sortType;
        int sortDetail = courseManagementUI.getProgrammeSortDetailsChoice();
        switch (sortDetail) {
            case 0:
                MessageUI.displayExitMessage();
                break;
            case 1:
                criteria = "ProgrammeCode";
                sortType = courseManagementUI.inputSortType();
                switch (sortType) {
                    case 1:
                        sortOrder = "Ascending";
                        programmeSortedList.sortByAscending(programme -> programme.getProgrammeCode());
                        break;
                    case 2:
                        sortOrder = "Descending";
                        programmeSortedList.sortByDescending(programme -> programme.getProgrammeCode());
                        break;
                }
                break;
            case 2:
                criteria = "Programme Name";
                sortType = courseManagementUI.inputSortType();
                switch (sortType) {
                    case 1:
                        sortOrder = "Ascending";
                        programmeSortedList.sortByAscending(programme -> programme.getProgrammeName());
                        break;
                    case 2:
                        sortOrder = "Descending";
                        programmeSortedList.sortByDescending(programme -> programme.getProgrammeName());
                        break;
                }
                break;

            default:
                MessageUI.displayInvalidChoiceMessage();
        }
    }

    /* Display Assignment Team*/
    public void displayAssignmentTeam(TutorialGroup group) {
        AssignmentTeamManagementUI.listAssignmentTeam(getAllAssignmentTeam(group));
    }

    /* Display Student For Assignment Team */
    public void displayStudentForAsgTeam(AssignmentTeam asgTeam) {
        AssignmentTeamManagementUI.listAssignmentTeamStudent(getAllTeamMember(asgTeam));
    }

    /* Display Student For Tutorial Group */
    public void displayStudentForTutorialGroup(TutorialGroup group) {
        AssignmentTeamManagementUI.listAssignmentTeamStudent(getStudentInTutorialGroup(group));
    }

    /* Display Programme Assignment Team Report */
    public void displayProgrammeAsgTeamReport() {
        AssignmentTeamManagementUI.asgTeamOfProgrammeReport(ProgrammeReport());
    }
    
    /* Display Tutorial Group Assignment Team Report */
    public void displayTutorialGroupAsgTeamReport() {
        AssignmentTeamManagementUI.asgTeamOfTutorialGroupReport(TutorialGroupReport());
    }

}
