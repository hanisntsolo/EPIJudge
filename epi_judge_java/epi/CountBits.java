package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class CountBits {
  @EpiTest(testDataFile = "count_bits.tsv")
// Using O(k) totalNumberOf set bits
  public static short countBits(int x) {
    short countBits = 0;
    while (x != 0) {
      x &= (x - 1); // What this does is sets the rightMost setBit to 0
      countBits++;
    }
    return countBits;
  }
  // Using O(n) total number of bits
//  public static short countBits(int x) {
//    short countBits = 0;
//    while (x != 0) {
//      if((1 & x) > 0) {
//        countBits++;
//      }
//      x >>= 1;
//    }
//    return countBits;
//  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CountBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
