/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.CourseManagementUI;
import dao.*;
import entity.Course;
import entity.Programme;
import entity.courseProgramme;
import entity.Faculty;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author jia shou
 */
public class CourseManagement {

    Scanner scanner = new Scanner(System.in);

    private ListInterface<Course> CourseList = new ArrayList<>();
    private ListInterface<Programme> ProgrammeList = new ArrayList<>();
    private ListInterface<courseProgramme> CourseProgrammeList = new ArrayList<>();
    private ListInterface<Faculty> FacultyList = new ArrayList<>();
    private CourseDAO CourseDAO = new CourseDAO();
    private CourseInitializer CourseInitializer = new CourseInitializer();
    private ProgrammeDAO ProgrammeDAO = new ProgrammeDAO();
    private ProgrammeInitializer ProgrammeInitializer = new ProgrammeInitializer();
    private courseProgrammeDAO CourseProgrammeDAO = new courseProgrammeDAO();
    private courseProgrammeInitializer CourseProgrammeInitializer = new courseProgrammeInitializer();
    private FacultyDAO FacultyDAO = new FacultyDAO();
    private FacultyInitializer FacultyInitializer = new FacultyInitializer();
    private CourseManagementUI CourseManagementUI = new CourseManagementUI();
    private ListInterface<Faculty> facultySortedList = new ArrayList<>();
    private ListInterface<Course> courseSortedList = new ArrayList<>();
    private ListInterface<Programme> programmeSortedList = new ArrayList<>();

    public CourseManagement() {
        CourseList = CourseDAO.retrieveFromFile();
        ProgrammeList = ProgrammeDAO.retrieveFromFile();
        CourseProgrammeList = CourseProgrammeDAO.retrieveFromFile();
        FacultyList = FacultyDAO.retrieveFromFile();
    }

    public static void main(String[] args) {
        CourseManagement courseManagement = new CourseManagement();
        courseManagement.runCourseManagement();
    }

