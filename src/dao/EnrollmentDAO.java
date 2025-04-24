package dao;
import adt.*;
import entity.Enrollment;
import java.io.*;
/**
 *
 * @author Lock Kwan
 */
public class EnrollmentDAO {
    private String fileName = "enrollment.dat";
    
    public void saveToFile(ListInterface<Enrollment> enrollmentList) {
    File file = new File(fileName);
    try {
      ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
      ooStream.writeObject(enrollmentList);
      ooStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nFile not found");
    } catch (IOException ex) {
      System.out.println("\nCannot save to file");
    }
  }
    
    public ListInterface<Enrollment> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Enrollment> enrollmentList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            enrollmentList = (ArrayList<Enrollment>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return enrollmentList;
        }
    }

}
