package model;



import exceptions.NoFighterFoundException;
import exceptions.NoOtherFighterException;
import exceptions.NotInWeightClassException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

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
public class WeightClass implements Writable {
    private final int weightClassCode;
    private final int upperWeightLimit;
    private final int lowerWeightLimit;
    private List<Fighter> fighters;
    private List<Fight> matchHistory;

    // Effects: creates a weight class with a list of fighters
    public WeightClass(int weightClassCode, int lowerWeightLimit, int upperWeightLimit) {
        this.weightClassCode = weightClassCode;
        fighters = new ArrayList<>();
        matchHistory = new ArrayList<>();

        this.upperWeightLimit = upperWeightLimit;
        this.lowerWeightLimit = lowerWeightLimit;
    }

    // MODIFIES: this
    // EFFECTS: adds a fighter to the list of fighters in the weight class if there are no other
    // fighters with the same name, and the fighter fits the weight class requirements
    public boolean addFighter(Fighter fighter) {
        if (lowerWeightLimit <= fighter.getWeight() && fighter.getWeight() <= upperWeightLimit
                && !fighters.contains(fighter)) {
            fighters.add(fighter);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a fight to the matchHistory if there are no other fights with the same name
    public boolean addFight(Fight fight) {
        if (!(matchHistory.contains(fight))) {
            matchHistory.add(fight);
            return true;
        } else {
            return false;
        }
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

    // MODIFIES: this and fighterA and fighterB
    // EFFECTS: generates the fighters next opponent chooses winner based on odds, then adds to list of previous fights.
    // Also creates a fight with a given winner and loser. If the two fighter's are the same returns null.
    public Fight getNextFight(Fighter fighterA, Fighter fighterB, String nameOfFight) {
        if (!(fighterA == fighterB) && fighters.contains(fighterA) && fighters.contains(fighterB)) {
            Fight fight = new Fight(fighterA, fighterB, nameOfFight);
            matchHistory.add(fight);
            fight.getLoser().addLoss();
            fight.getWinner().addWin();
            return fight;
        } else {
            return null;
        }
    }

    // EFFECTS: generates the given fighters opponent, throws NoOtherFighterException
    // if there is not another fighter in the weight division, throws NotInWeightClassException
    // if the fighter is not in this weight division, throw NotInWeightClassException if the
    // fighter is not in this weight division
    public Fighter chooseOpponent(Fighter fighter) throws NoOtherFighterException, NotInWeightClassException {
        if (!(fighters.size() >= 2)) {
            throw new NoOtherFighterException();
        }
        if (!(fighters.contains(fighter))) {
            throw new NotInWeightClassException();
        }
        Random random = new Random();                  // Found on stackoverflow: just returns a random
        int index = random.nextInt(getFightersSize()); // index based on the size of the list
        Fighter opponent = getFighterOfIndex(index);
        if (opponent.getName().equals(fighter.getName())) {
            return chooseOpponent(opponent);
        } else {
            return opponent;
        }
    }

    // EFFECTS: returns random fighter in the weight class, throws NoFighterFoundException
    // if there are 0 fighters in the weight division
    public Fighter getRandomFighter() throws NoFighterFoundException {
        if (!(fighters.size() >= 1)) {
            throw new NoFighterFoundException();
        }
        Random random = new Random();
        int index = random.nextInt(fighters.size());
        return getFighterOfIndex(index);
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("weightClassCode", weightClassCode);
        json.put("upperWeightLimit", upperWeightLimit);
        json.put("lowerWeightLimit", lowerWeightLimit);
        json.put("fighters", fightersToJson());
        json.put("matchHistory", fightsToJson());
        return json;
    }

    // EFFECTS: returns fighters as a JSONArray
    public JSONArray fightersToJson() {
        JSONArray jsonArrayOfFighters = new JSONArray();
        for (Fighter fighter : fighters) {
            jsonArrayOfFighters.put(fighter.toJson());
        }
        return jsonArrayOfFighters;
    }

    // EFFECTS: returns fights as a JSONArray
    public JSONArray fightsToJson() {
        JSONArray jsonArrayOfFights = new JSONArray();
        for (Fight fight : matchHistory) {
            jsonArrayOfFights.put(fight.toJson());
        }
        return jsonArrayOfFights;
    }

    // GETTERS:

    public int getWeightClassCode() {
        return this.weightClassCode;
    }

    public List<Fighter> getFighters() {
        return this.fighters;
    }

    public int getFightersSize() {
        return fighters.size();
    }

    public Fighter getFighterOfIndex(int index) {
        return fighters.get(index);
    }

    public List<Fight> getMatchHistory() {
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



