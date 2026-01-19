package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    List<Double> firstSellIndexes = new ArrayList<>();
    double minSeenSoFar = Double.MAX_VALUE;
    double maxProfit = 0.0D;
    for (Double price : prices) {
      minSeenSoFar = Math.min(minSeenSoFar, price);
      maxProfit = Math.max(maxProfit, price - minSeenSoFar);
      firstSellIndexes.add(maxProfit);
    }
    double maxSeenSoFar = Double.MIN_VALUE;
    for(int i = prices.size() - 1; i >= 0; i--) {
      maxSeenSoFar = Math.max(maxSeenSoFar, prices.get(i));
      maxProfit = Math.max(maxProfit, maxSeenSoFar - prices.get(i) + firstSellIndexes.get(i));
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
