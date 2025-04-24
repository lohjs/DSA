/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.CourseManagementUI;
import dao.*;
import boundary.TeachingAssignmentUI;
import boundary.TutorialGroupManagementUI;
import entity.Course;
import entity.CourseTutor;
import entity.GroupTutorCourse;
import entity.Tutor;
import entity.TutorialGroup;
import entity.courseProgramme;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author hgtan
 */
public class TeachingAssignment {
    
    Scanner scanner = new Scanner(System.in);
    
    private ListInterface<Tutor> TutorList = new ArrayList<>();
    private ListInterface<Course> CourseList = new ArrayList<>();
    private ListInterface<courseProgramme> courseProgrammeList = new ArrayList<>();
    private ListInterface<TutorialGroup> TutorialGroupList = new ArrayList<>();
    private ListInterface<CourseTutor> CourseTutorList = new ArrayList<>();
    private ListInterface<GroupTutorCourse> GroupTutorCourseList = new ArrayList<>();
    
    private TutorDAO TutorDAO = new TutorDAO();
    private CourseDAO CourseDAO = new CourseDAO();
    private TutorialGroupDAO TutorialGroupDAO = new TutorialGroupDAO();
    private CourseTutorDAO CourseTutorDAO = new CourseTutorDAO();
    private GroupTutorCourseDAO GroupTutorCourseDAO = new GroupTutorCourseDAO();
    private courseProgrammeDAO CourseProgrammeDAO = new courseProgrammeDAO();
    
    private TutorInitializer TutorInitializer = new TutorInitializer();
    private CourseInitializer CourseInitializer = new CourseInitializer();
    private CourseTutorInitializer CourseTutorInitializer = new CourseTutorInitializer();
    private GroupTutorCourseInitializer GroupTutorCourseInitializer = new GroupTutorCourseInitializer();
    
    private TeachingAssignmentUI TeachingAssignmentUI = new TeachingAssignmentUI();
    private CourseManagementUI CourseManagementUI = new CourseManagementUI();
    private TutorialGroupManagementUI GroupManagementUI = new TutorialGroupManagementUI();
    private CourseManagement CourseManagement = new CourseManagement();
    
    public TeachingAssignment() {
        TutorList = TutorDAO.retrieveFromFile();
        CourseList = CourseDAO.retrieveFromFile();
        TutorialGroupList = TutorialGroupDAO.retrieveFromFile();
        courseProgrammeList = CourseProgrammeDAO.retrieveFromFile();
        CourseTutorList = CourseTutorDAO.retrieveFromFile();
        GroupTutorCourseList = GroupTutorCourseDAO.retrieveFromFile();
    }

    public static void main(String[] args) {
        TeachingAssignment teachingAssignment = new TeachingAssignment();
        teachingAssignment.runTeachingAssignment();
    }
    
