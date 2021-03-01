package model;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
any other number = any other weight class
 */
public class WeightClass {
    private final int weightClassCode;
    private ArrayList<Fighter> fighters;
    private ArrayList<Fight> matchHistory;

    private final int upperWeightLimit;
    private final int lowerWeightLimit;

    // REQUIRES: that weight class was not already made
    // Effects: creates a weight class with a list of fighters
    public WeightClass(int weightClass, int lowerWeightLimit, int upperWeightLimit) {
        this.weightClassCode = weightClass;
        fighters = new ArrayList<>();
        matchHistory = new ArrayList<>();

        this.upperWeightLimit = upperWeightLimit;
        this.lowerWeightLimit = lowerWeightLimit;
    }

    // REQUIRES: a fighter with the same name is not already in the list,
    // and the fighter fits the weight class requirements
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

    // REQUIRES: the two fighters are in the given weight class and they are unique
    // MODIFIES: this and fighterA and fighterB
    // EFFECTS: generates the fighters next opponent chooses winner based on odds, then adds to list of previous fights.
    // Also creates a fight with a given winner and loser.
    public Fight getNextFight(Fighter fighterA, Fighter fighterB, String nameOfFight) {
        Fight fight = new Fight(fighterA, fighterB, nameOfFight);
        matchHistory.add(fight);
        fight.getLoser().addLoss();
        fight.getWinner().addWin();
        return fight;
    }

    // REQUIRES: at least one other fighter in the weight class
    // EFFECTS: generates the given fighters opponent
    public Fighter chooseOpponent(Fighter fighter) {
        Random random = new Random();                  // Found on stackoverflow: just returns a random
        int index = random.nextInt(getFightersSize()); // index based on the size of the list
        Fighter opponent = getFighterOfIndex(index);
        if (opponent.getName().equals(fighter.getName())) {
            return chooseOpponent(opponent);
        } else {
            return opponent;
        }
    }

    // REQUIRES: there is at least one fighter in the weight class
    // EFFECTS: returns random fighter in the weight class
    public Fighter getRandomFighter() {
        Random random = new Random();
        int index = random.nextInt(getFightersSize());
        Fighter fighter = getFighterOfIndex(index);
        return fighter;
    }

    // EFFECTS: creates x amount of fighters and adds to given weight class
    public void createFighters(List<String> names) {
        for (String name: names) {
            int goodOrBad;
            if (Math.random() < 0.5) {
                goodOrBad = 1;
            } else {
                goodOrBad = 0;
            }

            double weight;
            weight = Math.floor(Math.random() * (this.getUpperWeightLimit() - this.getLowerWeightLimit() + 1)
                    + this.getLowerWeightLimit());

            Fighter fighter = new Fighter(name, weight, goodOrBad);
            this.fighters.add(fighter);
        }
    }

    // EFFECTS: returns the fighter of the given name. If not found returns null.
    public Fighter getFighterByName(String name) {
        for (Fighter fighter: fighters) {
            if (fighter.getName().equals(name)) {
                return fighter;
            }
        }
        return null;
    }

    // EFFECTS: returns the fight of the given name. If not found returns null.
    public Fight getFightByName(String name) {
        for (Fight fight: matchHistory) {
            if (fight.getFightName().equals(name)) {
                return fight;
            }
        }
        return null;
    }

    // GETTERS:

    public int getWeightClassCode() {
        return this.weightClassCode;
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



