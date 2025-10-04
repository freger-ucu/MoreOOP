import lotr.Character;
import lotr.CharacterFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterFactoryTest {
    @Test
    public void testDiscoveryAndCreation() {
        CharacterFactory factory = new CharacterFactory();
        List<Class<? extends Character>> classes = factory.getRegisteredCharacterClasses();
        // Should discover at least Hobbit, Elf, King, Knight
        assertTrue(classes.size() >= 4);
        for (int i = 0; i < 10; i++) {
            Character c = factory.createCharacter();
            assertNotNull(c);
            assertTrue(c.getClass().getSimpleName().matches("Hobbit|Elf|King|Knight"));
        }
    }
}

