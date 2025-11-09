/****
 * A simple generic Stack ADT interface.
 * @param <T> element type
 */
public interface StackInterface<T> {
    /** Pushes an entry onto the top of this stack. */
    void push(T newEntry);

    /** Removes and returns this stack's top entry. */
    T pop();

    /** Retrieves this stack's top entry without removing it. */
    T peek();

    /** Detects whether this stack is empty. */
    boolean isEmpty();

    /** Removes all entries from this stack. */
    void clear();

    /** Returns the number of elements currently in the stack. */
    int size();
}
