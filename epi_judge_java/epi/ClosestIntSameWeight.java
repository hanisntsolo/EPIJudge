package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    int UNSIGNED_LEN = 63;
    for (int i = 0; i < UNSIGNED_LEN; i++) {
      if(((x >> i ) & 1) != ((x >> (i + 1)) & 1)) {
        x ^= (1L << i) | (1L << (i + 1));
        return x;
      }
    }
    throw new RuntimeException("Unsigned length of " + UNSIGNED_LEN + " is incorrect");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
