/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import entity.courseProgramme;

/**
 *
 * @author jia shou
 */
public class courseProgrammeInitializer {

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<courseProgramme> initializeCourseProgramme() {
        ListInterface<courseProgramme> cpList = new ArrayList<>();
        cpList.add(new courseProgramme("BACS1053", "RDS"));
        cpList.add(new courseProgramme("BACS1053", "RSD"));
        cpList.add(new courseProgramme("BACS1053", "RSW"));
        cpList.add(new courseProgramme("BACS1053", "REI"));
        cpList.add(new courseProgramme("BACS2026", "RSW"));
        cpList.add(new courseProgramme("BACS2026", "RDS"));
        cpList.add(new courseProgramme("BAIT1023", "RSD"));
        cpList.add(new courseProgramme("BAIT1023", "RMM"));
        cpList.add(new courseProgramme("BAIT1023", "RMM"));
        cpList.add(new courseProgramme("BAIT1043", "RSW"));
        cpList.add(new courseProgramme("BAIT1043", "REI"));
        cpList.add(new courseProgramme("BAMS1413", "RDS"));
        cpList.add(new courseProgramme("BAMS1413", "RSW"));
        cpList.add(new courseProgramme("BJEL1013", "RSD"));
        cpList.add(new courseProgramme("BJEL1013", "REI"));
        cpList.add(new courseProgramme("BAMS1413", "RMM"));
        cpList.add(new courseProgramme("BJEL1013", "RIS"));
        cpList.add(new courseProgramme("BJEL1013", "RST"));

        cpList.add(new courseProgramme("BABS1233", "RBS"));
        cpList.add(new courseProgramme("BACH2233", "RAN"));
        cpList.add(new courseProgramme("BACH1613", "RFN"));
        cpList.add(new courseProgramme("BJEL1013", "RNR"));
        cpList.add(new courseProgramme("BJEL1013", "RSE"));
        cpList.add(new courseProgramme("BJEL1013", "RBS"));
        cpList.add(new courseProgramme("BJEL1023", "RFN"));
        cpList.add(new courseProgramme("BJEL1023", "RAN"));

        cpList.add(new courseProgramme("BTME4263", "REE"));
        cpList.add(new courseProgramme("BGEE2614", "RME"));
        cpList.add(new courseProgramme("BTEE1513", "RMT"));
        cpList.add(new courseProgramme("BTEE4033", "REE"));
        cpList.add(new courseProgramme("BAMS1413", "REE"));
        cpList.add(new courseProgramme("BAMS1413", "RME"));
        cpList.add(new courseProgramme("BAMS1413", "RMT"));
        cpList.add(new courseProgramme("BAMS2014", "REE"));
        cpList.add(new courseProgramme("BAMS2014", "RMT"));
        cpList.add(new courseProgramme("BAMS2014", "RME"));

        return cpList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        courseProgrammeInitializer cp = new courseProgrammeInitializer();
        ListInterface<courseProgramme> courseProgramme = cp.initializeCourseProgramme();
        courseProgrammeDAO courseProgrammeDAO = new courseProgrammeDAO();
        courseProgrammeDAO.saveToFile(courseProgramme);
        System.out.println("\nCourse programmes:\n" + courseProgramme);
    }
}
