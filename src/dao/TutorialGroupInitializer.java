/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dao;

import adt.*;

import entity.TutorialGroup;
/**
 *
 * @author boonxiang
 */
public class TutorialGroupInitializer {
    
    
    public ListInterface<TutorialGroup> initializeTutorialGroup(){
        
        ListInterface<TutorialGroup> gList = new ArrayList<>();
        
        gList.add(new TutorialGroup("RDS"));
        gList.add(new TutorialGroup("RDS"));
        gList.add(new TutorialGroup("RDS"));
        gList.add(new TutorialGroup("RDS"));
        
        gList.add(new TutorialGroup("REE"));
        
        gList.add(new TutorialGroup("RBS"));
        
        gList.add(new TutorialGroup("RSW"));
        
        gList.add(new TutorialGroup("RAN"));
        
        gList.add(new TutorialGroup("RSD"));
        
        gList.add(new TutorialGroup("RMM"));
        
        gList.add(new TutorialGroup("RIS"));
        
        gList.add(new TutorialGroup("RST"));   
        
        gList.add(new TutorialGroup("REI"));
        
        gList.add(new TutorialGroup("RFN"));
        
        gList.add(new TutorialGroup("RNR"));
        
        gList.add(new TutorialGroup("RSE"));
        
        gList.add(new TutorialGroup("RME")); 
        
        gList.add(new TutorialGroup("RAN"));
        
        gList.add(new TutorialGroup("RMT"));
        gList.add(new TutorialGroup("RMT"));
        gList.add(new TutorialGroup("RMT"));
        
        
        return gList;
    }
    
    public static void main(String[] args) {
        TutorialGroupInitializer initializer = new TutorialGroupInitializer();
        ListInterface<TutorialGroup> glist = initializer.initializeTutorialGroup();
        TutorialGroupDAO tutorialGroupDAO = new TutorialGroupDAO(); 
        tutorialGroupDAO.saveToFile(glist); 
        System.out.println("\nTutorial Group: \n" +
                String.format("%-15s %-15s %-15s \n", "Programme Code","GroupID","Group Name") 
                + glist);

    }
}
