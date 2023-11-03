package stack_queue;

public class DynamicQueue extends CircularQueue {

    public DynamicQueue() {
        super();
    }

    public DynamicQueue(int size) {
        super(size);
    }

    @Override
    public boolean insert(int item) {
        if (isFull()) {
            int f = front;
            int i = 0;
            int[] newArr = new int[data.length * 2];
            do {
                newArr[i++] = data[f++];
                f %= data.length;
            } while (f != end);
            front = 0;
            end = i;
            data = newArr;
        }
        return super.insert(item);
    }
}
