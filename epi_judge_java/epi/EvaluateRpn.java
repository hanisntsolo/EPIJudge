package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.ToIntBiFunction;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    Deque<Integer> intermediateResults = new ArrayDeque<>();
    String DELIMITER = ",";
    final Map<String, ToIntBiFunction<Integer, Integer>> OPERATORS = Map.of( // SMART
    "+", (y, x) -> x + y,
    "-", (y, x) -> x - y,
    "/", (y, x) -> x / y,
    "*", (y, x) -> x * y
    );
    for(String token : expression.split(DELIMITER)) {
      if(OPERATORS.get(token) != null) {
        intermediateResults.addFirst(OPERATORS.get(token).applyAsInt(intermediateResults.removeFirst(), intermediateResults.removeFirst()));
      } else {
        intermediateResults.addFirst(Integer.parseInt(token));
      }
    }
    return intermediateResults.removeFirst();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
