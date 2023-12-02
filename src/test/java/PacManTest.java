import gamelogic.LevelData;
import gamelogic.entities.moving.PacMan;
import gamelogic.entities.moving.ghosts.Blinky;
import gamelogic.entities.moving.ghosts.Ghost;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utility.ResourceHandler;

import java.awt.*;

import static org.junit.Assert.*;
public class PacManTest {
    PacMan pacMan;
    Ghost ghost;
    Point pmInitialLocation;

    @BeforeClass
    public static void setUpClass(){
        ResourceHandler.init();
        LevelData levelData = ResourceHandler.loadLevel("src/test/resources/testing_level");
        ResourceHandler.setCurrentLevel(levelData);
    }
    @Before
    public void setUp(){
        pmInitialLocation = ResourceHandler.getInitialLocations().get("pacman");
        pacMan = new PacMan(pmInitialLocation.x, pmInitialLocation.y);
        Point blinkyInitialLocation = ResourceHandler.getInitialLocations().get("blinky");
        ghost = new Blinky(blinkyInitialLocation.x, blinkyInitialLocation.y);

    }

    @Test
    public void testCollision(){
        pacMan.setInitialDirection("up");
        pacMan.update();
        pacMan.update();
        assertEquals(3, pacMan.getX());
        assertEquals(2, pacMan.getY());
    }

    @Test
    public void testFrighteningGhosts(){
        pacMan.setCanEatGhosts(true);
        pacMan.addObserver(ghost);
        pacMan.update();
        assertTrue(ghost.isEdible());
    }

    @Test
    public void testPacManDiesWhenEatingNonFrightenedGhost(){
        ghost.eatenBy(pacMan);
        assertTrue(pacMan.isDead());
        assertFalse(ghost.isDead());
    }

    @Test
    public void testGhostDiesWhenFrightenedAndEatenByPacMan(){
        pacMan.setCanEatGhosts(true);
        pacMan.update();
        ghost.eatenBy(pacMan);
        assertTrue(ghost.isDead());
        assertFalse(pacMan.isDead());
    }

}
