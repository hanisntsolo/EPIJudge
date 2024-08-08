package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  static long[] preComputeReverse;
  static final int MASK_SIZE = 16;
  static final int BIT_MASK = 0xFFFF;
  static {
    preComputeReverseArray();
  }
  private static void preComputeReverseArray() {
    preComputeReverse = new long[65536];
    for (int i = 0; i < 65536; i++) {
      preComputeReverse[i] = reversePreCompute(i);
    }
  }
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    return
        preComputeReverse[(int)(x                       & BIT_MASK)] << (3 * MASK_SIZE) |
        preComputeReverse[(int)((x >>> MASK_SIZE)       & BIT_MASK)] << (2 * MASK_SIZE) |
        preComputeReverse[(int)((x >>> (2 * MASK_SIZE)) & BIT_MASK)] << MASK_SIZE |
        preComputeReverse[(int)((x >>> (3 * MASK_SIZE)) & BIT_MASK)];
  }
  private static long reversePreCompute(long x) {
    for (int i = 0; i < MASK_SIZE/2; i++) {
      x = SwapBits.swapBits(x, i, MASK_SIZE - 1 - i);
    }
    return x;
  }
  //Helper while creating preComputeArray
  private static String to16BitBinaryString(int num) {
    return String.format("%16s", Integer.toBinaryString(num)).replace(' ', '0');
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
