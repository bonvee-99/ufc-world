package model;

// Represents a fighter in the UFC... name, weight class, stats, etc.
public class Fighter {
    private String name;
    private String stance;
    private int weight;
    private int height;
    private int age;
    private int reach;
    private int wins;
    private int losses;
    private int weightClass;

    // REQUIRES: their weight class to already exist
    // EFFECTS: creates a fighter with a name a stats and assigns them to a weight class
    public Fighter(String name, String stance, int weight, int height, int age, int reach) {
        this.name = name;
        this.stance = stance;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.reach = reach;
        this.wins = 0;
        this.losses = 0;
        assignWeightClass();
    }

    // EFFECTS: creates a fighter with the given name and randomizes their stats to be either good or bad
    public Fighter(String name, int weight, int goodOrBad) {
        this.name = name;
        this.weight = weight;
        assignWeightClass();
        if (goodOrBad == 0) {
            generateBadStats();
        } else {
            generateGoodStats();
        }
    }

    // REQUIRES: weight classes to be created already
    // MODIFIES: this
    // EFFECTS: adds and assigns a fighter to a weight class based on their weight
    public void assignWeightClass() {
        if (weight <= 115) {
            weightClass = 0;
        } else if (weight <= 125) {
            weightClass = 1;
        } else if (weight <= 135) {
            weightClass = 2;
        } else if (weight <= 145) {
            weightClass = 3;
        } else if (weight <= 155) {
            weightClass = 4;
        } else if (weight <= 170) {
            weightClass = 5;
        } else if (weight <= 185) {
            weightClass = 6;
        } else if (weight <= 205) {
            weightClass = 7;
        } else {
            weightClass = 8;
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
        String stats = "Stance: " + this.stance
                + "\nWeight: " + this.weight
                + "\nHeight: " + this.height
                + "\nAge: " + this.age
                + "\nReach: " + this.reach
                + "\nWins: " + this.wins
                + "\nLosses: " + this.losses
                + "\nWin percentage: " + this.getWinPercentage();

        return stats;
    }

    // GETTERS:
    public String getName() {
        return this.name;
    }

    public String getStance() {
        return this.stance;
    }

    public int getWeight() {
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

    public int getWeightClass() {
        return this.weightClass;
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
            this.stance = "regular";
        } else {
            this.stance = "southpaw";
        }
        this.height = (int)Math.round(Math.random() * (76 - 60) + 60);
        this.age = (int)Math.round(Math.random() * (45 - 19) + 19);
        this.reach = (int)Math.round(Math.random() * (76 - 60) + 60);
        this.wins = (int)Math.round(Math.random() * (10) + 0);
        this.losses = (int)Math.round(Math.random() * (20 - 10) + 10);
    }

    // MODIFIES: this
    // EFFECTS: generates random good stats for the fighter
    private void generateGoodStats() {
        if (Math.random() < 0.8) {
            this.stance = "regular";
        } else {
            this.stance = "southpaw";
        }
        this.height = (int)Math.round(Math.random() * (76 - 60) + 60);
        this.age = (int)Math.round(Math.random() * (45 - 19) + 19);
        this.reach = (int)Math.round(Math.random() * (76 - 60) + 60);
        this.wins = (int)Math.round(Math.random() * (30 - 15) + 15);
        this.losses = (int)Math.round(Math.random() * (10 - 0) + 0);
    }




}

