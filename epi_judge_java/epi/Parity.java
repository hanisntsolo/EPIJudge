package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  // O(n) running time
//  @EpiTest(testDataFile = "parity.tsv")
//  public static short parity(long x) {
//    short countBits = 0;
//    while(x != 0) {
//      if((x & 1) == 1) {
//        countBits++;
//      }
//      x >>= 1;
//    }
//    return (short) (countBits % 2);
//  }
  // O(k) running time focussed on the set bits only using bit fiddling.
  @EpiTest(testDataFile = "parity.tsv")
  public static short parityBitFiddling(long x) {
    short parity = 0;
    while(x != 0) {
      parity ^= 1; // To track even or odd based on each iter.
      x = x & (x - 1); // Sets last bit to 0 based on each iter.
    }
    return parity;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
