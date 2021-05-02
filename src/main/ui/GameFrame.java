package ui;

import model.Score;
import model.Scoreboard;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GameFrame extends JFrame {
    private static final String JSON_STORE = "./data/Scoreboard.json";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    private GameComponent component;
    private static Scoreboard scoreboard;
    JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    JsonReader jsonReader = new JsonReader(JSON_STORE);

    // EFFECTS: Construct game frame.
    public GameFrame() {
        setResizable(false);
        component = new GameComponent();
        add(component);

        scoreboard = new Scoreboard("Ary's scoreboard");
    }

    // MODIFIES: this
    // EFFECTS: Set up the frame detail.
    public void setFrame() {
        setTitle("Spaceship Shooting Game");
        setSize(WIDTH, HEIGHT + 120);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: Set up the panel.
    public void setPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, HEIGHT, WIDTH - 6, 91);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Main menu"));
        panel.setLayout(new GridLayout(0, 4));

        setButton(panel);
        add(panel);
    }

    // MODIFIES: this
    // EFFECTS: Set up the buttons
    private void setButton(JPanel panel) {
        JButton play = new JButton("Play");
        JButton print = new JButton("Print scoreboard");
        JButton save = new JButton("Save scoreboard");
        JButton load = new JButton("Load scoreboard");

        play.addActionListener(new ButtonListener());
        print.addActionListener(new ButtonListener());
        save.addActionListener(new ButtonListener());
        load.addActionListener(new ButtonListener());

        panel.add(play);
        panel.add(print);
        panel.add(save);
        panel.add(load);
    }

    // EFFECTS: An inner class relates to ActionListener.
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (component.isInProgress()) {
                JOptionPane.showMessageDialog(null, "Game is in progress!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                userChoice(button);
            }
        }
    }

    // EFFECTS: Use of each button.
    private void userChoice(JButton button) {
        if (button.getText().equals("Play")) {
            play();
        } else if (button.getText().equals("Print scoreboard")) {
            printScores();
        } else if (button.getText().equals("Save scoreboard")) {
            saveScores();
        } else if (button.getText().equals("Load scoreboard")) {
            loadScores();
        }
    }

    // MODIFIES: this
    // EFFECTS: Add a score to the scoreboard.
    public static void addScore(Score s) {
        scoreboard.addScore(s);
    }

    // MODIFIES: component
    // EFFECTS: Method for playing the game
    private void play() {
        component.playAgain();
    }

    // EFFECTS: Print the scoreboard.
    private void printScores() {
        List<Score> scores = scoreboard.getScores();
        String output = scoreboard.getName() + "\n";

        for (Score s : scores) {
            output += s.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, output);
    }

    // EFFECTS: Saves the scoreboard to file.
    private void saveScores() {
        try {
            jsonWriter.open();
            jsonWriter.write(scoreboard);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved " + scoreboard.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads scoreboard from file.
    private void loadScores() {
        try {
            scoreboard = jsonReader.read();

            JOptionPane.showMessageDialog(null,
                    "Loaded " + scoreboard.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read to file: " + JSON_STORE);
        }
    }
}