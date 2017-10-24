package algo.linkedlist;

/**
 * Created by ndreddy on 22/08/17.
 */
public class LinkedListNode {
    public int data;
    public LinkedListNode next;
    public LinkedListNode prev;
    public LinkedListNode last;

    public LinkedListNode(int v, LinkedListNode n, LinkedListNode p) {
        this.data = v;
        setNext(n);
        setPrevious(p);
    }

    public LinkedListNode(int data) {
        this.data = data;
    }

    public LinkedListNode(int data, LinkedListNode next){
        this.data = data;
        this.next = next;
    }

    public LinkedListNode() {

    }

    public void setNext(LinkedListNode n) {
        next = n;
        if (this == last) {
            last = n;
        }
        if (n != null && n.prev != this) {
            n.setPrevious(this);
        }
    }

    public void setPrevious(LinkedListNode p) {
        prev = p;
        if (p != null && p.next != this) {
            p.setNext(this);
        }
    }

}
