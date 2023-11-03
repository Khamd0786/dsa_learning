package stack_queue;

public class CircularQueue {

    protected int[] data;
    private static final int DEFAULT_SIZE= 5;

    protected int front = 0;
    protected int end = 0;
    private int size = 0;

    public CircularQueue() {
        this(DEFAULT_SIZE);
    }

    public CircularQueue(int size) {
        data = new int[size];
    }

    public boolean isFull() {
        return size >= data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean insert(int item) {
        if (isFull()) {
            return false;
        }

        data[end++] = item;
        end = end % data.length;
        size++;
        return true;
    }

    //it will always remove the first item, same behaviour of queue (First come, First out)
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty, cannot remove!");
        }

        int removed = data[front++];
        front = front % data.length;
        size--;
        return removed;
    }

    public int front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        return data[front];
    }

    public void display() {
       if (isEmpty()) {
           System.out.printf("Empty");
           return;
       }

       int i = front;
       do {
           System.out.print(data[i] + " -> ");
           i++;
           i %= data.length;
       } while (i != end);
        System.out.println("END");
    }

}
