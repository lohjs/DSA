/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.Faculty;
import java.io.*;

/**
 *
 * @author jia shou
 */
public class FacultyDAO {
    private String fileName = "faculty.dat";

    public void saveToFile(ListInterface<Faculty> faculty) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(faculty);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nFailed to save to file");
        }
    }
    
    public ListInterface<Faculty> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Faculty> faculty = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            faculty = (ArrayList<Faculty>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nFailed to read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return faculty;
        }
    }
}
