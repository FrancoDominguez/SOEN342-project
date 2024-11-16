package lessonbooking.utils;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

  public static void printArray(String title, ArrayList<?> arrayList) {
    StringBuilder result = new StringBuilder();
    result.append("    " + title.toUpperCase() + ":\n\n");
    int index = 0;
    for (Object element : arrayList) {
      result.append(" " + index++ + ") " + element.toString()).append(System.lineSeparator() + "\n");
    }
    System.out.println(result.toString());
  }

  public static int random(int max) {
    Random random = new Random();
    return random.nextInt(max);
  }
}
