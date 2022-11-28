// create hero instance

import java.util.List;
import java.util.Random;
import java.util.Scanner;
// to create hero instance
public class HeroFactory {
    private String menuStr = "Name/mana/strength/agility/dexterity/starting money/starting experience";

    public Hero create(Config config) {
        System.out.println("Warriors");
        printAllHeroInfo(config.warriorsInfo);
        System.out.println("Sorcerers");
        printAllHeroInfo(config.sorcerersInfo);
        System.out.println("Paladins");
        printAllHeroInfo(config.paladinsInfo);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter W/w for warriors, S/s for sorcerers, P/p for Paladins");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("w")) {
                return createWarriors(config.warriorsInfo);
            } else if (name.equalsIgnoreCase("s")) {
                return createSorcerers(config.sorcerersInfo);
            } else if (name.equalsIgnoreCase("p")) {
                return createPaladins(config.paladinsInfo);
            } else {
                System.out.println("Please enter W/w,S/s,P/p");
            }
        }
    }

    ;

    public Hero createWarriors(List<List<String>> heroes) {
        System.out.println("Please choose the hero you want");
        System.out.println("From top to bottom, first one is num 0, next one is 1 ...");
        System.out.println("In the corresponding directory");
        Scanner sc = new Scanner(System.in);
        String tmpindex = sc.nextLine();
        int index = Integer.parseInt(tmpindex);
        List<String> hero = heroes.get(index);
        String name = hero.get(0);
        int mp = Integer.parseInt(hero.get(1));
        int strength = Integer.parseInt(hero.get(2));
        int agility = Integer.parseInt(hero.get(3));
        int dexterity = Integer.parseInt(hero.get(4));
        int gold = Integer.parseInt(hero.get(5));
        int exp = Integer.parseInt(hero.get(6));
        // init but nothing
        Bag bag = new Bag();
        Position position = new Position(-1, -1);
        Hero heroInstance = new Warriors(name, 1, exp, 100, mp, strength, dexterity, agility, gold, bag, position);
        System.out.println("Now you are " + name);
        System.out.println("\n");
        return heroInstance;
    }

    ;

    public Hero createSorcerers(List<List<String>> heroes) {
        System.out.println("Please choose the hero you want");
        System.out.println("From top to bottom, first one is num 0, next one is 1 ...");
        Scanner sc = new Scanner(System.in);
        String tmpindex = sc.nextLine();
        int index = Integer.parseInt(tmpindex);
        List<String> hero = heroes.get(index);
        String name = hero.get(0);
        int mp = Integer.parseInt(hero.get(1));
        int strength = Integer.parseInt(hero.get(2));
        int agility = Integer.parseInt(hero.get(3));
        int dexterity = Integer.parseInt(hero.get(4));
        int gold = Integer.parseInt(hero.get(5));
        int exp = Integer.parseInt(hero.get(6));
        // init but nothing
        Bag bag = new Bag();
        Position position = new Position(-1, -1);
        Hero heroInstance = new Sorcerers(name, 1, exp, 100, mp, strength, dexterity, agility, gold, bag, position);
        System.out.println("Now you are " + name);
        System.out.println("\n");
        return heroInstance;
    }

    ;

    public Hero createPaladins(List<List<String>> heroes) {
        System.out.println("Please choose the hero you want");
        System.out.println("From top to bottom, first one is num 0, next one is 1 ...");
        Scanner sc = new Scanner(System.in);
        String tmpindex = sc.nextLine();
        int index = Integer.parseInt(tmpindex);
        List<String> hero = heroes.get(index);
        String name = hero.get(0);
        int mp = Integer.parseInt(hero.get(1));
        int strength = Integer.parseInt(hero.get(2));
        int agility = Integer.parseInt(hero.get(3));
        int dexterity = Integer.parseInt(hero.get(4));
        int gold = Integer.parseInt(hero.get(5));
        int exp = Integer.parseInt(hero.get(6));
        // init but nothing
        Bag bag = new Bag();
        Position position = new Position(-1, -1);
        Hero heroInstance = new Paladins(name, 1, exp, 100, mp, strength, dexterity, agility, gold, bag, position);
        System.out.println("Now you are " + name);
        System.out.println("\n");
        return heroInstance;
    }

    ;

    public void printAllHeroInfo(List<List<String>> Heroes) {
        System.out.println(menuStr);
        for (int i = 0; i < Heroes.size(); i++) {
            List<String> hero = Heroes.get(i);
            System.out.println(hero);
        }
    }
}