    public void runCourseManagement() {
        int choice = 0;
        do {
            choice = CourseManagementUI.getMenuChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    addProgrammeToCourse(null, 'N');
                    break;
                case 2:
                    removeProgrammFromCourse();
                    break;
                case 3:
                    addNewCourse();
                    break;
                case 4:
                    removeProgrammFromCourse();
                    break;
                case 5:
                    searchCoursesBySemester();
                    break;
                case 6:
                    editCourse();
                    break;
                case 7:
                    listCourseForFaculty();
                    break;
                case 8:
                    listCourseForProgramme();
                    break;
                case 9:
                    Report();
                    break;
            }
        } while (choice != 0);
    }

    public void addProgrammeToCourse(String courseCode, char check) {
        String programmeCode = "";
        if (courseCode == null) {
            displayAllCourse(null);
            do {
                courseCode = CourseManagementUI.inputCourseCode();
                if (!findCourse(courseCode)) {
                    System.out.print("Invalid course!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check != 'Y');
        }
        check = 'N';
        displayAllProgramme(null);
        do {
            programmeCode = CourseManagementUI.inputProgrammeCode();
            if (!findProgramme(programmeCode)) {
                System.out.print("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        check = 'N';
        for (int i = 1; i <= CourseProgrammeList.getNumberOfEntries(); i++) {
            if (programmeCode.equals(CourseProgrammeList.getEntry(i).getProgrammeCode())) {
                if (CourseProgrammeList.getEntry(i).getCourseCode().equals(courseCode)) {
                    check = 'Y';
                    break;
                }
            }
        }
        System.out.println();
        if (check == 'Y') {
            System.out.println("Record already exist!");
        } else {
            courseProgramme newCourseProgramme = (courseProgramme) CourseManagementUI.addProgrammetoCourse(programmeCode, courseCode);
            CourseProgrammeList.add(newCourseProgramme);
            CourseProgrammeDAO.saveToFile(CourseProgrammeList);
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void removeProgrammFromCourse() {
        char check = 'Y';
        String programmeCode = "programme";
        String courseCode;
        displayAllProgramme(null);
        while (check == 'Y') {
            programmeCode = CourseManagementUI.inputProgrammeCode();
            if (!findProgramme(programmeCode)) {
                System.out.print("Invalid programme!");
            } else {
                check = 'N';
            }
            System.out.println();
        }
        if (getCourseForProgramme(programmeCode).isEmpty()) {
            System.out.println("There is no record!");
        } else {
            displayCourseForProgramme(programmeCode);

            do {
                courseCode = CourseManagementUI.inputCourseCode();
                if (!findCourse(courseCode)) {
                    System.out.print("Invalid course!");
                } else {
                    check = 'Y';
                }
                System.out.println();
            } while (check == 'N');
            System.out.print("Continue removing programme from course(Y/N): ");
            check = scanner.next().charAt(0);
            scanner.nextLine();
            int indexToRemove = -1;
            if (check == 'Y') {
                for (int i = 0; i < CourseProgrammeList.getNumberOfEntries() + 1; i++) {
                    if (CourseProgrammeList.getEntry(i) != null) {
                        if (CourseProgrammeList.getEntry(i).getCourseCode().equals(courseCode) && CourseProgrammeList.getEntry(i).getProgrammeCode().equals(programmeCode)) {
                            indexToRemove = i;
                            break;
                        }
                    }
                }
                if (indexToRemove != -1) {
                    CourseProgrammeList.remove(indexToRemove);
                    CourseProgrammeDAO.saveToFile(CourseProgrammeList);
                    System.out.println("" + programmeCode + " had been removed from " + courseCode + " successfully!");
                } else {
                    System.out.println("Failed to remove " + programmeCode + " from " + courseCode + " !");
                }
            } else {
                System.out.println("Programme remove cancelled!");
            }
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void addNewCourse() {
        String courseCode = CourseManagementUI.inputCourseCode();
        if (findCourse(courseCode)) {
            System.out.println("Course already exist!");
            System.out.print("\nPress enter to continue...");
            scanner.nextLine();
        } else {
            String courseName = CourseManagementUI.inputCourseName();
            double creditHour = CourseManagementUI.inputCreditHour();
            String academicYear = CourseManagementUI.inputAcademicYear();
            System.out.println();
            Course newCourse = (Course) CourseManagementUI.addNewCourse(courseCode, courseName, creditHour, academicYear);
            CourseList.add(newCourse);
            CourseDAO.saveToFile(CourseList);
            addProgrammeToCourse(courseCode, 'N');
        }
    }

    public void searchCoursesBySemester() {
        String academicYear = CourseManagementUI.inputAcademicYear();
        displayAllCourse(academicYear);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void editCourse() {
        System.out.println();
        String editCourseCode = CourseManagementUI.inputCourseCode();

        int indexToEdit = -1;
        for (int i = 1; i < CourseList.getNumberOfEntries() + 1; i++) {
            if (CourseList.getEntry(i).getCourseCode().equals(editCourseCode)) {
                indexToEdit = i;
                break;
            }
        }
        if (indexToEdit != -1) {
            int typeEditChoice = 0;
            Course editCourse = CourseList.getEntry(indexToEdit);
            do {
                typeEditChoice = CourseManagementUI.getTypeEditChoice();
                switch (typeEditChoice) {
                    case 0:
                        MessageUI.displayExitMessage();
                        break;
                    case 1:
                        String oldCourseName = editCourse.getCourseName();
                        System.out.println("Old Course Name for " + editCourseCode + ": " + oldCourseName);
                        editCourse.setCourseName(CourseManagementUI.inputCourseName());
                        System.out.println(oldCourseName + " had been changed to " + editCourse.getCourseName());
                        break;
                    case 2:
                        double oldCreditHour = editCourse.getCreditHour();
                        System.out.println("Old Credit Hour for " + editCourseCode + ": " + oldCreditHour);
                        editCourse.setCreditHour(CourseManagementUI.inputCreditHour());
                        System.out.println(oldCreditHour + " credit hour for " + editCourseCode + " had been changed to " + editCourse.getCreditHour());
                        break;
                    case 3:
                        String oldAcademicYear = editCourse.getAcademicYear();
                        System.out.println("Old Academic Year for " + editCourseCode + ": " + oldAcademicYear);
                        editCourse.setAcademicYear(CourseManagementUI.inputAcademicYear());
                        System.out.println(oldAcademicYear + " academic year for " + editCourseCode + " had been changed to " + editCourse.getAcademicYear());
                        break;
                }
            } while (typeEditChoice != 0);
            CourseList.replace(indexToEdit, editCourse);
            CourseDAO.saveToFile(CourseList);
        } else {
            System.out.println("Course with course code " + editCourseCode + " is not found in the list!\n");
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void listCourseForFaculty() {
        char check = 'N';
        String FacultyCode;
        displayAllFaculty(null);
        do {
            FacultyCode = CourseManagementUI.inputFacultyCode();
            if (!findFaculty(FacultyCode)) {
                System.out.println("Invalid faculty!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        displayAllFaculty(FacultyCode);
        displayCourseForFaculty(FacultyCode);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void listCourseForProgramme() {
        char check = 'N';
        String programmeCode;
        displayAllProgramme(null);
        do {
            programmeCode = CourseManagementUI.inputProgrammeCode();
            if (!findProgramme(programmeCode)) {
                System.out.print("Invalid programme!");
            } else {
                check = 'Y';
            }
            System.out.println();
        } while (check != 'Y');
        displayCourseForProgramme(programmeCode);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public void Report() {
        int choice = -1;
        do {
            choice = CourseManagementUI.reportChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    displayFacultyReport();
                    break;
                case 2:
                    displayProgrammeReport();
                    break;
                case 3:
                    displayCourseReport();
                    break;
            }
        } while (choice != 0);
    }

    public String getAllCourse(String academicYear) {
        String outputStr = "";
        if (academicYear == null) {
            for (int i = 1; i <= CourseList.getNumberOfEntries(); i++) {
                outputStr += CourseList.getEntry(i) + "\n";
            }
        } else {
            for (int i = 1; i <= CourseList.getNumberOfEntries(); i++) {
                if (academicYear.equals(CourseList.getEntry(i).getAcademicYear())) {
                    outputStr += CourseList.getEntry(i) + "\n";
                }
            }
        }
        return outputStr;
    }

    public boolean findCourse(String courseCode) {
        for (int i = 0; i < CourseList.getNumberOfEntries() + 1; i++) {
            if (CourseList.getEntry(i) != null) {
                if (CourseList.getEntry(i).getCourseCode().equals(courseCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findProgramme(String programmeCode) {
        for (int i = 0; i < ProgrammeList.getNumberOfEntries() + 1; i++) {
            if (ProgrammeList.getEntry(i) != null) {
                if (ProgrammeList.getEntry(i).getProgrammeCode().equals(programmeCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findFaculty(String FacultyCode) {
        for (int i = 0; i < FacultyList.getNumberOfEntries() + 1; i++) {
            if (FacultyList.getEntry(i) != null) {
                if (FacultyList.getEntry(i).getFacultyCode().equals(FacultyCode)) {
                    return true;
                }
            }
        }
        return false;
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
        return outputStr;
    }

    public String getCourseForProgramme(String programmeCode) {
        String outputStr = "";
        String courseCode = "";

        for (int i = 1; i <= CourseProgrammeList.getNumberOfEntries(); i++) {
            if (programmeCode.equals(CourseProgrammeList.getEntry(i).getProgrammeCode())) {
                courseCode = CourseProgrammeList.getEntry(i).getCourseCode();
            }
            for (int j = 1; j <= CourseList.getNumberOfEntries(); j++) {
                if (courseCode.equals(CourseList.getEntry(j).getCourseCode())) {
                    courseCode = "";
                    outputStr += CourseList.getEntry(j) + "\n";
                }
            }
        }
        return outputStr;
    }

    public String getAllFaculty(String FacultyCode) {
        String outputStr = "";
        if (FacultyCode == null) {
            for (int i = 1; i <= FacultyList.getNumberOfEntries(); i++) {
                outputStr += FacultyList.getEntry(i) + "\n";

            }
        } else {
            for (int i = 1; i <= FacultyList.getNumberOfEntries(); i++) {
                if (FacultyCode.equals(FacultyList.getEntry(i).getFacultyCode())) {
                    outputStr += FacultyList.getEntry(i) + "\n";
                }
            }
        }
        return outputStr;
    }

    public String getCourseForFaculty(String FacultyCode) {
        ListInterface<String> TEMPCourse = new ArrayList<>();
        ListInterface<String> TEMPProgramme = new ArrayList<>();
        String outputStr = "";
        for (int i = 1; i <= ProgrammeList.getNumberOfEntries(); i++) {
            if (FacultyCode.equals(ProgrammeList.getEntry(i).getFacultyCode())) {
                TEMPProgramme.add(ProgrammeList.getEntry(i).getProgrammeCode());
            }
        }
        for (int i = 1; i <= TEMPProgramme.getNumberOfEntries(); i++) {
            for (int j = 1; j <= CourseProgrammeList.getNumberOfEntries(); j++) {
                if (TEMPProgramme.getEntry(i).equals(CourseProgrammeList.getEntry(j).getProgrammeCode())) {
                    TEMPCourse.add(CourseProgrammeList.getEntry(j).getCourseCode());
                }
            }
        }
        for (int i = 1; i <= TEMPCourse.getNumberOfEntries(); i++) {
            String CurrentCourseCode = TEMPCourse.getEntry(i);
            for (int j = i + 1; j <= TEMPCourse.getNumberOfEntries(); j++) {
                if (CurrentCourseCode.equals(TEMPCourse.getEntry(j))) {
                    TEMPCourse.remove(j);
                    j--;
                }
            }
        }
        for (int i = 1; i <= TEMPCourse.getNumberOfEntries(); i++) {
            for (int j = 1; j <= CourseList.getNumberOfEntries(); j++) {
                if (TEMPCourse.getEntry(i).equals(CourseList.getEntry(j).getCourseCode())) {
                    outputStr += CourseList.getEntry(j) + "\n";
                }
            }
        }
        return outputStr;
    }

    public String FacultyReport() {
        sortFaculty();
        ListInterface<String> outputStr = new ArrayList<>();
        ListInterface<String> TEMPCourse = new ArrayList<>();
        ListInterface<String> TEMPProgramme = new ArrayList<>();
        ListInterface<String> TEMPFaculty = new ArrayList<>();
        ListInterface<Integer> facultyProgrammeCount = new ArrayList<>();
        ListInterface<Integer> facultyCourseCount = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();
        int max = 0;
        int min = 999;

        int x = 1;
        for (int i = 1; i <= facultySortedList.getNumberOfEntries(); i++) {
            TEMPFaculty.add(facultySortedList.getEntry(i).getFacultyCode());
        }

        while (x <= TEMPFaculty.getNumberOfEntries()) {
            for (int i = 1; i <= ProgrammeList.getNumberOfEntries(); i++) {
                if (TEMPFaculty.getEntry(x).equals(ProgrammeList.getEntry(i).getFacultyCode())) {
                    TEMPProgramme.add(ProgrammeList.getEntry(i).getProgrammeCode());
                }
            }
            facultyProgrammeCount.add(TEMPProgramme.getNumberOfEntries());
            for (int i = 1; i <= CourseProgrammeList.getNumberOfEntries(); i++) {
                for (int j = 1; j <= TEMPProgramme.getNumberOfEntries(); j++) {
                    if (CourseProgrammeList.getEntry(i).getProgrammeCode().equals(TEMPProgramme.getEntry(j))) {
                        TEMPCourse.add(CourseProgrammeList.getEntry(i).getCourseCode());
                    }
                }
            }
            for (int i = 1; i <= TEMPCourse.getNumberOfEntries(); i++) {
                String CurrentCourseCode = TEMPCourse.getEntry(i);
                for (int j = i + 1; j <= TEMPCourse.getNumberOfEntries(); j++) {
                    if (CurrentCourseCode.equals(TEMPCourse.getEntry(j))) {
                        TEMPCourse.remove(j);
                        j--;
                    }
                }
            }
            facultyCourseCount.add(TEMPCourse.getNumberOfEntries());
            TEMPCourse.clear();
            TEMPProgramme.clear();
            x += 1;
        }

        for (int i = 1; i <= facultySortedList.getNumberOfEntries(); i++) {
            if (i >= 10) {
                outputStr.add(i + ". " + facultySortedList.getEntry(i) + facultyProgrammeCount.getEntry(i) + " ".repeat(22) + facultyCourseCount.getEntry(i) + "\n");
            } else {
                outputStr.add(" " + i + ". " + facultySortedList.getEntry(i) + facultyProgrammeCount.getEntry(i) + " ".repeat(22) + facultyCourseCount.getEntry(i) + "\n");
            }
        }

        outputStr.add("-".repeat(150)
                + "\nTotal " + TEMPFaculty.getNumberOfEntries() + " Faculties");

        for (int i = 1; i <= facultyProgrammeCount.getNumberOfEntries(); i++) {
            if (facultyProgrammeCount.getEntry(i) != 0) {
                if (facultyProgrammeCount.getEntry(i) > max) {
                    max = facultyProgrammeCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (facultyProgrammeCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (facultyProgrammeCount.getEntry(i) < min) {
                    min = facultyProgrammeCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (facultyProgrammeCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        outputStr.add("-".repeat(150) + "\n" + "HIGHEST PROGRAMMES OFFERED: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + max + " Programmes] " + "<" + facultySortedList.getEntry(maxIndex.getEntry(i)).getFacultyCode() + "> " + facultySortedList.getEntry(maxIndex.getEntry(i)).getFacultyName());
        }

        outputStr.add("\nLOWEST PROGRAMMES OFFERED: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + min + " Programmes] " + "<" + facultySortedList.getEntry(minIndex.getEntry(i)).getFacultyCode() + "> " + facultySortedList.getEntry(minIndex.getEntry(i)).getFacultyName());
        }

        outputStr.add("\n" + "-".repeat(150));

        max = 0;
        min = 999;
        maxIndex.clear();
        minIndex.clear();

        for (int i = 1; i <= facultyCourseCount.getNumberOfEntries(); i++) {
            if (facultyCourseCount.getEntry(i) != 0) {
                if (facultyCourseCount.getEntry(i) > max) {
                    max = facultyCourseCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (facultyCourseCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (facultyCourseCount.getEntry(i) < min) {
                    min = facultyCourseCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (facultyCourseCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        outputStr.add("HIGHEST COURSES OFFERED: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + max + " Courses] " + "<" + facultySortedList.getEntry(maxIndex.getEntry(i)).getFacultyCode() + "> " + facultySortedList.getEntry(maxIndex.getEntry(i)).getFacultyName());
        }

        outputStr.add("\nLOWEST COURSES OFFERED: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + min + " Courses] " + "<" + facultySortedList.getEntry(minIndex.getEntry(i)).getFacultyCode() + "> " + facultySortedList.getEntry(minIndex.getEntry(i)).getFacultyName());
        }

        outputStr.add("\n[NOTE: 0 COURSES IS NOT COUNTED]\n"
                + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF FACULTY SUMMARY REPORT\n"
                + "=".repeat(150));

        return outputStr.toString();
    }

    public String CourseReport() {
        sortCourse();
        ListInterface<String> outputStr = new ArrayList<>();
        ListInterface<String> TEMPCourse = new ArrayList<>();
        ListInterface<String> TEMPProgramme = new ArrayList<>();
        ListInterface<String> TEMPFaculty = new ArrayList<>();
        ListInterface<Integer> courseProgrammeCount = new ArrayList<>();
        ListInterface<Integer> courseFacultyCount = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();
        int max = 0;
        int min = 999;

        for (int i = 1; i <= courseSortedList.getNumberOfEntries(); i++) {
            TEMPCourse.add(courseSortedList.getEntry(i).getCourseCode());
        }

        for (int i = 1; i <= TEMPCourse.getNumberOfEntries(); i++) {

            for (int j = 1; j <= CourseProgrammeList.getNumberOfEntries(); j++) {
                if (TEMPCourse.getEntry(i).equals(CourseProgrammeList.getEntry(j).getCourseCode())) {
                    TEMPProgramme.add(CourseProgrammeList.getEntry(j).getProgrammeCode());
                }
            }

            courseProgrammeCount.add(TEMPProgramme.getNumberOfEntries());

            for (int j = 1; j <= TEMPProgramme.getNumberOfEntries(); j++) {
                for (int k = 1; k <= ProgrammeList.getNumberOfEntries(); k++) {
                    if (TEMPProgramme.getEntry(j).equals(ProgrammeList.getEntry(k).getProgrammeCode())) {
                        TEMPFaculty.add(ProgrammeList.getEntry(k).getFacultyCode());
                    }
                }
            }

            for (int j = 1; j <= TEMPFaculty.getNumberOfEntries(); j++) {
                String CurrentFacultyCode = TEMPFaculty.getEntry(j);
                for (int k = j + 1; k <= TEMPFaculty.getNumberOfEntries(); k++) {
                    if (CurrentFacultyCode.equals(TEMPFaculty.getEntry(k))) {
                        TEMPFaculty.remove(k);
                        k--;
                    }
                }
            }

            courseFacultyCount.add(TEMPFaculty.getNumberOfEntries());
            TEMPProgramme.clear();
            TEMPFaculty.clear();
        }

        for (int i = 1; i <= courseSortedList.getNumberOfEntries(); i++) {
            if (i >= 10) {
                outputStr.add(i + ". " + courseSortedList.getEntry(i) + " ".repeat(15) + courseProgrammeCount.getEntry(i) + " ".repeat(17) + courseFacultyCount.getEntry(i) + "\n");
            } else {
                outputStr.add(" " + i + ". " + courseSortedList.getEntry(i) + " ".repeat(15) + courseProgrammeCount.getEntry(i) + " ".repeat(17) + courseFacultyCount.getEntry(i) + "\n");
            }
        }

        outputStr.add("-".repeat(150)
                + "\nTotal " + TEMPCourse.getNumberOfEntries() + " Courses");

        for (int i = 1; i <= courseProgrammeCount.getNumberOfEntries(); i++) {
            if (courseProgrammeCount.getEntry(i) != 0) {
                if (courseProgrammeCount.getEntry(i) > max) {
                    max = courseProgrammeCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (courseProgrammeCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (courseProgrammeCount.getEntry(i) < min) {
                    min = courseProgrammeCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (courseProgrammeCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        outputStr.add("-".repeat(150) + "\n" + "HIGHEST PROGRAMMES OFFERED: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + max + " Programmes] " + "<" + courseSortedList.getEntry(maxIndex.getEntry(i)).getCourseCode() + "> " + courseSortedList.getEntry(maxIndex.getEntry(i)).getCourseName());
        }

        outputStr.add("\nLOWEST PROGRAMMES OFFERED: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + min + " Programmes] " + "<" + courseSortedList.getEntry(minIndex.getEntry(i)).getCourseCode() + "> " + courseSortedList.getEntry(minIndex.getEntry(i)).getCourseName());
        }

        outputStr.add("\n[NOTE: 0 PROGRAMMES IS NOT COUNTED]" + "\n" + "-".repeat(150));

        max = 0;
        min = 999;
        maxIndex.clear();
        minIndex.clear();

        for (int i = 1; i <= courseFacultyCount.getNumberOfEntries(); i++) {
            if (courseFacultyCount.getEntry(i) != 0) {
                if (courseFacultyCount.getEntry(i) > max) {
                    max = courseFacultyCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (courseFacultyCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (courseFacultyCount.getEntry(i) < min) {
                    min = courseFacultyCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (courseFacultyCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        outputStr.add("HIGHEST FACULTIES OFFERED: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + max + " Faculties] " + "<" + courseSortedList.getEntry(maxIndex.getEntry(i)).getCourseCode() + "> " + courseSortedList.getEntry(maxIndex.getEntry(i)).getCourseName());
        }

        outputStr.add("\nLOWEST FACULTIES OFFERED: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + min + " Faculties] " + "<" + courseSortedList.getEntry(minIndex.getEntry(i)).getCourseCode() + "> " + courseSortedList.getEntry(minIndex.getEntry(i)).getCourseName());
        }

        outputStr.add("\n[NOTE: 0 FACULTIES IS NOT COUNTED]\n"
                + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF COURSE SUMMARY REPORT\n"
                + "=".repeat(150));

        return outputStr.toString();
    }

    public String ProgrammeReport() {
        sortProgramme();
        ListInterface<String> outputStr = new ArrayList<>();
        ListInterface<String> TEMPCourse = new ArrayList<>();
        ListInterface<String> TEMPProgramme = new ArrayList<>();
        ListInterface<String> TEMPFaculty = new ArrayList<>();
        ListInterface<Integer> programmeCourseCount = new ArrayList<>();
        ListInterface<Integer> programmeFacultyCount = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();
        int max = 0;
        int min = 999;

        for (int i = 1; i <= programmeSortedList.getNumberOfEntries(); i++) {
            TEMPProgramme.add(programmeSortedList.getEntry(i).getProgrammeCode());
        }

        for (int i = 1; i <= TEMPProgramme.getNumberOfEntries(); i++) {
            for (int j = 1; j <= CourseProgrammeList.getNumberOfEntries(); j++) {
                if (TEMPProgramme.getEntry(i).equals(CourseProgrammeList.getEntry(j).getProgrammeCode())) {
                    TEMPCourse.add(CourseProgrammeList.getEntry(j).getCourseCode());
                }
            }
            programmeCourseCount.add(TEMPCourse.getNumberOfEntries());
            TEMPCourse.clear();

            for (int j = 1; j <= ProgrammeList.getNumberOfEntries(); j++) {
                if (TEMPProgramme.getEntry(i).equals(ProgrammeList.getEntry(j).getProgrammeCode())) {
                    TEMPFaculty.add(ProgrammeList.getEntry(j).getFacultyCode());
                }
            }
            programmeFacultyCount.add(TEMPFaculty.getNumberOfEntries());
            TEMPFaculty.clear();
        }

        for (int i = 1; i <= programmeSortedList.getNumberOfEntries(); i++) {
            if (i >= 10) {
                outputStr.add(i + ". " + programmeSortedList.getEntry(i) + " ".repeat(10) + programmeCourseCount.getEntry(i) + " ".repeat(20) + programmeFacultyCount.getEntry(i) + "\n");
            } else {
                outputStr.add(" " + i + ". " + programmeSortedList.getEntry(i) + " ".repeat(10) + programmeCourseCount.getEntry(i) + " ".repeat(20) + programmeFacultyCount.getEntry(i) + "\n");
            }
        }

        outputStr.add("-".repeat(150)
                + "\nTotal " + TEMPProgramme.getNumberOfEntries() + " Programmes");

        for (int i = 1; i <= programmeCourseCount.getNumberOfEntries(); i++) {
            if (programmeCourseCount.getEntry(i) != 0) {
                if (programmeCourseCount.getEntry(i) > max) {
                    max = programmeCourseCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (programmeCourseCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (programmeCourseCount.getEntry(i) < min) {
                    min = programmeCourseCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (programmeCourseCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        outputStr.add("-".repeat(150) + "\n" + "HIGHEST COURSES OFFERED: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + max + " Courses] " + "<" + programmeSortedList.getEntry(maxIndex.getEntry(i)).getProgrammeCode() + "> " + programmeSortedList.getEntry(maxIndex.getEntry(i)).getProgrammeName());
        }

        outputStr.add("\nLOWEST COURSES OFFERED: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + min + " Courses] " + "<" + programmeSortedList.getEntry(minIndex.getEntry(i)).getProgrammeCode() + "> " + programmeSortedList.getEntry(minIndex.getEntry(i)).getProgrammeName());
        }

        outputStr.add("\n[NOTE: 0 COURSES IS NOT COUNTED]" + "\n" + "-".repeat(150));

        max = 0;
        min = 999;
        maxIndex.clear();
        minIndex.clear();

        for (int i = 1; i <= programmeFacultyCount.getNumberOfEntries(); i++) {
            if (programmeFacultyCount.getEntry(i) != 0) {
                if (programmeFacultyCount.getEntry(i) > max) {
                    max = programmeFacultyCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (programmeFacultyCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (programmeFacultyCount.getEntry(i) < min) {
                    min = programmeFacultyCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (programmeFacultyCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        outputStr.add("HIGHEST FACULTIES OFFERED: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + max + " Faculties] " + "<" + programmeSortedList.getEntry(maxIndex.getEntry(i)).getProgrammeCode() + "> " + programmeSortedList.getEntry(maxIndex.getEntry(i)).getProgrammeName());
        }

        outputStr.add("\nLOWEST FACULTIES OFFERED: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            outputStr.add("-> [" + min + " Faculties] " + "<" + programmeSortedList.getEntry(minIndex.getEntry(i)).getProgrammeCode() + "> " + programmeSortedList.getEntry(minIndex.getEntry(i)).getProgrammeName());
        }

        outputStr.add("\n[NOTE: 0 FACULTIES IS NOT COUNTED]\n"
                + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF PROGRAMME SUMMARY REPORT\n"
                + "=".repeat(150));

        return outputStr.toString();
    }

    public void sortFaculty() {
        facultySortedList.clear();
        for (int i = 1; i <= FacultyList.getNumberOfEntries(); i++) {
            facultySortedList.add(FacultyList.getEntry(i));
        }
        String criteria = null;
        String sortOrder = null;
        int sortType;
        int sortDetail = CourseManagementUI.getFacultySortDetailsChoice();
        switch (sortDetail) {
            case 0:
                MessageUI.displayExitMessage();
                break;
            case 1:
                criteria = "FacultyCode";
                sortType = CourseManagementUI.inputSortType();
                switch (sortType) {
                    case 1:
                        sortOrder = "Ascending";
                        facultySortedList.sortByAscending(faculty -> faculty.getFacultyCode());
                        break;
                    case 2:
                        sortOrder = "Descending";
                        facultySortedList.sortByDescending(faculty -> faculty.getFacultyCode());
                        break;
                }
                break;
            case 2:
                criteria = "Faculty Name";
                sortType = CourseManagementUI.inputSortType();
                switch (sortType) {
                    case 1:
                        sortOrder = "Ascending";
                        facultySortedList.sortByAscending(faculty -> faculty.getFacultyName());
                        break;
                    case 2:
                        sortOrder = "Descending";
                        facultySortedList.sortByDescending(faculty -> faculty.getFacultyName());
                        break;
                }
                break;

            default:
                MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void sortCourse() {
        courseSortedList.clear();
        for (int i = 1; i <= CourseList.getNumberOfEntries(); i++) {
            courseSortedList.add(CourseList.getEntry(i));
        }
        String criteria = null;
        String sortOrder = null;
        int sortType;
        int sortDetail = CourseManagementUI.getCourseSortDetailsChoice();
        switch (sortDetail) {
            case 0:
                MessageUI.displayExitMessage();
                break;
            case 1:
                criteria = "CourseCode";
                sortType = CourseManagementUI.inputSortType();
                switch (sortType) {
                    case 1:
                        sortOrder = "Ascending";
                        courseSortedList.sortByAscending(course -> course.getCourseCode());
                        break;
                    case 2:
                        sortOrder = "Descending";
                        courseSortedList.sortByDescending(course -> course.getCourseCode());
                        break;
                }
                break;
            case 2:
                criteria = "Course Name";
                sortType = CourseManagementUI.inputSortType();
                switch (sortType) {
                    case 1:
                        sortOrder = "Ascending";
                        courseSortedList.sortByAscending(course -> course.getCourseName());
                        break;
                    case 2:
                        sortOrder = "Descending";
                        courseSortedList.sortByDescending(course -> course.getCourseName());
                        break;
                }
                break;

            default:
                MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void sortProgramme() {
        programmeSortedList.clear();
        for (int i = 1; i <= ProgrammeList.getNumberOfEntries(); i++) {
            programmeSortedList.add(ProgrammeList.getEntry(i));
        }
        String criteria = null;
        String sortOrder = null;
        int sortType;
        int sortDetail = CourseManagementUI.getProgrammeSortDetailsChoice();
        switch (sortDetail) {
            case 0:
                MessageUI.displayExitMessage();
                break;
            case 1:
                criteria = "ProgrammeCode";
                sortType = CourseManagementUI.inputSortType();
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
                sortType = CourseManagementUI.inputSortType();
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

    public void displayAllCourse(String academicYear) {
        CourseManagementUI.listAllCourse(getAllCourse(academicYear));
    }

    public void displayAllProgramme(String programmeCode) {
        CourseManagementUI.listAllProgramme(getAllProgramme(programmeCode));
    }

    public void displayCourseForProgramme(String programmeCode) {
        CourseManagementUI.listAllCourse(getCourseForProgramme(programmeCode));
    }

    public void displayAllFaculty(String FacultyCode) {
        CourseManagementUI.listAllFaculty(getAllFaculty(FacultyCode));
    }

    public void displayCourseForFaculty(String FacultyCode) {
        CourseManagementUI.listAllCourse(getCourseForFaculty(FacultyCode));
    }

    public void displayFacultyReport() {
        CourseManagementUI.FacultyReport(FacultyReport());
    }

    public void displayCourseReport() {
        CourseManagementUI.CourseReport(CourseReport());
    }

    public void displayProgrammeReport() {
        CourseManagementUI.ProgrammeReport(ProgrammeReport());
    }

}
