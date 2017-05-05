package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Matt on 5/4/2017.
 */
public class Graph {
    private List<List<Integer>> graph = new ArrayList<>();
    private PriorityQueue<GraphPath> paths = new PriorityQueue<>(
            new Comparator<GraphPath>() {
                @Override
                public int compare(GraphPath o1, GraphPath o2) {
                    return o1.getCost() - o2.getCost();
                }
            });

    public void addRow(String row){
        String[] parts = row.split(" ");
        ArrayList<Integer> integers = new ArrayList<>();
        for(String s : parts){
             integers.add( Integer.valueOf(s) );
        }
        graph.add(integers);
    }

    public List<Integer> getRow(int i){
        return graph.get(i);
    }

    public GraphPath findShortestPath(){

        //add all of first column to queue
        for(int row = 0; row < numRows(); row++){
            Integer value = getRow(row).get(0);

            GraphPath initialPath = new GraphPath(0, row, value, row);
            addToQueue(initialPath);
        }

        while(!paths.isEmpty()){
            GraphPath path = popQueue();
            if(path.isFinished()){
                return path;
            }
            try {
                addChildPaths(path);
            } catch (IllegalArgumentException e){
                //all path child costs exceed 50
//                if(paths.isEmpty()){
//                    return path;
//                }

                if(paths.isEmpty()){
                    path.printPath();
                    return path;
                }else if(paths.peek().getCost() == 50){
                    return path;
                }
            }
        }
        return null;
    }

    private void addChildPaths(GraphPath path) {
        GraphPath eastPath = getEastChildPath(path);
        GraphPath northEast = getNorthEastChildPath(path);
        GraphPath southEast = getSouthEastChildPath(path);

        if(eastPath.getCost() > 50
                && northEast.getCost() > 50
                && southEast.getCost() > 50){
            throw new IllegalArgumentException("Cost too great.");
        }
        addToQueue(eastPath);
        addToQueue(northEast);
        addToQueue(southEast);
    }

    private int numRows() {
        return graph.size();
    }

    private GraphPath getNorthEastChildPath(GraphPath path){
        int costOfNext;

        int row = (path.getRow()+1) % graph.size();

        costOfNext = getRow(row).get(path.getCol()+1);

        GraphPath northEastPath = GraphPath.nextDiagonalEast(path, row, costOfNext);
        if(northEastPath.getCol() == getRow(0).size()-1){
            northEastPath.setFinished(true);
        }
        return northEastPath;
    }

    private GraphPath getEastChildPath(GraphPath path) {
        int costOfNext;

        costOfNext = getRow(path.getRow()).get(path.getCol()+1);
        GraphPath eastPath = GraphPath.nextEast(path, costOfNext);
        if(eastPath.getCol() == getRow(0).size()-1){
            eastPath.setFinished(true);
        }
        return eastPath;
    }

    private GraphPath getSouthEastChildPath(GraphPath path) {
        int costOfNext;

        int row = (path.getRow()-1) == -1 ? numRows()-1 : path.getRow()-1;

        costOfNext = getRow(row).get(path.getCol()+1);

        GraphPath southEastPath = GraphPath.nextDiagonalEast(path, row, costOfNext);
        if(southEastPath.getCol() == getRow(0).size()-1){
            southEastPath.setFinished(true);
        }
        return southEastPath;
    }

    public void addToQueue(GraphPath path){
        if(path.getCost() <= 50) {
            paths.add(path);
        }
    }

    public GraphPath popQueue(){
        if(paths.isEmpty()){
            return null;
        }
        return paths.remove();
    }
}
