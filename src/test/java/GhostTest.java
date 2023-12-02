import gamelogic.entities.moving.PacMan;
import gamelogic.entities.moving.ghosts.Blinky;
import gamelogic.entities.moving.ghosts.Ghost;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GhostTest {
    private Ghost ghost;
    private PacMan pacMan;

    @Before
    public void setUp() {
        ghost = new Blinky(5, 5);
        pacMan = new PacMan(5, 5);
    }

    @Test
    public void testEdibleWhenFrightened() {
        ghost.update(new Point(5, 5), "UP", true);
        assertTrue(ghost.isEdible());
    }

    @Test
    public void testNotEdibleWhenNotFrightened() {
        ghost.update(new Point(5, 5), "UP", false);
        assertFalse(ghost.isEdible());
    }

    @Test
    public void testDeathTimer() {
        ghost.update(new Point(5, 5), "UP", true);
        ghost.eatenBy(pacMan);
        for (int i = 0; i < 6000/utility.GameConstants.TIMER_DELAY; i++) {
            ghost.update(new Point(5, 5), "UP", false);
        }
        assertFalse(ghost.isDead());
    }
}