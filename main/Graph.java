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
        int col = 0;
        int row = 0;
        Integer value = getRow(row).get(col);
        GraphPath initialPath = new GraphPath(col, row, value, row+1);

        addToQueue(initialPath);

        while(!paths.isEmpty()){
            GraphPath path = popQueue();
            if(path.isFinished()){
                return path;
            }

            addChildPaths(path, paths);
        }
        return null;
    }

    private void addChildPaths(GraphPath path, PriorityQueue<GraphPath> paths) {
        GraphPath eastPath = GraphPath.nextEast(path);
        if(eastPath.getCol() > getRow(0).size()){
            eastPath.setFinished(true);
        }
        paths.add(eastPath);
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
