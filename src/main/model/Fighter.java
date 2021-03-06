package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

// Represents a fighter in the UFC... name, weight class, stats, etc.
public class Fighter implements Writable {
    private final String name;
    private String stance;
    private Double weight;
    private int height;
    private int reach;
    private int age;
    private int wins;
    private int losses;

    // REQUIRES: 21 <= age <= 34
    // EFFECTS: creates a fighter with a name a stats and assigns them to a weight class
    public Fighter(String name, String stance, Double weight, int height, int age, int reach) {
        this.name = name;
        this.stance = stance;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.reach = reach;
        this.wins = 0;
        this.losses = 0;
    }

    // REQUIRES: a fighter with a weight in the min max range.
    // EFFECTS: creates a fighter with the given name and randomizes their stats to be either good or bad
    public Fighter(String name, Double weight, int goodOrBad) {
        this.name = name;
        this.weight = weight;
        if (goodOrBad == 0) {
            generateBadStats();
        } else {
            generateGoodStats();
        }
    }

    // REQUIRED: a fighter with 0 or greater losses
    // EFFECTS: returns the win percentage of the fighter
    public int getWinPercentage() {
        if (wins == 0 && losses == 0) {
            return 0;
        } else if (wins == 0) {
            return 0;
        } else if (losses == 0) {
            return 100;
        } else {
            double totalMatches = wins + losses;
            return (int)Math.round(100 * (wins / totalMatches));
        }
    }

    // EFFECTS: returns a fighter's weight, height, reach, win/loss ratio, age, stance
    public String getStats() {
        return
                "\n" + this.getName() + "'s stats:"
                + "\nStance: " + this.stance + "\nWeight: " + this.weight + "lbs" + "\nHeight: " + this.height + "in"
                + "\nReach: " + this.reach + "\nAge: " + this.age + "\nWins: " + this.wins + "\nLosses: " + this.losses
                + "\nWin percentage: " + this.getWinPercentage();
    }

    // MODIFIES: this
    // EFFECTS: adds a win
    public void addWin() {
        wins++;
    }

    // MODIFIES: this
    // EFFECTS: adds a loss
    public void addLoss() {
        losses++;
    }

    // MODIFIES: this
    // EFFECTS: generates random poor stats for the fighter
    private void generateBadStats() {
        if (Math.random() < 0.8) {
            this.stance = "orthodox";
        } else {
            this.stance = "southpaw";
        }
        Random random = new Random();
        this.height = random.nextInt(76 + 1 - 60) + 60; // youtube video:
        this.age = random.nextInt(34 + 1 - 21) + 21;    // https://www.youtube.com/watch?v=dlA67TRejww
        this.reach = random.nextInt(76 + 1 - 60) + 60;
        this.wins = random.nextInt(10 + 1);
        this.losses = random.nextInt(20 + 1 - 10) + 10;
    }

    // MODIFIES: this
    // EFFECTS: generates random good stats for the fighter
    private void generateGoodStats() {
        if (Math.random() < 0.8) {
            this.stance = "orthodox";
        } else {
            this.stance = "southpaw";
        }
        Random random = new Random();
        this.height = random.nextInt(76 + 1 - 60) + 60; // youtube video:
        this.age = random.nextInt(34 + 1 - 21) + 21;    // https://www.youtube.com/watch?v=dlA67TRejww
        this.reach = random.nextInt(76 + 1 - 60) + 60;
        this.wins = random.nextInt(30 + 1 - 15) + 15;
        this.losses = random.nextInt(10 + 1);
    }

    // GETTERS:
    public String getName() {
        return this.name;
    }

    public String getStance() {
        return this.stance;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getHeight() {
        return this.height;
    }

    public int getAge() {
        return this.age;
    }

    public int getReach() {
        return this.reach;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("stance", stance);
        json.put("weight", weight);
        json.put("height", height);
        json.put("reach", reach);
        json.put("age", age);
        json.put("wins", wins);
        json.put("losses", losses);
        return json;
    }
}

