package test;

import main.Graph;
import main.GraphPath;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Matt on 5/4/2017.
 */
public class GraphTest {

    @Test
    public void addRowToGraphTest(){
        Graph subject = new Graph();

        subject.addRow("1 2 3 4 5");

        assertEquals(1, subject.getRow(0).get(0).intValue());
        assertEquals(2, subject.getRow(0).get(1).intValue());
        assertEquals(3, subject.getRow(0).get(2).intValue());
        assertEquals(4, subject.getRow(0).get(3).intValue());
        assertEquals(5, subject.getRow(0).get(4).intValue());

        subject.addRow("1 2 3 4 5");

        assertEquals(1, subject.getRow(1).get(0).intValue());
        assertEquals(2, subject.getRow(1).get(1).intValue());
        assertEquals(3, subject.getRow(1).get(2).intValue());
        assertEquals(4, subject.getRow(1).get(3).intValue());
        assertEquals(5, subject.getRow(1).get(4).intValue());
    }

    @Test
    public void getShortestPath(){
        Graph subject = new Graph();
        subject.addRow("1 2 3 4 5");

        GraphPath result = subject.findShortestPath();

        GraphPath expected = new GraphPath(0, 0, 1 , 0);
        expected = GraphPath.nextEast(expected, 2);
        expected = GraphPath.nextEast(expected, 3);
        expected = GraphPath.nextEast(expected, 4);
        expected = GraphPath.nextEast(expected, 5);
        expected.setFinished(true);

        assertNotNull(result);
//        assertEquals(expected, result);
        assertEquals(1+2+3+4+5, result.getCost());
    }

    @Test
    public void getShortestPath_2Rows(){
        Graph subject = new Graph();
        subject.addRow("1 10 1 10 1");
        subject.addRow("2 2 2 2 2");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        assertEquals(1+2+1+2+1, result.getCost());
        assertEquals(true, result.isFinished());
        assertEquals(5, result.getRowsVisited().size());
        assertEquals(0, result.getRowsVisited().get(0).intValue());
        assertEquals(1, result.getRowsVisited().get(1).intValue());
        assertEquals(0, result.getRowsVisited().get(2).intValue());
        assertEquals(1, result.getRowsVisited().get(3).intValue());
        assertEquals(0, result.getRowsVisited().get(4).intValue());
    }

    @Test
    public void getShortestPath_case1(){
        Graph subject = new Graph();

        subject.addRow("3 4 1 2 8 6 ");
        subject.addRow("6 1 8 2 7 4");
        subject.addRow("5 9 3 9 9 5");
        subject.addRow("8 4 1 3 2 6");
        subject.addRow("3 7 2 8 6 4");

        GraphPath path = subject.findShortestPath();

        assertNotNull(path);
        assertEquals(16, path.getCost());

        List<Integer> expectedRows = new ArrayList<>();
        expectedRows.add(1-1);
        expectedRows.add(2-1);
        expectedRows.add(3-1);
        expectedRows.add(4-1);
        expectedRows.add(4-1);
        expectedRows.add(5-1);

        assertEquals(expectedRows, path.getRowsVisited());
    }

    @Test
    public void getShortestPath_case2(){
        //fails but path is same total cost

        Graph subject = new Graph();
        subject.addRow("3 4 1 2 8 6");
        subject.addRow("6 1 8 2 7 4");
        subject.addRow("5 9 3 9 9 5");
        subject.addRow("8 4 1 3 2 6");
        subject.addRow("3 7 2 1 2 3");

        GraphPath path = subject.findShortestPath();

        assertNotNull(path);
        assertEquals(11, path.getCost());

        List<Integer> expectedRows = new ArrayList<>();
        expectedRows.add(1-1);
        expectedRows.add(2-1);
        expectedRows.add(1-1);
        expectedRows.add(5-1);
        expectedRows.add(4-1);
        expectedRows.add(5-1);

        assertEquals(expectedRows, path.getRowsVisited());
    }

    @Test
    public void getShortestPath_case3(){
        Graph subject = new Graph();
        subject.addRow("19 10 19 10 19");
        subject.addRow("21 23 20 19 12");
        subject.addRow("20 12 20 11 10");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
//        assertEquals(48, result.getCost());

        System.out.print(result.getCost());
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(0);
        expectedPath.add(0);
        expectedPath.add(0);
        assertEquals(expectedPath, result.getRowsVisited());
    }

    @Test
    public void getShortestPath_1X5matrix(){
        Graph subject = new Graph();
        subject.addRow("5 8 5 3 5");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        assertEquals(26, result.getCost());
    }

    @Test
    public void getShortestPath_5x1matrix(){
        Graph subject = new Graph();
        subject.addRow("5");
        subject.addRow("8");
        subject.addRow("5");
        subject.addRow("3");
        subject.addRow("5");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        assertEquals(3, result.getCost());
    }

    @Test
    public void priorityQueueTest(){
        Graph subject = new Graph();

        GraphPath path = new GraphPath(0,0,0,0);
        subject.addToQueue(path);

        assertEquals(path, subject.popQueue());
        assertNull(subject.popQueue());
    }
}
