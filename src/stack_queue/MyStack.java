package stack_queue;

public class MyStack {

	protected int[] data;
	private static final int DEFAULT_SIZE = 5;
	
	private int pointer = -1;
	
	public MyStack(){
		this(DEFAULT_SIZE);
	}
	
	public MyStack(int size) {
		data = new int[size];
	}
	
	public boolean push(int item) {
		if (isFull()) {
			return false;
		}
		data[++pointer] = item;
		return true;
	}
	
	public int pop() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("Cannot pop from an empty stack!");
		}
		return data[pointer--];
	}
	
	public int peek() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("Cannot peek from an empty stack!");
		}
		return data[pointer];
	}
	
	/**
	 * It will return true if pointer is already at last position
	 * else false
	 * @return boolean (true/false)
	 */
	public boolean isFull() {
		return pointer >= data.length - 1; 
	}
	
	public boolean isEmpty() {
		return pointer == - 1;
	}
	
}
