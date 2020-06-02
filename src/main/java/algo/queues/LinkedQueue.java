package algo.queues;

import java.util.NoSuchElementException;

public class LinkedQueue<T> {
    private static class QueueNode<T> {
        private T data;
        private QueueNode<T> next;

        public QueueNode(T data) {
            this.data = data;
        }
    }

    private QueueNode<T> first;
    private QueueNode<T> last;

    public void enqueue(T item) {
        QueueNode<T> t = new QueueNode<T>(item);
        if (first == null) {
            first = last = t;
        } else {
            last.next = t;
            last = t;
        }
    }

    public T dequeue() {
        if (first == null) throw new NoSuchElementException();
        T data = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return data;
    }

    public T peek() {
        if (first == null) throw new NoSuchElementException();
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }


}