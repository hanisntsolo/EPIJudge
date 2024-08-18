package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SnakeString {
  @EpiTest(testDataFile = "snake_string.tsv")

  public static String snakeString(String s) {
//    1 5 9 first part of the snake
//    0 2 4 second part
//    3 7 11 last part
    StringBuilder sb = new StringBuilder();
    for(int i = 1; i < s.length(); i+=4) {
      sb.append(s.charAt(i));
    }
    for(int i = 0; i < s.length(); i+=2) {
      sb.append(s.charAt(i));
    }
    for(int i = 3; i < s.length(); i+=4) {
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SnakeString.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
