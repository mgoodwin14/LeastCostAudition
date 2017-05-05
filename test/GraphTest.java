package test;

import main.Graph;
import main.GraphPath;
import org.junit.Ignore;
import org.junit.Test;

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
        subject.addRow("1 10 1 1 1");
        subject.addRow("2 2 2 2 2");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        assertEquals(10, result.getCost());
        assertEquals(true, result.isFinished());
        assertEquals(5, result.getRowsVisited().size());
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
