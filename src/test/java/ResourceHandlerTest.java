import org.junit.BeforeClass;
import org.junit.Test;

import utility.ResourceHandler;
import gamelogic.LevelData;

import static org.junit.Assert.*;

public class ResourceHandlerTest {
    @BeforeClass
    public static void setUp() {
        ResourceHandler.init();
    }

    @Test
    public void testSpriteLoader() {
        assertNotNull(ResourceHandler.getEntityTypes());
        assertNotEquals(0, ResourceHandler.getEntityTypes().size());
        for(String entityType : ResourceHandler.getEntityTypes()){
            assertNotNull(ResourceHandler.getSpriteMap(entityType));
        }
    }

    @Test
    public void testFontLoader() {
        assertNotNull(ResourceHandler.getPacFont());
        assertEquals("Emulogic", ResourceHandler.getPacFont().getFontName());
    }

    @Test
    public void testLevelLoader() {
        LevelData levelData = ResourceHandler.loadLevel("src/test/resources/testing_level");
        ResourceHandler.setCurrentLevel(levelData);
        assertEquals(levelData.getEntities(), ResourceHandler.getLevelEntities());
        assertEquals(levelData.getLocations(), ResourceHandler.getInitialLocations());
        assertEquals(5, ResourceHandler.getInitialLocations().size());
        assertEquals(levelData.getFoodCount(), ResourceHandler.getFoodCount());

        // Check resetLevel
        ResourceHandler.resetLevel();
        assertNull(ResourceHandler.getLevelEntities());
        assertEquals(0, ResourceHandler.getFoodCount());
        assertNull(ResourceHandler.getInitialLocations());
    }

}
