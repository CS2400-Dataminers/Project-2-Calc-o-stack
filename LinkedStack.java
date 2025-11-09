public class LinkedStack<T> implements StackInterface<T> {
    private class Node {
        T data;
        Node next;
        Node(T data, Node next) { this.data = data; this.next = next; }
    }

    private Node top = null;

    public void push(T item) {
        top = new Node(item, top);
    }

    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        T val = top.data;
        top = top.next;
        return val;
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}