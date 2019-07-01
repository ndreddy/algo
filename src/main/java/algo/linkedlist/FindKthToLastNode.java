package algo.linkedlist;

import algo.AlgoUtil;

/**
 * Created by ndreddy on 04/09/17.
 */
public class FindKthToLastNode {

    /**
     * Walk one pointer k nodes from the head.
     * Keep another pointer at the head.
     * Walk both pointers, at the same speed, towards the tail. This keeps a distance of k between them.
     * When first pointer hits the tail, second one is on the target (since it's k nodes from the end of the list).
     *
     * @param head
     * @param k
     * @return
     */
    public static LinkedListNode findKthToLastNode(LinkedListNode head, int k) {
        LinkedListNode p1 = head; // Fast pointer
        LinkedListNode p2 = head; // Slow pointer

		/* Walk fast pointer k nodes from the head */
        for (int i = 0; i < k; i++) {
            if (p1 == null) return null; // Out of bounds
            p1 = p1.next;
        }

		/* Move them at the same pace. When fast hits the end,
         * slow will be at the kth element. */
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * Finds by calculating lenghts
     *
     * @param head
     * @param k
     * @return
     */
    public static LinkedListNode kthToLastNodeByLength(LinkedListNode head, int k) {

        if (k < 1) {
            throw new IllegalArgumentException("Impossible to find less than first to last node: " + k);
        }

        // STEP 1: get the length of the list
        // start at 1, not 0
        // else we'd fail to count the head node!
        int listLength = 1;
        LinkedListNode currentNode = head;

        // traverse the whole list,
        // counting all the nodes
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            listLength += 1;
        }

        // if k is greater than the length of the list, there can't
        // be a kth-to-last node, so we'll return an error!
        if (k > listLength) {
            throw new IllegalArgumentException("k is larger than the length of the linked list: " + k);
        }

        // STEP 2: walk to the target node
        // calculate how far to go, from the head,
        // to get to the kth to last node
        int howFarToGo = listLength - k;

        currentNode = head;
        for (int i = 0; i < howFarToGo; i++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3};
        LinkedListNode head = AlgoUtil.createLinkedListFromArray(array);
        for (int i = 0; i <= array.length + 1; i++) {
            LinkedListNode node = findKthToLastNode(head, i);
            String nodeValue = node == null ? "null" : "" + node.data;
            System.out.println(i + ": " + nodeValue);
        }
    }

}
