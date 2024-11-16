package lessonbooking;

import lessonbooking.CLI.MainMenu;

public class Driver {
  public static void main(String[] args) throws Exception {
    Test test = new Test();
    test.runTest();
    MainMenu mainMenu = new MainMenu();
    mainMenu.startMenu();
  }
}
