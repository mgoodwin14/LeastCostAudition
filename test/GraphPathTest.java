package test;

import main.GraphPath;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matt on 5/4/2017.
 */
public class GraphPathTest {

    @Test
    public void constructorsTest(){
        GraphPath path1 = new GraphPath(0,0,0,0);

        assertEquals(0, path1.getCol());
        assertEquals(0, path1.getRow());
        assertEquals(0, path1.getCost());
        assertEquals(0, path1.getRowsVisited().get(0).intValue());

        GraphPath path2 = GraphPath.nextEast(path1, 0);

        assertEquals(1, path2.getCol());
        assertEquals(0, path2.getRow());
        assertEquals(0, path2.getCost());
        assertEquals(0, path2.getRowsVisited().get(1).intValue());

        GraphPath path3 = GraphPath.nextNorthEast(path2, 1, 0);

        assertEquals(2, path3.getCol());
        assertEquals(1, path3.getRow());
        assertEquals(0, path3.getCost());
        assertEquals(0, path3.getRowsVisited().get(1).intValue());
    }
}
