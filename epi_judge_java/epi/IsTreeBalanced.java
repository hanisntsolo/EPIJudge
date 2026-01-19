package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  static class BalancedStatusWithHeight {

    private boolean isBalanced;
    private int height;

    public BalancedStatusWithHeight(boolean isBalanced, int height) {
      this.isBalanced = isBalanced;
      this.height = height;
    }
    private static BalancedStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
      if (tree == null) {
        return new BalancedStatusWithHeight(true/*null tree is always height balanced*/,
            -1/*Height is going to be -1 for null tree*/);
      }
      BalancedStatusWithHeight left = checkBalanced(tree.left);
      if (!left.isBalanced) {
        return left;
      }
      BalancedStatusWithHeight right = checkBalanced(tree.right);
      if (!right.isBalanced) {
        return right;
      }
      boolean isBalanced = Math.abs(left.height - right.height) <= 1;
      int height = Math.max(left.height, right.height) + 1;
      return new BalancedStatusWithHeight(isBalanced, height);
    }
  }
  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return BalancedStatusWithHeight.checkBalanced(tree).isBalanced;
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
