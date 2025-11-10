import java.util.Arrays;

public class ResizeableArrayStack<T> implements StackInterface<T> { // Array-based stack implementation that can store any type T
    private Object[] a = new Object[8];
    private int n = 0;

    /*
     * Doubles the size of the array if needed and pushes x onto the stack.
     */
    public void push(T x) {
        if (n == a.length) a = Arrays.copyOf(a, a.length * 2); 
        a[n++] = x; 
    }
    /*
     * Pops and returns the top element of the stack.
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        T val = (T) a[--n]; 
        a[n] = null;
        return val;
    }
    /*
     * Returns the top element of the stack without removing it.
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return (T) a[n - 1];
    }

    public boolean isEmpty() {
        return n == 0;
    }
}