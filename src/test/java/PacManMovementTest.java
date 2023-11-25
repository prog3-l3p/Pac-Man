import gamelogic.LevelData;
import gamelogic.entities.moving.PacMan;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utility.ResourceHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class PacManMovementTest {
    PacMan pacMan;
    String direction;
    Point position;

    public PacManMovementTest(String direction, Point position){
        this.direction = direction;
        this.position = position;
    }
    @BeforeClass
    public static void setUpClass(){
        ResourceHandler.init();
        LevelData levelData = ResourceHandler.loadLevel("src/test/resources/pm_test_level_1");
        ResourceHandler.setCurrentLevel(levelData);

    }
    @Before
    public void setUp(){
        Point pmLocation = ResourceHandler.getInitialLocations().get("pacman");
        pacMan = (PacMan) ResourceHandler.getLevelEntities().get(pmLocation.y).get(pmLocation.x);
    }

    @Test
    public void testMovement(){
        pacMan.setInitialDirection(direction);
        pacMan.update();
        assertEquals(position.x, pacMan.getX());
        assertEquals(position.y, pacMan.getY());
    }

    @Parameterized.Parameters
    public static ArrayList<Object[]> parameters(){
        ArrayList<Object[]> params = new ArrayList<>();
        // Initial location is (2,2)
        params.add(new Object[] {"left", new Point(1,2)});
        // Move left then right -> back to starting position
        params.add(new Object[] {"right", new Point(2,2)});
        params.add(new Object[] {"up", new Point(2,1)});
        // Move up and down -> back to starting position
        params.add(new Object[] {"down", new Point(2,2)});
        return params;
    }
}
