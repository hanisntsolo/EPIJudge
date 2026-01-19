package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    double minSeenSoFar = Double.MAX_VALUE;
    double maxProfit = 0.0D;
    for (Double price : prices) {
        minSeenSoFar = Math.min(minSeenSoFar, price);
        maxProfit = Math.max(maxProfit, price - minSeenSoFar);
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
