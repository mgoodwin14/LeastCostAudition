package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 5/4/2017.
 */
public class Graph {
    private List<List<Integer>> graph = new ArrayList<>();

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
    
}
