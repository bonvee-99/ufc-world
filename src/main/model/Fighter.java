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

    // creates a fighter with a name a stats and assigns them to a weight class
    public Fighter(String name, String stance, int weight, int height, int age, int reach, int wins, int losses) {
        this.name = name;
        this.stance = stance;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.reach = reach;
        this.wins = wins;
        this.losses = losses;
        this.addToWeightClass();
    }

    // EFFECTS: creates a fighter with the given name and randomizes their stats to be either good or bad
    public Fighter(String name, int weight, int goodOrBad) {
        this.name = name;
        this.weight = weight;
        if (goodOrBad == 0) {
            generateBadStats();
        } else {
            generateGoodStats();
        }
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

    // SETTERS:
    public void setName(String name) {
        this.name = name;
    }

    public void setStance(String stance) {
        this.stance = stance;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
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
        this.wins = (int)Math.round(Math.random() * (20 - 0) + 0);
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

    // MODIFIES: this
    // EFFECTS: adds a fighter to a weight class based on their weight
    public int addToWeightClass() {
        return 0;
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

    // REQUIRED: a fighter with 0 or greater losses
    // EFFECTS: prints the win percentage of the fighter
    public String getWinPercentage() {
        if (this.losses == 0) {
            return "100%";
        } else {
            int winRate = wins / (wins + losses);
            return winRate + "%";
        }
    }

    // EFFECTS: generates the next fighters opponent
    public String generateNextFight() {
        return null;
    }


}

