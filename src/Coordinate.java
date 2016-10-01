import java.util.Objects;

/**
 * Created by renstange on 9/18/16.
 */
public class Coordinate extends Object{
    private int row;
    private int col;

    public Coordinate(int x, int y) {
        this.row = x;
        this.col = y;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String toString() {
        return ("Point: [" + getRow() + "," + getCol() + "]");
    }
    
    public boolean equals(Coordinate x) {
        if (this.row == x.row && this.col == x.col) {
            return true;
        } else {
            return false;
        }
    }
}
