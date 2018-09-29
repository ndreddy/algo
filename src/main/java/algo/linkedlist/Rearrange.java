package algo.linkedlist;

public class Rearrange {

    void rearrange(LinkedListNode head) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        p2 = head;

        p2.next = p1;
    }
}
