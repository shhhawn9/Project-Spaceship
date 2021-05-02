package persistence;

import model.Score;
import model.Scoreboard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Scoreboard board = reader.read();
            fail("IOException was expected");
        } catch (IOException e) {
            // Expected
        }
    }

    @Test
    void testReaderEmptyScoreboard() {
        JsonReader reader = new JsonReader("./data/testReadEmptyScoreboard.json");
        try {
            Scoreboard board = reader.read();
            assertEquals("Scoreboard 0.0", board.getName());
            assertEquals(0, board.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReadGeneralScoreboard.json");
        try {
            Scoreboard board = reader.read();
            assertEquals("Scoreboard 1.0", board.getName());
            List<Score> scores = board.getScores();
            assertEquals(3, scores.size());

            assertEquals(5000, scores.get(0).getScore());
            assertEquals("Bry", scores.get(1).getName());
            assertEquals("Cry: 15000", scores.get(2).toString());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}