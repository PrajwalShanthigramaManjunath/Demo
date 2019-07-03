package prajwal.main;

import java.io.IOException;
import prajwal.common.InputReader;
import prajwal.professor.ProfessorLogin;
import prajwal.student.login.StudentLogin;
import prajwal.user.Login;

/**
 * Welcome page for the application.
 */
public class Welcome {
  private InputReader inputReader = new InputReader();

  /**
   * Helper method to login the user.This method will login the user to appropriate type
   * which is either a student or professor and takes them to appropriate work flow.
   */
  public void getUserType(){
    System.out.println("Welcome to Prajwal university. Please select the user type.");
    System.out.println("Press 1 for Professor.");
    System.out.println("Press 2 for Student.");
    System.out.println("Press 3 to exit application");

    try {
      int userType = Integer.parseInt(inputReader.readInput());
      switch(userType) {
        case 1:
          Login professorLogin = new ProfessorLogin();
          professorLogin.login();

        case 2:
          Login studentLogin = new StudentLogin();
          studentLogin.login();

        case 3:
          System.out.println("Exiting application.");
          System.exit(0);

        default:
          System.out.println("Invalid option. Please enter valid option.");
          getUserType();
      }
    }
    catch(NumberFormatException | IOException e){
      System.out.println("Invalid Option Entered. Please enter a valid option.");
      getUserType();
    }
  }
}
