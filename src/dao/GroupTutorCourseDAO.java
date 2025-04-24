/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.GroupTutorCourse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author hgtan
 */
public class GroupTutorCourseDAO {
    private String fileName = "groupTutorCourse.dat";
    
    public void saveToFile(ListInterface<GroupTutorCourse> groupTutorCourseList) {
        File file = new File(fileName);
        try {
          ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
          ooStream.writeObject(groupTutorCourseList);
          ooStream.close();
        } catch (FileNotFoundException ex) {
          System.out.println("\nFile not found");
        } catch (IOException ex) {
          System.out.println("\nCannot save to file");
        }
    }
    
    public ListInterface<GroupTutorCourse> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<GroupTutorCourse> groupTutorCourseList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            groupTutorCourseList = (ArrayList<GroupTutorCourse>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return groupTutorCourseList;
        }
    }
}
