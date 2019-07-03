package prajwal.student;

import java.util.Map;

/**
 * Class to represent student information.
 */
public class Student {

  private String studentName;
  private int studentId;
  private Map<String, String> marks;

  public Student(String studentName, int studentId, Map<String, String> marks) {
    this.studentName = studentName;
    this.studentId = studentId;
    this.marks = marks;
  }

  public String getStudentName() {
    return studentName;
  }

  public int getStudentId() {
    return studentId;
  }

  public Map<String, String> getMarks() {
    return marks;
  }

  @Override
  public String toString() {
    return "Student Name = '" + studentName + '\'' +
        ", Student Id = '" + studentId + '\'' +
        ", Marks = " + marks;
  }
}
