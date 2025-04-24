package control;

import adt.*;
import boundary.StudentManagementUI;
import boundary.CourseManagementUI;
import dao.*;
import entity.Student;
import entity.Enrollment;
import entity.Course;
import entity.courseProgramme;
import entity.Programme;
import utility.MessageUI;
import java.util.Scanner;
import java.util.function.Function;

/**
 *
 * @author Lock Kwan
 */
public class StudentManagement {

    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<Enrollment> enrollmentList = new ArrayList<>();
    private ListInterface<Course> courseList = new ArrayList<>();
    private ListInterface<Student> sortedList = new ArrayList();
    private ListInterface<courseProgramme> courseProgrammeList = new ArrayList<>();
    private ListInterface<Programme> programmeList = new ArrayList<>();
    private StudentDAO StudentDAO = new StudentDAO();
    private EnrollmentDAO EnrollmentDAO = new EnrollmentDAO();
    private StudentInitializer StudentInitializer = new StudentInitializer();
    private EnrollmentInitializer EnrollmentInitializer = new EnrollmentInitializer();
    private StudentManagementUI studentUI = new StudentManagementUI();
    private CourseManagementUI courseUI = new CourseManagementUI();
    private CourseDAO CourseDAO = new CourseDAO();
    private CourseInitializer CourseInitializer = new CourseInitializer();
    private courseProgrammeInitializer courseProgrammeInitializer = new courseProgrammeInitializer();
    private courseProgrammeDAO courseProgrammeDAO = new courseProgrammeDAO();
    private ProgrammeInitializer ProgrammeInitializer = new ProgrammeInitializer();
    private ProgrammeDAO ProgrammeDAO = new ProgrammeDAO();
    private ListInterface<Student> studentFilteredList = new ArrayList<>();
    private ListInterface<Student> studentSortedList = new ArrayList<>();
    static final double PRICEPERCREDITHOUR = 200.00;

    Scanner scanner = new Scanner(System.in);

    public StudentManagement() {
        studentList = StudentDAO.retrieveFromFile();
        enrollmentList = EnrollmentDAO.retrieveFromFile();
        courseList = CourseDAO.retrieveFromFile();
        courseProgrammeList = courseProgrammeDAO.retrieveFromFile();
        programmeList = ProgrammeDAO.retrieveFromFile();
        /*
        studentList = StudentInitializer.initializeStudents();
        StudentDAO.saveToFile(studentList);
        enrollmentList = EnrollmentInitializer.initializeEnrollment();
        EnrollmentDAO.saveToFile(enrollmentList);
        courseList = CourseInitializer.initializeCourses();
        CourseDAO.saveToFile(courseList);
        courseProgrammeList = courseProgrammeInitializer.initializeCourseProgramme();
        courseProgrammeDAO.saveToFile(courseProgrammeList);
        ProgrammeDAO.saveToFile(programmeList);
        */
    }

