# CS611-<Assignment 3>
## Monster and Legend
---------------------------------------------------------------------------
Taoyu Chen 
mirack@bu.edu
U82740711

## Compiling Instruction
---------------------------------------------------------------------------
cd correspond folder(where all java files are)
javac Main.java
java Main
(Please include all .txt data in another folder call configs, or you have to change the import data path)

If using intellij, just put all .java files to src, and create a new folder then put all .txt in

## Potential Bonus Points
---------------------------------------------------------------------------
1.add more commands in main menu
'''
System.out.println("W/w: move up");
System.out.println("A/a: move left");
System.out.println("S/s: move down");
System.out.println("D/d: move right");
System.out.println("");
System.out.println("Q/q: quit game");
System.out.println("I/i: show current team information");
System.out.println("B/b: show current Bag information of every team member");
System.out.println("E/e: change equipment");
System.out.println("P/p: drink potions");
System.out.println("M/m: show map");
System.out.println("");
System.out.println("H/h: show help");
'''

2.first born in market place, so player can buy items to be stronger

3.factory design pattern. Use to generate hero instance, monster instance and so on.

4.put all config(txt data) in a new class and can fetch them with an instance.

5。level of monsters is exactly levels of heroes. Like heroes can be level 2, 5, 9; and the monsters they meet will be 2,5,9 too.

## Design
---------------------------------------------------------------------------
Items: father of all items in the market, including weapon, armor, potion, spell.

Weapon, Armor, Potion, Spell and their Factory: concrete items have different attributes maybe. And use factory to create their instances.
And Spell has 3 different sons Fire/Ice/Lightning

Bag: restore all items of a hero

World, Cell, FightCell, MarketCell, Inaccessible Cell: world is basically a map, consist of 2-d array cells. Cell has 3 different sons.

Role, Monster, Hero and their Factory
Warriors, Sorcerers, Paladins, Dragons, Exoskeletons, Spirits are concrete heroes and monsters, their sons.
Team is consist of a Hero array[]

Config: read all the txt files and store in the class.

Main, Game, MonstersAndHeroes: main loop of the game, control main things.

## Others
---------------------------------------------------------------------------
1.When the instructions in the terminal asking for a number input, don't enter a char or string. I haven't solve all the input problem for limited time.
And do not exceed the index.
2.I adjust some math value of the heroes and monsters to make the game easier.
3.There might be some functions I implemented but forgot to print out, sorry for that.