package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    int l1Length = length(l1);
    int l0Length = length(l0);
    if(l1Length > l0Length) {
      l1 = advanceListByK(l1Length - l0Length, l1);
    } else {
      l0 = advanceListByK(l0Length - l1Length, l0);
    }
    while(l1 != null && l0!= null && l1 != l0) {
      l1 = l1.next;
      l0 = l0.next;
    }
    return l0;
  }
  public static ListNode<Integer> advanceListByK(int k, ListNode<Integer> list) {
    while(k-- > 0) {
      list = list.next;
    }
    return list;
  }
  public static int length(ListNode<Integer> list) {
    int length = 0;
    while(list != null) {
      list = list.next;
      length++;
    }
    return length;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
