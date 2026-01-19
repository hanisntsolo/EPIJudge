package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {
  private static int getDepth(BinaryTree<Integer> tree) {
    int depth = 0;
    while(tree.parent != null) {
      tree = tree.parent;
      ++depth;
    }
    return depth;
  }
  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int depth0 = getDepth(node0);
    int depth1 = getDepth(node1);
    if(depth1 > depth0) { // Make node0 deeper if it is not deeper.
      BinaryTree<Integer> temp = node0;
      node0 = node1;
      node1 = temp;
    }
    int depthDiff = Math.abs(depth1 - depth0);
    while(depthDiff-- > 0) {
      node0 = node0.parent; // Equalize both the nodes using parent pointer to deeper node.
    }
    // Now ascends both until there is a match.
    while(node0 != node1) {
      node0 = node0.parent;
      node1 = node1.parent;
    }
    return node1; // Node 0 or Node1 since both will be same.
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
