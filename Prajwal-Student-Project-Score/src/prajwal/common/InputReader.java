package prajwal.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helper class to read the input from console.
 */
public class InputReader {

  /**
   * Helper method to read input from console.
   */
  public String readInput() throws IOException {
    BufferedReader reader =
        new BufferedReader(new InputStreamReader(System.in));
    return reader.readLine();
  }

  /**
   * Helper method to read username from console.
   */
  public String getUserName() throws IOException {
    System.out.println("Enter your username");
    return readInput();
  }

  /**
   * Helper method to read userId from console.
   */
  public String getUserId() throws IOException {
    System.out.println("Enter your student id number");
    return readInput();
  }

  /**
   * Helper method to read password from console.
   */
  public String getUserPassword() throws IOException {
    System.out.println("Enter your password");
    return readInput();
  }
}
