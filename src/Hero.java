import java.util.Random;
import java.util.Scanner;

// basic hero class
// can be extended to specific hero type
// hp = level*100
// level up mp = 1.1*mp
// atk = (strength + weapon)*0.05
// dodge = agility*0.02
// exp required = level*10
public abstract class Hero extends Role {
    protected int experience; // current exp, when level up exp will minus the exp used for last level.
    protected int mp;
    protected int strength;
    protected int dexterity;
    protected int agility;
    protected int gold;
    protected Bag bag;
    protected Weapon weapon;
    protected Armor armor;

    protected final int hands = 2; // each hero has 2 hands

    protected int defense = 0;

    protected Position home; // hero's home for market and respawn
    protected int lane; // 0 for top, 1 for mid, 2 for bot

    private double normalRate;
    private double favorRate;

    // init a hero
    public Hero(String name, int level, int experience, int hp, int mp, int strength, int dexterity, int agility, int gold, Bag bag, Position pos) {
        super(name, level, hp, pos);
        this.experience = experience;
        this.mp = mp;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.gold = gold;
        this.bag = bag;
        this.weapon = null;
        this.armor = null;
    }

    public void setHome(Position home) {
        this.home = home;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public abstract void addAttribute();

    // after each fight we check if the hero can level up
    // at most level 10
    public void levelUp() {
        if (level >= 10) {
            return;
        }
        int requireExp = 10 * this.level;
        while (this.experience >= requireExp) { // hero might level up 2 levels or more.
            System.out.println("Hero " + name);
            System.out.println("LEVEL UP!");
            this.level += 1;
            this.hp = Math.max(level * 100, this.hp);
            this.mp = (int) (this.mp * 1.1);
            this.updateExp(-requireExp);
            addAttribute();
            requireExp = 10 * this.level;
        }
    }

    public void printHeroInfo() {
        System.out.println("Name: " + name + " Level: " + level + " Current Exp: " + experience);
        System.out.println("HP: " + hp + " MP: " + mp);
        System.out.println("Gold: " + gold);
        System.out.println("Strength: " + strength + " Dexterity: " + dexterity + " Agility: " + agility + " Defense: " + defense);
        if (weapon != null) {
            weapon.printAccurateWeaponInfo();
        }
        if (armor != null) {
            armor.printAccurateArmorInfo();
        }
    }

    // monster defense = value * 0.1
    // monster dodge  = value * 0.005
    // hero atk = value * 0.1
    public void attack(Monster monster) {
        int originDamage;
        if(weapon==null){
            originDamage = (int) ((strength) * 0.1);
        }
        else{
            originDamage = (int) ((strength + weapon.getDamage()) * 0.1);
        }

        int actualDamage = originDamage - (int) (monster.getDefense() * 0.1);

        double dodgeRate = monster.getDodge() * 0.005;
        double rate = Math.random();
        if (rate < dodgeRate) {
            System.out.println("Hero " + name + " misses his attack on " + monster.getName());
            return;
        } else {
            System.out.println("Hero " + name + " attacks " + monster.getName() + " with " + actualDamage + " hp");
            monster.hp -= actualDamage;
        }
    }

    public void equip() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nHere are your items in the bag: ");
        bag.printItems();
        System.out.println("\nDo you want to equip? [Q/q] to quit else continue");
        String ans = sc.nextLine();
        if (ans.equalsIgnoreCase("q")) {
            return;
        }
        System.out.println("which do you want to equip?");
        System.out.println("Enter a number, for top to bottom on Weapon list, the first one is 0, second one is 1...");
        int index = sc.nextInt();
        // remove item from bag and put it on heroes
        // if already an item on hand, put it back to bag
        // for simple now we only allow hero to have one weapon
        Item tmpItem = bag.removeItem(index);
        if (tmpItem instanceof Weapon) {
            if (weapon == null) {
                setWeapon((Weapon) tmpItem);
                System.out.println("You have equipped: " + tmpItem.name);
            } else {
                Item undressed = weapon;
                bag.addItem(undressed);
                setWeapon((Weapon) tmpItem);
                System.out.println("You have equipped: " + tmpItem.name);
            }
        } else if (tmpItem instanceof Armor) {
            if (armor == null) {
                setArmor((Armor) tmpItem);
                System.out.println("You have equipped: " + tmpItem.name);
            } else {
                Item undressed = armor;
                bag.addItem(undressed);
                setArmor((Armor) tmpItem);
                System.out.println("You have equipped: " + tmpItem.name);
            }
        } else {
            System.out.println("equipment error, type wrong");
            //put the item back
            bag.addItem(tmpItem);
        }
    }

    public void drinkPotion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nHere are your items in the bag: ");
        bag.printItems();
        System.out.println("\nDo you want to use Potion? [Q/q] to quit else continue");
        String ans = sc.nextLine();
        if (ans.equalsIgnoreCase("q")) {
            return;
        }
        System.out.println("which do you want to drink?");
        System.out.println("Enter a number, for top to bottom on Potion list, the first one is 0, second one is 1...");
        int index = sc.nextInt();

