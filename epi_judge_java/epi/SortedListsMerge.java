package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  //22-09-2024
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {
    /*

      L1 : 1|N --> 2|N --> 4|N--->null
                                  ^
      L2 : 3|N --> [5|N --> 7|N--->null]
                   ^
      dummyNode = null|N--> 1|N --> 2|N ---> 3|N --> 4|N --> L2 // The reference is held by currentNode to move forward. dummyNode.next will contain the merged list.
      currentNode null|N--> 1|N --> 2|N ---> 3|N --> 4|N --> L2
      return dummyNode.next
    */
    ListNode dummyNode = new ListNode(0,null); // DummyNode reference is very Important when moving to Solution.
    ListNode currNode = dummyNode;
    while (L1 != null && L2 != null) {
      if(L1.data < L2.data) {
        currNode.next = L1;
        L1 = L1.next;
      } else {
        currNode.next = L2;
        L2 = L2.next;
      }
      currNode = currNode.next;
    }
    currNode.next = L1 == null ? L2 : L1;
    return dummyNode.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
