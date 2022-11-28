// to create armors
//Name/cost/required level/damage reduction
public class Armor extends Item {
    private int reduction;

    public Armor(String name, int price, int level, int reduction) {
        super(name, price, level);
        this.reduction = reduction;
    }

    public void printArmorInfo() {
        System.out.println(name + " " + price + " " + level + " " + reduction);
    }

    public void printAccurateArmorInfo() {
        System.out.println("Armor name: " + name + " price: " + price + " level: " + level + " reduction: " + reduction);
    }

    public int getReduction() {
        return reduction;
    }
}
