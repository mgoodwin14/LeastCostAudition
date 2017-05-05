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

    public int getCost() {
        return cost;
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
}
