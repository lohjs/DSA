/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package utility;

import adt.ArrayList;
import adt.ListInterface;
import boundary.CourseManagementUI;
import dao.CourseDAO;
import dao.CourseInitializer;
import dao.FacultyDAO;
import dao.FacultyInitializer;
import dao.ProgrammeDAO;
import dao.ProgrammeInitializer;
import dao.courseProgrammeDAO;
import dao.courseProgrammeInitializer;
import entity.Course;
import entity.Faculty;
import entity.Programme;
import entity.courseProgramme;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jorda
 */
public class Assignment {

    private static ListInterface<Course> CourseList = new ArrayList<>();
    private static ListInterface<Programme> ProgrammeList = new ArrayList<>();
    private static ListInterface<courseProgramme> CourseProgrammeList = new ArrayList<>();
    private static ListInterface<Faculty> FacultyList = new ArrayList<>();
    private static CourseDAO CourseDAO = new CourseDAO();
    private static CourseInitializer CourseInitializer = new CourseInitializer();
    private static ProgrammeDAO ProgrammeDAO = new ProgrammeDAO();
    private static ProgrammeInitializer ProgrammeInitializer = new ProgrammeInitializer();
    private static courseProgrammeDAO CourseProgrammeDAO = new courseProgrammeDAO();
    private static courseProgrammeInitializer CourseProgrammeInitializer = new courseProgrammeInitializer();
    private static FacultyDAO FacultyDAO = new FacultyDAO();
    private static FacultyInitializer FacultyInitializer = new FacultyInitializer();
    private static CourseManagementUI CourseManagementUI = new CourseManagementUI();
    Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CourseList = CourseDAO.retrieveFromFile();
        ProgrammeList = ProgrammeDAO.retrieveFromFile();
        CourseProgrammeList = CourseProgrammeDAO.retrieveFromFile();
        FacultyList = FacultyDAO.retrieveFromFile();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd-MM-yyyy HH:mma");
        String formattedDateTime = currentDateTime.format(formatter);
        String outputStr = "";

    }
}
