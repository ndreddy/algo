package algo.linkedlist;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Implement a function to check if a linked list is a palindrome.
 */
public class PalindromeLinkedList {


    public static boolean isPalindrome(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        Stack<Integer> stack = new Stack<Integer>();

        // Push into stack till the middle.
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

		/* Has odd number of elements, so skip the middle */
		// if list is odd, fast will be at last node, if even it will be at null
        if (fast != null) {
            slow = slow.next;
        }

        // From the middle pop from the stack
        while (slow != null) {
            int top = stack.pop().intValue();
            if (top != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    public static boolean isPermutionPalindrome(LinkedListNode head) {
        Set<Integer> set = new HashSet<>();
        LinkedListNode curr = head;
        while (curr != null){
            if(set.contains(curr.data)){
                set.remove(curr.data);
            } else {
                set.add(curr.data);
            }
        }

        if (set.size() > 1) {
            return  false;
        }

        return true;
    }


    @Test
    public void testIsPalindrome() throws Exception {
        LinkedListNode l1 = new LinkedListNode(3);
        l1.next = new LinkedListNode(1);
        l1.next.next = new LinkedListNode(3);

        LinkedListNode l2 = new LinkedListNode(5);
        l2.next = new LinkedListNode(9);
        l2.next.next = new LinkedListNode(2);

        assertTrue(isPalindrome(l1));
        assertTrue(isPermutionPalindrome(l1));
        assertFalse(isPalindrome(l2));

    }
}
