package model;

import java.util.ArrayList;

// Represents a weight class in the UFC
/* NOTE:
weight class representations:
0 = strawweight
1 = flyweight
2 = bantamweight
3 = featherweight
4 = lightweight
5 = welterweight
6 = middleweight
7 = light heavyweight
8 = heavyweight
 */
public class WeightClass {
    private int weightClass;
    private ArrayList<Fighter> fighters;
    private ArrayList<String> matchHistory;

    // REQUIRES: that weight class was not already made
    // Effects: creates a weight class with a list of fighters
    public WeightClass(int weightClass) {
        this.weightClass = weightClass;
        fighters = new ArrayList<Fighter>();
        matchHistory = new ArrayList<String>();
    }

    // REQUIRES: a fighter with the same name is not already in the list, and the fighter fits the weight class
    // MODIFIES: this
    // EFFECTS: adds a fighter to the list of fighters in the weight class
    public void addFighter(Fighter fighter) {
        this.fighters.add(fighter);
    }

    // REQUIRES: a fight with the same name is not already in the list
    // MODIFIES: this
    // EFFECTS: adds a fight to the matchHistory
    public void addFight(String fight) {
        matchHistory.add(fight);
    }

    // GETTERS:

    public int getWeightClass() {
        return this.weightClass;
    }

    // EFFECTS: creates list of fighters from fighter list
    public String listFighters() {
        String fighterList = "";
        for (Fighter fighter: fighters) {
            fighterList += "\n" + fighter.getName();
        }
        return fighterList;
    }

    // REQUIRES: at least one other fighter in the weight class
    // MODIFIES: this
    // EFFECTS: generates the fighters next opponent chooses winner based on odds, then adds to list of previous fights
    public String getNextFight(Fighter fighter) {
        Fighter opponent;
        opponent = chooseOpponent(fighter);

        Fighter winner;
        winner = chooseWinner(fighter, opponent);

        Fighter loser;
        if (winner.getName() == fighter.getName()) {
            loser = opponent;
        } else {
            loser = fighter;
        }

        String result;
        result = chooseResult();
        String fightResult = winner.getName() + " beat " + loser.getName() + result;
        matchHistory.add(fightResult);
        return fightResult;
    }

    // REQUIRES: at least one other fight in the weight class
    // EFFECTS: generates the given fighters opponent
    public Fighter chooseOpponent(Fighter fighter) {
        int size = getFightersSize() - 1;
        int index = (int)Math.round(Math.random() * size);
        Fighter opponent = getFighterOfIndex(index);
        if (opponent.getName() == fighter.getName()) {
            return chooseOpponent(opponent);
        } else {
            return opponent;
        }
    }

    // EFFECTS: chooses the winner based off of their win percentage and matches
    public Fighter chooseWinner(Fighter fighterA, Fighter fighterB) {
        int randomNum = (int)Math.round((Math.random()));
        return fighterA;
    }

    // EFFECTS: chooses random result of a fight
    public String chooseResult() {
        int randomNum = (int)Math.round((Math.random()));
        if (randomNum < 0.5) {
            return " by knockout!";
        } else if (randomNum < 0.8) {
            return " by technical knockout!";
        } else {
            return " by judges decision!";
        }
    }

    public ArrayList<Fighter> getFighters() {
        return this.fighters;
    }

    public int getFightersSize() {
        return fighters.size();
    }

    public Fighter getFighterOfIndex(int index) {
        return fighters.get(index);
    }

    public ArrayList<String> getMatchHistory() {
        return this.matchHistory;
    }

    public int getMatchHistorySize() {
        return matchHistory.size();
    }

    public String getFightOfIndex(int index) {
        return matchHistory.get(index);
    }



}



