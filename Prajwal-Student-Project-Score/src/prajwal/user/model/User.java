package prajwal.user.model;

/**
 * Java object representing student.
 */

public class User {

  private static User instance = null;

  private String userName;
  private String userType;

  public static User getInstance(){
    if(instance == null) {
      instance = new User();
    }
    return instance;
  }

  public static void resetUser(){
    instance = null;
  }

  public void setUserType(String userName, String userType){
    this.userName = userName;
    this.userType = userType;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserType() {
    return userType;
  }
}
