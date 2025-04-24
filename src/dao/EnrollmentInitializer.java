package dao;
import adt.*;
import entity.Enrollment;

/**
 *
 * @author Lock Kwan
 */
public class EnrollmentInitializer {
    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Enrollment> initializeEnrollment() {
        ListInterface<Enrollment> scList = new ArrayList<>();
        scList.add(new Enrollment("S1000", "BACS1053", "Resit"));
        scList.add(new Enrollment("S1001", "BAMS1413", "Main"));
        scList.add(new Enrollment("S1002", "BACS1053","Main"));
        scList.add(new Enrollment("S1003", "BACH1613","Repeat"));
        scList.add(new Enrollment("S1000", "BACS2026", "Main"));
        scList.add(new Enrollment("S1003", "BJEL1023", "Main"));//
        scList.add(new Enrollment("S1002", "BACS2026", "Elective"));
        scList.add(new Enrollment("S1007", "BTEE1513", "Resit"));
        scList.add(new Enrollment("S1008", "BAMS1413", "Repeat"));
        scList.add(new Enrollment("S1008", "BTEE1513", "Main"));
        scList.add(new Enrollment("S1000", "BAMS1413", "Main"));
        scList.add(new Enrollment("S1009", "BTME4263", "Resit"));
        scList.add(new Enrollment("S1010", "BACS2026", "Main"));
        scList.add(new Enrollment("S1011", "BACS2026", "Resit"));//
        scList.add(new Enrollment("S1011", "BACS2026", "Main"));
        scList.add(new Enrollment("S1012", "BACS2026", "Main"));
        scList.add(new Enrollment("S1012", "BAMS1413", "Resit"));
        scList.add(new Enrollment("S1013", "BAMS1413", "Elective"));
        scList.add(new Enrollment("S1014", "BAMS1413", "Main"));
        scList.add(new Enrollment("S1015", "BACS2026", "Main"));
        scList.add(new Enrollment("S1015", "BACS1053", "Resit"));
        scList.add(new Enrollment("S1016", "BACS1053", "Main"));
        scList.add(new Enrollment("S1016", "BACS2026", "Main"));
        scList.add(new Enrollment("S1017", "BTME4263", "Main"));
        scList.add(new Enrollment("S1017", "BAMS2014", "Main"));
        scList.add(new Enrollment("S1018", "BAMS1413", "Elective"));
        scList.add(new Enrollment("S1019", "BACS2026", "Resit"));
        scList.add(new Enrollment("S1020", "BACS1053", "Main"));//
        scList.add(new Enrollment("S1020", "BJEL1013", "Main"));
        scList.add(new Enrollment("S1021", "BAMS1413", "Elective"));
        scList.add(new Enrollment("S1022", "BJEL1013", "Resit"));
        scList.add(new Enrollment("S1024", "BABS1233", "Main"));
        scList.add(new Enrollment("S1025", "BACH2233", "Main"));
        scList.add(new Enrollment("S1026", "BJEL1013", "Main"));
        scList.add(new Enrollment("S1028", "BACS1053", "Main"));
        scList.add(new Enrollment("S1029", "BGEE2614", "Main"));
        scList.add(new Enrollment("S1029", "BAMS2014", "Elective"));
        scList.add(new Enrollment("S1030", "BACH2233", "Elective"));
        scList.add(new Enrollment("S1030", "BACH2233", "Elective"));
        scList.add(new Enrollment("S1032", "BJEL1013", "Resit"));
        scList.add(new Enrollment("S1035", "BGEE2614", "Main"));
        return scList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        EnrollmentInitializer sc = new EnrollmentInitializer();
        ListInterface<Enrollment> ListInterfaceList = sc.initializeEnrollment();
        System.out.println("\nStudents Courses:\n" + ListInterfaceList);
    }
}
