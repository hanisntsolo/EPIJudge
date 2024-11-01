package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Objects;

public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    return tree == null || checkSymmetry(tree.left, tree.right);
  }
  /*
   * T:O(n) where n is the total number of elements in the tree.
   * S:O(h) where h is the height of the tree.
   */
  private static boolean checkSymmetry(BinaryTreeNode<Integer> treeNodeLeft, BinaryTreeNode<Integer> treeNodeRight) {
    if (treeNodeLeft == null && treeNodeRight == null) {
      return true;
    } else if(treeNodeLeft != null && treeNodeRight != null) {
      return Objects.equals(treeNodeLeft.data, treeNodeRight.data) &&
          checkSymmetry(treeNodeLeft.left, treeNodeRight.right) &&
          checkSymmetry(treeNodeLeft.right, treeNodeRight.left);
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
