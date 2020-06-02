package algo.stacks;

import java.util.Stack;

/**
 * How would you design a stack which, in addition to push and pop, also has a function min which returns the minimum
 * element? Push, pop and min should all operate in O(1) time.
 */
public class MinStack extends Stack<Integer> {
    Stack<Integer> minStack;

    public MinStack() {
        minStack = new Stack<Integer>();
    }

    public void push(int item) {
        if (item <= min()) {
            minStack.push(item);
        }
        super.push(item);
    }

    public Integer pop() {
        int item = super.pop();
        if (item == min()) {
            minStack.pop();
        }
        return item;
    }

    public int min() {
        if (minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return minStack.peek();
        }
    }
}