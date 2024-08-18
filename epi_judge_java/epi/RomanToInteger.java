package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
  final static Map<Character, Integer> romanToInt = Map.of('I',1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
  @EpiTest(testDataFile = "roman_to_integer.tsv")
  public static int romanToInteger(String s) {
    int sum = romanToInt.get(s.charAt(s.length() - 1));
    for(int i = s.length() - 2; i >= 0; i--) {
      if(romanToInt.get(s.charAt(i)) < romanToInt.get(s.charAt(i + 1))) {
        sum -= romanToInt.get(s.charAt(i));
      } else {
        sum += romanToInt.get(s.charAt(i));
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
