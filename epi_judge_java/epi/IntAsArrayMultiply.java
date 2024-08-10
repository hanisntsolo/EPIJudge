package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    if(num1.isEmpty() || num2.isEmpty()) return List.of(0);
    final int sign = (num1.get(0) < 0) ^ (num2.get(0) < 0) ? -1 : 1;                                      // 9 8 7
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));
    List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0)); // Create an array to hold consecutive multiplications
    for(int i = num1.size() - 1; i >= 0; --i) {
      for(int j = num2.size() - 1; j >= 0; --j) { // Nested loop to visit each element/digit to each digit in the other multiplier.
        result.set(i + j + 1, result.get(i + j + 1) + num1.get(i) * num2.get(j)); // Last element
        result.set(i + j, result.get(i + j) + result.get(i + j + 1) / 10); // Increment preceding by carry
        result.set(i + j + 1, result.get(i + j + 1) % 10); // Reset last element with remainder
      }
    }
    int leadingZeroes = 0;
    while (leadingZeroes < result.size() && result.get(leadingZeroes) == 0) {
      ++leadingZeroes;
    }
    //remove extra zeroes from front
    result = result.subList(leadingZeroes, result.size());

    if(result.isEmpty()) {
      return List.of(0);
    } else {
      result.set(0, result.get(0) * sign); // introduce the extracted sign.
      return result;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
