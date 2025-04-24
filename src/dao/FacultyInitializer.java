/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.Faculty;

/**
 *
 * @author jia shou
 */
public class FacultyInitializer {

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Faculty> initializeFacultys() {
        ListInterface<Faculty> fList = new ArrayList<>();
        fList.add(new Faculty("FOET", "Faculty of Engineering and Technology"));
        fList.add(new Faculty("FOCS", "Faculty of Computing and Information Technology"));
        fList.add(new Faculty("FOAS", "Faculty of Applied Sciences"));
        
        return fList;
    }
    
    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        FacultyInitializer f = new FacultyInitializer();
        ListInterface<Faculty> faculty = f.initializeFacultys();
        FacultyDAO FacultyDAO = new FacultyDAO();
        FacultyDAO.saveToFile(faculty);
        System.out.println("\nFacultys:\n" + faculty);
    }
}
