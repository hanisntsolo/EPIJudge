package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    double result = 1.0;
    int pow = y;
    if(y < 0) {
      pow = -pow;
      x = 1.0 / x;
    }
    while(pow != 0) { // Interested in reducing the power //
      if((pow & 1) != 0) {
        result *= x;
      }
      x *= x;
      pow >>>= 1;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
