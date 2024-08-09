package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;

public class PrimitiveDivide {
  // BRUTE FORCE // WORST RUNNING TIME.
//  @EpiTest(testDataFile = "primitive_divide.tsv")
//  public static int divide(int x, int y) {
//    int quotient = 0;
//    while(x >= y) {
//      quotient++;
//      x -= y;
//    }
//    return quotient;
//  }
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    long result = 0;
    int power = 32;
    long yPower = (long) y << power;
    while(x >= y) {
      while (yPower > x) { // This is the place where the improvement is happening.
        yPower >>>= 1;
        --power;
      }
      result += (1L << power);
      x -= (int) yPower;
//      System.out.println("x: " + x + ", y: " + y + ", result: " + result + ", power: " + power);
    }
    return (int) result;
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
