// to create spells
//Name/cost/required level/damage/mana cost
public class Spell extends Item {
    private int damage;
    private int mpCost;
    private String type;

    public Spell(String name, int price, int level, int damage, int mpCost, String type) {
        super(name, price, level);
        this.damage = damage;
        this.mpCost = mpCost;
        this.type = type;
    }

    public void printSpellInfo() {
        System.out.println(name + " " + price + " " + level + " " + damage + " " + mpCost + " " + type);
    }

    public void printAccurateSpellInfo() {
        System.out.println("Spell name: " + name + " price: " + price + " level: " + level + " damage: " + damage + " mpCost: " + mpCost + " type: " + type);
    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getMpCost() {
        return mpCost;
    }
}
