package persistence;

import model.Fight;
import model.Fighter;
import model.UfcWorld;
import model.WeightClass;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// reads UfcWorld from JSON data in file
// inspired by JSONSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: creates a reader to read from the given source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the UfcWorld from the source file and returns it, throws IOException
    // if an error occurs trying to read data from the source file
    public UfcWorld read() throws IOException {
        String jsonData = readFile(source);
        JSONObject json = new JSONObject(jsonData);
        return parseUfcWorld(json);
    }

    // EFFECTS: reads the given source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from jsonUfcWorld and returns it
    private UfcWorld parseUfcWorld(JSONObject jsonUfcWorld) {
        String name = jsonUfcWorld.getString("name");
        UfcWorld myWorld = new UfcWorld(name, false);
        addWeightClasses(myWorld, jsonUfcWorld);
        return myWorld;
    }

    // MODIFIES: myWorld
    // EFFECTS: parses weight classes from jsonUfcWorld and adds them to the UfcWorld
    private void addWeightClasses(UfcWorld myWorld, JSONObject jsonUfcWorld) {
        JSONArray jsonWeightClassList = jsonUfcWorld.getJSONArray("WeightClassList");
        for (Object weightClassObject : jsonWeightClassList) {
            JSONObject nextWeightClassObject = (JSONObject) weightClassObject;
            addWeightClass(myWorld, nextWeightClassObject);
        }
    }

    // MODIFIES: myWorld
    // EFFECTS: parses weight class from nextWeightClassObject and adds it to the UfcWorld
    private void addWeightClass(UfcWorld myWorld, JSONObject nextWeightClassObject) {
        int code = nextWeightClassObject.getInt("weightClassCode");
        int upperLimit = nextWeightClassObject.getInt("upperWeightLimit");
        int lowerLimit = nextWeightClassObject.getInt("lowerWeightLimit");
        WeightClass weightClass = new WeightClass(code, lowerLimit, upperLimit);
        addFighters(weightClass, nextWeightClassObject);
        addFights(weightClass, nextWeightClassObject);
        myWorld.addWeightClass(weightClass);
    }

    // MODIFIES: weightClass
    // EFFECTS: parses fighters from the nextWeightClassObject and adds it to the WeightClass
    private void addFighters(WeightClass weightClass, JSONObject nextWeightClassObject) {
        JSONArray jsonFighters = nextWeightClassObject.getJSONArray("fighters");
        for (Object fighterObject : jsonFighters) {
            JSONObject nextFighterObject = (JSONObject) fighterObject;
            addFighter(weightClass, nextFighterObject);
        }
    }


    // MODIFIES: weightClass
    // EFFECTS: parses fighter from the nextFighterObject and adds it to the WeightClass fighter list
    private void addFighter(WeightClass weightClass, JSONObject nextFighterObject) {
        String name = nextFighterObject.getString("name");
        String stance = nextFighterObject.getString("stance");
        Double weight = nextFighterObject.getDouble("weight");
        int height = nextFighterObject.getInt("height");
        int reach = nextFighterObject.getInt("reach");
        int age = nextFighterObject.getInt("age");
        int wins = nextFighterObject.getInt("wins");
        int losses = nextFighterObject.getInt("losses");
        Fighter fighter = new Fighter(name, stance, weight, height, age, reach);
        for (int i = 0; i < wins; i++) {
            fighter.addWin();
        }
        for (int i = 0; i < losses; i++) {
            fighter.addLoss();
        }
        weightClass.addFighter(fighter);
    }

    // MODIFIES: weightClass
    // EFFECTS: parses match history from the nextWeightClassObject and adds it to the WeightClass
    private void addFights(WeightClass weightClass, JSONObject nextWeightClassObject) {
        JSONArray jsonFights = nextWeightClassObject.getJSONArray("matchHistory");
        for (Object fightObject : jsonFights) {
            JSONObject nextFightObject = (JSONObject) fightObject;
            addFight(weightClass, nextFightObject);
        }
    }

    // MODIFIES: weightClass
    // EFFECTS: parses fight from the nextFightObject and adds it to the WeightClass match history list
    private void addFight(WeightClass weightClass, JSONObject nextFightObject) {
        String fightName = nextFightObject.getString("fightName");
        String result = nextFightObject.getString("result");;
        nextFightObject.getJSONObject("loser");
        Fighter winner = getFighterObject(nextFightObject.getJSONObject("winner"));
        Fighter loser = getFighterObject(nextFightObject.getJSONObject("loser"));

        Fight fight = new Fight(winner, loser, fightName, result);
    }

    // REQUIRES: fighter has all fields and they are valid. name, stance, weight, height, reach, age, wins, losses
    // EFFECTS: parses fighter from the fighter
    private Fighter getFighterObject(JSONObject fighterObject) {
        String name = fighterObject.getString("name");
        String stance = fighterObject.getString("stance");
        Double weight = fighterObject.getDouble("weight");
        int height = fighterObject.getInt("height");
        int reach = fighterObject.getInt("reach");
        int age = fighterObject.getInt("age");
        int wins = fighterObject.getInt("wins");
        int losses = fighterObject.getInt("losses");

        Fighter fighter = new Fighter(name, stance, weight, height, age, reach);
        for (int i = 0; i < wins; i++) {
            fighter.addWin();
        }
        for (int i = 0; i < losses; i++) {
            fighter.addLoss();
        }
        return fighter;
    }
}
