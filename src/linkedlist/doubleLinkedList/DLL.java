package linkedlist.doubleLinkedList;

public class DLL {

	public static void main(String[] args) {
		
		MyDoubleLinkedList linkedList = new MyDoubleLinkedList();
		
		for (int i = 0; i <= 9; i++) {
			linkedList.insertLast(i);
		}
		
		linkedList.display();
		linkedList.insertAt(1000, 100);
		linkedList.display();
	}
}
