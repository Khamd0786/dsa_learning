package linkedlist.circularLinkedList;

public class MyCircularLinkedList {
	
	Node head;
	Node tail;
	int size;
	
	public MyCircularLinkedList() {
		size = 0;
	}
	
	public void insert(int value) {
		Node node =  new Node(value, null, head);
		
		if (head == null ) { //first insertion
			head = node;
			tail = node;
			
			size++;
			return;
		}
		
		tail.next = node;
		tail = node;
		
		size++;
	}
	
	public void display() {
		if (head != null) {
			Node node= head;
			do {
				System.out.print(node.value + " -> ");
				node = node.next;
			} while (node != head);
			System.out.println("END");
		}
	}
	
	
	private class Node {
		int value;
		Node prev;
		Node next;
		
		public Node(int value) {
			this.value = value;
		}
		
		public Node(int value, Node prev, Node next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}
}
