package model;

import org.json.JSONObject;
import persistence.Writable;

public class Score implements Writable {
    private String name;
    private int score;

    // EFFECTS: Construct score with given name and score point.
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    // EFFECTS: Return name of the score.
    public String getName() {
        return name;
    }

    // EFFECTS: Return the score point of the score.
    public int getScore() {
        return score;
    }

    // EFFECTS: Returns string representation of this Score.
    public String toString() {
        return name + ": " + score;
    }

    @Override
    // EFFECTS: Returns score as JSON object.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Score", score);
        return json;
    }
}
