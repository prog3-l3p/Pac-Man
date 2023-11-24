import gamelogic.EntityObserver;
import gamelogic.LevelData;
import gamelogic.entities.moving.PacMan;
import gamelogic.entities.nonmoving.Food;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import utility.ResourceHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class PacManTest {
    PacMan pacMan;
    String direction;
    Point position;

    public PacManTest(String direction, Point position){
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
        params.add(new Object[] {"right", new Point(3,2)});
        params.add(new Object[] {"left", new Point(2,2)});
        params.add(new Object[] {"up", new Point(2,1)});
        params.add(new Object[] {"down", new Point(2,2)});
        return params;
    }
}
