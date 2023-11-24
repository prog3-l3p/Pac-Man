import gamelogic.entities.nonmoving.Wall;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utility.ResourceHandler;

import java.awt.image.BufferedImage;

import static org.junit.Assert.*;
public class WallTest {
    Wall w;
    @BeforeClass
    public static void setUpClass(){
        ResourceHandler.init();
    }
    @Before
    public void setUp(){
        w = new Wall(0,0);
    }

    @Test
    public void testEmptyWallIsTraversable(){
        assertTrue(w.isTraversableByGhosts());
        assertTrue(w.isTraversableByGhosts());
    }

    @Test
    public void testPMNonTraverse(){
        w.setSprite("pm-non-traverse");
        assertFalse(w.isTraversableByPacMan());
        assertTrue(w.isTraversableByGhosts());
    }

    @Test
    public void testGhostNonTraverse(){
        w.setSprite("ghost-non-traverse");
        assertFalse(w.isTraversableByPacMan());
        assertFalse(w.isTraversableByGhosts());
    }

    @Test
    public void testNonEmptyWallIsNotTraversable(){
        w.setSprite("horizontal");
        assertFalse(w.isTraversableByPacMan());
        assertFalse(w.isTraversableByGhosts());
    }
    @Test
    public void testSpecialAndEmptyWallsShowUpAsEmpty() {
        BufferedImage emptyWallImage = ResourceHandler.getSpriteMap("walls").get("empty");
        assertEquals(emptyWallImage, w.getSprite());
        w.setSprite("pm-non-traverse");
        assertEquals(emptyWallImage, w.getSprite());
        w.setSprite("ghost-non-traverse");
        assertEquals(emptyWallImage, w.getSprite());
    }

}
