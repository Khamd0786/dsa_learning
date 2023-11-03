package linkedlist.circularLinkedList;

public class CLL {

	public static void main(String[] args){
		
		MyCircularLinkedList ll = new MyCircularLinkedList();
		for (int i = 0 ; i <= 9; i++) {
			ll.insert(i);
		}
		
		ll.display();
		
	}
}
