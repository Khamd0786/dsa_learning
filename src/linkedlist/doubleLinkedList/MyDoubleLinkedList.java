package linkedlist.doubleLinkedList;

public class MyDoubleLinkedList {

	Node head;
	Node tail;
	int size;
	
	public MyDoubleLinkedList() {
		size = 0;
	}
	
	public void insertFirst(int value) {
		Node node = new Node(value, null, head);
		if (head != null) {
			head.prev = node;	
		}
		head = node;
		
		if (tail == null) {
			tail = head;
		}
		
		size++;
	}
	
	public void insertLast(int value) {
		if (head == null) {
			insertFirst(value);
			return;
		}
		
		Node node = new Node(value, tail, null);
		if (tail != null) {
			tail.next = node;	
		}
		tail = node;
		
		size++;
	}
	
	public void insertAt(int index, int value) {
		if (index == 0) {
			insertFirst(value);
			return;
		}
		
		if (index >= size) {
			insertLast(value);
			return;
		}
		
		Node prevNode  = get(index); //indexed Previous node;
		Node nextNode = prevNode.next;
		
		Node node = new Node(value, prevNode, nextNode);
		prevNode.next = node;
		if (nextNode != null) {
			nextNode.prev = node;	
		}
		
		size++;
	}
	
	public Node get(int index) {
		if (index == 0) {
			return head;
		}
		
		Node node = head;
		for (int i = 1; i < index && i < size; i++) {
			node = node.next;
		}
		
		return node;
	}
	
	public void display() {
		Node node = head;
		while(node != null) {
			System.out.print(node.value + " -> ");
			node = node.next; 
		}
		
		System.out.println("END");
	}
	
	public void displayRev() {
		Node node = tail;
		while (node != null) {
			System.out.print(node.value + " <- ");
			node = node.prev; 
		}
		
		System.out.println("END");
	}
	
	
	
	private class Node {
		int value;
		Node next;
		Node prev;
		
		public Node(int value) {
			this.value = value;
		}
		
		public Node(int value, Node prev, Node next) {
			this.value =value;
			this.prev = prev;
			this.next = next;
		}
	}
}
