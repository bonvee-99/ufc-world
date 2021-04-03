# UFC Stats and Fights

---

## Overview:

This application is for a user to interact with a UFC world. The user will be able to create their own fighters
and generate fights between their own fighters or between other ones. They will also be able to look at current fighters 
and their stats. Anyone who is interested in the UFC or likes to look at stats wil enjoy this.

This project is of interest to me because I myself enjoy watching the UFC. I have also played many games where you can
create your own player. Although this application is not exactly a simulation of the real life UFC, it allows you to 
play around and interact with other pre-made fighters, and the ones that you created yourself.

---

###User Stories
- As a user I want to create my own fighter(s) and add them to a weight class
- As a user I want to be able to view a fighter's stats
- As a user I want to be able to see all the fighters and recent matches in a weight class
- As a user I want to be able to make two fighters of my own choosing (or two random ones) fight

- As a user I want to be able to save the state of the UFC world. This will save the randomly 
generated fighters created when you start a new world, any fighters you create, any 
fights you add, and the corresponding stats the fights change.
- As a user I want to be able to load a previously saved file and continue from there.

###Phase 4: Task 2
I made the WeightClass class robust. I handled all the requires by changing the implementation of some methods. 
I also created methods that throw exceptions. These methods are (1) getRandomFighter() and (2) chooseOpponent(Fighter)
which can be found in the WeightClass class. After these changes I decided to add a new button that allows the user
to create a world with no fighters but with the 9 weight divisions (or else these changes would have been useless).
Now, if you create a new world without any fighters, if you try to generate a random fight, getRandomFighter() will
throw an exception that is then caught and displays to the user that they need to create/add some fighters. Also,
if the user tries to generate a random fight when there is only one fighter in the weight class, the error will be
caught, and the gui will display to the user they need to add at least one more fighter. Lastly, if chooseOpponent 
calls chooseOpponent with an argument of a fighter not in the weight class then it will display that there is
a system error. These exceptions are all caught within handleRandomFight() in the MainOptionsPage class.

###Phase 4: Task 3
I would implement the UfcGUI to only have a list of MainMenu and just access each page by their name by creating a 
method such as getMenuPanelByName, and removing the individual fields.

I could have also only had a list of buttons in each subclass of MainMenu instead of having multiple button 
fields, and a list of buttons, just by implementing a getButtonByName method and removing the individual fields.

I would create a hierarchy for each different type of fighter: CreatedFighter, GoodFighter, and BadFighter instead
of having multiple constructors within the Fighter class. I would have created an abstract class called Fighter
and have these 3 classes extend it. The abstract class would have the needed fields, abstract methods, 
and normal methods, and each subclass would implement the needed method(s) and have access to those fields.

I would create an Observer Pattern where UfcGUI "observes" over the MainMenu subclasses so that they do not each
need a gui field. UfcGUI would detect any changes, by calling notify() in each of the actionListener methods in each
subclass. This would then reduce some coupling in my project.









