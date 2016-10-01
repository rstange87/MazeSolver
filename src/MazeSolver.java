import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by aaron on 9/20/16.
 */
public class MazeSolver {
    public static MazeDisplay myWindow;

    public static void findPath(Maze theMaze) {
        ArrayStack mazePath = new ArrayStack(theMaze.getRows() * theMaze.getCols());
        mazePath.push(theMaze.getStart());
        boolean finishReached = false;
        while (!finishReached) {
            Coordinate currentPosition = (Coordinate) mazePath.pop();
            theMaze.visit(currentPosition);
            mazePath.push(currentPosition);

            if (currentPosition.equals(theMaze.getFinish())) {
                finishReached = true;
            } else if ((currentPosition.getRow()>0) && !(theMaze.getSquareAt(theMaze.northOf(currentPosition)).getWall(Direction.SOUTH)) && !(theMaze.getSquareAt(theMaze.northOf(currentPosition)).isVisited())) {
                currentPosition = theMaze.northOf(currentPosition);
            } else if ((currentPosition.getRow()<theMaze.getRows()-1) && !(theMaze.getSquareAt(currentPosition).getWall(Direction.SOUTH)) && !(theMaze.getSquareAt(theMaze.southOf(currentPosition)).isVisited())) {
                currentPosition = theMaze.southOf(currentPosition);
            } else if ((currentPosition.getCol()<theMaze.getCols()-1) && !(theMaze.getSquareAt(currentPosition).getWall(Direction.EAST)) && !(theMaze.getSquareAt(theMaze.eastOf(currentPosition)).isVisited())) {
                currentPosition = theMaze.eastOf(currentPosition);
            } else if ((currentPosition.getCol()>0) && !(theMaze.getSquareAt(theMaze.westOf(currentPosition)).getWall(Direction.EAST)) && !(theMaze.getSquareAt(theMaze.westOf(currentPosition)).isVisited())) {
                currentPosition = theMaze.westOf(currentPosition);
            } else {
                theMaze.abandon(currentPosition);
                mazePath.pop();
                currentPosition = (Coordinate) mazePath.pop();
            }
            mazePath.push(currentPosition);
        }
        System.out.println(theMaze.getStart());
        System.out.println(theMaze.getFinish());
    }

    public static void main(String[] args) {
        Scanner fromUser = new Scanner(System.in);
        System.out.print("Number of rows? ");
        int ROWS = fromUser.nextInt();
        System.out.print("Number of cols? ");
        int COLS = fromUser.nextInt();

        for (int i = 0; i < 5; i++) {
            Maze aMaze = new Maze(ROWS, COLS);
            myWindow = new MazeDisplay(aMaze, ROWS, COLS);
            myWindow.showMaze();
            findPath(aMaze);
            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
            }
            myWindow.destroyMaze();
        }
    }
}