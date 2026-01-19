package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    int inversionIndex = perm.size() - 2;
    while(inversionIndex >= 0 && perm.get(inversionIndex) >= perm.get(inversionIndex + 1)) {
      --inversionIndex;
    }
    if(inversionIndex  == -1) {
      return Collections.EMPTY_LIST;
    }
    for(int i = perm.size() - 1; i > inversionIndex; --i) {
      if(perm.get(i) > perm.get(inversionIndex)) {
        Collections.swap(perm, i, inversionIndex);
        break;
      }
    }
    Collections.reverse(perm.subList(inversionIndex + 1, perm.size()));
    return perm;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
