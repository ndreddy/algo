package algo.stackqueue;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertTrue;

/**
 * You want to be able to access the largest element in a stack. Use the built-in Stack class to implement a new class
 * MaxStack with a function getMax() that returns the largest element in the stack. getMax() should not dequeue the
 * item.
 * <p>
 * Your stacks will contain only integers.
 */
public class MaxStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> maxStack = new Stack<>();

    // Add a new item to the top of our stack. If the item is greater
    // than or equal to the last item in maxStack, it's
    // the new max! So we'll enqueue it to maxStack.
    public void push(int item) {
        stack.push(item);
        if (maxStack.empty() || item >= maxStack.peek()) {
            maxStack.push(item);
        }
    }

    // Remove and return the top item from our stack. If it equals
    // the top item in maxStack, they must have been pushed in together.
    // So we'll pop it out of maxStack too.
    public int pop() {
        int item = stack.pop();
        if (item == maxStack.peek()) {
            maxStack.pop();
        }
        return item;
    }

    // The last item in maxStack is the max item in our stack.
    public int getMax() {
        return maxStack.peek();
    }



    @Test
    public void testMaxStacak() throws Exception {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(2);
        maxStack.push(6);
        maxStack.push(9);
        maxStack.push(7);

        int item = maxStack.getMax();

//        System.out.println("maxItem = " + item);
        assertTrue(item == 9);
        maxStack.pop();
        assertTrue(maxStack.getMax() == 9);
        maxStack.pop();
        assertTrue(maxStack.getMax() == 6);

    }

}
