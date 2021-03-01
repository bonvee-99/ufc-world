package model;

import java.util.ArrayList;
import java.util.List;

public class UfcWorld {
    private final List<WeightClass> weightClassList;

    private static final int numberOfNames = 5;

    // EFFECTS: initializes 9 weight classes and creates fighters and adds them to the 9 weight classes
    public UfcWorld() {
        weightClassList = new ArrayList<>();
        initializeWeightClasses();
        initializeFighters();
    }

    // MODIFIES: this
    // EFFECTS: initializes weight classes
    private void initializeWeightClasses() {
        WeightClass strawWeight = new WeightClass(0, 0, 115);
        weightClassList.add(strawWeight);
        WeightClass flyWeight = new WeightClass(1, 115, 125);
        weightClassList.add(flyWeight);
        WeightClass bantamWeight = new WeightClass(2, 125, 135);
        weightClassList.add(bantamWeight);
        WeightClass featherWeight = new WeightClass(3, 135, 145);
        weightClassList.add(featherWeight);
        WeightClass lightWeight = new WeightClass(4, 145, 155);
        weightClassList.add(lightWeight);
        WeightClass welterWeight = new WeightClass(5, 155, 170);
        weightClassList.add(welterWeight);
        WeightClass middleWeight = new WeightClass(6, 170, 185);
        weightClassList.add(middleWeight);
        WeightClass lightHeavyWeight = new WeightClass(7, 185, 205);
        weightClassList.add(lightHeavyWeight);
        WeightClass heavyWeight = new WeightClass(8, 205, 265);
        weightClassList.add(heavyWeight);
    }

    // MODIFIES: this
    // EFFECTS: creates fighters and adds them to the 9 weight classes
    private void initializeFighters() {
        Names names = new Names();
        getWeightClassByCode(0).createFighters(names.createNames(numberOfNames));
        getWeightClassByCode(1).createFighters(names.createNames(numberOfNames));
        getWeightClassByCode(2).createFighters(names.createNames(numberOfNames));
        getWeightClassByCode(3).createFighters(names.createNames(numberOfNames));
        getWeightClassByCode(4).createFighters(names.createNames(numberOfNames));
        getWeightClassByCode(5).createFighters(names.createNames(numberOfNames));
        getWeightClassByCode(6).createFighters(names.createNames(numberOfNames));
        getWeightClassByCode(7).createFighters(names.createNames(numberOfNames));
        getWeightClassByCode(8).createFighters(names.createNames(numberOfNames));
    }

    // EFFECTS: return the weight class with the same code
    public WeightClass getWeightClassByCode(int code) {
        for (WeightClass weightClass : weightClassList) {
            if (code == weightClass.getWeightClassCode()) {
                return weightClass;
            }
        }
        return null;
    }
}
