package model;

// represents a fight between two fighters
public class Fight {
    private String winner;
    private String loser;
    private String outcome;

    // EFFECTS: creates a fight with a winner and a loser and an outcome
    public Fight(String fighterA, String fighterB) {
        ;
    }

    // GETTERS:
    public String getWinner() {
        return this.winner;
    }

    public String getLoser() {
        return this.loser;
    }

    public String getOutcome() {
        return this.outcome;
    }

    // EFFECTS: produces random outcome (add list of possible outcomes... ex tko or knockout or judges decision
    private String randomOutcome() {
        return null;
    }

}
