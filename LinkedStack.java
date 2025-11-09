import java.util.EmptyStackException;

class Node<T> { T data; Node<T> next; Node(T d, Node<T> n){data=d;next=n;} }

public class LinkedStack<T> implements StackInterface<T> {
    private Node<T> top;
    private int n;

    public void push(T x){ top = new Node<>(x, top); n++; }
    public T pop(){ if (isEmpty()) throw new EmptyStackException(); T v=top.data; top=top.next; n--; return v; }
    public T peek(){ if (isEmpty()) throw new EmptyStackException(); return top.data; }
    public boolean isEmpty(){ return top==null; }
    public void clear(){ top=null; n=0; }
    public int size(){ return n; }
}