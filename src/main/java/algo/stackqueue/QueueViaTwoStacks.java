package algo.stackqueue;

import java.util.Stack;

/**
 * Implement a MyQueue class which implements a queue using two stacks.
 */
public class QueueViaTwoStacks<T> {


    Stack<T> inStack, outStack;

    public QueueViaTwoStacks() {
        inStack = new Stack<T>();
        outStack = new Stack<T>();
    }

    public void enqueue(T value) {
        // Push onto in stack
        inStack.push(value);
    }

    public T dequeue() {
        flipStacks();
        return outStack.pop(); // pop the oldest item.
    }

    public T peek() {
        flipStacks();
        return outStack.peek(); // retrieve the oldest item.
    }

    /* Move elements from inStack into outStack. This is usually done so that we can
     * do operations on outStack.
     */
    private void flipStacks() {
        if (outStack.empty()) {
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    public int size() {
        return inStack.size() + outStack.size();
    }

}

