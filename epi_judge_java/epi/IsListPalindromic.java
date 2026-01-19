package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    ListNode<Integer> slow = L, fast = L;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    // Once you are done you would be at the center of the list as per what slow entails
    ListNode<Integer> start =  L;
    ListNode<Integer> mid = ReverseList.reverseList(slow);
    while(start != null && mid != null) {
      if(!start.data.equals(mid.data)) {
        return false;
      }
      start = start.next;
      mid = mid.next;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
