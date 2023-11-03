package linkedlist;

public class MyLinkedList {

	private ListNode head;
	private ListNode tail;
	private int size;

	public MyLinkedList() {
		this.size = 0;
	}

	public void insertFirst(int value) {
		ListNode node = new ListNode(value, head);
		this.head = node;

		if (tail == null) {
			this.tail = head;
		}

		size += 1;
	}

	public void insertLast(int value) {
		if (tail == null) {
			insertFirst(value);
			return;
		}

		ListNode node = new ListNode(value);
		tail.next = node;
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

		ListNode tempHead = head;

		for (int i = 1; i < index && i < size; i++) {
			tempHead = tempHead.next;
		}

		ListNode nextTempNode = tempHead.next;

		ListNode node = new ListNode(value, tempHead.next);
		tempHead.next = node;

		size++;
	}

	public void insertRec(int index, int value) {
		head = insertRec(value, index, head);
	}

	private ListNode insertRec(int value, int index, ListNode node) {
		if (index == 0) {
			ListNode temp = new ListNode(value, node);
			size++;
			return temp;
		}

		node.next = insertRec(value, index - 1, node.next);
		return node;
	}

	public void deleteFirst() {
		if (head == null) {
			return;
		}

		ListNode newHead = head.next;
		head = newHead;

		if (head == null) {
			tail = null;
		}

		size--;
	}

	public void deleteLast() {
		if (size <= 0) {
			deleteFirst();
			return;
		}

		ListNode secondLast = get(size - 2);
		secondLast.next = null;
		tail = secondLast;

		size--;
	}

	public void deleteAt(int index) {
		if (index == 0) {
			deleteFirst();
			return;
		}

		ListNode secondLast = get(index - 1);
		ListNode last = secondLast.next;

		secondLast.next = last.next;

		size--;

	}

	public ListNode get(int index) {
		ListNode node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}

		return node;
	}

	public void display() {
		ListNode temp = head;
		if (isCyclic()) {
			System.out.println("List is Cyclic, hence cannot be printed directly");
			return;
		}

		while (temp != null) {
			System.out.print(temp.value + " -> ");
			temp = temp.next;
		}
		System.out.println("END");
	}

	public boolean isCyclic() {
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}

		return false;
	}

	public ListNode findValue(int value) {
		ListNode node = head;

		while (node != null) {
			if (node.value == value) {
				return node;
			}
			node = node.next;
		}

		return null;
	}

	public void sort() {
		head = sort(head);
	}

	/**
	 * 
	 * @param node head of the LinkedList, which needs to sort;
	 * @return node which will be the head of sorted LinkedList
	 */
	private ListNode sort(ListNode node) {
		if (node == null || node.next == null) {
			return node;
		}

		ListNode mid = getMid(node); // mid will break it into two half as well
		ListNode first = sort(node);
		ListNode second = sort(mid);

		return merge(first, second);
	}

	private ListNode merge(ListNode head1, ListNode head2) {
		ListNode dummyHead = new ListNode();
		ListNode node = dummyHead;

		while (head1 != null && head2 != null) {
			if (head1.value < head2.value) {
				node.next = head1;
				head1 = head1.next;
			} else {
				node.next = head2;
				head2 = head2.next;
			}
			node = node.next;
		}

		node.next = (head1 != null) ? head1 : head2;

		return dummyHead.next;
	}

	private ListNode middleNode(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	private ListNode getMid(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		ListNode prev = null;

		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		if (prev != null) {
			prev.next = null; // breaking the link in two half's
		}

		return slow;
	}

	public void reverse() {
		head = reverse(head);
	}

	private ListNode reverse(ListNode node) {
		if (node == null) {
			return node;
		}

		ListNode prev = null;
		ListNode pres = node;
		ListNode next = node.next;

		while (pres != null) {
			pres.next = prev;
			prev = pres;
			pres = next;
			if (next != null) {
				next = next.next;
			}
		}

		return prev;
	}

	// https://leetcode.com/problems/palindrome-linked-list/
	// asked in Google, Facebook, Amazon, Microsoft, etc...
	public boolean isPalindrome(ListNode head) {
		ListNode mid = middleNode(head);
		ListNode secondHead = reverse(mid);

		ListNode pointer1 = head;
		ListNode pointer2 = secondHead;

		while (pointer1 != null && pointer2 != null && pointer1 != secondHead) {
			if (pointer1.value != pointer2.value) {
				return false;
			}
			pointer1 = pointer1.next;
			pointer2 = pointer2.next;
		}

		return true;
	}

	// https://leetcode.com/problems/reorder-list/
	// asked in Google, Facebook, Amazon, Microsoft, etc...
	public void reorderList(ListNode head) {
		ListNode mid = middleNode(head);
		ListNode secondHead = reverse(mid);

		ListNode hf = head;
		ListNode hs = secondHead;

		while (hf != null && hs != null) {
			ListNode temp = hf.next;
			hf.next = hs;
			hf = temp;

			temp = hs.next;
			hs.next = hf;

			hs = temp;
		}

		if (hf != null) {
			hf.next = null;
		}
	}

	public void reverseKGroup(int k) {
		reverseKGroup(head, k);
	}
	
	// https://leetcode.com/problems/reverse-nodes-in-k-group/
	// asked in Google, Facebook, Amazon, Microsoft, etc...	
	private void reverseKGroup(ListNode head, int k) {
		if (k <= 1 && head == null) {
			return;
		}
		
		ListNode prev = null;
		ListNode current = head;
		
		while(current != null) {
			ListNode last = prev;
			ListNode newEnd = current;
			
			ListNode next = current.next;
			
			for (int i = 0; current != null && i < k; i++) {
				current.next = prev;
				prev = current;
				current = next;
				if (next != null) {
					next =  next.next;
				}
			}
			
			if (last != null) {
				last.next = prev;
			} else {
				head = prev;
			}
			
			newEnd.next = current;
			
			if (current == null) {
				break;
			}
			
			prev = newEnd;
		}
	}

	public void reverseBetween(int left, int right) {
		if (left >= size || right >= size) {
			return;
		}

		head = reverseBetween(head, left, right);
	}

	// important**
	private ListNode reverseBetween(ListNode head, int left, int right) {
		if (left == right) {
			return head;
		}

		ListNode prev = null;
		ListNode current = head;

		// skip the first left-1 nodes
		for (int i = 0; current != null && i < left - 1; i++) {
			prev = current;
			current = current.next;
		}

		ListNode last = prev;
		ListNode newEnd = current;

		ListNode next = current.next;
		for (int i = 0; current != null && i < right - left + 1; i++) {
			current.next = prev;
			prev = current;
			current = next;
			if (next != null) {
				next = next.next;
			}
		}

		if (last != null) {
			last.next = prev;
		} else {
			head = prev;
		}

		newEnd.next = current;
		return head;
	}

	public void reverseRec() {
		reverseRec(head);
	}

	private void reverseRec(ListNode node) {
		if (node == null) {
			return;
		}

		if (node == tail) {
			head = tail;
			return;
		}

		reverseRec(node.next);

		tail.next = node;
		tail = node;
		tail.next = null;

	}

	private class ListNode {
		private int value;
		private ListNode next;

		public ListNode() {

		}

		public ListNode(int value) {
			this.value = value;
		}

		public ListNode(int value, ListNode next) {
			this.value = value;
			this.next = next;
		}
	}

}
