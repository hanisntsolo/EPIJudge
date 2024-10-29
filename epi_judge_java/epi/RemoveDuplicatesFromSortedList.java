package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    ListNode<Integer> iter = L;
    while(iter != null) {
      ListNode<Integer> nextDistinct = iter.next;
      while(nextDistinct != null && nextDistinct.data == iter.data) { // Check for the successive nodes if they have the same data as the earlier node if so skip them once done update the iter.
        nextDistinct = nextDistinct.next;
      }
      iter.next = nextDistinct;
      iter = nextDistinct;
    }
    return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
