import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DemoTest {
    @Test
    void demoRunsAndPrintsFight() {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(baos));
            Demo.main(new String[]{});
        } finally {
            System.setOut(original);
        }
        String out = baos.toString();
        assertTrue(out.contains("Starting a demo fight"), "Should print demo start line");
        assertTrue(out.contains("Fight starts:"), "Should print fight start line");
        assertTrue(out.contains("wins") || out.contains("Draw"), "Should print a result");
    }
}
