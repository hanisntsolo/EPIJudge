package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    Deque<Character> charStack = new ArrayDeque<>();
    final Map<Character, Character> LOOKUP = Map.of(
      '(',')',
      '{','}',
      '[',']'
    );
    for(int i = 0; i < s.length(); i++) {
      if(LOOKUP.get(s.charAt(i)) != null) {
        charStack.addFirst(s.charAt(i));
      } else if(charStack.isEmpty() || LOOKUP.get(charStack.removeFirst()) != s.charAt(i)) {
        return false;
      }
    }
    return charStack.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
