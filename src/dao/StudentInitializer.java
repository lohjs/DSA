package dao;
import adt.*;
import entity.Student;
/**
 *
 * @author Lock Kwan
 */
public class StudentInitializer {
    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Student> initializeStudents() {
        ListInterface<Student> sList = new ArrayList<>();
        sList.add(new Student("S1000", "Tan Lock Kwan",21,"Female", "012-11112222", "tanlk-wp21@student.tarc.edu.my","RDS")); //0
        sList.add(new Student("S1001", "Lim Hoi Yau", 21, "Male", "018-12326756", "limhy-wp22@student.tarc.edu.my", "RSW"));
        sList.add(new Student("S1002", "Goh Boon Xiang",22, "Male", "016-34098124", "gohbx-wp21@student.tarc.edu.my", "RDS"));
        sList.add(new Student("S1003", "Tan Hoong Guan",23, "Male", "018-2277975", "tanhg-wp21@student.tarc.edu.my", "RFN"));
        sList.add(new Student("S1004", "Loh Jia Shou",21, "Male", "017-8319687", "lohjs-wp21@student.tarc.edu.my", "RME"));
        sList.add(new Student("S1005", "Tan Ling Ling",21,"Female", "012-12341234", "tanll-wp21@student.tarc.edu.my","RDS"));
        sList.add(new Student("S1006", "Lim Jia Jia", 21, "Male", "018-123456789", "limjj-wp22@student.tarc.edu.my", "RSW"));
        sList.add(new Student("S1007", "Goh Jia Jie",22, "Male", "016-45678956", "gohjj-wp21@student.tarc.edu.my", "RMT"));
        sList.add(new Student("S1008", "Tan Jia Jun",23, "Male", "018-45623123", "tanjj-wp21@student.tarc.edu.my", "RMT"));
        sList.add(new Student("S1009", "Loh Jia Jia",21, "Male", "017-10201312", "lohjj-wp21@student.tarc.edu.my", "REE"));//9
        sList.add(new Student("S1010", "Tan Qing",21,"Female", "012-4561230", "tanq-wp21@student.tarc.edu.my","RDS"));//0
        sList.add(new Student("S1011", "Lim Qiang", 21, "Male", "018-78124510", "limq-wp22@student.tarc.edu.my", "RDS"));
        sList.add(new Student("S1012", "Goh Qian",22, "Female", "016-98235610", "gohq-wp21@student.tarc.edu.my", "RDS"));
        sList.add(new Student("S1013", "Tan Qiu",23, "Male", "018-79461311", "tanqiu-wp23@student.tarc.edu.my", "RDS"));
        sList.add(new Student("S1014", "Loh Qi",21, "Male", "017-79791313", "lohq-wp21@student.tarc.edu.my", "RDS"));
        sList.add(new Student("S1015", "Tan Yu",21,"Female", "018-4562585", "tany-wp21@student.tarc.edu.my","RDS"));
        sList.add(new Student("S1016", "Lim Yao", 21, "Male", "017-75395120", "limy-wp22@student.tarc.edu.my", "RDS"));
        sList.add(new Student("S1017", "Goh Yi",22, "Female", "014-85285245", "gohy-wp21@student.tarc.edu.my", "REE"));
        sList.add(new Student("S1018", "Tan Yuan",23, "Male", "012-75486914", "tany-wp21@student.tarc.edu.my", "REE"));
        sList.add(new Student("S1019", "Loh Ying",21, "Female", "014-02175855", "lohy-wp21@student.tarc.edu.my", "RDS"));//9
        
        sList.add(new Student("S1020", "Tan Ping",21,"Female", "017-4561230", "tanp-wp21@student.tarc.edu.my","RSD"));//0
        sList.add(new Student("S1021", "Lim Peng", 21, "Male", "012-78124510", "limp-wp22@student.tarc.edu.my", "RMM"));
        sList.add(new Student("S1022", "Goh Pan ",26, "Female", "013-98235610", "gohp-wp21@student.tarc.edu.my", "RIS"));
        sList.add(new Student("S1023", "Tan Pai Pai",23, "Male", "014-79461311", "tanpp-wp21@student.tarc.edu.my", "RET"));
        sList.add(new Student("S1024", "Loh Pu",22, "FeMale", "016-79791313", "lohp-wp21@student.tarc.edu.my", "RBS"));
        sList.add(new Student("S1025", "Tan Ting",25,"Female", "019-4562585", "tant-wp21@student.tarc.edu.my","RAN"));
        sList.add(new Student("S1026", "Lim Tao", 20, "Male", "011-75395120", "limt-wp22@student.tarc.edu.my", "RSE"));
        sList.add(new Student("S1027", "Goh Ti",22, "Female", "016-85285245", "goht-wp21@student.tarc.edu.my", "RSE"));
        sList.add(new Student("S1028", "Tan Tuan Tuan",24, "Male", "013-75486914", "tantt-wp21@student.tarc.edu.my", "RSE"));
        sList.add(new Student("S1029", "Loh Ting Ting",27, "Female", "015-02175855", "lohtt-wp21@student.tarc.edu.my", "RME"));

        sList.add(new Student("S1030", "Tan Xing",25,"Female", "019-45327895", "tanx-wp21@student.tarc.edu.my","RAN"));
        sList.add(new Student("S1031", "Lim Xiao", 20, "Male", "011-15423601", "limx-wp22@student.tarc.edu.my", "RSE"));
        sList.add(new Student("S1032", "Goh Xi",22, "Female", "016-75192846", "gohx-wp21@student.tarc.edu.my", "RSE"));
        sList.add(new Student("S1033", "Tan Xia Xia",24, "Male", "013-85147320", "tanxx-wp21@student.tarc.edu.my", "RSE"));
        sList.add(new Student("S1034", "Loh Xing Xing",27, "Female", "015-84527545", "lohtt-wp21@student.tarc.edu.my", "RME"));

        
        return sList;
    }
    
    public static void main(String[] args) {
    // To illustrate how to use the initializeProducts() method
    StudentInitializer s = new StudentInitializer();
    ListInterface<Student> studentList = s.initializeStudents();
    StudentDAO studentDAO = new StudentDAO(); 
    studentDAO.saveToFile(studentList); 
    
    System.out.println("\nStudents:\n" + studentList);
  }
}