    public void runStudentManagement() {
        int choice = 0;
        do {
            choice = studentUI.getMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    displayStudentList();
                    break;
                case 2:
                    addNewStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    editStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    addStudentToCourse();
                    break;
                case 7:
                    removeStudentFromCourse();
                    break;
                case 8:
                    calculateCourseFee();
                    break;
                case 9:
                    filterStudents();
                    break;
                case 10:
                    sortStudents();
                    break;
                case 11:
                    displayReport();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    //Display Student List
    public void displayStudentList() {
        displayStudents();
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    //Add new student
    public void addNewStudent() {
        String newStudentAgain = "Yes";
        do {
            boolean check;
            int nextStudentIDNum = getStudentIDNum() + 1;
            String newStduentID = "S" + nextStudentIDNum ;
            String newStudentName = studentUI.inputNewStudentName();
            int newStudentAge = studentUI.inputNewStudentAge();
            String newStudentGender = studentUI.inputNewStudentGender();
            String newStudentPhoneNo = studentUI.inputNewStudentPhoneNo();
            String newStudentEmail = studentUI.inputNewStudentEmail();
            displayProgramme();
            String newStudentProgrammeCode;
            do {
                newStudentProgrammeCode = studentUI.inputNewStudentProgrammeCode();
                if (!findProgramme(newStudentProgrammeCode)) {
                    System.out.println("Invalid Programme Code! Please input a valid Programme Code.\n");
                    check = false;
                } else {
                    check = true;
                }
            } while (!check);
            Student newStudent = (Student) studentUI.inputNewStudentDetails(newStduentID, newStudentName, newStudentAge, newStudentGender, newStudentPhoneNo, newStudentEmail, newStudentProgrammeCode);
            studentList.add(newStudent);
            StudentDAO.saveToFile(studentList);
            newStudentAgain = studentUI.inputNewStudentAgain();
        } while ("Yes".equals(newStudentAgain));
    }

    //Remove Student 
    public void removeStudent() {
        displayStudents();
        if (studentList.isEmpty()) {
            System.out.println("Student list is empty.");
        } else {
            String removeStudentID = studentUI.inputRemoveStudentID();
            int indexToRemove = -1;
            for (int i = 1; i < studentList.getNumberOfEntries() + 1; i++) {
                Student student = studentList.getEntry(i); // Retrieve Student object at index i
                if (student.getStudentID().equals(removeStudentID)) {
                    indexToRemove = i;
                    break; // Once found, exit the loop
                }
            }
            if (indexToRemove != -1) {
                studentList.remove(indexToRemove);
                StudentDAO.saveToFile(studentList);
                System.out.println("\nStudent with Student ID: " + removeStudentID + " removed successfully.");
            } else {
                System.out.println("\nStudent with Student ID: " + removeStudentID + " not found in the list.");
            }
            System.out.print("\nPress enter to continue...");
            scanner.nextLine();
        }
    }

    //Edit Student Details
    public void editStudent() {
        displayStudents();
        boolean studentCheck = true;
        do {
            String editStudentID = studentUI.inputEditStudentID();

            int indexToEdit = -1;
            for (int i = 1; i < studentList.getNumberOfEntries() + 1; i++) {
                Student student = studentList.getEntry(i);
                if (student.getStudentID().equals(editStudentID)) {
                    indexToEdit = i;
                    studentCheck = true;
                    break;
                }
            }
            if (indexToEdit != -1) {
                int typeEditChoice = 0;
                Student editStudent = studentList.getEntry(indexToEdit);
                do {
                    typeEditChoice = studentUI.getTypeEditChoice();
                    switch (typeEditChoice) {
                        case 0:
                            MessageUI.displayExitMessage();
                            break;
                        case 1:
                            String oldStudentName = editStudent.getName();
                            System.out.println("Old Name: " + oldStudentName);
                            editStudent.setName(studentUI.inputNewName());
                            System.out.print("\nStudent's name modified successfully!\n");
                            break;
                        case 2:
                            int oldStudentAge = editStudent.getAge();
                            System.out.println("Old Age: " + oldStudentAge);
                            editStudent.setAge(studentUI.inputNewAge());
                            System.out.print("\nStudent's age modified successfully!\n");
                            break;

                        case 3:
                            String oldStudentGender = editStudent.getGender();
                            System.out.println("Old Gender: " + oldStudentGender);
                            editStudent.setGender(studentUI.inputNewGender());
                            System.out.print("\nStudent's gender modified successfully!\n");
                            break;
                        case 4:
                            String oldStudentPhoneNo = editStudent.getPhoneNo();
                            System.out.println("Old Phone Number: " + oldStudentPhoneNo);
                            editStudent.setPhoneNo(studentUI.inputNewPhoneNo());
                            System.out.print("\nStudent's hone number modified successfully!\n");
                            break;

                        case 5:
                            String oldStudentEmail = editStudent.getEmail();
                            System.out.println("Old Email: " + oldStudentEmail);
                            editStudent.setEmail(studentUI.inputNewEmail());
                            System.out.print("\nStudent's email modified successfully!\n");
                            break;

                        case 6:
                            displayProgramme();
                            String StudentProgrammeCode;
                            boolean check;
                            do {
                                String oldStudentProgrammeCode = editStudent.getProgrammeCode();
                                System.out.println("Old ProgrammeCode: " + oldStudentProgrammeCode);
                                StudentProgrammeCode = studentUI.inputNewProgrammeCode();
                                if (!findProgramme(StudentProgrammeCode)) {
                                    System.out.println("Invalid Programme Code! Please input a valid Programme Code.\n");
                                    check = false;
                                } else {
                                    check = true;
                                }
                            } while (!check);
                            editStudent.setProgrammeCode(StudentProgrammeCode);
                            System.out.print("\nStudent's programe ID modified successfully!\n");
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                } while (typeEditChoice != 0);
                studentList.replace(indexToEdit, editStudent);
                StudentDAO.saveToFile(studentList);
            } else {
                studentCheck = false;
                System.out.print("Student with Student ID: " + editStudentID + " is not found in the list. Please type a valid Student ID.\n");
            }
        } while (!studentCheck);
    }

    //Search Student for registered course
    public void searchStudent() {

        String searchStudentAgain = "Yes";

        do {
            String studentID;
            String courseCode;
            boolean check;
            displayStudents();
            do {
                studentID = studentUI.inputStudentID();
                if (!findStudent(studentID)) {
                    System.out.println("Invalid Student ID! Please input a valid Student ID.\n");
                    check = false;
                } else {
                    check = true;
                }
            } while (!check);
            displayCourses();
            do {
                courseCode = studentUI.inputCourseCode();
                if (!findCourse(courseCode)) {
                    System.out.println("Invalid Course Code! Please input a valid Course Code.\n");
                    check = false;
                } else {
                    check = true;
                }
            } while (!check);

            boolean enrollmentFound = false;
            String enrollmentStatus = null;

            for (int i = 1; i <= enrollmentList.getNumberOfEntries(); i++) {
                Enrollment enrollment = enrollmentList.getEntry(i); // Retrieve Enrollment object at index i
                if (enrollment.getStudentID().equals(studentID) && enrollment.getCourseCode().equals(courseCode)) {
                    enrollmentFound = true;
                    enrollmentStatus = enrollment.getEnrollmentStatus();
                    break;
                }
            }

            if (!enrollmentFound) {
                System.out.println("\nStudent with Student ID: " + studentID + " is not enrolled in " + courseCode);
            } else {
                System.out.println("\nStudent with Student ID: " + studentID + " is enrolled in " + courseCode
                        + " as " + enrollmentStatus);
            }
            searchStudentAgain = studentUI.inputSearchStudentAgain();
        } while ("Yes".equalsIgnoreCase(searchStudentAgain));

    }

    //Add student to the course
    public void addStudentToCourse() {
        String newAddStudentToCourseAgain = "Yes"; //if course added the programme id is not same as student's programmeID, then error. And check got same or not
        do {
            boolean check = false;
            String studentID;
            String courseCode;
            Student student = null;
            courseProgramme courseProgramme;
            boolean programmeCheck = true;
            displayStudents();
            do {
                studentID = studentUI.inputStudentID();
                if (!findStudent(studentID)) {
                    System.out.println("Invalid Student ID! Please input a valid Student ID.\n");
                    check = false;
                } else {
                    check = true;
                }
            } while (!check);
            displayCourses();
            do {
                courseCode = studentUI.inputCourseCode();
                if (!findCourse(courseCode)) {
                    System.out.println("Invalid Course Code! Please input a valid Course Code.\n");
                    check = false;
                } else {
                    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                        student = studentList.getEntry(i); // Retrieve Student object at index i
                        if (student.getStudentID().equals(studentID)) {
                            break; // Once found, exit the loop
                        }
                    }
                    for (int i = 1; i <= courseProgrammeList.getNumberOfEntries(); i++) {
                        courseProgramme = courseProgrammeList.getEntry(i); // Retrieve Student object at index i
                        if (courseProgramme.getCourseCode().equals(courseCode)) {
                            if (courseProgramme.getProgrammeCode().equals(student.getProgrammeCode())) {
                                programmeCheck = true;
                                check = true;
                                break; // Once found, exit the loop
                            } else {
                                programmeCheck = false;
                                check = false;
                            }
                        }
                    }
                    if (!programmeCheck) {
                        System.out.println("This student is ineligible for course enrollment due to a program code mismatch. "
                                + "Please provide a valid Course Code.");
                    }
                }
            } while (!check);

            String enrollmentStatus = studentUI.inputEnrollmentStatus();
            for (int i = 1; i <= enrollmentList.getNumberOfEntries(); i++) {
                Enrollment enrollment = enrollmentList.getEntry(i); // Retrieve Enrollment object at index i
                if (enrollment.getStudentID().equals(studentID) && enrollment.getCourseCode().equals(courseCode)) {
                    System.out.println("\nStudent with Student ID: " + studentID + " ALREADY enrolled in " + courseCode
                            + " as " + enrollmentStatus);
                    enrollmentStatus = enrollment.getEnrollmentStatus();
                } else {
                    Enrollment newStudentToCourse = (Enrollment) new Enrollment(studentID, courseCode, enrollmentStatus);
                    enrollmentList.add(newStudentToCourse);
                    EnrollmentDAO.saveToFile(enrollmentList);
                    System.out.print("\nStudent has been added to a new course successfully!\n");
                    newAddStudentToCourseAgain = studentUI.inputAddStudentToCourseAgain();
                    break;
                }
            }
        } while ("Yes".equals(newAddStudentToCourseAgain));
    }

    //Remove student from the courses
    public void removeStudentFromCourse() {
        String enrollmentStudentID;
        String enrollmentCourseCode;
        boolean check;
        displayStudents();
        do {
            enrollmentStudentID = studentUI.inputStudentID();
            if (!findStudent(enrollmentStudentID)) {
                System.out.println("Invalid Student ID! Please input a valid Student ID.\n");
                check = false;
            } else {
                check = true;
            }
        } while (!check);
        displayCourses();
        do {
            enrollmentCourseCode = studentUI.inputRemoveEnrollmentCourseCode();
            if (!findCourse(enrollmentCourseCode)) {
                System.out.println("Invalid Course Code! Please input a valid Course Code.\n");
                check = false;
            } else {
                check = true;
            }
        } while (!check);
        String enrollmentStatus = studentUI.inputRemoveEnrollmentStatus();
        int indexToRemove = -1;
        for (int i = 1; i < enrollmentList.getNumberOfEntries() + 1; i++) {
            Enrollment enrollment = enrollmentList.getEntry(i); // Retrieve Student object at index i
            if (enrollment.getStudentID().equals(enrollmentStudentID) && enrollment.getCourseCode().equals(enrollmentCourseCode) && enrollment.getEnrollmentStatus().equals(enrollmentStatus)) {
                indexToRemove = i;
                break; // Once found, exit the loop
            }
        }
        if (indexToRemove != -1) {
            enrollmentList.remove(indexToRemove);
            EnrollmentDAO.saveToFile(enrollmentList);
            System.out.println("\nStudent with Student ID: " + enrollmentStudentID + " removed successfully from "
                    + enrollmentCourseCode + " as " + enrollmentStatus);
        } else {
            System.out.println("\nThe student could not be removed from enrollment. No enrollment found matching the provided student ID, course ID, and enrollment status.");
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    //Filter Student
    public void filterStudents() {
        int criteriaType = 0;
        do {
            String criteria = null;
            studentFilteredList.clear();
            criteriaType = studentUI.inputCriteriaType();
            switch (criteriaType) {
                case 0:
                    break;
                case 1:
                    //Filter Students by Age
                    criteria = "Age";
                    int minAge = studentUI.inputMinAge();
                    int maxAge = studentUI.inputMaxAge();
                    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                        Student student = studentList.getEntry(i);
                        if (student.getAge() <= maxAge && student.getAge() >= minAge) {
                            studentFilteredList.add(student);
                        }
                    }
                    break;
                case 2:
                    //Filter Students by Gender
                    criteria = "Gender";
                    String gender = studentUI.inputGender();
                    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                        Student student = studentList.getEntry(i);
                        if (student.getGender().equals(gender)) {
                            studentFilteredList.add(student);
                        }
                    }
                    break;
                case 3:
                    //Filter Students by ProgrammCode
                    boolean check;
                    displayProgramme();
                    criteria = "Programme Code";
                    String programmeCode;
                    do {
                        programmeCode = studentUI.inputProgrammeCode();
                        if (!findProgramme(programmeCode)) {
                            System.out.println("Invalid Programme Code! Please input a valid Programme Code.\n");
                            check = false;
                        } else {
                            check = true;
                        }
                    } while (!check);
                    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                        Student student = studentList.getEntry(i);
                        if (student.getProgrammeCode().equals(programmeCode)) {
                            studentFilteredList.add(student);
                        }
                    }
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }

            if (studentFilteredList.isEmpty()) {
                if (criteria != null) {
                    System.out.println("Unfortunately there are no students who meet your specified criteria.");
                } else {
                    System.out.println();
                }
            } else {
                System.out.println("\nList of Filtered Students based on " + criteria + ":\n");
                displayFilteredStudents();
            }

            System.out.print("\nPress enter to continue...");
            scanner.nextLine();
        } while (criteriaType != 0);
    }

    //Sort Student
    public void sortStudents() {
        int sortType;
        int sortDetail = 0;
        do {
            String criteria = null;
            String sortOrder = null;
            sortDetail = studentUI.getSortDetailsChoice();
            studentSortedList.clear();
            for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                studentSortedList.add(studentList.getEntry(i));
            }
            switch (sortDetail) {
                case 0:
                    break;
                case 1:
                    //Sort Students by StudentID
                    criteria = "Student ID";
                    sortType = studentUI.inputSortType();
                    switch (sortType) {
                        case 1:
                            sortOrder = "Ascending";
                            studentSortedList.sortByAscending(student -> student.getStudentID());
                            break;
                        case 2:
                            sortOrder = "Descending";
                            studentSortedList.sortByDescending(student -> student.getStudentID());
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                    break;
                case 2:
                    //Sort Students by Name
                    criteria = "Name";
                    sortType = studentUI.inputSortType();
                    switch (sortType) {
                        case 1:
                            sortOrder = "Ascending";
                            studentSortedList.sortByAscending(student -> student.getName());
                            break;
                        case 2:
                            sortOrder = "Descending";
                            studentSortedList.sortByDescending(student -> student.getName());
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                    break;
                case 3:
                    //Sort Students by Age
                    criteria = "Age";
                    sortType = studentUI.inputSortType();
                    switch (sortType) {
                        case 1:
                            sortOrder = "Ascending";
                            studentSortedList.sortByAscending(student -> student.getAge());
                            break;
                        case 2:
                            sortOrder = "Descending";
                            studentSortedList.sortByDescending(student -> student.getAge());
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                    break;
                case 4:
                    //Sort Students by Gender
                    criteria = "Gender";
                    sortType = studentUI.inputSortType();
                    switch (sortType) {
                        case 1:
                            sortOrder = "Ascending";
                            studentSortedList.sortByAscending(student -> student.getGender());
                            break;
                        case 2:
                            sortOrder = "Descending";
                            studentSortedList.sortByDescending(student -> student.getGender());
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                    break;
                case 5:
                    //Sort Students by Phone Number
                    criteria = "Phone Number";
                    sortType = studentUI.inputSortType();
                    switch (sortType) {
                        case 1:
                            sortOrder = "Ascending";
                            studentSortedList.sortByAscending(student -> student.getPhoneNo());
                            break;
                        case 2:
                            sortOrder = "Descending";
                            studentSortedList.sortByDescending(student -> student.getPhoneNo());
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                    break;
                case 6:
                    //Sort Students by Email
                    criteria = "Email";
                    sortType = studentUI.inputSortType();
                    switch (sortType) {
                        case 1:
                            sortOrder = "Ascending";
                            studentSortedList.sortByAscending(student -> student.getEmail());
                            break;
                        case 2:
                            sortOrder = "Descending";
                            studentSortedList.sortByDescending(student -> student.getEmail());
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                    break;
                case 7:
                    //Sort Students by Age
                    criteria = "Programme";
                    sortType = studentUI.inputSortType();
                    switch (sortType) {
                        case 1:
                            sortOrder = "Ascending";
                            studentSortedList.sortByAscending(student -> student.getProgrammeCode());
                            break;
                        case 2:
                            sortOrder = "Descending";
                            studentSortedList.sortByDescending(student -> student.getProgrammeCode());
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                    break;

                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
            if (criteria != null && sortOrder != null) {
                System.out.println("\nList of Sorted Students based on " + criteria + " by " + sortOrder + " order:\n");
                displaySortedStudents();
            } else {
                System.out.println();
            }
            System.out.print("\nPress enter to continue...");
            scanner.nextLine();
        } while (sortDetail != 0);
    }

    //Calculate Course Fee of students
    public void calculateCourseFee() {
        do {
            ListInterface<String> studentCourseCodeList = new ArrayList<>();
            ListInterface<String> studentCourseNameList = new ArrayList<>();
            ListInterface<Double> courseAmountList = new ArrayList<>();
            ListInterface<String> courseStatusList = new ArrayList<>();
            Double total = 0.00;
            studentCourseCodeList.clear();
            studentCourseNameList.clear();
            courseStatusList.clear();
            courseAmountList.clear();
            displayStudents();
            String studentID = studentUI.inputStudentID();
            int index = -1;
            for (int i = 1; i < studentList.getNumberOfEntries() + 1; i++) {
                Student student = studentList.getEntry(i); // Retrieve Student object at index i
                if (student.getStudentID().equals(studentID)) {
                    index = i;
                    break; // Once found, exit the loop
                }
            }
            if (index != -1) {
                Student student = studentList.getEntry(index);
                String studentName = student.getName();
                String programmeName = student.getProgrammeCode();
                for (int i = 1; i <= enrollmentList.getNumberOfEntries(); i++) {
                    Enrollment enrollment = enrollmentList.getEntry(i); // Retrieve Student object at index i
                    if (enrollment.getStudentID().equals(studentID)) {
                        studentCourseCodeList.add(enrollment.getCourseCode());
                        courseStatusList.add(enrollment.getEnrollmentStatus());
                    }
                }
                for (int i = 1; i <= studentCourseCodeList.getNumberOfEntries(); i++) {
                    String courseCode = studentCourseCodeList.getEntry(i);
                    for (int j = 1; j <= courseList.getNumberOfEntries(); j++) {
                        Course course = courseList.getEntry(j);
                        if (course.getCourseCode().equals(courseCode)) {
                            studentCourseNameList.add(course.getCourseName());

                            double fee = PRICEPERCREDITHOUR * course.getCreditHour();
                            courseAmountList.add(fee);
                            total += fee;
                        }
                    }
                }

                //Display bill 
                System.out.println();
                System.out.print("Student Name: " + studentName);
                System.out.print("\nStudent ID: " + studentID);
                System.out.print("\nProgramme: " + programmeName);
                System.out.println();
                studentUI.StudentBill();
                for (int i = 1; i <= studentCourseCodeList.getNumberOfEntries(); i++) {
                    System.out.printf("%-8s %-38s %-14s %8.2f%n", studentCourseCodeList.getEntry(i),
                            studentCourseNameList.getEntry(i), courseStatusList.getEntry(i),
                            courseAmountList.getEntry(i));
                }
                System.out.println("---------------------------------------------------------------------------");
                System.out.printf("%62s %8.2f", "Total Amount: ", total);
                System.out.println("\n---------------------------------------------------------------------------\n");
            } else {
                System.out.println("\nStudent with Student ID: " + studentID + " not found in the list.");
            }
        } while ("Yes".equals(studentUI.inputCalculateCourseFeeAgain()));
    }

    //Summary Report
    public void displayReport() {
        int reportType = 0;
        do {
            reportType = studentUI.inputReportType();
            switch (reportType) {
                case 0:
                    break;
                case 1:
                    StudentReport();
                    break;
                case 2:
                    CourseReport();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (reportType != 0);
    }

    //Search for a student with the given student ID in the student list
    public boolean findStudent(String studentID) {
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            if (studentList.getEntry(i) != null) {
                if (studentList.getEntry(i).getStudentID().equals(studentID)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Search for a programme with the given programme code in the student list
    public boolean findProgramme(String programmmeCode) {
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            if (programmeList.getEntry(i) != null) {
                if (programmeList.getEntry(i).getProgrammeCode().equals(programmmeCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Determines whether a course with a specified code exists in the course list.
    public boolean findCourse(String courseCode) {
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            if (courseList.getEntry(i) != null) {
                if (courseList.getEntry(i).getCourseCode().equals(courseCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Retrieves all students from the student list and returns them as a formatted string.
    public String getAllStudents() {
        String outputStr = "";
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            outputStr += studentList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    //Displays the list of students
    public void displayStudents() {
        System.out.println("\nList of Students:\n");
        studentUI.listAllStudents(getAllStudents());
    }

    //Retrieves all courses from the course list and returns them as a formatted string.
    public String getAllCourses() {
        String outputStr = "";
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            outputStr += courseList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    //Retrieves all progrramme from the course list and returns them as a formatted string.
    public String getAllProgrammes() {
        String outputStr = "";
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            outputStr += programmeList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    //Displays the list of programme
    public void displayProgramme() {
        courseUI.listAllProgramme(getAllProgrammes());
    }

    //Displays the list of courses
    public void displayCourses() {
        courseUI.listAllCourse(getAllCourses());
    }

    //Retrieves filtered students from the student filtered list and returns them as a formatted string.
    public String getFilteredStudents() {
        String outputStr = "";
        for (int i = 1; i <= studentFilteredList.getNumberOfEntries(); i++) {
            outputStr += studentFilteredList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    //Displays the list of filtered students
    public void displayFilteredStudents() {
        studentUI.listAllStudents(getFilteredStudents());
    }

    //Retrieves sorted students from the student sorted list and returns them as a formatted string.
    public String getSortedStudents() {
        String outputStr = "";
        for (int i = 1; i <= studentSortedList.getNumberOfEntries(); i++) {
            outputStr += studentSortedList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    //Displays the list of sorted students
    public void displaySortedStudents() {
        studentUI.listAllStudents(getSortedStudents());
    }
    
    //Retrieve the last student's student id last 4 number
    public int getStudentIDNum(){
        int count = 999;
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++){
            count++;
        }
        return count;
    }
    
    //Student Summary Report
    public void StudentReport() {
        ListInterface<Student> StudentDetails = new ArrayList<>();
        ListInterface<String> TEMPCourseCode = new ArrayList<>();
        ListInterface<Integer> CourseCount = new ArrayList<>();
        ListInterface<Integer> CreditHourCount = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();
        int totalCreditHour = 0;
        int max = 0;
        int min = 999;

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            StudentDetails.add(studentList.getEntry(i));
        }

        StudentDetails.sortByAscending(student -> student.getStudentID());

        for (int i = 1; i <= StudentDetails.getNumberOfEntries(); i++) {
            for (int j = 1; j <= enrollmentList.getNumberOfEntries(); j++) {
                if (StudentDetails.getEntry(i).getStudentID().equals(enrollmentList.getEntry(j).getStudentID())) {
                    TEMPCourseCode.add(enrollmentList.getEntry(j).getCourseCode());
                    for (int k = 1; k <= courseList.getNumberOfEntries(); k++) {
                        if (enrollmentList.getEntry(j).getCourseCode().equals(courseList.getEntry(k).getCourseCode())) {
                            totalCreditHour += courseList.getEntry(k).getCreditHour();

                        }
                    }
                }
            }
            CreditHourCount.add(totalCreditHour);
            CourseCount.add(TEMPCourseCode.getNumberOfEntries());
            TEMPCourseCode.clear();
            totalCreditHour = 0;
        }

        studentUI.StudentReport();
        for (int i = 1; i <= StudentDetails.getNumberOfEntries(); i++) {
            System.out.printf("%2d. %-20s %-78s %7d %22d\n", i, StudentDetails.getEntry(i).getStudentID(),
                    StudentDetails.getEntry(i).getName(), CourseCount.getEntry(i), CreditHourCount.getEntry(i));
        }
        System.out.print("-".repeat(150));
        System.out.print("\nTotal " + StudentDetails.getNumberOfEntries() + " Students\n");

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

        System.out.print("-".repeat(150) + "\n" + "HIGHEST COURSES TAKEN: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Courses] " + "<" + StudentDetails.getEntry(maxIndex.getEntry(i)).getStudentID() + "> " + StudentDetails.getEntry(maxIndex.getEntry(i)).getName());
        }

        System.out.print("\n\nLOWEST COURSES TAKEN : ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Courses] " + "<" + StudentDetails.getEntry(minIndex.getEntry(i)).getStudentID() + "> " + StudentDetails.getEntry(minIndex.getEntry(i)).getName());
        }

        System.out.print("\n[NOTE: 0 COURSES IS NOT COUNTED]" + "\n" + "-".repeat(150));

        max = 0;
        min = 999;

        maxIndex.clear();

        minIndex.clear();

        for (int i = 1; i <= CreditHourCount.getNumberOfEntries(); i++) {
            if (CreditHourCount.getEntry(i) != 0) {
                if (CreditHourCount.getEntry(i) > max) {
                    max = CreditHourCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (CreditHourCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (CreditHourCount.getEntry(i) < min) {
                    min = CreditHourCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (CreditHourCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        System.out.print("\nHIGHEST CREDIT HOURS OBTAINED: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Credit Hours] " + "<" + StudentDetails.getEntry(maxIndex.getEntry(i)).getStudentID() + "> " + StudentDetails.getEntry(maxIndex.getEntry(i)).getName());
        }

        System.out.print("\n\nLOWEST CREDIT HOURS OBTAINED : ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Credit Hours] " + "<" + StudentDetails.getEntry(minIndex.getEntry(i)).getStudentID() + "> " + StudentDetails.getEntry(minIndex.getEntry(i)).getName());
        }

        System.out.print(
                "\n[NOTE: 0 CREDIT HOURS IS NOT COUNTED]\n"
                + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF STUDENT SUMMARY REPORT\n"
                + "=".repeat(150));

        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    //Student Course Report
    public void CourseReport() {
        ListInterface<Course> CourseDetails = new ArrayList<>();
        ListInterface<String> TEMPStudentID = new ArrayList<>();
        ListInterface<String> TEMPStatus = new ArrayList<>();
        ListInterface<Integer> StudentCount = new ArrayList<>();
        ListInterface<Integer> StatusMainCount = new ArrayList<>();
        ListInterface<Integer> StatusRepeatCount = new ArrayList<>();
        ListInterface<Integer> StatusResitCount = new ArrayList<>();
        ListInterface<Integer> StatusElectiveCount = new ArrayList<>();
        ListInterface<Integer> maxIndex = new ArrayList<>();
        ListInterface<Integer> minIndex = new ArrayList<>();
        int MainCount = 0;
        int RepeatCount = 0;
        int ResitCount = 0;
        int ElectiveCount = 0;
        int max = 0;
        int min = 999;

        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            CourseDetails.add(courseList.getEntry(i));
        }

        CourseDetails.sortByAscending(course -> course.getCourseCode());

        for (int i = 1; i <= CourseDetails.getNumberOfEntries(); i++) {
            for (int j = 1; j <= enrollmentList.getNumberOfEntries(); j++) {
                if (CourseDetails.getEntry(i).getCourseCode().equals(enrollmentList.getEntry(j).getCourseCode())) {
                    TEMPStudentID.add(enrollmentList.getEntry(j).getStudentID());
                    TEMPStatus.add(enrollmentList.getEntry(j).getEnrollmentStatus());
                }
            }
            for (int k = 1; k <= TEMPStatus.getNumberOfEntries(); k++) {
                if (TEMPStatus.getEntry(k).equals("Main")) {
                    MainCount += 1;
                } else if (TEMPStatus.getEntry(k).equals("Repeat")) {
                    RepeatCount += 1;
                } else if (TEMPStatus.getEntry(k).equals("Resit")) {
                    ResitCount += 1;
                } else if (TEMPStatus.getEntry(k).equals("Elective")) {
                    ElectiveCount += 1;
                }
            }

            StudentCount.add(TEMPStudentID.getNumberOfEntries());
            StatusMainCount.add(MainCount);
            StatusRepeatCount.add(RepeatCount);
            StatusResitCount.add(ResitCount);
            StatusElectiveCount.add(ElectiveCount);
            TEMPStudentID.clear();
            TEMPStatus.clear();
            MainCount = 0;
            RepeatCount = 0;
            ResitCount = 0;
            ElectiveCount = 0;
        }

        studentUI.CourseReport();

        for (int i = 1; i <= CourseDetails.getNumberOfEntries(); i++) {
            System.out.printf("%2d. %-15s %-53s %-19.1f %-17d Main:%-2d Resit:%-2d Repeat:%-2d Elective:%-2d\n", i, CourseDetails.getEntry(i).getCourseCode(),
                    CourseDetails.getEntry(i).getCourseName(), CourseDetails.getEntry(i).getCreditHour(),
                    StudentCount.getEntry(i), StatusMainCount.getEntry(i), StatusResitCount.getEntry(i),
                    StatusRepeatCount.getEntry(i), StatusElectiveCount.getEntry(i));
        }
        System.out.print("-".repeat(150));
        System.out.print("\nTotal " + CourseDetails.getNumberOfEntries() + " Courses\n");

        for (int i = 1; i <= StudentCount.getNumberOfEntries(); i++) {
            if (StudentCount.getEntry(i) != 0) {
                if (StudentCount.getEntry(i) > max) {
                    max = StudentCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (StudentCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (StudentCount.getEntry(i) < min) {
                    min = StudentCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (StudentCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        System.out.print("-".repeat(150) + "\n" + "TOP ENROLLED COURSE: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Students] " + "<" + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n\nLEAST ENROLLED COURSE : ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Students] " + "<" + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n[NOTE: 0 STUDENTS IS NOT COUNTED]" + "\n" + "-".repeat(150));

        max = 0;
        min = 999;

        maxIndex.clear();

        minIndex.clear();

        for (int i = 1; i <= StatusMainCount.getNumberOfEntries(); i++) {
            if (StatusMainCount.getEntry(i) != 0) {
                if (StatusMainCount.getEntry(i) > max) {
                    max = StatusMainCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (StatusMainCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (StatusMainCount.getEntry(i) < min) {
                    min = StatusMainCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (StatusMainCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        System.out.print("\nCOURSE WITH HIGHEST MAIN ENROLLMENT: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Students] " + "<" + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n\nCOURSE WITH LOWEST MAIN ENROLLMENT: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Students] " + "<" + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n[NOTE: 0 STUDENTS IS NOT COUNTED]" + "\n" + "-".repeat(150));

        max = 0;
        min = 999;

        maxIndex.clear();

        minIndex.clear();

        for (int i = 1; i <= StatusResitCount.getNumberOfEntries(); i++) {
            if (StatusResitCount.getEntry(i) != 0) {
                if (StatusResitCount.getEntry(i) > max) {
                    max = StatusResitCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (StatusResitCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (StatusResitCount.getEntry(i) < min) {
                    min = StatusResitCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (StatusResitCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        System.out.print("\nCOURSE WITH HIGHEST RESIT ENROLLMENT: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Students] " + "<" + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n\nCOURSE WITH LOWEST RESIT ENROLLMENT: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Students] " + "<" + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n[NOTE: 0 STUDENTS IS NOT COUNTED]" + "\n" + "-".repeat(150));

        max = 0;
        min = 999;

        maxIndex.clear();

        minIndex.clear();

        for (int i = 1; i <= StatusRepeatCount.getNumberOfEntries(); i++) {
            if (StatusRepeatCount.getEntry(i) != 0) {
                if (StatusRepeatCount.getEntry(i) > max) {
                    max = StatusRepeatCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (StatusRepeatCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (StatusRepeatCount.getEntry(i) < min) {
                    min = StatusRepeatCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (StatusRepeatCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        System.out.print("\nCOURSE WITH HIGHEST REPEAT ENROLLMENT: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Students] " + "<" + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n\nCOURSE WITH LOWEST REPEAT ENROLLMENT: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Students] " + "<" + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseName());
        }
        System.out.print("\n[NOTE: 0 STUDENTS IS NOT COUNTED]" + "\n" + "-".repeat(150));

        max = 0;
        min = 999;

        maxIndex.clear();

        minIndex.clear();

        for (int i = 1; i <= StatusElectiveCount.getNumberOfEntries(); i++) {
            if (StatusElectiveCount.getEntry(i) != 0) {
                if (StatusElectiveCount.getEntry(i) > max) {
                    max = StatusElectiveCount.getEntry(i);
                    maxIndex.clear();
                    maxIndex.add(i);
                } else if (StatusElectiveCount.getEntry(i) == max) {
                    maxIndex.add(i);
                }
                if (StatusElectiveCount.getEntry(i) < min) {
                    min = StatusElectiveCount.getEntry(i);
                    minIndex.clear();
                    minIndex.add(i);
                } else if (StatusElectiveCount.getEntry(i) == min) {
                    minIndex.add(i);
                }
            }
        }

        System.out.print("\nCOURSE WITH HIGHEST ELECTIVE ENROLLMENT: ");

        for (int i = 1; i <= maxIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + max + " Students] " + "<" + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(maxIndex.getEntry(i)).getCourseName());
        }

        System.out.print("\n\nCOURSE WITH LOWEST ELECTIVE ENROLLMENT: ");

        for (int i = 1; i <= minIndex.getNumberOfEntries(); i++) {
            System.out.print("\n-> [" + min + " Students] " + "<" + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseCode() + "> " + CourseDetails.getEntry(minIndex.getEntry(i)).getCourseName());
        }

        System.out.print(
                "\n[NOTE: 0 STUDENTS IS NOT COUNTED]\n"
                + "-".repeat(150) + "\n"
                + " ".repeat(60) + "END OF STUDENT SUMMARY REPORT\n"
                + "=".repeat(150));
        System.out.print("\nPress enter to continue...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        StudentManagement studentManagement = new StudentManagement();
        studentManagement.runStudentManagement();
    }
}