        // get potion and use potion.use()
        Item tmpItem = bag.removeItem(index);
        if (tmpItem instanceof Potion) {
            ((Potion) tmpItem).usePotion(this);
        } else {
            System.out.println("potion error, type wrong");
            bag.addItem(tmpItem);
        }
    }

    //Hero’s spell damage = 𝑠𝑝𝑒𝑙𝑙_𝑏𝑎𝑠𝑒_𝑑𝑎𝑚𝑎𝑔𝑒+(𝑑𝑒𝑥𝑡𝑒𝑟𝑖𝑡𝑦/10000)×𝑠𝑝𝑒𝑙𝑙_𝑏𝑎𝑠𝑒_𝑑𝑎𝑚𝑎𝑔𝑒
    //Monster’s skill loss caused by spell effect = 𝑎𝑓𝑓𝑒𝑐𝑡𝑒𝑑𝑠𝑘𝑖𝑙𝑙×0.1
    public void useSpell(Monster monster) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nHere are your items in the bag: ");
        bag.printItems();
        System.out.println("which spell you want to use?");
        System.out.println("Enter a number, for top to bottom on Spell list, the first one is 0, second one is 1...");
        int index = sc.nextInt();

        // get the spell and check if the hero can use it
        // if not but it back to the bag
        Item tmpItem = bag.removeItem(index);
        // if it is a spell
        if (tmpItem instanceof Spell) {
            // if enough mp
            if (mp >= ((Spell) tmpItem).getMpCost()) {
                double dodgeRate = monster.getDodge() * 0.005;
                double rate = Math.random();
                // if monster dodge
                if (rate < dodgeRate) {
                    System.out.println("Hero " + name + " misses his spell on " + monster.getName());
                    return;
                } else {
                    String type = ((Spell) tmpItem).getType();
                    int originDamage = ((Spell) tmpItem).getDamage() + (int) ((dexterity / 10000.0) * ((Spell) tmpItem).getDamage());
                    int actualDamage = originDamage - (int) (monster.getDefense() * 0.1);
                    monster.hp -= actualDamage;
                    if (type.equalsIgnoreCase("Fire")) {
                        System.out.println("Hero " + name + " uses FireSpell on " + monster.getName() + " with " + actualDamage + " damage");
                        monster.setDamage((int) (0.9 * monster.getDamage()));
                        System.out.println("Monster " + monster.getName() + " damage decreased by 10%");
                    } else if (type.equalsIgnoreCase("Ice")) {
                        System.out.println("Hero " + name + " uses IceSpell on " + monster.getName() + " with " + actualDamage + " damage");
                        monster.setDefense((int) (0.9 * monster.getDefense()));
                        System.out.println("Monster " + monster.getName() + " defense decreased by 10%");
                    } else if (type.equalsIgnoreCase("Lightning")) {
                        System.out.println("Hero " + name + " uses LightningSpell on " + monster.getName() + " with " + actualDamage + " damage");
                        monster.setDodge((int) (0.9 * monster.getDodge()));
                        System.out.println("Monster " + monster.getName() + " dodge decreased by 10%");
                    }
                }
            } else {
                System.out.println("You don't have enough MP to use this item");
                bag.addItem(tmpItem);
            }
        } else {
            System.out.println("spell error, type wrong");
            bag.addItem(tmpItem);
        }
    }

    // when hero wins and gets exp ; when level up hero consumes exp
    public void updateExp(int val) {
        this.experience += val;
    }

    // hero can get money or use money
    public void updateGold(int val) {
        this.gold += val;
    }

    public void minusHp(int val) {
        hp -= val;
        System.out.println("Hero " + name + " hp decrease from " + (hp + val) + " to " + hp);
    }

    public void addHp(int val) {
        hp += val;
        System.out.println("Hero " + name + " hp increase from " + (hp - val) + " to " + hp);
    }

    public void addMp(int val) {
        mp += val;
        System.out.println("Hero " + name + " mp increase from " + (mp - val) + " to " + mp);
    }

    public void addStrength(int val) {
        strength += val;
        System.out.println("Hero " + name + " strength increase from " + (strength - val) + " to " + strength);
    }

    public void addAgility(int val) {
        agility += val;
        System.out.println("Hero " + name + " agility increase from " + (agility - val) + " to " + agility);
    }

    public void addDexterity(int val) {
        dexterity += val;
        System.out.println("Hero " + name + " dexterity increase from " + (dexterity - val) + " to " + dexterity);
    }

    public void addDefense(int val) {
        defense += val;
        System.out.println("Hero " + name + " defense increase from " + (defense - val) + " to " + defense);
    }


    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setHp(int hp) {
        super.setHp(hp);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    @Override
    public void setPos(Position pos) {
        super.setPos(pos);
    }

    @Override
    public Position getPos() {
        return super.getPos();
    }

    public int getMp() {
        return mp;
    }

    public int getExperience() {
        return experience;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public Bag getBag() {
        return bag;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Armor getArmor() {
        return armor;
    }

}
