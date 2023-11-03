package stack_queue;

public class StackMain {

	public static void main(String[] args) {
		MyStack stack = new DynamicStack();
		stack.push(10);
		stack.push(12);
		stack.push(13);
		stack.push(14);
		stack.push(15);
		stack.push(18);
		stack.push(20);
		stack.push(22);
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}
}
