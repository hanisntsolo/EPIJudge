package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SubstringMatch {
  @EpiTest(testDataFile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  // DATE : 22-Sep-2024
  public static int rabinKarp(String t, String s) {
    if(s.length() > t.length()) return -1; // Search string is greater than reference string.
    int tHash = 0, sHash = 0, powerS = 1;
    final int BASE = 26;
    for(int i = 0; i < s.length(); i++) {
      powerS = i > 0 ? powerS * BASE : 1;
      tHash = tHash * BASE + t.charAt(i);
      sHash = sHash * BASE + s.charAt(i);
    }
    for(int i = s.length(); i < t.length(); i++) {
      if(tHash == sHash && t.substring(i - s.length(), i).equals(s))
        return i - s.length(); // Found a match
      tHash -= t.charAt(i - s.length()) * powerS; // Subtract the first occurrence
      tHash = tHash * BASE + t.charAt(i); // Add the new occurrence
    }
    // Edge case : //To match last s.length() characters to check for a possible match.
    if(tHash == sHash && t.substring(t.length() - s.length()).equals(s)) {
      return t.length() - s.length(); // Found a match // Edge case
    }
    return -1;//No match found
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
