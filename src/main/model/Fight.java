package model;

// Represents a fight between two fighters
public class Fight {
    private final String fightName;
    private final Fighter winner;
    private final Fighter loser;
    private String result;

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
