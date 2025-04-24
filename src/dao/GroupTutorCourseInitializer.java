/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.GroupTutorCourse;

/**
 *
 * @author hgtan
 */
public class GroupTutorCourseInitializer {
    public ListInterface<GroupTutorCourse> initializeGroupTutorCourse() {
        ListInterface<GroupTutorCourse> gtcList = new ArrayList<>();
        gtcList.add(new GroupTutorCourse("RDS0001", "T1000", "BACS1053"));
        gtcList.add(new GroupTutorCourse("RDS0003", "T1000", "BACS1053"));
        gtcList.add(new GroupTutorCourse("RSW0001", "T1001", "BACS2026"));
        gtcList.add(new GroupTutorCourse("RSD0001", "T1003", "BAIT1023"));

        return gtcList;
    }
    
    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        GroupTutorCourseInitializer gtc = new GroupTutorCourseInitializer();
        ListInterface<GroupTutorCourse> groupTutorCourseList = gtc.initializeGroupTutorCourse();
        GroupTutorCourseDAO groupTutorCourseDAO = new GroupTutorCourseDAO(); 
        groupTutorCourseDAO.saveToFile(groupTutorCourseList); 

        System.out.println("\nTutorial group tutors:\n" + groupTutorCourseList);
    }
}
