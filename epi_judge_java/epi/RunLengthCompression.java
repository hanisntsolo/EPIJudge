package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class RunLengthCompression {

  public static String decoding(String s) {
    int count = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if(Character.isDigit(s.charAt(i))) {
        count = count * 10 + Character.getNumericValue(s.charAt(i));
      } else {
        while(count > 0) {
          sb.append(s.charAt(i));
          count--;
        }
      }
    }
    return sb.toString();
  }
  public static String encoding(String s) {
    int count = 1;
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= s.length(); i++) {
      if(i == s.length() || s.charAt(i) != s.charAt(i-1)) {
        sb.append(count).append(s.charAt(i - 1));
        count = 1;
      } else {
        count++;
      }
    }
    return sb.toString();
  }
  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
