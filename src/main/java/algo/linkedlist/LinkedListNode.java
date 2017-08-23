package algo.linkedlist;

/**
 * Created by ndreddy on 22/08/17.
 */
public class LinkedListNode {
    public int value;
    public LinkedListNode next;

    public LinkedListNode(int value) {
        this.value = value;
    }

    public LinkedListNode(int value, LinkedListNode next){
        this.value = value;
        this.next = next;
    }


}
