package algo.linkedlist;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by ndreddy on 23/08/17.
 * Write code to dequeue duplicates from an unsorted linked list.
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDups {


    public void removeDups(LinkedListNode head) {

        LinkedListNode curr = head;
        LinkedListNode prev = null;

        Set<Integer> set = new HashSet<>();

//        a -> b -> a -> c
        while (curr != null) {
            // If set does not contain, add and preserve it ref.
            if (!set.contains(curr.data)) {
                set.add(curr.data);
                prev = curr;
            } else {
                prev.next = curr.next;
            }
            curr = curr.next;
        }
    }

    /**
     * Removes duplicates in LinkedList without using a buffer (HashSet/HashTable)
     * Without a buffer, we can iterate with two pointers: “current” does a normal iteration, while “runner” iterates
     * through all prior nodes to check for dups. Runner will only see one dup per node, because if there were multiple
     * duplicates they would have been removed already.
     *
     * @param head
     */
    public static void removeDupsWithoutBuffer(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast;

        while (slow != null) {
            /* Remove all future nodes that have the same data */
            fast = slow;
            while (fast.next != null) {
                if (fast.next.data == slow.data) {
                    fast.next = fast.next.next;
                } else {
                    fast = fast.next;
                }
            }
            slow = slow.next;
        }
    }

    @Test
    public void testDups() throws Exception {
        LinkedListNode head = new LinkedListNode(3);
        head.next = new LinkedListNode(5);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(1);

        assertTrue(head.next.next.data == 3);
        removeDups(head);
        removeDupsWithoutBuffer(head);
        assertTrue(head.next.next.data == 1);

    }

    @Test
    public void testDupsWithoutBuffer() throws Exception {
       
        LinkedListNode head = new LinkedListNode(3);
        head.next = new LinkedListNode(5);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(1);

        assertTrue(head.next.next.data == 3);
        removeDupsWithoutBuffer(head);
        assertTrue(head.next.next.data == 1);

    }


}
