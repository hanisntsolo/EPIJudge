package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if(x <= 0)
      return x == 0;
    //To calculate number of digits
    final int numberOfDigits = (int) Math.floor(Math.log10(x)) + 1;
    int msdMask = (int) Math.pow(10, numberOfDigits - 1);
    for (int i = 0; i < numberOfDigits/2; i++) {
      if(x / msdMask != x % 10) { // Compare if front and last digits match.
        return false; // The first and last digits don't match
      }
      x %= msdMask; // Remove leftMost digit
      x /= 10; // Remove rightMost Digit
      msdMask /= 100; // Reduce the mask by two digits which were lost in above two steps.
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
