import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// cell for marketplace
public abstract class MarketCell extends Cell {
    protected Scanner sc;
    protected List<Weapon> weaponList;
    protected List<Armor> armorList;
    protected List<Potion> potionList;
    protected List<Spell> spellList;

    protected WeaponFactory weaponFactory;
    protected ArmorFactory armorFactory;
    protected PotionFactory potionFactory;
    protected SpellFactory spellFactory;

    public MarketCell(Position pos) {
        super(pos);
        //create lists
        weaponList = new ArrayList<>();
        armorList = new ArrayList<>();
        potionList = new ArrayList<>();
        spellList = new ArrayList<>();
        //create factory
        weaponFactory = new WeaponFactory();
        armorFactory = new ArmorFactory();
        potionFactory = new PotionFactory();
        spellFactory = new SpellFactory();
    }

    // each market will generate different items
    // if enter the market again the item will change
    // there can be same item for sells
    // we will sell 9 items for each catalog for convenience, for it is easier for heroes to buy more things
    public void generateRandomItems(Config config) {
        int weaponSize = config.weaponryInfo.size();
        int armorSize = config.armoryInfo.size();
        int potionSize = config.potionsInfo.size();
        int fireSpellSize = config.fireSpellsInfo.size();
        int iceSpellSize = config.iceSpellsInfo.size();
        int lightningSpellSize = config.lightningSpellsInfo.size();

        //Random randomNumber = new Random();
        //I write it in different loop because that we might use random number of different kinds in the future
        Random randomIndex = new Random();
        int number = 9;
        for (int i = 0; i < number; i++) {
            Weapon tmpWeapon = weaponFactory.create(config, randomIndex.nextInt(weaponSize));
            weaponList.add(tmpWeapon);
        }
        for (int i = 0; i < number; i++) {
            Armor tmpArmor = armorFactory.create(config, randomIndex.nextInt(armorSize));
            armorList.add(tmpArmor);
        }
        for (int i = 0; i < number; i++) {
            Potion tmpPotion = potionFactory.create(config, randomIndex.nextInt(potionSize));
            potionList.add(tmpPotion);
        }
        for (int i = 0; i < number / 3; i++) {
            Spell tmpFireSpell = spellFactory.createFireSpell(config, randomIndex.nextInt(fireSpellSize));
            Spell tmpIceSpell = spellFactory.createIceSpell(config, randomIndex.nextInt(iceSpellSize));
            Spell tmpLightningSpell = spellFactory.createLightningSpell(config, randomIndex.nextInt(lightningSpellSize));
            spellList.add(tmpFireSpell);
            spellList.add(tmpIceSpell);
            spellList.add(tmpLightningSpell);
        }
    }

    public void printSellingItems() {
        System.out.println("Here are weapons:");
        System.out.println("Name | cost | level | damage | required hands");
        for (Weapon weapon : weaponList) {
            weapon.printWeaponInfo();
        }
        System.out.println("Here are Armors:");
        System.out.println("Name | cost | required level | damage reduction");
        for (Armor armor : armorList) {
            armor.printArmorInfo();
        }
        System.out.println("Here are Potions:");
        System.out.println("Name | cost | required level | attribute increase | attribute affected");
        for (Potion potion : potionList) {
            potion.printPotionInfo();
        }
        System.out.println("Here are Spells:");
        System.out.println("Name | cost | required level | damage | mana cost | type");
        for (Spell spell : spellList) {
            spell.printSpellInfo();
        }
    }



