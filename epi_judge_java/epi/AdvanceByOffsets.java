package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    int farthestReach = 0, lastPosition = maxAdvanceSteps.size() - 1;
    for(int i = 0; i <= farthestReach && farthestReach < lastPosition; i++) {
      farthestReach = Math.max(farthestReach, i + maxAdvanceSteps.get(i));
    }
    return farthestReach >= lastPosition;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
