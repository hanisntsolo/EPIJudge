package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    ListNode<Integer> dummyHead = new ListNode(0, null);
    ListNode<Integer> placeIterator = dummyHead;
    int carry = 0;
    while(L1 != null || L2 != null || carry != 0) {
      // Do something creating the sum
      int val = carry + (L1 == null ? 0 : L1.data) + (L2 == null ? 0 : L2.data);
      L1 = L1 == null ? null : L1.next;
      L2 = L2 == null ? null : L2.next;
      placeIterator.next = new ListNode<>(val % 10, null);
      carry = val > 9 ? 1 : 0;
      placeIterator = placeIterator.next;
    }
    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
