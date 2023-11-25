import gamelogic.LevelData;
import gamelogic.entities.moving.PacMan;
import gamelogic.entities.moving.ghosts.Blinky;
import gamelogic.entities.moving.ghosts.Ghost;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utility.ResourceHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;
public class PacManTest {
    PacMan pacMan;
    Ghost ghost;

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
        Point blinkyLocation = ResourceHandler.getInitialLocations().get("blinky");
        ghost = (Ghost) ResourceHandler.getLevelEntities().get(blinkyLocation.y).get(blinkyLocation.x);

    }

    @Test
    public void testCollision(){
        pacMan.setInitialDirection("up");
        pacMan.update();
        pacMan.update();
        assertEquals(2, pacMan.getX());
        assertEquals(1, pacMan.getY());
    }

    @Test
    public void testEatingFood(){
        pacMan.setInitialDirection("down");
        pacMan.update();
        BufferedImage entitySprite = ResourceHandler.getLevelEntities().get(pacMan.getY()).get(pacMan.getX()).getSprite();
        BufferedImage noFood = ResourceHandler.getSpriteMap("edibles").get("none");
        assertEquals(noFood, entitySprite);
    }

    @Test
    public void testFrighteningGhosts(){
        pacMan.setInitialDirection("up");
        pacMan.addObserver(ghost);
        pacMan.update();
        assertTrue(ghost.isEdible());
    }

}
