package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.math.BigInteger;
import java.util.BitSet;

public class Parity {
  static int[] preComputedParity;
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
//  @EpiTest(testDataFile = "parity.tsv")
//  public static short parityBitFiddling(long x) {
////    System.out.println(Integer.toBinaryString(0xFFFF));
////    System.out.println(Integer.toBinaryString(0xFF));
////    System.out.println(0xFFFF);
////    System.out.println(0xFF);
////    System.out.println(0x1);
//    short parity = 0;
//    while(x != 0) {
//      parity ^= 1; // To track even or odd based on each iter.
//      x = x & (x - 1); // Sets last bit to 0 based on each iter.
//    }
//    return parity;
//  }
  private static void preComputeParity() {
    preComputedParity = new int[65536];
    for(int i = 0; i < 65536; i++) {
      preComputedParity[i] = parityBitFiddlingOlogN(i);
    }
  }
  static {
    preComputeParity();
  }
  // With preComputedParity using a lookup table
  @EpiTest(testDataFile = "parity.tsv")
  public static short parityWithPreComputedParity(long x) {

    final int MASK_SIZE = 16;
    final int BIT_MASK = 0xFFFF;
    return (short)
       (preComputedParity[(int) ((x >>> (3 * MASK_SIZE)) & BIT_MASK)] ^
        preComputedParity[(int)((x >>> (2 * MASK_SIZE)) & BIT_MASK)] ^
        preComputedParity[(int)((x >>> MASK_SIZE) & BIT_MASK)] ^
        preComputedParity[(int)(x & BIT_MASK)]);
  }
  // O(log n) Most optimized way of computing parity of 64 bit word.
//  @EpiTest(testDataFile = "parity.tsv")
  public static short parityBitFiddlingOlogN(long x) {
    x ^= x >> 32;
    x ^= x >> 16;
    x ^= x >> 8;
    x ^= x >> 4;
    x ^= x >> 2;
    x ^= x >> 1;
    return (short)(x & 0x1);
  }

  /**
   * parity means count of 1's
   * if count of 1's is odd means parity 1, even mean parity 0.
   * @param args
   */
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
