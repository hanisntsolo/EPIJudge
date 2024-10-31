package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> result = new ArrayList<>();
    if(tree == null) {
      return result;
    }
    List<BinaryTreeNode<Integer>> currDepthNode = List.of(tree);
    while(!currDepthNode.isEmpty()) {
      result.add(currDepthNode.stream().map(curr -> curr.data).toList()); /*Add to result*/
      currDepthNode = currDepthNode.stream() /* Update the current Depth Node with immediate children for next Step processing.*/
                                   .map(curr -> Arrays.asList(curr.left, curr.right))
                                   .flatMap(List::stream)
                                   .filter(Objects::nonNull).toList();
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
