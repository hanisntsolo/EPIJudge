package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class DoListsOverlap {
  // Brute force using hashMap
  // T - O(n)
  // S - O(n)
//  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
//                                                   ListNode<Integer> l1) {
//    Set<ListNode> listNodeMap = new HashSet<>();
//    while(l0 != null) {
//      listNodeMap.add(l0);
//      l0 = l0.next;
//    }
//    while(l1 != null) {
//      if(listNodeMap.contains(l1))
//        return l1;
//      l1 = l1.next;
//    }
//    return null;
//  }
  // Can we do something better?
  // Lets find the length of each list keep going from longer until the lenghts become equal keep advancing to see if the node match if yes return or else null;
  // T - O(n)
  // S - O(1)
  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
      ListNode<Integer> l1) {
      int l0Length = length(l0), l1Length = length(l1);
      if(l0Length > l1Length) {
        l0 = advanceListByK(l0Length - l1Length, l0);
      } else {
        l1 = advanceListByK(l1Length - l0Length, l1);
      }
      while(l0 != null && l1 != null && l0 != l1) {
        l0 = l0.next;
        l1 = l1.next;
      }
      return l0;
  }
  private static ListNode<Integer> advanceListByK(int k, ListNode<Integer> node) {
    while(k-- > 0) {
      node = node.next;
    }
    return node;
  }
  private static int length(ListNode<Integer> node) {
      int length = 0;
      while(node != null) {
        ++length;
        node = node.next;
      }
      return length;
  }
  @EpiTest(testDataFile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws Exception {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingLists(finalL0, finalL1));

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
