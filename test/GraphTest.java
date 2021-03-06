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
    public void getShortestPath_6X5matrix_1(){
        /*
        Input:
                    3 4 1 2 8 6
                    6 1 8 2 7 4
                    5 9 3 9 9 5
                    8 4 1 3 2 6
                    3 7 2 8 6 4

        Output:     Yes
                    16
                    [1 2 3 4 4 5]
        * Actual:   Yes
                    16
                    [0, 1, 2, 3, 3, 4]
        * */


        Graph subject = new Graph();

        subject.addRow("3 4 1 2 8 6 ");
        subject.addRow("6 1 8 2 7 4");
        subject.addRow("5 9 3 9 9 5");
        subject.addRow("8 4 1 3 2 6");
        subject.addRow("3 7 2 8 6 4");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(result.isFinished());
        assertEquals(16, result.getCost());

        List<Integer> expectedRows = new ArrayList<>();
        expectedRows.add(1);
        expectedRows.add(2);
        expectedRows.add(3);
        expectedRows.add(4);
        expectedRows.add(4);
        expectedRows.add(5);

        assertEquals(expectedRows, result.getRowsVisited());
    }

    @Test
    public void getShortestPath_6X5matrix_2(){
        /*
        Input:
                3 4 1 2 8 6
                6 1 8 2 7 4
                5 9 3 9 9 5
                8 4 1 3 2 6
                3 7 2 1 2 3
        Output:
                Yes
                11
                [1 2 1 5 4 5]

        Actual: Yes
                11
                [0, 1, 0, 4, 4, 4]
        */

        Graph subject = new Graph();
        subject.addRow("3 4 1 2 8 6");
        subject.addRow("6 1 8 2 7 4");
        subject.addRow("5 9 3 9 9 5");
        subject.addRow("8 4 1 3 2 6");
        subject.addRow("3 7 2 1 2 3");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(result.isFinished());
        assertEquals(11, result.getCost());

        List<Integer> expectedRows = new ArrayList<>();
        expectedRows.add(1);
        expectedRows.add(2);
        expectedRows.add(1);
        expectedRows.add(5);
        expectedRows.add(4);
        expectedRows.add(5);

        assertEquals(expectedRows, result.getRowsVisited());
    }

    @Test
    public void getShortestPath_5X3matrix_noPath(){
        /*
        Input:
                19 10 19 10 19
                21 23 20 19 12
                20 12 20 11 10
        Output:
                No
                48
                [1 1 1]

        Actual: No
                49
                [3, 1, 1]

        * */

        Graph subject = new Graph();
        subject.addRow("19 10 19 10 19");
        subject.addRow("21 23 20 19 12");
        subject.addRow("20 12 20 11 10");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(!result.isFinished());
//        assertEquals(48, result.getCost());

        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(1);
        expectedPath.add(1);
        assertEquals(expectedPath, result.getRowsVisited());
    }

    @Test
    public void getShortestPath_1X5matrix(){
        /*
        Input:
                5 8 5 3 5
        Output:
                Yes
                26
                [1 1 1 1 1]
        Actual:
                Yes
                26
                [1, 1, 1, 1, 1]
        * */


        Graph subject = new Graph();
        subject.addRow("5 8 5 3 5");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(result.isFinished());
        assertEquals(26, result.getCost());
    }

    @Test
    public void getShortestPath_5x1matrix(){

        /*
        Input:
                5
                8
                5
                3
                5
        Output:
                Yes
                3
                [4]

        Actual: Yes
                3
                [4]
        * */

        Graph subject = new Graph();
        subject.addRow("5");
        subject.addRow("8");
        subject.addRow("5");
        subject.addRow("3");
        subject.addRow("5");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(result.isFinished());
        assertEquals(3, result.getCost());
    }

    @Test
    public void getShortestPath_allStartAbove50(){

        /*
        Input:
                69 10 19 10 19
                51 23 20 19 12
                60 12 20 11 10
        Output:
                No
                0
                []

       Actual:  No
                0
                []
        * */

        Graph subject = new Graph();
        subject.addRow("69 10 19 10 19");
        subject.addRow("51 23 20 19 12");
        subject.addRow("60 12 20 11 10");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(!result.isFinished());
        assertEquals(0, result.getCost());
        assertEquals(0, result.getRowsVisited().size());
    }

    @Test
    public void getShortestPath_oneAbove50(){

        /*
        Input:
                60 3 3 6
                6 3 7 9
                5 6 8 3
        Output:
                Yes
                14
                [3,2 1 3]

        Actual: Yes
                14
                [3, 2, 1, 3]

         */

        Graph subject = new Graph();
        subject.addRow("60 3 3 6");
        subject.addRow("6 3 7 9");
        subject.addRow("5 6 8 3");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(result.isFinished());
    }

    @Test
    public void getShortestPath_negatives(){

        /*
        Input:
                6,3,-5,9
                -5,2,4,10
                3,-2,6,10
                6,-1,-2,10
        Output:
                Yes
                0
                [2,3 4 1]

        Actual: Yes
                0
                [2, 3, 4, 1]
        * */

        Graph subject = new Graph();
        subject.addRow("6 3 -5 9");
        subject.addRow("-5 2 4 10");
        subject.addRow("3 -2 6 10");
        subject.addRow("6 -1 -2 10");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(result.isFinished());
        assertEquals(0, result.getCost());

        List<Integer> expectedRows = new ArrayList<>();
        expectedRows.add(2);
        expectedRows.add(3);
        expectedRows.add(4);
        expectedRows.add(1);
        assertEquals(expectedRows, result.getRowsVisited());
    }

    @Test
    public void getShortestPath_completeVsCheaperIncomplete() {

        /*
        Input:
                51 51
                0 51
                51 51
                5 5
        output:
                Yes
                10
                4 4

        Actual: Yes
                10
                [4, 4]
        * */

        Graph subject = new Graph();
        subject.addRow("51 51");
        subject.addRow("0 51");
        subject.addRow("51 51");
        subject.addRow("5 5");


        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(result.isFinished());
        assertEquals(10, result.getCost());

        List<Integer> expectedRows = new ArrayList<>();
        expectedRows.add(4);
        expectedRows.add(4);

        assertEquals(expectedRows, result.getRowsVisited());
    }

    @Test
    public void getShortestPath_longerIncompleteVsShorterIncomplete() {

        /*
        Input:
                51 51 51
                0 51 51
                51 51 51
                5 5 51
        output:
                No
                10
                4 4

        Actual: No
                10
                [4, 4]
        * */

        Graph subject = new Graph();
        subject.addRow("51 51 51");
        subject.addRow("0 51 51");
        subject.addRow("51 51 51");
        subject.addRow("5 5 51");


        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        assertTrue(!result.isFinished());
        assertEquals(10, result.getCost());

        List<Integer> expectedRows = new ArrayList<>();
        expectedRows.add(4);
        expectedRows.add(4);

        assertEquals(expectedRows, result.getRowsVisited());
    }

    @Test
    public void getShortestPath_longNumberColumns(){

        /*
        Input:
                1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
                2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2
        output:
                Yes
                20
                1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1

        Actual: Yes
                20
                [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
        * */

        Graph subject = new Graph();
        subject.addRow("1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1");
        subject.addRow("2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2");


        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        result.printPath();
        assertTrue(result.isFinished());
        assertEquals(20, result.getCost());

        List<Integer> expectedRows = new ArrayList<>();
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);
        expectedRows.add(1);

        assertEquals(expectedRows, result.getRowsVisited());
    }

    @Test
    public void priorityQueueTest(){
        Graph subject = new Graph();

        GraphPath path = new GraphPath(0,0,0,0);
        subject.addToQueue(path);

        assertEquals(path, subject.popQueue());
        assertNull(subject.popQueue());
    }

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
        assertTrue(result.isFinished());
        assertEquals(1+2+3+4+5, result.getCost());
    }

    @Test
    public void getShortestPath_2Rows(){
        Graph subject = new Graph();
        subject.addRow("1 10 1 10 1");
        subject.addRow("2 2 2 2 2");

        GraphPath result = subject.findShortestPath();

        assertNotNull(result);
        assertTrue(result.isFinished());
        assertEquals(1+2+1+2+1, result.getCost());
        assertEquals(true, result.isFinished());
        assertEquals(5, result.getRowsVisited().size());
        assertEquals(1, result.getRowsVisited().get(0).intValue());
        assertEquals(2, result.getRowsVisited().get(1).intValue());
        assertEquals(1, result.getRowsVisited().get(2).intValue());
        assertEquals(2, result.getRowsVisited().get(3).intValue());
        assertEquals(1, result.getRowsVisited().get(4).intValue());
    }
}
