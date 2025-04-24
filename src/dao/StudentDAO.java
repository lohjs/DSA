package dao;
import adt.*;
import entity.Student;
import java.io.*;
/**
 *
 * @author Lock Kwan
 */
public class StudentDAO {
    private String fileName = "student.dat";
    
    public void saveToFile(ListInterface<Student> studentList) {
    File file = new File(fileName);
    try {
      ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
      ooStream.writeObject(studentList);
      ooStream.close();
    } catch (FileNotFoundException ex) {
      System.out.println("\nFile not found");
    } catch (IOException ex) {
      System.out.println("\nCannot save to file");
    }
  }
    
    public ListInterface<Student> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Student> studentList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            studentList = (ArrayList<Student>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return studentList;
        }
    }

}
