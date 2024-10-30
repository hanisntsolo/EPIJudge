package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class SunsetView {
  static class BuildingWithHeight {
    Integer id;
    Integer height;
    public BuildingWithHeight(Integer id, Integer height) {
      this.id = id;
      this.height = height;
    }
  }
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    int buildingIdX = 0;
    Deque<BuildingWithHeight> buildings = new ArrayDeque<>();
    while (sequence.hasNext()) {
      Integer buildingHeight = sequence.next();
      while(!buildings.isEmpty() && buildingHeight >= buildings.peekFirst().height) {
        buildings.removeFirst();
      }
      buildings.addFirst(new BuildingWithHeight(buildingIdX++, buildingHeight));
    }
    return buildings.stream().map(tower -> tower.id).toList();
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
