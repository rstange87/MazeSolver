import java.util.Arrays;

/**
 * Created by renstange on 9/20/16.
 */
public class MazeSquare {
    private boolean visited;
    private boolean abandoned;
    private Coordinate myPosition;
    private boolean[] wall = {false, false, false, false};

    public MazeSquare(Coordinate position) {
        myPosition = position;
        this.clear();
    }

    public MazeSquare(Coordinate position, boolean[] wallSet) {
        this(position);
        for (int i=0; i < wallSet.length && i < wall.length; i++) {
            wall[i] = wallSet[i];
        }
    }

    public void toggleWall(Direction dir) {
        wall[dir.value()] = !wall[dir.value()];
    }

    public boolean getWall(Direction dir) {
        return wall[dir.value()];
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public boolean isAbandoned() {
        return abandoned;
    }

    public void abandon() {
        abandoned = true;
    }

    public void clear() {
        abandoned = false;
        visited = false;
    }

    public boolean equals(MazeSquare other) {
        return (this.myPosition.equals(other.myPosition));
    }
    public Coordinate getPosition() {
        return myPosition;
    }

    public String toString() {
        return ("Location: " + this.myPosition + "\nVisited: " + this.visited + "\nAbandoned: " + this.abandoned + "\n");
    }
}
