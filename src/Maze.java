import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by renstange on 9/20/16.
 */
public class Maze {
    private MazeSquare[][] square;
    private Coordinate startPosition;
    private Coordinate finishPosition;
    private int numRows;
    private int numCols;

    public Maze(int rows, int cols){
        this.numRows = rows;
        this.numCols = cols;
        //Generates start and finish.
        startPosition = new Coordinate((int)(Math.random()*100) % numRows,0);
        do {
            finishPosition = new Coordinate((int)(Math.random()*100) % numRows,numCols-1);
        } while (finishPosition.equals(startPosition));

        this.square = new MazeSquare[rows][cols];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                square[i][j] = new MazeSquare(new Coordinate(i,j), new boolean[]{false, true, true, false});
                if (i==0) {
                    square[i][j].toggleWall(Direction.NORTH);
                }
                if (j==0 && !square[i][j].getPosition().equals(startPosition)) {
                    square[i][j].toggleWall(Direction.WEST);
                }
                if (square[i][j].equals(finishPosition)) {
                    square[i][j].toggleWall(Direction.EAST);
                }
            }
        }
        this.generateMaze();
        this.clear();
    }

    public MazeSquare getSquareAt(Coordinate p) {
        return this.square[p.getRow()][p.getCol()];
    }

    public void visit(Coordinate p) {
        square[p.getRow()][p.getCol()].visit();
    }

    public void abandon(Coordinate p) {
        square[p.getRow()][p.getCol()].abandon();
    }

    public Coordinate getStart() {
        return startPosition;
    }

    public Coordinate getFinish() {
        return finishPosition;
    }

    public Coordinate northOf(Coordinate p) {
        return new Coordinate((p.getRow()-1),p.getCol());
    }

    public Coordinate southOf(Coordinate p) {
        return new Coordinate((p.getRow()+1),p.getCol());
    }

    public Coordinate eastOf(Coordinate p) {
        return new Coordinate(p.getRow(),(p.getCol()+1));
    }

    public Coordinate westOf(Coordinate p) {
        return new Coordinate(p.getRow(),(p.getCol()-1));
    }

    public int getRows() { return this.numRows; }

    public int getCols() { return this.numCols; }

    public boolean movePossible(Coordinate from, Coordinate to) {
        return (!from.equals(finishPosition) && validPosition(from) && validPosition(to) && (northOf(from).equals(to) || southOf(from).equals(to) || eastOf(from).equals(to) || westOf(from).equals(to)) && !square[to.getRow()][to.getCol()].isVisited() && !square[to.getRow()][to.getCol()].isAbandoned());
    }

    private void clear() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                square[i][j].clear();
            }
        }
    }

    private boolean validPosition(Coordinate p) {
        return (p.getCol()>=0 && p.getCol()< numCols && p.getRow()>=0 && p.getRow()< numRows);
    }

    private ArrayList<Coordinate> unvisitedNeighbors(Coordinate p) {
        ArrayList<Coordinate> list = new ArrayList<Coordinate>();

        int r = p.getRow();
        int c = p.getCol();

        if (r > 0 && !square[r - 1][c].isVisited())
            list.add(new Coordinate(r - 1, c));
        if (r < numRows - 1 && !square[r + 1][c].isVisited())
            list.add(new Coordinate(r + 1, c));
        if (c > 0 && !square[r][c - 1].isVisited())
            list.add(new Coordinate(r, c - 1));
        if (c < numCols - 1 && !square[r][c + 1].isVisited())
            list.add(new Coordinate(r, c + 1));

        return list;
    }

    private void generateMaze() {
        Coordinate currentPosition = this.startPosition;
        ArrayStack mazePath = new ArrayStack(this.numRows*this.numCols);
        int squaresVisited = 0;
        do {
            if (!(this.getSquareAt(currentPosition).isVisited())) {
                visit(currentPosition);
                squaresVisited++;
                mazePath.push(currentPosition);
            }
            ArrayList<Coordinate> availSpots = unvisitedNeighbors(currentPosition);
            if (availSpots.size() > 0 && !currentPosition.equals(this.getFinish())) {
                int randDirection = (int)((Math.random()*100) % availSpots.size());
                if (availSpots.get(randDirection).equals(northOf(currentPosition))) {
                    currentPosition = northOf(currentPosition);
                    getSquareAt(currentPosition).toggleWall(Direction.SOUTH);
                } else if (availSpots.get(randDirection).equals(southOf(currentPosition))) {
                    getSquareAt(currentPosition).toggleWall(Direction.SOUTH);
                    currentPosition = southOf(currentPosition);
                } else if (availSpots.get(randDirection).equals(eastOf(currentPosition))) {
                    getSquareAt(currentPosition).toggleWall(Direction.EAST);
                    currentPosition = eastOf(currentPosition);
                } else if (availSpots.get(randDirection).equals(westOf(currentPosition))) {
                    currentPosition = westOf(currentPosition);
                    getSquareAt(currentPosition).toggleWall(Direction.EAST);
                }
            } else {
                if (!currentPosition.equals(this.finishPosition)) {
                    abandon(currentPosition);
                }
                mazePath.pop();
                currentPosition = (Coordinate) mazePath.top();

            }


        } while (squaresVisited < mazePath.size());

    }

    public static void main(String[] args) {
        int tempCols = 30;
        int tempRows = 30;
        Maze testMaze = new Maze(tempRows,tempCols);
        System.out.print("Start: " + testMaze.getStart() + "\nFinish: " + testMaze.getFinish() + "\n");
        MazeDisplay myDisplay = new MazeDisplay(testMaze, tempRows, tempCols);
        myDisplay.showMaze();

        //Prints maze.
        String printMaze = "";

        printMaze += "\n";
        for (int i=-1; i<tempRows; i++) {
            for (int j=0; j<tempCols; j++) {
                if (i == -1 && testMaze.square[i+1][j].getWall(Direction.NORTH)) {
                    printMaze += " _";
                } else if (j == 0 && (i != testMaze.getStart().getRow())) {
                    printMaze += "|";
                } else if (j == 0 && (i == testMaze.getStart().getRow())) {
                    printMaze += " ";
                }
                if (i != -1 && testMaze.square[i][j].getWall(Direction.SOUTH)) {
                    printMaze += "_";
                } else if (i != -1){
                    printMaze += " ";
                }
                if (i != -1 && testMaze.square[i][j].getWall(Direction.EAST) && !(i == testMaze.getFinish().getRow() && j == testMaze.getFinish().getCol())) {
                    printMaze += "|";
                } else if (i != -1){
                    printMaze += ".";
                }

            }
            printMaze += "\n";

        }
        System.out.print(printMaze);
    }
}
