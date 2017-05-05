package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 5/4/2017.
 */
public class GraphPath {
    private int cost;
    private boolean finished;
    private int row, col;
    private final List<Integer> rowsVisited = new ArrayList<>();

    public static GraphPath nextEast(GraphPath path, int nextCost){
        return new GraphPath(path, path.row, nextCost);
    }

    public static GraphPath nextDiagonalEast(GraphPath path, int nextRow, int costOfNext) {
        return new GraphPath(path, nextRow, costOfNext);
    }

    public GraphPath(){

    }

    public GraphPath(int col, int row, Integer cost, int initialRow) {
        this.col = col;
        this.row = row;
        this.cost = cost;
        this.rowsVisited.add(initialRow);
    }

    private GraphPath(GraphPath path, int nextRow, int nextCost){
        this.col = path.col+1;
        this.row = nextRow;
        this.cost = path.cost + nextCost;
        rowsVisited.addAll(path.rowsVisited);
        rowsVisited.add(nextRow);
    }

    public int getCost() {
        return cost;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public List<Integer> getRowsVisited() {
        return rowsVisited;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphPath graphPath = (GraphPath) o;

        if (cost != graphPath.cost) return false;
        if (finished != graphPath.finished) return false;
        if (row != graphPath.row) return false;
        if (col != graphPath.col) return false;
        return rowsVisited != null ? rowsVisited.equals(graphPath.rowsVisited) : graphPath.rowsVisited == null;
    }

    @Override
    public int hashCode() {
        int result = cost;
        result = 31 * result + (finished ? 1 : 0);
        result = 31 * result + row;
        result = 31 * result + col;
        result = 31 * result + rowsVisited.hashCode();
        return result;
    }

    public void printPath(){
        String output = finished ? "Yes" : "No";
        output += "\n" +cost + "\n";
        output += rowsVisited;

        System.out.print(output);
    }
}
