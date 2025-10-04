import lotr.Character;
import lotr.CharacterFactory;
import lotr.GameManager;

public class Demo {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();
        Character c1 = factory.createCharacter();
        Character c2 = factory.createCharacter();
        System.out.println("Starting a demo fight:\n");
        new GameManager().fight(c1, c2);
    }
}
