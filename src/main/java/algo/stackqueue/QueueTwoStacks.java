package algo.stackqueue;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

/**
 * Implement a queue with 2 stacks. Your queue should have an enqueue and a dequeue function and it should be
 * "first in first out" (FIFO). Optimize for the time cost of m function calls on your queue. These can be any mix
 * of enqueue and dequeue calls.
 * <p>
 * Assume you already have a stack implementation and it gives O(1)O(1) time push and pop.
 */

public class QueueTwoStacks {

    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();

    public void enqueue(int item) {
        inStack.push(item);
    }

    public int dequeue() {
        if (outStack.empty()) {

            // Move items from inStack to outStack, reversing order
            while (!inStack.empty()) {
                int newestInStackItem = inStack.peek();
                outStack.push(newestInStackItem);
                inStack.pop();
            }

            // If outStack is still empty, raise an error
            if (outStack.empty()) {
                throw new NoSuchElementException("Can't dequeue from empty queue!");
            }
        }
        return outStack.pop();
    }


    @Test
    public void testDequeue() throws Exception {
        QueueTwoStacks queue = new QueueTwoStacks();
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);

        assertTrue(5 == queue.dequeue());
        assertTrue(4 == queue.dequeue());
        assertTrue(3 == queue.dequeue());
        assertTrue(2 == queue.dequeue());

    }


}