package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
    ListNode<Integer> tail = L;
    if(tail == null) { // What if there is only one node and that is null.
      return L;
    }
    int n = 1;
    while(tail.next != null) {
      n+=1;
      tail = tail.next;
    }
    k %= n;
    if(k == 0) { // The list remains unchanged.
      return L;
    }
    // Connect the tail to the head.
    tail.next = L;
    // Compute the remaining distance and advance the list till there and then splice it into newHead and newTail.
    int stepsToAdvance = n - k;
    ListNode<Integer> newTail = tail;
    while(stepsToAdvance-- > 0) {
      newTail = newTail.next;
    }
    ListNode<Integer> newHead = newTail.next;
    newTail.next = null;
    return newHead;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
