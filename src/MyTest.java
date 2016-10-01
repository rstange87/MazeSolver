/**
 * Created by renstange on 9/20/16.
 */
public class MyTest {

    public static void main (String[] args) {
        ArrayStack testStack = new ArrayStack();
        Coordinate testObject = new Coordinate(0,0);
        Coordinate testObject2 = new Coordinate(1,2);
        Coordinate testObject3 = new Coordinate(0,0);
        Coordinate testObject4 = new Coordinate(2,3);
        testStack.push(testObject);
        testStack.push(testObject2);
        testStack.push(testObject3);
        testStack.push(testObject4);
        System.out.print(testStack.size()+"\n");
        testStack.print();
        System.out.print(testStack.pop()+"\n");
        testStack.print();
        System.out.print(testStack.top()+"\n");
        System.out.print(testStack.pop()+"\n");
        System.out.print(testStack.pop()+"\n");
        System.out.print(testStack.pop()+"\n");
        System.out.print(testStack.pop()+"\n");
    }
}
