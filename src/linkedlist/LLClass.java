package linkedlist;

public class LLClass {

	public static void main(String[] args) {
		
		MyLinkedList linkedList = new MyLinkedList();
		
		for (int i = 0; i <= 9; i++) {
			linkedList.insertLast(i);
		}
		
		//linkedList.insertRec(6, 120);
		//linkedList.deleteLast();
		//linkedList.deleteAt(2);
		
//		linkedList.sort();
		linkedList.display();
//		linkedList.reverseBetween(3, 5);
		linkedList.reverseKGroup(3);
		linkedList.display();
	}
	

}
