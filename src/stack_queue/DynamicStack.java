package stack_queue;

public class DynamicStack extends MyStack {
	
	public DynamicStack() {
		super(); // it will call MyStack()
	}
	
	public DynamicStack(int size) {
		super(size); //it will call MyStack(int size)
	}
	
	@Override
	public boolean push(int item) {
		if (this.isFull()) {
			int[] newArr = new int[data.length * 2];
			for(int i = 0; i < data.length; i++) {
				newArr[i] = data[i];
			}
			data = newArr;
		}
		
		return super.push(item);
	}
}
