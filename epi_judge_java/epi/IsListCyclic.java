package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class IsListCyclic {

  // Another approach which first goes on finding a cycle and then calculates the head of the cycle.
//  public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
//    ListNode<Integer> slow = head, fast = head;
//    while(fast != null && fast.next != null) {
//      slow = slow.next;
//      fast = fast.next.next;
//      if(slow == fast) { // if the nodes overlap compute cycle length
//        int cycleLen = 0;
//        do {
//          cycleLen++;
//          fast = fast.next;
//        } while (fast != slow);
//        ListNode<Integer> cycleAdvanceIter = head;
//        while(cycleLen-- > 0) {
//          cycleAdvanceIter = cycleAdvanceIter.next;
//        }
//        ListNode<Integer> iter = head;
//        while(iter != cycleAdvanceIter) {
//          iter = iter.next;
//          cycleAdvanceIter = cycleAdvanceIter.next;
//        }
//        return iter;
//      }
//    }
//    return null;
//  }
//    Intuitive approach for finding a cycle in the LinkedList, which should be ideally followed.
  public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
    ListNode<Integer> slow = head, fast = head;
    while(fast != null && fast.next != null && fast.next.next != null) { // Wanna make sure not going out of bounds while doing this.
      slow = slow.next;
      fast = fast.next.next;
      if(slow == fast) {
        //that means there exist a cycle
        slow = head;
        while(slow != fast) {
          slow = slow.next;
          fast = fast.next;
        }
        return slow; // This would be the place the cycle starts.
      }
    }
    return null;
  }
  @EpiTest(testDataFile = "is_list_cyclic.tsv")
  public static void HasCycleWrapper(TimedExecutor executor,
                                     ListNode<Integer> head, int cycleIdx)
      throws Exception {
    int cycleLength = 0;
    if (cycleIdx != -1) {
      if (head == null) {
        throw new RuntimeException("Can't cycle empty list");
      }
      ListNode<Integer> cycleStart = null, cursor = head;
      while (cursor.next != null) {
        if (cursor.data == cycleIdx) {
          cycleStart = cursor;
        }
        cursor = cursor.next;
        if (cycleStart != null) {
          cycleLength++;
        }
      }
      if (cursor.data == cycleIdx) {
        cycleStart = cursor;
      }
      if (cycleStart == null) {
        throw new RuntimeException("Can't find a cycle start");
      }
      cursor.next = cycleStart;
      cycleLength++;
    }

    ListNode<Integer> result = executor.run(() -> hasCycle(head));

    if (cycleIdx == -1) {
      if (result != null) {
        throw new TestFailure("Found a non-existing cycle");
      }
    } else {
      if (result == null) {
        throw new TestFailure("Existing cycle was not found");
      }

      ListNode<Integer> cursor = result;
      do {
        cursor = cursor.next;
        cycleLength--;
        if (cursor == null || cycleLength < 0) {
          throw new TestFailure(
              "Returned node does not belong to the cycle or is not the closest node to the head");
        }
      } while (cursor != result);

      if (cycleLength != 0) {
        throw new TestFailure(
            "Returned node does not belong to the cycle or is not the closest node to the head");
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListCyclic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
