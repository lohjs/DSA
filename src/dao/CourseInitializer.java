/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Course;

/**
 *
 * @author Admin
 */
public class CourseInitializer {

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Course> initializeCourses() {
        ListInterface<Course> cList = new ArrayList<>();
        cList.add(new Course("BACS1053", "Database Management", 3.0, "202401"));
        cList.add(new Course("BACS2026", "Data Structure And Algorithms", 4.0, "202401"));
        cList.add(new Course("BAIT1023", "Web Design And Development", 2.0, "202401"));
        cList.add(new Course("BAIT1043", "System Analysis And Design", 3.0, "202401"));

        cList.add(new Course("BABS1233", "Microbiology", 2.0, "202401"));
        cList.add(new Course("BACH2233", "Food Chemistry And Analysis", 4.0, "202401"));
        cList.add(new Course("BACH1613", "Physical Chemistry", 3.0, "202401"));

        cList.add(new Course("BTME4263", "Heat Transfer", 3.0, "202401"));
        cList.add(new Course("BGEE2614", "Electrical Power Systems", 2.0, "202401"));
        cList.add(new Course("BTEE1513", "Electrical Technology", 4.0, "202401"));
        cList.add(new Course("BTEE4033", "Integrated Circuit Technology", 3.0, "202401"));

        cList.add(new Course("BJEL1023", "Academic English", 3.0, "202401"));
        cList.add(new Course("BJEL1013", "English For Tertiary Studies", 3.0, "202401"));
        cList.add(new Course("BAMS1413", "Calculus And Algebra", 3.0, "202401"));
        cList.add(new Course("BAMS2014", "Linear Algebra", 3.0, "202401"));

        return cList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        CourseInitializer c = new CourseInitializer();
        ListInterface<Course> course = c.initializeCourses();
        CourseDAO CourseDAO = new CourseDAO();
        CourseDAO.saveToFile(course);
        System.out.println("\nCourses:\n" + course);
    }
}
