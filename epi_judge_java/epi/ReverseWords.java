package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    int n = input.length;
    reverse(input, 0, n - 1);// First reverse the whole string and then start reversing individual words.
    int start = 0, finish = 0;
    while(start < n) {
      while(start < finish || start < n && input[start] == ' ') {
        ++start;
      }
      while(finish < start || finish < n && input[finish] != ' ') {
        ++finish;
      }
      reverse(input, start, finish - 1);
    }
  }
  private static void reverse(char[] array, int start, int end) {
    while(start < end) {
      char temp = array[start];
      array[start++] = array[end];
      array[end--] = temp;
    }
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
