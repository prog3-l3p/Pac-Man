import gamelogic.EntityObserver;
import gamelogic.entities.moving.PacMan;
import gamelogic.entities.nonmoving.Food;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utility.ResourceHandler;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FoodTest {
    private EntityObserver observer;
    private Food food;
    private PacMan pacMan;
    private final HashMap<String, BufferedImage> spriteMap = ResourceHandler.getSpriteMap("edibles");

    @BeforeClass
    public static void setUpClass(){
        ResourceHandler.init();
    }

    @Before
    public void setUp() {
        observer = new EntityObserver();
        food = new Food(0, 0);
        food.addObserver(observer);
        pacMan = new PacMan(0, 0);
    }

    @Test
    public void testEdible() {
        assertTrue(food.isEdible());
    }

    @Test
    public void testPellet() {
        assertEquals(spriteMap.get("food"), food.getSprite());
        food.eatenBy(pacMan);
        assertEquals(10, observer.getScore());
    }

    @Test
    public void testPowerPellet() {
        assertFalse(pacMan.canEatGhosts());
        food.setSprite("power_pellet");
        assertEquals(spriteMap.get("power_pellet"), food.getSprite());
        food.eatenBy(pacMan);
        assertEquals(50, observer.getScore());
        assertTrue(pacMan.canEatGhosts());
    }

    @Test
    public void testCherry() {
        food.setSprite("cherry");
        assertEquals(spriteMap.get("cherry"), food.getSprite());
        food.eatenBy(pacMan);
        assertEquals(100, observer.getScore());
    }

    @Test
    public void testEatingRemovesFood() {
        food.eatenBy(pacMan);
        assertEquals(spriteMap.get("none"), food.getSprite());
    }
}