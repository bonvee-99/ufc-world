package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Names {
    private static final String[] firstNames = {"Ben", "James", "Joel", "Rick", "Sam", "Tony", "Sameer", "Antoni",
            "Kier", "Connor", "Kelsey", "Tyrese", "Tyson", "Jasper", "Joss", "Josh", "Wil", "Yusef", "Caine", "Dwayne",
            "Casper", "Liam", "Trevor", "Dwayne", "Carl", "Denzel", "Patryk", "Cayden", "Macaulay", "Maverick",
            "Kohen", "Sahil", "Dominic", "Imani", "Ronaldo", "Lawson", "Gerard", "Matthias", "Hunter", "Charles",
            "Lucien", "Saad", "Kristopher", "Dainton", "Emmett", "Jason", "Stef", "Alex", "Lucas", "Justin", "Nick",
            "Bob", "David", "Jonathan"};

    private static final String[] lastNames = {"Tim", "Hammond", "Feder", "Davids", "Brooks", "Marsh", "Frey", "Kim",
            "Fong", "Cain", "Huber", "Marsh", "Zuniga", "Lindsey", "Werner", "Cortex", "Oakley", "Russel", "Vinnick",
            "Holcomb", "Foley", "Workman", "Daniel", "Wilkes", "Jenkins", "Webber", "Cornish", "Rodriquez", "Hendricks",
            "Wade", "Mcnamara", "Bowes", "Bush", "Walker", "Ortega", "Potter", "Nicholls", "Gallegos", "Russo", "Tang",
            "Grant", "Mann", "Petty", "Sharp", "Redmond", "Mcneil", "Carson", "Esparza", "Macdonald", "Petterson",
            "Davidson", "Haggerty"};

    private List<String> firstNamesList;
    private List<String> lastNamesList;

    public Names() {
        firstNamesList = Arrays.asList(firstNames);
        lastNamesList = Arrays.asList(lastNames);
    }

    // GETTERS:
    public List<String> getFirstNamesList() {
        return this.firstNamesList;
    }

    public List<String> getLastNamesList() {
        return this.lastNamesList;
    }

    // REQUIRES: there are greater or more elements in lastNamesList than numOfNames
    // MODIFIES: this
    // EFFECTS: creates a list of random names
    public List<String> createNames(int numOfNames) {
        List<String> myFirstNames = getSomeFirstNames(numOfNames);
        List<String> myLastNames = getSomeLastNames(numOfNames);

        List<String> fullNames = new ArrayList<>();

        for (String name : myFirstNames) {
            fullNames.add(name + " " + myLastNames.get(0));
            myLastNames.remove(0);
        }
        return fullNames;
    }

    // EFFECTS: creates list of first names of given size
    public List<String> getSomeFirstNames(int numOfNames) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < numOfNames; i++) {
            Random random = new Random();
            int index = random.nextInt(firstNamesList.size());
            names.add(firstNamesList.get(index));
        }
        return names;
    }

    // REQUIRES: there are greater or more elements in lastNamesList than numOfNames
    // MODIFIES: this
    // EFFECTS: creates list of last names of given size
    public List<String> getSomeLastNames(int numOfNames) {
        List<String> names = new ArrayList<>();
        int i = 0;
        while (i < numOfNames) {
            Random random = new Random();
            int index = random.nextInt(lastNamesList.size());
            if (!names.contains(lastNamesList.get(index))) {
                names.add(lastNamesList.get(index));
                i++;
            }
        }
        return names;
    }
}


