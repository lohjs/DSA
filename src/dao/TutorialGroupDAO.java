/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.TutorialGroup;
import java.io.*;

/**
 *
 * @author boonxiang
 */
public class TutorialGroupDAO {
    
    private String fileName = "tutorialGroup.dat";
    
    public void saveToFile(ListInterface<TutorialGroup> tutorialGroupList){
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ooStream.writeObject(tutorialGroupList);
            System.out.println("Tutorial groups saved to file successfully.");
        } catch (IOException ex) {
            System.out.println("Failed to save tutorial groups to file: " + ex.getMessage());
        }
    }
    
    public ListInterface<TutorialGroup> retrieveFromFile() {
        ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(fileName))) {
            tutorialGroupList = (ArrayList<TutorialGroup>) oiStream.readObject();
            System.out.println("Tutorial groups retrieved from file successfully.");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Failed to retrieve tutorial groups from file: " + ex.getMessage());
        }
        return tutorialGroupList;
    }    
}
