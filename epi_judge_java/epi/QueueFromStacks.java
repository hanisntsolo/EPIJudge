package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
public class QueueFromStacks {
  /*Can only be implemented using two[2] stacks!!*/
  public static class Queue {
    Deque<Integer> enqueue = new ArrayDeque<>();
    Deque<Integer> dequeue = new ArrayDeque<>();
    public void enqueue(Integer x) {
      enqueue.addFirst(x);
    }
    public Integer dequeue() {
      if(dequeue.isEmpty()) { // Put everything back into dequeue if dequeue becomes empty.
        while(!enqueue.isEmpty()) {
          dequeue.addFirst(enqueue.removeFirst());
        }
      }
      return dequeue.removeFirst();
    }
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

  @EpiTest(testDataFile = "queue_from_stacks.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    try {
      Queue q = new Queue();

      for (QueueOp op : ops) {
        switch (op.op) {
        case "QueueWithMax":
          q = new Queue();
          break;
        case "enqueue":
          q.enqueue(op.arg);
          break;
        case "dequeue":
          int result = q.dequeue();
          if (result != op.arg) {
            throw new TestFailure("Dequeue: expected " +
                                  String.valueOf(op.arg) + ", got " +
                                  String.valueOf(result));
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
            .runFromAnnotations(args, "QueueFromStacks.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
