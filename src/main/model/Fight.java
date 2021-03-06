package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a fight between two fighters
public class Fight implements Writable {
    private final String fightName;
    private String result;
    private final Fighter winner;
    private final Fighter loser;

    // EFFECTS: creates a fight between two chosen fighters and names the fight fightName,
    // generates a random winner, loser, and result summary
    public Fight(Fighter fighterA, Fighter fighterB, String fightName) {
        winner = chooseWinner(fighterA, fighterB);
        if (winner.getName().equals(fighterA.getName())) {
            loser = fighterB;
        } else {
            loser = fighterA;
        }
        this.fightName = fightName;
        chooseResult();
    }

    // EFFECTS: creates fight (for documentation) with the given winner, loser, name, and result summary
    public Fight(Fighter winner, Fighter loser, String fightName, String result) {
        this.winner = winner;
        this.loser = loser;
        this.fightName = fightName;
        this.result = result;
    }

    // EFFECTS: chooses random result of a fight
    private void chooseResult() {
        double randomNum = Math.random();
        if (randomNum < 0.5) {
            result = " by knockout!";
        } else if (randomNum < 0.8) {
            result = " by technical knockout!";
        } else {
            result = " by judges decision!";
        }
    }

    // EFFECTS: chooses the winner based off of their win percentage and matches
    public Fighter chooseWinner(Fighter fighterA, Fighter fighterB) {
        if (Math.random() < 0.5) {
            return fighterA;
        } else {
            return fighterB;
        }
    }

    public String getSummary() {
        String result;
        result = this.result;
        return winner.getName() + " beat " + loser.getName() + result;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("fightName", fightName);
        json.put("result", result);
        json.put("winner", winner.toJson());
        json.put("loser", loser.toJson());
        return json;
    }

    // GETTERS:
    public String getFightName() {
        return this.fightName;
    }

    public Fighter getWinner() {
        return this.winner;
    }

    public Fighter getLoser() {
        return this.loser;
    }

    public String getResult() {
        return this.result;
    }


}
