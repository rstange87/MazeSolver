/**
 * Created by renstange on 9/18/16.
 */
public interface Stack<E> {
    public E pop(); //remove an element and return.
    public E top(); //return the top element without removing.
    public int size(); //return number of elements in the stack.
    public void push(E elt); //add element to the top of the stack.
}
