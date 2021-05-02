package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scoreboard implements Writable {
    private String name;
    private List<Score> scoreboard;

    // EFFECTS: Constructs workroom with a name and empty list of scores
    public Scoreboard(String name) {
        this.name = name;
        this.scoreboard = new ArrayList<>();
    }

    // EFFECTS: Return the name of scoreboard.
    public String getName() {
        return name;
    }

    // EFFECTS: Returns an unmodifiable list of scores on this scoreboard.
    public List<Score> getScores() {
        return Collections.unmodifiableList(scoreboard);
    }

    // EFFECTS: Returns number of scores on this scoreboard.
    public int getSize() {
        return scoreboard.size();
    }

    // MODIFIES: this
    // EFFECTS: Add score to this scoreboard.
    public void addScore(Score s) {
        scoreboard.add(s);
    }

    @Override
    // EFFECTS: Returns scoreboard as JSON object.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Scores", scoresToJson());
        return json;
    }

    // EFFECTS: Returns scores on this scoreboard as a JSON array.
    private JSONArray scoresToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Score t : scoreboard) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
