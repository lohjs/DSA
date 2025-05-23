/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import adt.*;
import entity.Tutor;
import java.io.*;

/**
 *
 * @author hgtan
 */
public class TutorDAO {
    private String fileName = "tutor.dat";
    
    public void saveToFile(ListInterface<Tutor> tutorList) {
    File file = new File(fileName);
    try {
      ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
      ooStream.writeObject(tutorList);
      ooStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nFile not found");
    } catch (IOException ex) {
      System.out.println("\nCannot save to file");
    }
  }
    
    public ListInterface<Tutor> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Tutor> tutor = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            tutor = (ArrayList<Tutor>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return tutor;
        }
    }
}
