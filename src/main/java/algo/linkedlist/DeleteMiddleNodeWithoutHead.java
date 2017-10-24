package algo.linkedlist;

import algo.AlgoUtil;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Delete a node from a singly-linked list, given only a variable pointing to that node.
 * we don't know where the head of the list is—we only have a reference to the node we want to delete.
 */
public class DeleteMiddleNodeWithoutHead {

    public static boolean deleteMiddleNode(LinkedListNode n) {
        if (n == null || n.next == null) {
            return false; // Failure
        }

        n.data = n.next.data;
        n.next = n.next.next;
        return true;
    }

    /**
     * We take data and next from the input node's next node and copy them into the input node.
     * Now the input node previous node effectively skips the input node's old data!
     *
     * @param n
     */
    public static void deleteNode(LinkedListNode n) {

        // get the input node's next node, the one we want to skip to
        LinkedListNode next = n.next;

        if (next != null) {

            // replace the input node's data and pointer with the next
            // node's data and pointer. the previous node now effectively
            // skips over the input node
            n.data = next.data;
            n.next = next.next;

        } else {

            // eep, we're trying to delete the last node!
            throw new IllegalArgumentException("Can't delete the last node with this method!");
        }
    }



    /** Delete with header
     *
     * @param head
     * @param value
     * @return
     */
    LinkedListNode deleteNode(LinkedListNode head, int value) {
        LinkedListNode curr = head;

        // Head node to be deleted
        if (curr.data == value) {
            return head.next; /* moved head */
        }

        while (curr.next != null) {
            if (curr.next.data == value) {
                curr.next = curr.next.next;
                return head; /* head didn’t change */
            }
            curr = curr.next;
        }
        return head;
    }

    @Test
    public void testDups() throws Exception {
        LinkedListNode head = new LinkedListNode(3);
        head.next = new LinkedListNode(5);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(1);

        assertTrue(head.next.next.data == 3);
        deleteMiddleNode(head.next.next);
        assertTrue(head.next.next.data == 1);

    }

    public static void main(String[] args) {
        LinkedListNode head = AlgoUtil.randomLinkedList(10, 0, 10);
        deleteNode(head.next.next.next.next); // delete node 4
    }

}
