import lotr.GameManager;
import lotr.Hobbit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameManagerTest {
    @Test
    public void testFightWithHobbitsDoesNotHang() {
        Hobbit h1 = new Hobbit();
        Hobbit h2 = new Hobbit();
        int hp1 = h1.getHp();
        int hp2 = h2.getHp();
        new GameManager().fight(h1, h2);
        // Hobbits can't damage; ensure state unchanged and both alive
        assertEquals(hp1, h1.getHp());
        assertEquals(hp2, h2.getHp());
        assertTrue(h1.isAlive() && h2.isAlive());
    }
}

