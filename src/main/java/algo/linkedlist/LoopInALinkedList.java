package algo.linkedlist;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by ndreddy on 22/08/17.
 */
public class LoopInALinkedList {

    public boolean containsLoop (LinkedListNode firstNode) {

        // start both runners at the beginning
        LinkedListNode slowRunner = firstNode;
        LinkedListNode fastRunner = firstNode;

        // until we hit the end of the list
        while (fastRunner!=null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;

            // case: fastRunner is about to "lap" slowRunner
            if(fastRunner == slowRunner) {
                return true;
            }
        }

        // case: fastRunner hit the end of the list
        return false;
    }


    public LinkedListNode findLoopBeginning(LinkedListNode firstNode) {
        // start both runners at the beginning
        LinkedListNode slowRunner = firstNode;
        LinkedListNode fastRunner = firstNode;

        // until we hit the end of the list
        while (fastRunner!=null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;

            // case: fastRunner is about to "lap" slowRunner
            if(fastRunner == slowRunner) {
                break;
            }
        }

        // Error check: there is not meeting point so no loop
        if(fastRunner.next == null) {
            return null;
        }

        // Move slow runner to head, keep faster runner at meeting point
        slowRunner = firstNode;


        // Each are at K steps from the loop start, if they move at same pace,
        // they must meet at start of the loop as per Floyd’s Cycle finding algorithm.
        while (slowRunner != fastRunner) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next;
        }

        return fastRunner;
    }

    /**
     * Detects and removes loop
     *
     * @param firstNode
     */
    public void detectAndRemoveLoop(LinkedListNode firstNode) {
        LinkedListNode slowRunner = firstNode;
        LinkedListNode fastRunner = firstNode;

        while (fastRunner != null && fastRunner.next !=null){
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
            if(slowRunner == fastRunner) {
                break;
            }
        }

        // Error check: there is not meeting point so no loop
        if(fastRunner.next == null) {
            return;
        }

        // Move slowRunner to head
        slowRunner = firstNode;
        while (slowRunner.next != fastRunner.next){
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next;
        }

        // This is the starting of loop, remove it.
        fastRunner.next = null;

    }



    @Test
    public void testLoop() throws Exception {

        // Create 50 -> 20 -> 15 -> 4 -> 10 ->
        LinkedListNode head = new LinkedListNode(50);
        head.next = new LinkedListNode(20);
        head.next.next = new LinkedListNode(15);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(10);

        // Creating a loop for testing 50 -> 20 -> 15 -> 4 -> 10 -> 15 ...
        head.next.next.next.next.next = head.next.next;

        assertTrue("No loop detected", containsLoop(head));
        assertTrue("No loop detected", findLoopBeginning(head) == head.next.next);
        print(head);
        detectAndRemoveLoop(head);
        print(head);
    }


    void print(LinkedListNode node) {
        if(containsLoop(node)) {
            System.out.println("Loop exist at " + findLoopBeginning(node).value);
            return;
        }

        System.out.print("LinkedList = ");
        while (node != null) {
            System.out.print( node.value + " -> " );
            node = node.next;
        }
    }

}
