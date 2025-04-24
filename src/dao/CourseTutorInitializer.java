/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.CourseTutor;

/**
 *
 * @author hgtan
 */
public class CourseTutorInitializer {
    public ListInterface<CourseTutor> initializeCourseTutors() {
        ListInterface<CourseTutor> ctList = new ArrayList<>();
        char[] type1 = {'L', 'P'};
        ctList.add(new CourseTutor("T1000", "BACS1053", type1));
        char[] type2 = {'L', 'P'};
        ctList.add(new CourseTutor("T1001", "BACS2026", type2));
        char[] type3 = {'P'};
        ctList.add(new CourseTutor("T1003", "BAIT1023", type3));
        char[] type5 = {'L', 'T'};
        ctList.add(new CourseTutor("T1015", "BAMS1413", type5));
        
        return ctList;
    }
    
    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        CourseTutorInitializer ct = new CourseTutorInitializer();
        ListInterface<CourseTutor> courseTutorList = ct.initializeCourseTutors();
        CourseTutorDAO courseTutorDAO = new CourseTutorDAO(); 
        courseTutorDAO.saveToFile(courseTutorList); 

        System.out.println("\nCourse tutors:\n" + courseTutorList);
    }
}
