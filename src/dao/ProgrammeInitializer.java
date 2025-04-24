/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.Programme;

/**
 *
 * @author jia shou
 */
public class ProgrammeInitializer {

    public ListInterface<Programme> initializeProgrammes() {
        ListInterface<Programme> pList = new ArrayList<>();
        pList.add(new Programme("RDS", "Bachelor Of Computer Science (HONOURS) In Data Science", "FOCS"));
        pList.add(new Programme("RSW", "Bachelor Of Software Engineering (HONOURS)", "FOCS"));
        pList.add(new Programme("RSD", "Bachelor Of Information Technology (HONOURS) In Software Systems Development", "FOCS"));
        pList.add(new Programme("RMM", "Bachelor Of Science (Honours) In Management Mathematics With Computing", "FOCS"));
        pList.add(new Programme("RIS", "Bachelor Of Information Technology (Honours) In Information Security", "FOCS"));
        pList.add(new Programme("RST", "Bachelor Of Computer Science (Honours) In Interactive Software Technology", "FOCS"));
        pList.add(new Programme("REI", "Bachelor Of Information Systems (Honours) In Enterprise Information Systems", "FOCS"));

        pList.add(new Programme("RBS", "Bachelor Of Science (Hons) In Bioscience With Chemistry", "FOAS"));
        pList.add(new Programme("RAN", "Bachelor Of Science (Hons) In Analytical Chemistry", "FOAS"));
        pList.add(new Programme("RFN", "Bachelor Of Science (Hons) In Food Science", "FOAS"));
        pList.add(new Programme("RNR", "Bachelor Of Science (Hons) In Nutrition", "FOAS"));
        pList.add(new Programme("RSE", "Bachelor Of Science (Hons) In Sports And Exercise Science", "FOAS"));

        pList.add(new Programme("REE", "Bachelor Of Electrical And Electronics Engineering With Honours", "FOET"));
        pList.add(new Programme("RME", "Bachelor Of Mechanical Engineering With Honours", "FOET"));
        pList.add(new Programme("RMT", "Bachelor Of Engineering (Honours) Material", "FOET"));

        return pList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        ProgrammeInitializer p = new ProgrammeInitializer();
        ListInterface<Programme> programme = p.initializeProgrammes();
        ProgrammeDAO ProgrammeDAO = new ProgrammeDAO();
        ProgrammeDAO.saveToFile(programme);
        System.out.println("\nProgrammes:\n" + programme);
    }
}
