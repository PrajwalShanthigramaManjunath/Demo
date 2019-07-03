package prajwal.professor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import prajwal.common.InputReader;
import prajwal.user.model.User;
import prajwal.main.Welcome;
import prajwal.student.Student;
import prajwal.student.StudentMarks;
import prajwal.user.Login;

/**
 * Professor Login workflow page.
 */
public class ProfessorLogin implements Login {

  InputReader inputReader = new InputReader();
  private Welcome welcomePage = new Welcome();

  /**
   * Login view for professor which will validate username and password.
   */
  @Override
  public void login() {
    try {
      String professorName = inputReader.getUserName();
      String professorPassword = inputReader.getUserPassword();

      if(!professorName.isEmpty() && !professorPassword.isEmpty() && professorName.equalsIgnoreCase("admin")
          && professorPassword.equalsIgnoreCase("admin")){
        System.out.println("Welcome Professor");
        User.getInstance().setUserType("professor", PROFESSOR_USER_TYPE);
        professorAction();
      }
      else {
        System.out.println("Invalid username or password. Enter the details again");
      }
    } catch (NumberFormatException| IOException e) {
      System.out.println("Invalid username or password. Enter the details again");
    }
    login();
  }

  /**
   * Logs in the professor and shows all the options to the professor like entering marks to students
   * or viewing all the marks entered to students or logout.
   */
  private void professorAction(){
    try {
      System.out.println("Press 1 to Enter the marks for the students.");
      System.out.println("Press 2 to view all students marks.");
      System.out.println("Press 3 to logout.");
      int userType = Integer.parseInt(inputReader.readInput());
      switch (userType) {
        case 1:
          getStudentInformation();
        case 2:
          viewStudentMarks();
          professorAction();
        case 3:
          User.resetUser();
          welcomePage.getUserType();
        default:
          System.out.println("Invalid option. Choose the option again.");
          professorAction();
      }

    } catch (NumberFormatException | IOException e) {
      System.out.println("Invalid option. Choose the option again.");
      professorAction();
    }
  }

  /**
   * Helper class to view the students marks which professor has entered.
   */
  private void viewStudentMarks(){
    if(!StudentMarks.getInstance().getStudentList().isEmpty()){
      for (Map.Entry<Integer,Student> entry : StudentMarks.getInstance().getStudentList().entrySet())
        System.out.println(entry.getValue().toString());
    }
    System.out.println();
    System.out.println();
  }

  /**
   * Helper method to collect all the student information from professor.
   */
  private void getStudentInformation(){
    try{
      Student student = getStudentMarks();
      System.out.println("You have entered following details for the student.");
      System.out.println(student.toString());
      System.out.println("Enter 1 to confirm or 2 to re-enter");
      int option = Integer.parseInt(inputReader.readInput());

      switch (option) {
      case 1:
        if(StudentMarks.getInstance().addMarksForStudent(student)){
          System.out.println("Successfully submitted marks for student " + student.getStudentName());
          break;
        }
        System.out.println("Error occurred while entering marks for student " + student.getStudentName());
      case 2:
        break;

      default:
        System.out.println("Invalid option. Lets try this again professor.");
      }
      professorAction();
    }
    catch (NumberFormatException | IOException e) {
      System.out.println("Invalid entry. Lets try again.");
      getStudentInformation();
    }
  }

  /**
   * Helper method to enter marks of a student.
   */
  private Student getStudentMarks() throws IOException {
    Map<String, String> marks = new HashMap<>();
    System.out.println("Enter Student Name");
    String userName = inputReader.readInput();

    System.out.println("Enter Student Id");
    int userId = Integer.parseInt(inputReader.readInput());

    System.out.println("Enter Marks for Subject Computer graphics");
    String cgMarks = inputReader.readInput();
    marks.put("Computer graphics", cgMarks);

    System.out.println("Enter Marks for Subject Computer Science");
    String csMarks = inputReader.readInput();
    marks.put("Computer Science", csMarks);

    System.out.println("Enter Marks for Computer Design ");
    String cdMarks = inputReader.readInput();
    marks.put("Computer Design", cdMarks);

    return new Student(userName, userId, marks);
  }
}
