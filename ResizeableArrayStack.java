import java.util.Arrays;

public class ResizeableArrayStack<T> implements StackInterface<T> {
    private Object[] a = new Object[8];
    private int n = 0;

    public void push(T x) {
        if (n == a.length) a = Arrays.copyOf(a, a.length * 2);
        a[n++] = x;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        T val = (T) a[--n];
        a[n] = null;
        return val;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return (T) a[n - 1];
    }

    public boolean isEmpty() {
        return n == 0;
    }
}