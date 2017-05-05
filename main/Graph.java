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

        //add all of 0 index to queue
        for(int row =0; row < maxRows(); row++){
            Integer value = getRow(row).get(0);
            GraphPath initialPath = new GraphPath(0, row, value, row);
            addToQueue(initialPath);
        }

        while(!paths.isEmpty()){
            GraphPath path = popQueue();
            if(path.isFinished()){
                return path;
            }
            addEastChildPath(path);
            addNorthEastChildPath(path);
        }
        return null;
    }

    private int maxRows() {
        return graph.size();
    }

    private void addNorthEastChildPath(GraphPath path){
        int costOfNext;

        int row = (path.getRow()+1) % graph.size();

        costOfNext = getRow(row).get(path.getCol()+1);

        GraphPath northEastPath = GraphPath.nextNorthEast(path, row, costOfNext);
        if(northEastPath.getCol() == getRow(0).size()-1){
            northEastPath.setFinished(true);
        }
        addToQueue(northEastPath);
    }

    private void addEastChildPath(GraphPath path) {
        int costOfNext;

        costOfNext = getRow(path.getRow()).get(path.getCol()+1);
        GraphPath eastPath = GraphPath.nextEast(path, costOfNext);
        if(eastPath.getCol() == getRow(0).size()-1){
            eastPath.setFinished(true);
        }

        addToQueue(eastPath);
    }

    public void addToQueue(GraphPath path){
        paths.add(path);
    }

    public GraphPath popQueue(){
        if(paths.isEmpty()){
            return null;
        }
        return paths.remove();
    }
}
