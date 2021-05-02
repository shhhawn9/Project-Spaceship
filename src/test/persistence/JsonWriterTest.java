package persistence;

import model.Score;
import model.Scoreboard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    @Test
    void testWriteInvalidFile() {
        try {
            Scoreboard board = new Scoreboard("Scoreboard 0.0");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Expected
        }
    }

    @Test
    void testWriteEmptyScoreboard() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyScoreboard.json");
            writer.open();
            writer.write(new Scoreboard("Scoreboard 0.0"));
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyScoreboard.json");
            Scoreboard board = reader.read();
            assertEquals("Scoreboard 0.0", board.getName());
            assertEquals(0, board.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriteGeneralScoreboard() {
        try {
            Scoreboard board = new Scoreboard("Scoreboard 1.0");
            board.addScore(new Score("Deep", 100));
            board.addScore(new Score("Dark", 200));
            board.addScore(new Score("Fantasy", 300));

            JsonWriter writer = new JsonWriter("./data/testWriteGeneralScoreboard.json");
            writer.open();
            writer.write(board);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteGeneralScoreboard.json");
            board = reader.read();

            assertEquals("Scoreboard 1.0", board.getName());
            List<Score> scores = board.getScores();
            assertEquals(3, scores.size());

            assertEquals("Deep", scores.get(0).getName());
            assertEquals(200, scores.get(1).getScore());
            assertEquals("Fantasy: 300", scores.get(2).toString());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
