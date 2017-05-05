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
        return null;
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
