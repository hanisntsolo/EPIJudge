package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    if(((x >> i) & 1) != ((x >> j) & 1)) { // This is the case when the bits differ. and then we make a mask and flip them using XOR Operation with mask.
       return x ^ (1L << i | 1L << j); // (1L << i | 1L << j) this is the mask for flipping.
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
