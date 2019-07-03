package prajwal.student;

import java.util.HashMap;
import java.util.Map;
import prajwal.user.Login;
import prajwal.user.model.User;

/**
 * Singleton to save student information.
 */
public class StudentMarks {

  private static StudentMarks instance = null;
  private Map<Integer, Student> studentList= new HashMap<>();


  public static StudentMarks getInstance(){
    if(instance == null) {
      instance = new StudentMarks();
    }
    return instance;
  }

  public boolean addMarksForStudent(Student student){
    if(!User.getInstance().getUserType().equalsIgnoreCase(Login.PROFESSOR_USER_TYPE)){
      System.out.println("You don't have the permission to enter marks.");
      return false;
    }
    studentList.put(student.getStudentId(), student);
    return true;
  }

  public Map<Integer, Student> getStudentList() {
    return studentList;
  }
}
