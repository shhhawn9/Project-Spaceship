package persistence;

import model.Score;
import model.Scoreboard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads scoreboard from JSON data stored in file.
public class JsonReader {
    private String source;

    // EFFECTS: Construct reader to read from source file.
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Read scoreboard from file and returns it;
    //          throws IOException if an error occurs reading data from file.
    public Scoreboard read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScoreboard(jsonObject);
    }

    // EFFECTS: Read source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Scoreboard parseScoreboard(JSONObject jsonObject) {
        Scoreboard board = new Scoreboard(jsonObject.getString("Name"));
        addThingies(board, jsonObject);
        return board;
    }

    // MODIFIES: board
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addThingies(Scoreboard board, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Scores");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addScore(board, nextThingy);
        }
    }

    // MODIFIES: board
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addScore(Scoreboard board, JSONObject jsonObject) {
        board.addScore(new Score(jsonObject.getString("Name"), jsonObject.getInt("Score")));
    }
}
