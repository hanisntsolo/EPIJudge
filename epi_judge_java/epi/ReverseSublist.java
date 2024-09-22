package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode dummyNode = new ListNode(0, L);
    ListNode subListHead = dummyNode;
    int k = 1;
    while(k++ < start) {
      subListHead = subListHead.next;
    }
    ListNode<Integer> subListIter = subListHead.next;
    while(start++ < finish) {
      ListNode<Integer> temp = subListIter.next;
      subListIter.next = temp.next;
      temp.next = subListHead.next;
      subListHead.next = temp;
    }
    return dummyNode.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
