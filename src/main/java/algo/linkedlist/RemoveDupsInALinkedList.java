package algo.linkedlist;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by ndreddy on 23/08/17.
 * Write code to remove duplicates from an unsorted linked list.
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDupsInALinkedList {


    public void removeDups(LinkedListNode head) {

        LinkedListNode curr = head;
        LinkedListNode prev = null;

        Set<Integer> set = new HashSet<>();
        while (curr != null) {
            if(set.contains(curr.value)){
                prev.next = curr.next;
            } else {
                set.add(curr.value);
                prev = curr;
            }
            curr = curr.next;
        }
    }


    @Test
    public  void  testDups() throws Exception {
        LinkedListNode head = new LinkedListNode(3);
        head.next = new LinkedListNode(5);
        head.next.next =  new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(1);

        assertTrue(head.next.next.value == 3);
        removeDups(head);
        assertTrue(head.next.next.value == 1);

    }


}
