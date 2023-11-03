package stack_queue;

import java.util.Arrays;

public class MyQueue {
	
	private int[] data;
	
	private static final int DEFAULT_SIZE = 5;
	
	private int end = 0;
	
	public MyQueue() {
		this(DEFAULT_SIZE);
	}
	
	public MyQueue(int size) {
		data = new int[size];
	}
	
	public boolean isFull() {
		return end >= data.length;
	}
	
	public boolean isEmpty() {
		return end == -1;
	}
	
	public boolean insert(int item) {
		if (isFull()) {
			return false;
		}
		
		data[end++] = item;
		return true;
	}
	
	public int remove() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("Can't Remove as Queue is empty!");
		}
		
		int removed = data[0];

		//shift the items towards the left;
		for (int i = 1; i < end; i++) {
			data[i-1] = data[i];
		}
		end--;

		return removed;
	}
	
	public int front() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("Can't Remove as Queue is empty!");
		}
		
		return data[0];
	}
	
	public void display() {
		for (int i = 0; i < end; i++) {
			System.out.print(data[i] + " <- ");
		}
		System.out.println("END");
	}
	
	
}
