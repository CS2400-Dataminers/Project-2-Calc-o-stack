import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizeableArrayStack<T> implements StackInterface<T> {
    private Object[] a = new Object[8];
    private int n = 0;

    public void push(T x){ if (n==a.length) a = Arrays.copyOf(a, a.length*2); a[n++] = x; }
    @SuppressWarnings("unchecked")
    public T pop(){ if (isEmpty()) throw new EmptyStackException(); T v=(T)a[--n]; a[n]=null; if (n>0 && n==a.length/4) a=Arrays.copyOf(a, a.length/2); return v; }
    @SuppressWarnings("unchecked")
    public T peek(){ if (isEmpty()) throw new EmptyStackException(); return (T)a[n-1]; }
    public boolean isEmpty(){ return n==0; }
    public void clear(){ Arrays.fill(a,0,n,null); n=0; }
    public int size(){ return n; }
}