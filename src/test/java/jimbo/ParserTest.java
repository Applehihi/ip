package jimbo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parserDeadlineInvalidTest() {
        String input = "deadline /aaaa test";
        Parser parser = new Parser();
        assertThrows(JimboException.class, () -> {
            parser.parse(input);
        });
    }
}
