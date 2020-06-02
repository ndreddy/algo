package algo.queues;

import java.util.NoSuchElementException;

public class ArrayQueue {
    int[] arr;
    int first;
    int last;

    public ArrayQueue(int size) {
        arr = new int[size];
        first = last = -1;
    }

    public void enqueue(int item) {
        if (first == -1) {
            first = last = 0;
        }
        if (last < arr.length)
            arr[last++] = item;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    public int dequeue() {
        if (first == -1) throw new NoSuchElementException();
        int t = arr[first];
        last--;
        for (int i = 0; i < last; i++) {
            arr[i] = arr[i + 1];
        }
        return t;
    }
}
