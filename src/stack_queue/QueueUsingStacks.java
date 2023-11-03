package stack_queue;

import java.util.Stack;

class QueueUsingStacks {
    Stack<Integer> stack;
    Stack<Integer> helper;

    public QueueUsingStacks() {
        stack = new Stack<>();
        helper = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        while (!stack.isEmpty()) {
            helper.push(stack.pop());
        }
        int removed = helper.pop();

        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }

        return removed;
    }

    public int peek() {
        while (!stack.isEmpty()) {
            helper.push(stack.pop());
        }
        int peeked = helper.peek();

        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }

        return peeked;
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}

