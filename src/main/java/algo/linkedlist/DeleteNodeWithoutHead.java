package algo.linkedlist;

/**
 * Delete a node from a singly-linked list, given only a variable pointing to that node.
 * we don't know where the head of the list is—we only have a reference to the node we want to delete.
 */
public class DeleteNodeWithoutHead {

    /**
     * We take value and next from the input node's next node and copy them into the input node. Now the input node's
     * previous node effectively skips the input node's old value!
     *
     * @param nodeToDelete
     */
    public static void deleteNode(LinkedListNode nodeToDelete) {

        // get the input node's next node, the one we want to skip to
        LinkedListNode nextNode = nodeToDelete.next;

        if (nextNode != null) {

            // replace the input node's value and pointer with the next
            // node's value and pointer. the previous node now effectively
            // skips over the input node
            nodeToDelete.value = nextNode.value;
            nodeToDelete.next = nextNode.next;

        } else {

            // eep, we're trying to delete the last node!
            throw new IllegalArgumentException("Can't delete the last node with this method!");
        }
    }
}
