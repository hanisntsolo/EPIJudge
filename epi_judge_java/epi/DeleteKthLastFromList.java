package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> dummyHead = new ListNode<>(null, L);
    ListNode<Integer> first = dummyHead.next;
    while(k-- > 0) {
      first = first.next;
    }
    // -> now basically move the first to the end using a secondary list initialized from first.
    ListNode<Integer> second = dummyHead; // Take a reference of dummyHead into another list go
    while(first != null) {
      second = second.next;
      first = first.next;
    }
    // When you are done with the above you will be at a location where you can delete the kth guy from the list.
    second.next = second.next.next;
    return dummyHead.next; // Return the original reference of the list which will contain the deleted node,from above.
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
