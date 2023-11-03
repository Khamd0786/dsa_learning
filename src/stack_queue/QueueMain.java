package stack_queue;

public class QueueMain {

	public static void main(String[] args) {
		CircularQueue queue = new DynamicQueue();
		
		queue.insert(3);
		queue.insert(31);
		queue.insert(32);
		queue.insert(33);
		queue.insert(34);
		
		queue.display();

		System.out.println(queue.remove()+"");
		queue.insert(3455);
		queue.insert(23);
		queue.insert(231);
		queue.insert(232);
		queue.insert(233);
		queue.insert(234);
		queue.display();

		System.out.println(queue.remove()+"");
		queue.display();
		
		
	}
}
