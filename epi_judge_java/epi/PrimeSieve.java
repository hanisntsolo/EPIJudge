package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
//    return approachOne(n);
    return approachTwo(n);
  }

  private static List<Integer> approachTwo(int n) {
    List<Integer> primes = new ArrayList<>();
    //Formula based approach given n => total primes will be : ((n - 3) / 2) + 1
    // Sieving from p^2, whose value is (4i^2 + 12i + 9). The index of this value in isPrime is 2i^2 + 6i + 3 because isPrime(i) represents 2i + 3
    if(n < 2) return Collections.emptyList();
    final int size = (int)Math.floor(0.5 * (n - 3)) + 1;
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(size, true));
    primes.add(2);
    for(long i = 0; i < size; ++i) {
      if(isPrime.get((int)i)) {
        int p = ((int) (i * 2)) + 3;
        primes.add(p);
        for(long j = ((i * i) * 2 ) + 6 * i + 3; j < size; j += p) {
          isPrime.set((int)j, false);
        }
      }
    }
    return primes;
  }

  public static List<Integer> approachOne(int n) {
    List<Integer> primes = new ArrayList<>();
    List<Boolean> primeSieve = new ArrayList<>(Collections.nCopies(n + 1, true));
    primeSieve.set(0, false);
    primeSieve.set(1, false);
    for(int i = 2; i <= n; i++) {
      if(primeSieve.get(i)) {
        primes.add(i); // Add that result to the resulting array.
      }
      // Make all multiples ineligible to be prime
      for(int p = 2 * i; p <= n; p += i) {
        primeSieve.set(p, false);
      }
    }
    return primes;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
