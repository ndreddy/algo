package algo.linkedlist;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * You have two numbers represented by a linked list, where each node contains a single digit.
 * The digits are stored in reverse order, such that the 1’s digit is at the head of the list.
 * Write a function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE Input: (3 -> 1 -> 5), (5 -> 9 -> 2) Output: 8 -> 0 -> 8
 */
public class SumLists {

    /**
     *
     * @param l1 - linked list one
     * @param l2    linked list two
     * @param c     carry
     * @return resulting linked list.
     */
    private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int c) {
        if (l1 == null && l2 == null && c == 0) return null;

        LinkedListNode r = new LinkedListNode(); // result

        int value = c;
        if (l1 != null) value += l1.data;
        if (l2 != null) value += l2.data;
        r.data = value % 10;
        if (l1 != null || l2 != null) {
            r.next = addLists(l1 == null ? null : l1.next,
                    l2 == null ? null : l2.next,
                    value >= 10 ? 1 : 0);
        }
        return r;
    }


    @Test
    public void testAddLists() throws Exception {
        LinkedListNode l1 = new LinkedListNode(3);
        l1.next = new LinkedListNode(1);
        l1.next.next = new LinkedListNode(5);

        LinkedListNode l2 = new LinkedListNode(5);
        l2.next = new LinkedListNode(9);
        l2.next.next = new LinkedListNode(2);


        LinkedListNode l3 = addLists(l1, l2, 0);

        assertTrue(l3.data == 8);
        assertTrue(l3.next.data == 0);
        assertTrue(l3.next.next.data == 8);

    }

    public static int linkedListToInt(LinkedListNode node) {
        int value = 0;
        if (node.next != null) {
            value = 10 * linkedListToInt(node.next);
        }
        return value + node.data;
    }

}
