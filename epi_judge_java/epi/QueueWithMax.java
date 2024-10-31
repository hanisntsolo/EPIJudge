package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueWithMax<T extends Comparable<T>> {
  /*1.Implement using two queues which feels more natural*/
  static class QueueWithMaxIntuition<T extends Comparable<T>> {
    private final Queue<T> entries = new ArrayDeque<>();
    private final Deque<T> candidateForMax = new ArrayDeque<>();
    public void enqueue(T value) {
      entries.add(value);
      while(!candidateForMax.isEmpty() && candidateForMax.peekLast().compareTo(value) < 0) {
        //Eliminate dominating element in candidate max at the last.
        candidateForMax.removeLast();
      }
      candidateForMax.addLast(value);
    }
    public T dequeue() {
      T result = entries.remove();
      if(result.equals(candidateForMax.peekFirst())) {
        candidateForMax.removeFirst();
      }
      return result;
    }
    public T max() {
      return candidateForMax.peekFirst();
    }
  }

  /*2.Model the question using reduction, queue with two stack max*/
  private final StackWithMax.Stack enqueue = new StackWithMax.Stack();
  private final StackWithMax.Stack dequeue = new StackWithMax.Stack();
  public void enqueue(Integer x) {
    enqueue.push(x);
  }
  public Integer dequeue() {
    if(dequeue.empty()) {
      while(!enqueue.empty()) {
        dequeue.push(enqueue.pop());
      }
    }
    return dequeue.pop();
  }
  public Integer max() {
    if(!enqueue.empty()) {
      return dequeue.empty() ? enqueue.max() :
                               Math.max(enqueue.max(), dequeue.max());
    }
    return dequeue.max();
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "queue_with_max.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    try {
      QueueWithMax q = new QueueWithMax();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new QueueWithMax();
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          int result = (int) q.dequeue();
          if (result != op.arg) {
            throw new TestFailure("Dequeue: expected " +
                                  String.valueOf(op.arg) + ", got " +
                                  String.valueOf(result));
          }
          break;
        case "max":
          int s = (int) q.max();
          if (s != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "QueueWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
