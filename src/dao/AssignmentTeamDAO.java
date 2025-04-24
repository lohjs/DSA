/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.AssignmentTeam;
import java.io.*;

/**
 *
 * @author Lim Hoi Yau
 */
public class AssignmentTeamDAO {
    
     private String fileName = "assignmentTeam.dat";

    public void saveToFile(ListInterface<AssignmentTeam> assignmentList) {
    File file = new File(fileName);
    try {
        ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
        ooStream.writeObject(assignmentList);
        ooStream.close();
    } catch (FileNotFoundException ex) {
        System.out.println("\nFile not found: " + ex.getMessage());
        ex.printStackTrace();
    } catch (IOException ex) {
        System.out.println("\nFailed to save to file: " + ex.getMessage());
        ex.printStackTrace();
    }
}
    
    public ListInterface<AssignmentTeam> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<AssignmentTeam> asgTeam = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            asgTeam = (ArrayList<AssignmentTeam>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nFailed to read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return asgTeam;
        }
    }
}
