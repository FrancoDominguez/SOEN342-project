package lessonbooking.utils;

import java.util.ArrayList;

public class PrintArray {
  public static String printArray(ArrayList<?> arrayList) {
    StringBuilder result = new StringBuilder();
    for (Object element : arrayList) {
      result.append(element.toString()).append(System.lineSeparator());
    }
    return result.toString();
  }
}