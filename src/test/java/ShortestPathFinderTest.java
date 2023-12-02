import org.junit.BeforeClass;
import org.junit.Test;
import java.awt.*;

import utility.ResourceHandler;
import gamelogic.LevelData;
import utility.ShortestPathFinder;

import static org.junit.Assert.*;
public class ShortestPathFinderTest {
    @BeforeClass
    public static void setUp(){
        ResourceHandler.init();
        LevelData levelData = ResourceHandler.loadLevel("src/test/resources/testing_level");
        ResourceHandler.setCurrentLevel(levelData);
    }

    @Test
    public void testSameStartAndEnd(){
        Point start = new Point(0,0);
        Point end = new Point(0,0);
        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(start, end);
        assertEquals(start, nextCell);
    }

    @Test
    public void testDifferentStartAndEnd(){
        Point start = new  Point(0,0);
        Point end = new Point(1, 0);
        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(start, end);
        assertNotEquals(start, nextCell);
    }

    @Test
    public void testNoPathBetweenStartAndEnd(){
        Point start = new Point(0,0);
        Point end = new Point(4,1);
        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(start, end);
        assertEquals(start, nextCell);
    }

    @Test
    public void testActuallyShortest(){
        Point start = new Point(0,0);
        Point end = new Point(6,4);
        Point nextCell = ShortestPathFinder.findNextCellForShortestPath(start, end);
        Point expected = new Point(0, 1);
        assertEquals(expected, nextCell);
    }

}
