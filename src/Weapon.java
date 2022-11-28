//Name/cost/level/damage/required hands
// for weapon
public class Weapon extends Item {
    private int damage;
    private int hands;

    public Weapon(String name, int price, int level, int damage, int hands) {
        super(name, price, level);
        this.damage = damage;
        this.hands = hands;
    }

    public int getDamage() {
        return damage;
    }

    public int getHands() {
        return hands;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    public void printWeaponInfo() {
        System.out.println(name + " " + price + " " + level + " " + damage + " " + hands);
    }

    public void printAccurateWeaponInfo() {
        System.out.println("Weapon name: " + name + " price: " + price + " level: " + level + " damage: " + damage + " hands: " + hands);
    }
}
