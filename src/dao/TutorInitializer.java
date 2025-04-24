/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import adt.*;
import entity.Tutor;

/**
 *
 * @author hgtan
 */
public class TutorInitializer {
    
    public ListInterface<Tutor> initializeTutors() {
        ListInterface<Tutor> tList = new ArrayList<>();
        tList.add(new Tutor("Heng Jooi Huang", 40, "Male", "Hengjh@tarc.edu.my", "016-3115453"));
        tList.add(new Tutor("Thamarai A/P Subramaniam", 45, "Female", "Thamarai@tarc.edu.my", "017-4875233"));
        tList.add(new Tutor("Tan Mei Ling", 36, "Female", "Tanml@tarc.edu.my", "010-5274012"));
        tList.add(new Tutor("Lim Wei Jie", 29, "Male", "Limwj@tarc.edu.my", "010-0156282"));
        tList.add(new Tutor("Wong Kok Keong", 37, "Male", "Wongkk@tarc.edu.my", "017-9950755"));
        tList.add(new Tutor("Loh Siew Hui", 42, "Female", "Lohsh@tarc.edu.my", "012-5220749"));
        tList.add(new Tutor("Lee Chee Seng", 42, "Male", "Leecs@tarc.edu.my", "015-9795730"));
        tList.add(new Tutor("Ng Mei Yee", 40, "Female", "Ngwy6@tarc.edu.my", "017-7022759"));
        tList.add(new Tutor("Chan Hui Min", 43, "Female", "Chanhm@tarc.edu.my", "018-8212160"));
        tList.add(new Tutor("Teh Yee Ling", 29, "Female", "Tehyl@tarc.edu.my", "018-0690467"));
        tList.add(new Tutor("Yap Chin Hock", 29, "Male", "Ych@tarc.edu.my", "014-1078771"));
        tList.add(new Tutor("Ong Chong Wei", 38, "Male", "Ongcw@tarc.edu.my", "012-2592962"));
        tList.add(new Tutor("Nurul Azizah binti Abdullah", 32, "Female", "Nurulazizah@tarc.edu.my", "019-0208637"));
        tList.add(new Tutor("Siti Aishah binti Mohd Yusuf", 38, "Female", "Sitiaishah@tarc.edu.my", "014-4799824"));
        tList.add(new Tutor("Ravi Kumar", 37, "Male", "Ravi@tarc.edu.my", "017-0436496"));
        tList.add(new Tutor("Ngeoh Boon Heong", 42, "Male", "Ngeohbh@tarc.edu.my", "010-3728188"));
        tList.add(new Tutor("Chew Kah Heng", 55, "Male", "Chewkh@tarc.edu.my", "012-7886162"));
        tList.add(new Tutor("Siti Sara Binti Mohd Ariff", 35, "Female", "Sitisara@tarc.edu.my", "017-3538834"));
        tList.add(new Tutor("Vijayamalini A/P Sathasivam", 58, "Female", "Vijayamalini@tarc.edu.my", "016-6547133"));
        tList.add(new Tutor("Lai Weng Kin", 62, "Male", "Laiwk@tarc.edu.my", "012-3478838"));

        return tList;
    }
    
    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        TutorInitializer t = new TutorInitializer();
        ListInterface<Tutor> tutor = t.initializeTutors();
        TutorDAO tutorDAO = new TutorDAO(); 
        tutorDAO.saveToFile(tutor); 

        System.out.println("\nTutors:\n" + tutor);
    }
}
