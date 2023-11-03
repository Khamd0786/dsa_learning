package leetcodeQuestions;

class MyLinkedList2 {
	ListNode head;
	ListNode tail;
	int size;

	public MyLinkedList2() {
		size = 0;
	}

	public int get(int index) {
		ListNode node = getNode(index);
		if (node == null) {
			return -1;
		} else {
			return node.val;
		}
	}

	public void addAtHead(int val) {
		ListNode node = new ListNode(val, head);
		this.head = node;

		if (tail == null) {
			this.tail = head;
		}

		size++;
	}

	public void addAtTail(int val) {
		if (tail == null) {
			addAtHead(val);
			return;
		}

		ListNode node = new ListNode(val);
		tail.next = node;
		tail = node;

		size++;
	}

	public void addAtIndex(int index, int val) {
		if (index < 0 || index > size) {
			return;
		}

		if (index == 0) {
			addAtHead(val);
			return;
		}
		
		if (index == size) {
			addAtTail(val);
			return;
		}

		ListNode lastNode = getNode(index - 1);
		if (lastNode == null) {
			return;
		}

		ListNode node = new ListNode(val, lastNode.next);
		lastNode.next = node;

		size++;
	}

	private ListNode getNode(int index) {
		if (index < 0 || index >= size) {
			return null;
		}

		ListNode current = head;
		for (int i = 1; current != null && i <= index; i++) {
			current = current.next;
		}

		return current;
	}

	public void deleteAtIndex(int index) {
		if (index < 0 || index >= size) {
			return;
		}

		ListNode secondLast = getNode(index - 1);

		if (secondLast == null) {
			head = head.next;
		} else {
			ListNode current = secondLast.next;
			secondLast.next = current.next;
		}

		size--;
	}

	private class ListNode {
		int val;
		ListNode next;

		public ListNode() {

		}

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

	}
	
	public void display() {
		ListNode current =  head;
		
		while (current != null) {
			System.out.print(current.val + " -> ");
			current = current.next;
		}
		
		System.out.println("END");
	}
}