    public void runTeachingAssignment() {
        int choice = 0;
        do {
            choice = TeachingAssignmentUI.getMenuChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    AssignTutorToCourse();
                    break;
                case 2:
                    AddTutorialGroupToTutor();
                    break;
                case 3:
                    AddTutorToTutorialGroupForCourse();
                    break;
                case 4:
                    SearchCourseUnderTutor();
                    break;
                case 5:
                    SearchTutorForCourse();
                    break;
                case 6:
                    ListTutorAndGroupForCourse();
                    break;
                case 7:
                    ListCourseForEachTutor();
                    break;  
                case 8:
                    FilterTutor();
                    break;
                case 9:
                    SummaryReport();
                    break;
            }
        } while (choice != 0);
    }
    
    public void AssignTutorToCourse(){
        String courseCode = "";
        String tutorID = "";
        char check = 'N';
        // Display course list and prompt user to select a course to assign tutor.
        CourseManagement.displayAllCourse(null);
        do {
            courseCode = CourseManagementUI.inputCourseCode();
            if (!CourseManagement.findCourse(courseCode)) {
                System.out.print("Invalid course!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        check = 'N';
        // Display tutor list and prompt user to enter a tutor to be assign to the course selected.
        displayTutorList(null);
        do {
            tutorID = TeachingAssignmentUI.inputTutorID();
            if (!findTutor(tutorID)) {
                System.out.print("Invalid Tutor ID!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        char tutorType = 'N';
        do {
            // Prompt user to select the tutor type to be assign to the tutor selected.
            tutorType = TeachingAssignmentUI.inputTutorType();
            // Check if Leturer already exists in the course
            if (tutorType == 'L') {
                for (int i = 1; i <= CourseTutorList.getNumberOfEntries(); i++) {
                    if (CourseTutorList.getEntry(i).getCourseCode().equals(courseCode)) {
                        char[] checkArr = CourseTutorList.getEntry(i).getTutorTypeArr();
                        for (int j = 0; j < checkArr.length; j++) {
                            if (checkArr[j] == 'L') {
                                tutorType = 'N';
                                System.out.println("Lecturer Already Exists in This Course!");
                                break;
                            }
                        }
                    }
                }
            }
        } while (tutorType == 'N');

        // Check if record already exists.
        check = 'N';
        int checkPhase = 1;
        int position = -1;
        char[] tempArr = new char[1];
        char[] tutorTypeArr = new char[3];
        for (int i = 1; i <= CourseTutorList.getNumberOfEntries(); i++) {
            if (tutorID.equals(CourseTutorList.getEntry(i).getTutorID())) {
                if (CourseTutorList.getEntry(i).getCourseCode().equals(courseCode)) {
                    checkPhase++;
                    position = i;
                    tempArr = CourseTutorList.getEntry(i).getTutorTypeArr();
                    for (int j = 0; j < tempArr.length; j++) {
                        tutorTypeArr[j] = tempArr[j];
                        if (tutorTypeArr[j] == tutorType) {
                            check = 'Y';
                            break;
                        }
                    }
                }
            }
        }
        System.out.println();
        if (check == 'Y') {
            System.out.println("Record already exist!");
        } 
        
        // Add new record and save into file
        else {
            if (checkPhase == 1) {
                tutorTypeArr[0] = tutorType;
                CourseTutor newCourseTutor = (CourseTutor) TeachingAssignmentUI.assignTutorToCourse(tutorID, courseCode, tutorTypeArr);
                CourseTutorList.add(newCourseTutor);
                CourseTutorDAO.saveToFile(CourseTutorList);
            } else {
                for (int i = 0; i < 3; i++) {
                    if (tutorTypeArr[i] == '\0') {
                        tutorTypeArr[i] = tutorType;
                        break;
                    }
                }
                CourseTutor newCourseTutor = (CourseTutor) TeachingAssignmentUI.assignTutorToCourse(tutorID, courseCode, tutorTypeArr);
                CourseTutorList.getEntry(position).setTutorTypeArr(tutorTypeArr);
                CourseTutorDAO.saveToFile(CourseTutorList);
            }
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void AddTutorialGroupToTutor() {
        String tutorID = "";
        String groupID = "";
        char check = 'N';
        // Display Tutor list and prompt user to select a tutor to add Tutorial Group to
        displayTutorList(null);
        do {
            tutorID = TeachingAssignmentUI.inputTutorID();
            if (!findTutor(tutorID)) {
                System.out.print("Invalid Tutor ID!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        check = 'N';
        
        // Display tutor list and prompt user to enter a tutor to be assign to the course selected.
        displayProgrammeGroup(null);
        do {
            groupID = TeachingAssignmentUI.inputTutorialGroup();
            if (!findTutorialGroup(groupID)) {
                System.out.print("Invalid Group ID!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        check = 'N';
        
        // Check if the record already exists
        for (int i = 1; i <= GroupTutorCourseList.getNumberOfEntries(); i++) {
            if (tutorID.equals(GroupTutorCourseList.getEntry(i).getTutorID())) {
                if (GroupTutorCourseList.getEntry(i).getGroupID().equals(groupID)) {
                    check = 'Y';
                    break;
                }
            }
        }
        System.out.println();
        if (check == 'Y') {
            System.out.println("Record already exist!");
        } else {
            GroupTutorCourse newGroupTutorCourse = (GroupTutorCourse) TeachingAssignmentUI.addTutorialGroupToTutor(groupID, tutorID, "");
            GroupTutorCourseList.add(newGroupTutorCourse);
            GroupTutorCourseDAO.saveToFile(GroupTutorCourseList);
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void AddTutorToTutorialGroupForCourse() {
        String courseCode = "";
        String groupID = "";
        String tutorID = "";
        char check = 'N';
        
        // Display course list and prompt user to selsect course
        CourseManagement.displayAllCourse(null);
        do {
            courseCode = CourseManagementUI.inputCourseCode();
            if (!CourseManagement.findCourse(courseCode)) {
                System.out.print("Invalid course!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        check = 'N';
        
        // Display tutorial group list under the course
        displayProgrammeGroupUnderCourse(courseCode);
        
        // Prompt user to input tutorial group id to add tutor
        do {
            groupID = TeachingAssignmentUI.inputTutorialGroup();
            if (!findTutorialGroup(groupID)) {
                System.out.print("Invalid Group ID!");
            } 
            else if (!findTutorialGroupUnderCourse(courseCode, groupID)) {
                System.out.print("The Tutorial Group Is Not Under the Course!");
            }
            else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        check = 'N';
        
        // Display tutor list under the course
        displayTutorsUnderCourse(courseCode);
        
        // Prompt user to input tutor id to be added to the tutorial group
        do {
            tutorID = TeachingAssignmentUI.inputTutorID();
            if (!findTutor(tutorID)) {
                System.out.print("Invalid Tutor ID!");
            } 
            else if (!findTutorUnderCourse(courseCode, tutorID)) {
                System.out.print("The Tutor Is Not Under The Course!");
            }
            else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        check = 'N';
        
        // Check if there is an CourseCode empty version of the record exist, if yes, remove it
        for (int i = 1; i <= GroupTutorCourseList.getNumberOfEntries(); i++) {
            if (GroupTutorCourseList.getEntry(i).getGroupID().equals(groupID)) {
                if (GroupTutorCourseList.getEntry(i).getTutorID().equals(tutorID)) {
                    if (GroupTutorCourseList.getEntry(i).getCourseCode().equals("")) {
                        GroupTutorCourseList.remove(i);
                        break;
                    }
                }
            }
        }
        
        // Check if the record already exists
        for (int i = 1; i <= GroupTutorCourseList.getNumberOfEntries(); i++) {
            if (GroupTutorCourseList.getEntry(i).getGroupID().equals(groupID)) {
                if (GroupTutorCourseList.getEntry(i).getTutorID().equals(tutorID)) {
                    if (GroupTutorCourseList.getEntry(i).getCourseCode().equals(courseCode)) {
                        check = 'Y';
                        break;
                    }
                }
            }
        }
        System.out.println();
        if (check == 'Y') {
            System.out.println("Record already exist!");
        } else {
            GroupTutorCourse newGroupTutorCourse = (GroupTutorCourse) TeachingAssignmentUI.addTutorialGroupToTutor(groupID, tutorID, courseCode);
            GroupTutorCourseList.add(newGroupTutorCourse);
            GroupTutorCourseDAO.saveToFile(GroupTutorCourseList);
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void SearchCourseUnderTutor() {
        String tutorID = "";
        char check = 'N';
        
        // Display tutor list and prompt user to input tutor id
        displayTutorList(null);
        do {
            tutorID = TeachingAssignmentUI.inputTutorID();
            if (!findTutor(tutorID)) {
                System.out.print("Invalid Tutor ID!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        // List courses under the tutor
        displayCoursesUnderTutor(tutorID);
        
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void SearchTutorForCourse() {
        String courseCode = "";
        String tutorID = "";
        char check = 'N';
        
        // Display course list and prompt user to input course
        CourseManagement.displayAllCourse(null);
        do {
            courseCode = CourseManagementUI.inputCourseCode();
            if (!CourseManagement.findCourse(courseCode)) {
                System.out.print("Invalid course!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        check = 'N';
        
        // Prompt user to enter tutor type
        char tutorType = TeachingAssignmentUI.inputTutorType();
        
        // Display tutor list with the type selected under the course
        displayTutorsUnderCourseWithType(courseCode, tutorType);
        
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void ListTutorAndGroupForCourse() {
        String courseCode = "";
        char check = 'N';
        
        // Display course list and prompt user to input course
        CourseManagement.displayAllCourse(null);
        do {
            courseCode = CourseManagementUI.inputCourseCode();
            if (!CourseManagement.findCourse(courseCode)) {
                System.out.print("Invalid course!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        
        check = 'N';
        
        // Display tutor and tutorial group for the course
        displayTutorAndGroupForCourse(courseCode);
        
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void ListCourseForEachTutor() {
        // Display all tutors and their courses
        displayTutorCourse();
        
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void FilterTutor() {
        // Display tutor list in full
        displayTutorList(null);
        
        // Get user criteria choice
        int criteriaChoice = 0;
        criteriaChoice = TeachingAssignmentUI.getCriteriaChoice();

        // Filter and display tutor list based on the criteria
        int age = 0;
        switch (criteriaChoice) {
            case 0:
                MessageUI.displayExitMessage();
                break;
            case 1:
                // filter by age above
                age = TeachingAssignmentUI.inputTutorAgeAbove();
                ageFilter(criteriaChoice, age);
                break;
            case 2:
                // filter by age below
                age = TeachingAssignmentUI.inputTutorAgeBelow();
                ageFilter(criteriaChoice, age);
                break;
            case 3:
                // filter by gender
                String gender = TeachingAssignmentUI.inputTutorGender();
                genderFilter(gender);   
                break;
        }
        
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void SummaryReport() {
        int choice = -1;
        do {
            choice = TeachingAssignmentUI.inputReportChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    TutorReport();
                    break;
                case 2:
                    CourseReport();
            }
        } while (choice != 0);
    }
    
    public void TutorReport() {
        ListInterface<Tutor> TutorDetails = new ArrayList<>();
        ListInterface<Integer> CourseCount = new ArrayList<>();
        ListInterface<Integer> TutorialGroupCount = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();

        int max = 0;
        int min = 999;
        
        for (int i = 1; i <= TutorList.getNumberOfEntries(); i++) {
            TutorDetails.add(TutorList.getEntry(i));
        }
        
        // Sort list by tutor id
        TutorDetails.sortByAscending(tutor -> tutor.getTutorID());
        
        // Count the number of courses teached by each tutor
        for (int i = 1; i <= TutorDetails.getNumberOfEntries(); i++) {
            int totalCourse = 0;
            String tutorID = TutorDetails.getEntry(i).getTutorID();
            for (int j = 1; j <= CourseTutorList.getNumberOfEntries(); j++) {
                if (CourseTutorList.getEntry(j).getTutorID().equals(tutorID)) {
                    totalCourse++;
                }
            }
            CourseCount.add(totalCourse);
        }
        
        // Count the number of tutorial groups handled by each tutor
        for (int i = 1; i <= TutorDetails.getNumberOfEntries(); i++) {
            int totalGroup = 0;
            String tutorID = TutorDetails.getEntry(i).getTutorID();
            for (int j = 1; j <= GroupTutorCourseList.getNumberOfEntries(); j++) {
                if (GroupTutorCourseList.getEntry(j).getTutorID().equals(tutorID)) {
                    String groupID = GroupTutorCourseList.getEntry(j).getGroupID();
                    if (j > 1) {
                        if (!groupID.equals(GroupTutorCourseList.getEntry(j-1).getGroupID())) {
                            totalGroup++;
                        }
                    } else {
                        totalGroup++;
                    }
                }
            }
            TutorialGroupCount.add(totalGroup);
        }
        
        // Display Report
        TeachingAssignmentUI.TutorReport();
        for (int i = 1; i <= TutorDetails.getNumberOfEntries(); i++) {
            System.out.printf("%2d. %-20s %-78s %4d %24d\n", i, TutorDetails.getEntry(i).getTutorID(), 
                    TutorDetails.getEntry(i).getName(), CourseCount.getEntry(i), 
                    TutorialGroupCount.getEntry(i));
        }
        
        System.out.print("-".repeat(150));
        System.out.print("\nTotal " + TutorDetails.getNumberOfEntries() + " Tutors\n");
        
        // Calculate min max CourseCount for tutor
        for (int i = 1; i <= CourseCount.getNumberOfEntries(); i++) {
            if (CourseCount.getEntry(i) != 0) {
                if (CourseCount.getEntry(i) > max) {
                    max = CourseCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (CourseCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (CourseCount.getEntry(i) < min) {
                    min = CourseCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (CourseCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }
        
        System.out.print("-".repeat(150) + "\n" + "HIGHEST COURSES HANDLED: ");
        
        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Courses] " + "<" + TutorDetails.getEntry(maxIndex.getEntry(i)).getTutorID() + "> " + TutorDetails.getEntry(maxIndex.getEntry(i)).getName());
        }

        System.out.print("\n\nLOWEST COURSES HANDLED : ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Courses] " + "<" + TutorDetails.getEntry(minIndex.getEntry(i)).getTutorID() + "> " + TutorDetails.getEntry(minIndex.getEntry(i)).getName());
        }

        System.out.print("\n[NOTE: 0 COURSES IS NOT COUNTED]" + "\n" + "-".repeat(150));
        
        max = 0;
        min = 999;
        
        // Calculate min max TutorialGroupCount for tutor
        for (int i = 1; i <= TutorialGroupCount.getNumberOfEntries(); i++) {
            if (TutorialGroupCount.getEntry(i) != 0) {
                if (TutorialGroupCount.getEntry(i) > max) {
                    max = TutorialGroupCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (TutorialGroupCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (TutorialGroupCount.getEntry(i) < min) {
                    min = TutorialGroupCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (TutorialGroupCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }
        
        System.out.print("\n" + "MOST TUTORIAL GROUPS HANDLED TUTOR(S): ");
        
        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Tutorial Groups] " + "<" + TutorDetails.getEntry(maxIndex.getEntry(i)).getTutorID() + "> " + TutorDetails.getEntry(maxIndex.getEntry(i)).getName());
        }

        System.out.print("\n\nLEAST TUTORIAL GROUPS HANDLED TUTOR(S): ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Tutorial Groups] " + "<" + TutorDetails.getEntry(minIndex.getEntry(i)).getTutorID() + "> " + TutorDetails.getEntry(minIndex.getEntry(i)).getName());
        }
        
        System.out.print(
                "\n[NOTE: 0 TUTORIAL GROUPS IS NOT COUNTED]\n"
                + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF TUTOR SUMMARY REPORT\n"
                + "=".repeat(150));

        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }
    
    public void CourseReport() {
        ListInterface<Course> CourseDetailList = new ArrayList<>();
        ListInterface<Integer> TutorCount = new ArrayList<>();
        ListInterface<Integer> LectureCount = new ArrayList<>();
        ListInterface<Integer> TutorialCount = new ArrayList<>();
        ListInterface<Integer> PracticalCount = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();
        int max = 0;
        int min = 999;
        
        for (int i = 1; i <= CourseList.getNumberOfEntries(); i++) {
            CourseDetailList.add(CourseList.getEntry(i));
        }
        
        CourseDetailList.sortByAscending(course -> course.getCourseCode());
        
        // Count the number of tutors for each course
        for (int i = 1; i <= CourseDetailList.getNumberOfEntries(); i++) {
            int totalTutor = 0;
            int LCount = 0;
            int TCount = 0;
            int PCount = 0;
            String courseCode = CourseDetailList.getEntry(i).getCourseCode();
            for (int j = 1; j <= CourseTutorList.getNumberOfEntries(); j++) {
                if (CourseTutorList.getEntry(j).getCourseCode().equals(courseCode)) {
                    totalTutor++;
                    
                    // Count the number of L, T, P for each course
                    char[] typeArr = CourseTutorList.getEntry(j).getTutorTypeArr();
                    for (int k = 0; k < typeArr.length; k++) {
                        switch (typeArr[k]) {
                            case 'L':
                                LCount++;
                                break;
                            case 'T':
                                TCount++;
                                break;
                            case 'P':
                                PCount++;
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            TutorCount.add(totalTutor);
            LectureCount.add(LCount);
            TutorialCount.add(TCount);
            PracticalCount.add(PCount);
        }
        
        // Display Report
        TeachingAssignmentUI.CourseReport();
        for (int i = 1; i <= CourseDetailList.getNumberOfEntries(); i++) {
            System.out.printf("%2d. %-20s %-78s %6d %15d %5d %5d\n", i,
                   CourseDetailList.getEntry(i).getCourseCode(),
                   CourseDetailList.getEntry(i).getCourseName(),
                   TutorCount.getEntry(i), LectureCount.getEntry(i),
                   TutorialCount.getEntry(i), PracticalCount.getEntry(i));
        }
        
        System.out.print("-".repeat(150));
        System.out.print("\nTotal " + CourseDetailList.getNumberOfEntries() + " Courses\n");
        
        // Calculate min max TutorCount for course
        for (int i = 1; i <= TutorCount.getNumberOfEntries(); i++) {
            if (TutorCount.getEntry(i) != 0) {
                if (TutorCount.getEntry(i) > max) {
                    max = TutorCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (TutorCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (TutorCount.getEntry(i) < min) {
                    min = TutorCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (TutorCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }
        
        System.out.print("-".repeat(150) + "\n" + "MOST TUTORS COURSE: ");
        
        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Tutors] " + "<" + CourseDetailList.getEntry(maxIndex.getEntry(i)).getCourseCode()+ "> " + CourseDetailList.getEntry(maxIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n\nLEAST TUTORS COURSE : ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Tutors] " + "<" + CourseDetailList.getEntry(minIndex.getEntry(i)).getCourseCode() + "> " + CourseDetailList.getEntry(minIndex.getEntry(i)).getCourseName());
        }

        System.out.print(
                "\n[NOTE: 0 TUTOR IS NOT COUNTED]\n"
                + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF COURSE TUTOR SUMMARY REPORT\n"
                + "=".repeat(150));
        
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public String getAllTutor(String tutorID) {
        String outputStr = "";
        if (tutorID == null) {
            for (int i = 1; i <= TutorList.getNumberOfEntries(); i++) {
                outputStr += TutorList.getEntry(i) + "\n";

            }
        } else {
            for (int i = 1; i <= TutorList.getNumberOfEntries(); i++) {
                if (tutorID.equals(TutorList.getEntry(i).getTutorID())) {
                    outputStr += TutorList.getEntry(i) + "\n";
                }
            }
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
    
    public String getAllTutorCourse() {
        String outputStr = "";
        for (int i = 1; i <= CourseTutorList.getNumberOfEntries(); i++) {
            outputStr += CourseTutorList.getEntry(i) + "\n";
        }
        return outputStr;
    }
    
    public String getProgrammeGroupUnderCourse(String courseCode) {
        String outputStr = "";
        for (int i = 1; i <= courseProgrammeList.getNumberOfEntries(); i++) {
            if (courseProgrammeList.getEntry(i).getCourseCode().equals(courseCode)) {
                String programmeCode = courseProgrammeList.getEntry(i).getProgrammeCode();
                for (int j = 1; j <= TutorialGroupList.getNumberOfEntries(); j++) {
                    if (TutorialGroupList.getEntry(j).getProgrammeCode().equals(programmeCode)) {
                        outputStr += TutorialGroupList.getEntry(j) + "\n";
                    }
                }
            }
        }
        return outputStr;
    }
    
    public String getTutorsUnderCourse(String courseCode) {
        String outputStr = "";
        for (int i = 1; i <= CourseTutorList.getNumberOfEntries(); i++) {
            if (CourseTutorList.getEntry(i).getCourseCode().equals(courseCode)) {
                String tutorID = CourseTutorList.getEntry(i).getTutorID();
                for (int k = 1; k <= TutorList.getNumberOfEntries(); k++) {
                    if (TutorList.getEntry(k).getTutorID().equals(tutorID)) {
                        outputStr += TutorList.getEntry(k) + "\n";
                    }
                }  
            }
        }
        return outputStr;
    }
    
    public String getCoursesUnderTutor(String tutorID) {
        String outputStr = "";
        for (int i = 1; i <= CourseTutorList.getNumberOfEntries(); i++) {
            if (CourseTutorList.getEntry(i).getTutorID().equals(tutorID)) {
                String courseCode = CourseTutorList.getEntry(i).getCourseCode();
                for (int j = 1; j <= CourseList.getNumberOfEntries(); j++) {
                    if (CourseList.getEntry(j).getCourseCode().equals(courseCode)) {
                        outputStr += CourseList.getEntry(j) + "\n";
                    }
                }
            }
        }
        return outputStr;
    }
    
    public String getTutorsUnderCourseWithType(String courseCode, char tutorType) {
        String outputStr = "";
        for (int i = 1; i <= CourseTutorList.getNumberOfEntries(); i++) {
            if (CourseTutorList.getEntry(i).getCourseCode().equals(courseCode)) {
                char[] checkArr = CourseTutorList.getEntry(i).getTutorTypeArr();
                for (int j = 0; j < checkArr.length; j++) {
                    if (checkArr[j] == tutorType) {
                        String tutorID = CourseTutorList.getEntry(i).getTutorID();
                        for (int k = 1; k <= TutorList.getNumberOfEntries(); k++) {
                            if (TutorList.getEntry(k).getTutorID().equals(tutorID)) {
                                outputStr += TutorList.getEntry(k) + "\n";
                            }
                        }
                    }
                }
            }
        }
        return outputStr;
    }
    
    public String getTutorAndGroupForCourse(String courseCode) {
        String outputStr = "";
        for (int i = 1; i <= GroupTutorCourseList.getNumberOfEntries(); i++) {
            if (GroupTutorCourseList.getEntry(i).getCourseCode().equals(courseCode)) {
                outputStr += GroupTutorCourseList.getEntry(i) + "\n";
            }
        }
        return outputStr;
    }
    
    public String getTutorForGender(String gender) {
        String outputStr = "";
        for (int i = 1; i <= TutorList.getNumberOfEntries(); i++) {
            if (TutorList.getEntry(i).getGender().equals(gender)) {
                outputStr += TutorList.getEntry(i) + "\n";
            }
        }
        return outputStr;
    }
    
    public String getTutorForAge(int criteriaChoice, int age) {
        String outputStr = "";
        switch (criteriaChoice) {
            case 1:
                for (int i = 1; i <= TutorList.getNumberOfEntries(); i++) {
                    if (TutorList.getEntry(i).getAge() > age) {
                        outputStr += TutorList.getEntry(i) + "\n";
                    }
                }
                break;
            case 2:
                for (int i = 1; i <= TutorList.getNumberOfEntries(); i++) {
                    if (TutorList.getEntry(i).getAge() < age) {
                        outputStr += TutorList.getEntry(i) + "\n";
                    }
                }
                break;
        }
        return outputStr;
    }
    
    public boolean findTutor(String tutorID) {
        for (int i = 0; i < TutorList.getNumberOfEntries() + 1; i++) {
            if (TutorList.getEntry(i) != null) {
                if (TutorList.getEntry(i).getTutorID().equals(tutorID)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean findTutorialGroup(String groupID) {
        for (int i = 0; i < TutorialGroupList.getNumberOfEntries() + 1; i++) {
            if (TutorialGroupList.getEntry(i) != null) {
                if (TutorialGroupList.getEntry(i).getGroupID().equals(groupID)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean findTutorialGroupUnderCourse(String courseCode, String groupID) {
        for (int i = 1; i <= courseProgrammeList.getNumberOfEntries(); i++) {
            if (courseProgrammeList.getEntry(i).getCourseCode().equals(courseCode)) {
                String programme = courseProgrammeList.getEntry(i).getProgrammeCode();
                for (int j = 1; j <= TutorialGroupList.getNumberOfEntries(); j++) {
                    if (TutorialGroupList.getEntry(j).getProgrammeCode().equals(programme)) {
                        if (TutorialGroupList.getEntry(j).getGroupID().equals(groupID)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean findTutorUnderCourse(String courseCode, String tutorID) {
        for (int i = 1; i <= CourseTutorList.getNumberOfEntries(); i++) {
            if (CourseTutorList.getEntry(i).getCourseCode().equals(courseCode)) {
                if (CourseTutorList.getEntry(i).getTutorID().equals(tutorID)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void displayTutorList(String tutorID) {
        TeachingAssignmentUI.listAllTutor(getAllTutor(tutorID));
    }
    
    public void displayProgrammeGroup(String programmeCode) {
        GroupManagementUI.listAllTutorialGroup(getAllProgrammeGroup(programmeCode));
    }
    
    public void displayProgrammeGroupUnderCourse(String courseCode) {
        TeachingAssignmentUI.listProgrammeGroupUnderCourse(getProgrammeGroupUnderCourse(courseCode), courseCode);
    }
    
    public void displayTutorsUnderCourse(String courseCode) {
        TeachingAssignmentUI.listTutorsUnderCourse(getTutorsUnderCourse(courseCode), courseCode);
    }
    
    public void displayTutorCourse() {
        TeachingAssignmentUI.listTutorCourse(getAllTutorCourse());
    }
    
    public void displayCoursesUnderTutor(String tutorID) {
        TeachingAssignmentUI.listCoursesUnderTutor(getCoursesUnderTutor(tutorID), tutorID);
    }
    
    public void displayTutorsUnderCourseWithType(String courseCode, char tutorType) {
        TeachingAssignmentUI.listTutorsUnderCourseWithType(getTutorsUnderCourseWithType(courseCode, tutorType), courseCode, tutorType);
    }
    
    public void displayTutorAndGroupForCourse(String courseCode) {
        TeachingAssignmentUI.listTutorAndGroupForCourse(getTutorAndGroupForCourse(courseCode), courseCode);
    }
    
    public void genderFilter(String gender) {
        TeachingAssignmentUI.listAllTutor(getTutorForGender(gender));
    }
    
    public void ageFilter(int criteriaChoice, int age) {
        TeachingAssignmentUI.listAllTutor(getTutorForAge(criteriaChoice, age));
    }

}
