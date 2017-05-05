package test;

import main.Graph;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
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
    }
}
