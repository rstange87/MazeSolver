/**
 * Created by renstange on 9/18/16.
 */
public class ArrayStack<E> implements Stack {
    private E[] stackArray;
    private int stackTop;

    public ArrayStack() {
        this(100);
    }
    public ArrayStack(int setSize) {
        stackTop = 0;
        stackArray = (E[]) new Object[setSize];
    }

    @Override
    public E pop() {
        if (this.stackTop == 0) {
            return null;
        } else {
            return stackArray[this.stackTop-- - 1];
        }
    }

    @Override
    public E top() {
        if (this.stackTop == 0) {
            return null;
        } else {
            return stackArray[this.stackTop - 1];
        }
    }

    @Override
    public int size() {
        return stackArray.length;
    }

    @Override
    public void push(Object elt) {
        stackArray[this.stackTop++] = (E) elt;
    }

    public void print() {
        if (this.stackTop == 0) {
            System.out.print("Stack is empty.\n");
        } else {
            for (int i=stackTop; i>0; i--) {
                System.out.print(stackArray[i-1] + "\n");
            }
        }
    }
}
