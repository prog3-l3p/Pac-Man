import gamelogic.entities.Entity;
import gamelogic.entities.moving.PacMan;
import org.junit.BeforeClass;
import org.junit.Test;
import java.awt.image.BufferedImage;

import utility.ResourceHandler;

import static org.junit.Assert.*;
public class EntityTest {
    static Entity e;
    @BeforeClass
    public static void setUp(){
        ResourceHandler.init();
        e = new PacMan(0,0);
    }

    @Test
    public void testGetters(){
        assertEquals(0, e.getX());
        assertEquals(0, e.getY());
        assertNotEquals(0, e.getWidth());
        assertNotEquals(0, e.getHeight());
        assertNotNull(e.getSprite());
    }

    @Test
    public void testSetters(){
        BufferedImage previousSprite = e.getSprite();
        e.setSprite("left_1");
        assertNotEquals(previousSprite, e.getSprite());

        e.setNotTraversableByPacMan();
        e.setNotTraversableByGhosts();
        assertFalse(e.isTraversableByPacMan());
        assertFalse(e.isTraversableByGhosts());
    }
}
