package prajwal.student.login;

import java.io.IOException;
import java.util.Map;
import prajwal.common.InputReader;
import prajwal.main.Welcome;
import prajwal.student.Student;
import prajwal.student.StudentMarks;
import prajwal.user.Login;
import prajwal.user.model.User;

/**
 * Student Login workflow page.
 */
public class StudentLogin  implements Login {

  InputReader inputReader = new InputReader();
  Welcome welcome = new Welcome();

  /**
   * Login the student.If success take student to the student workflow.
   */
  @Override
  public void login() {
    try {
      int userId = Integer.parseInt(inputReader.getUserId());
      String password = inputReader.getUserPassword();

      if(!password.isEmpty() && password.equalsIgnoreCase("student")){
        if(!StudentMarks.getInstance().getStudentList().isEmpty() && StudentMarks.getInstance().getStudentList().containsKey(userId)){
          Student studentInfo = StudentMarks.getInstance().getStudentList().get(userId);
          User.resetUser();
          User.getInstance().setUserType(studentInfo.getStudentName(),STUDENT_USER_TYPE);
          showStudentsChoice(studentInfo);
        }else{
          System.out.println("Professor has not yet graded your marks. Check back later.");
          System.out.println("Logging you out.");
          User.resetUser();
          welcome.getUserType();
        }
      }
      System.out.println("Invalid username or password. Enter the details again");
    } catch (NumberFormatException | IOException e) {
      System.out.println("Enter valid options.");
    }
    login();
  }

  /**
   * \Helper method that displays all the option for the student once login is successful.
   */
  private void showStudentsChoice(Student student){
    try {
      System.out.println("Press 1 to view the marks");
      System.out.println("Press 2 to exit");
      int option = Integer.parseInt(inputReader.readInput());
      switch (option) {
        case 1:
          showStudentsMarks(student);
          showStudentsChoice(student);
        case 2:
          User.resetUser();
          welcome.getUserType();
        default:
          System.out.println("Invalid Option. Lets try again.");
          showStudentsChoice(student);
      }
    } catch (NumberFormatException | IOException e) {
      System.out.println("Enter valid options.");
    }
  }

  /**
   * Helper method ot show marks for the student.
   */
  private void showStudentsMarks(Student student){
    System.out.println("Student Name:" +student.getStudentName());
    System.out.println("Student Id:" +student.getStudentId());
    for (Map.Entry<String,String> entry : student.getMarks().entrySet())
      System.out.println("Subject: " +entry.getKey() + " Marks Obtained : " +entry.getValue().toString());
  }
}