    public void buy(Hero hero) {
        //System.out.println("");
        printSellingItems();
        System.out.println("There can be duplicate items in the market");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("CMD：");
            System.out.println("Press 1 for weapon");
            System.out.println("Press 2 for armor");
            System.out.println("Press 3 for potion");
            System.out.println("Press 4 for spell");
            System.out.println("Press q to leave");
            System.out.println("Your gold: " + hero.getGold() + "\tYour level: " + hero.getLevel());
            System.out.println("What do you want to buy?");
            String userInput = sc.nextLine();
            if (userInput.equals("1")) {
                buyWeapon(hero);
            } else if (userInput.equals("2")) {
                buyArmor(hero);
            } else if (userInput.equals("3")) {
                buyPotion(hero);
            } else if (userInput.equals("4")) {
                buySpell(hero);
            } else if (userInput.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("Invalid input, try again!");
            }
        }
    }

    public void buyWeapon(Hero hero) {
        sc = new Scanner(System.in);
        System.out.println("Which one do you want to buy");
        System.out.println("Enter a number, for top to bottom on Weapon list, the first one is 0, second one is 1...");
        int index = sc.nextInt();
        Weapon tmpWeapon = weaponList.get(index);
        // if hero has enough gold and level he can buy
        // hero gold minus
        // add item to bag
        if (hero.gold >= tmpWeapon.price && hero.level >= tmpWeapon.level) {
            hero.updateGold(-tmpWeapon.price);
            hero.bag.addItem(tmpWeapon);
            System.out.println("You have bought: " + tmpWeapon.name);
        } else {
            System.out.println("You don't have enough gold or level! Can't buy this item");
        }
    }

    public void buyArmor(Hero hero) {
        sc = new Scanner(System.in);
        System.out.println("Which one do you want to buy");
        System.out.println("Enter a number, for top to bottom on Armor list, the first one is 0, second one is 1...");
        int index = sc.nextInt();
        Armor tmpArmor = armorList.get(index);
        // if hero has enough gold and level he can buy
        // hero gold minus
        // add item to bag
        if (hero.gold >= tmpArmor.price && hero.level >= tmpArmor.level) {
            hero.updateGold(-tmpArmor.price);
            hero.bag.addItem(tmpArmor);
            System.out.println("You have bought: " + tmpArmor.name);
        } else {
            System.out.println("You don't have enough gold or level! Can't buy this item");
        }
    }

    public void buyPotion(Hero hero) {
        sc = new Scanner(System.in);
        System.out.println("Which one do you want to buy");
        System.out.println("Enter a number, for top to bottom on Potion list, the first one is 0, second one is 1...");
        int index = sc.nextInt();
        Potion tmpPotion = potionList.get(index);
        // if hero has enough gold and level he can buy
        // hero gold minus
        // add item to bag
        if (hero.gold >= tmpPotion.price && hero.level >= tmpPotion.level) {
            hero.updateGold(-tmpPotion.price);
            hero.bag.addItem(tmpPotion);
            System.out.println("You have bought: " + tmpPotion.name);
        } else {
            System.out.println("You don't have enough gold or level! Can't buy this item");
        }
    }

    public void buySpell(Hero hero) {
        sc = new Scanner(System.in);
        System.out.println("Which one do you want to buy");
        System.out.println("Enter a number, for top to bottom on Spell list, the first one is 0, second one is 1...");
        int index = sc.nextInt();
        Spell tmpSpell = spellList.get(index);
        // if hero has enough gold and level he can buy
        // hero gold minus
        // add item to bag
        if (hero.gold >= tmpSpell.price && hero.level >= tmpSpell.level) {
            hero.updateGold(-tmpSpell.price);
            hero.bag.addItem(tmpSpell);
            System.out.println("You have bought: " + tmpSpell.name);
        } else {
            System.out.println("You don't have enough gold or level! Can't buy this item");
        }
    }

    public void sell(Hero hero) {
        hero.bag.printItems();
        System.out.println("Which one do you want to sell");
        System.out.println("Enter a number, for top to bottom, the first one is 0, second one is 1...");
        sc = new Scanner(System.in);
        int index = sc.nextInt();
        // remove it from bag
        Item soldItem = hero.bag.removeItem(index);
        // add gold to hero
        int value = soldItem.price;
        System.out.println("You get " + value / 2 + " golds!");
        hero.updateGold((int) (value / 2));
    }
}
