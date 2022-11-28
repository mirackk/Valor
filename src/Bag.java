import java.util.ArrayList;

//heroes can put items in the bag, and take out to equip
public class Bag {
    private ArrayList<Item> items;

    public Bag() {
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(int index) {
        Item tmpItem = items.get(index);
        items.remove(index);
        return tmpItem;
    }

    public void printItems() {
        for (Item item : items) {
            if (item instanceof Weapon) {
                ((Weapon) item).printAccurateWeaponInfo();
            }
            if (item instanceof Armor) {
                ((Armor) item).printAccurateArmorInfo();
            }
            if (item instanceof Potion) {
                ((Potion) item).printAccuratePotionInfo();
            }
            if (item instanceof Spell) {
                ((Spell) item).printAccurateSpellInfo();
            }
        }
    }


}
