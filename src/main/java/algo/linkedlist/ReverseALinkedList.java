package algo.linkedlist;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by ndreddy on 20/04/17.
 */
public class ReverseALinkedList {


    public LinkedListNode reverse(LinkedListNode head) {

        LinkedListNode curr = head;
        LinkedListNode next;
        LinkedListNode prev = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // head is prev because curr = null
        return prev;
    }

    @Test
    public void testReverse() {
        LinkedListNode a = new LinkedListNode(1, null);
        LinkedListNode b = new LinkedListNode(2, a);
        LinkedListNode c = new LinkedListNode(3, b);

        LinkedListNode rv = reverse(c);
        assertTrue("reverse incorrect", rv.data == 1);
    }

    @Test
    public void testReverseEdgeCase1() {
        LinkedListNode a = new LinkedListNode(1, null);
        LinkedListNode rv = reverse(a);
        assertTrue("reverse incorrect", rv.data == 1);
    }
}
