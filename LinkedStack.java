public class LinkedStack<T> implements StackInterface<T> { // Linked-list-based stack implementation that can store any type T
    private class Node {
        T data;
        Node next;
        Node(T data, Node next) { this.data = data; this.next = next; } // Node constructor
    }

    private Node top = null; // Most recently added node

    public void push(T item) { // Pushes new item to the top of the stack
        top = new Node(item, top);
    }

    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty"); // Error if stack is empty
        T val = top.data;
        top = top.next; // Moves top node to the next leaving the top node empty for garbage collection
        return val; // Returns the value of the popped node
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return top.data; // Returns the value of the top node without removing it
    }

    public boolean isEmpty() {
        return top == null;
    }
}