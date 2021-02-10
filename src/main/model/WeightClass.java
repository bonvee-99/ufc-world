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
    private final int weightClass;
    private ArrayList<Fighter> fighters;
    private ArrayList<Fight> matchHistory;

    private final int upperWeightLimit;
    private final int lowerWeightLimit;

    // REQUIRES: that weight class was not already made
    // Effects: creates a weight class with a list of fighters
    public WeightClass(int weightClass, int upperWeightLimit, int lowerWeightLimit) {
        this.weightClass = weightClass;
        fighters = new ArrayList<>();
        matchHistory = new ArrayList<>();

        this.upperWeightLimit = upperWeightLimit;
        this.lowerWeightLimit = lowerWeightLimit;
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
    public void addFight(Fight fight) {
        matchHistory.add(fight);
    }


    // EFFECTS: creates list of fighter names from fighter list
    public String listFighters() {
        String fighterList = "\nFighters:";
        for (Fighter fighter: fighters) {
            fighterList += "\n" + fighter.getName();
        }
        return fighterList;
    }

    // EFFECTS: creates list of fight names from fight list
    public String listFights() {
        String fightList = "\nFights:";
        for (Fight fight: matchHistory) {
            fightList += "\n" + fight.getFightName();
        }
        return fightList;
    }

    // EFFECTS: lists fight summaries of all recent matches
    public String listFightSummaries() {
        String summaries = "\nFight Summaries:";
        for (Fight fight : matchHistory) {
            summaries += "\n" + fight.getSummary();
        }
        return summaries;
    }

    // REQUIRES: at least one other fighter in the weight class, there is no other fight of the given name
    // MODIFIES: this
    // EFFECTS: generates the fighters next opponent chooses winner based on odds, then adds to list of previous fights
    public Fight getNextFight(Fighter fighterA, Fighter fighterB, String nameOfFight) {
        Fight fight = new Fight(fighterA, fighterB, nameOfFight);
        matchHistory.add(fight);
        fight.getLoser().addLoss();
        fight.getWinner().addWin();
        return fight;
    }

    // REQUIRES: at least one other fight in the weight class
    // EFFECTS: generates the given fighters opponent
    public Fighter chooseOpponent(Fighter fighter) {
        int size = getFightersSize() - 1;
        int index = (int)Math.round(Math.random() * size);
        Fighter opponent = getFighterOfIndex(index);
        if (opponent.getName().equals(fighter.getName())) {
            return chooseOpponent(opponent);
        } else {
            return opponent;
        }
    }

    // EFFECTS: creates x amount of fighters and adds to given weight class
    public void createFighters(ArrayList<String> names) {
        for (String name: names) {
            int goodOrBad;
            if (Math.random() < 0.5) {
                goodOrBad = 1;
            } else {
                goodOrBad = 0;
            }

            int weight;
            weight = (int)Math.floor(Math.random() * (this.getUpperWeightLimit() - this.getLowerWeightLimit() + 1)
                    + this.getLowerWeightLimit());

            Fighter fighter = new Fighter(name, weight, goodOrBad);
            this.fighters.add(fighter);
        }
    }

    // REQUIRES: there is a fighter of the given name
    // EFFECTS: returns the fighter of the given name
    public Fighter getFighterByName(String name) {
        for (Fighter fighter: fighters) {
            if (fighter.getName().equals(name)) {
                return fighter;
            }
        }
        return null;
    }

    // REQUIRES: there is a fight of the given name
    // EFFECTS: returns the fight of the given name
    public Fight getFightByName(String name) {
        for (Fight fight: matchHistory) {
            if (fight.getFightName().equals(name)) {
                return fight;
            }
        }
        return null;
    }

    // GETTERS:

    public int getWeightClass() {
        return this.weightClass;
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

    public ArrayList<Fight> getMatchHistory() {
        return this.matchHistory;
    }

    public int getMatchHistorySize() {
        return matchHistory.size();
    }

    public Fight getFightOfIndex(int index) {
        return matchHistory.get(index);
    }

    public int getUpperWeightLimit() {
        return this.upperWeightLimit;
    }

    public int getLowerWeightLimit() {
        return this.lowerWeightLimit;
    }
}



