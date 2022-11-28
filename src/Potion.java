//hero can use one-time potion
//Name/cost/required level/attribute increase/attribute affected
public class Potion extends Item {
    private int increaseVal;
    private String attributeAffect;

    public Potion(String name, int price, int level, int val, String attributeAffect) {
        super(name, price, level);
        this.increaseVal = val;
        this.attributeAffect = attributeAffect;
    }

    public void usePotion(Hero hero) {
        if (attributeAffect.equals("Health")) {
            hero.addHp(increaseVal);
        }
        if (attributeAffect.equals("Strength")) {
            hero.addStrength(increaseVal);
        }
        if (attributeAffect.equals("Mana")) {
            hero.addMp(increaseVal);
        }
        if (attributeAffect.equals("Agility")) {
            hero.addAgility(increaseVal);
        }
        if (attributeAffect.equals("Health/Mana/Strength/Agility")) {
            hero.addHp(increaseVal);
            hero.addStrength(increaseVal);
            hero.addMp(increaseVal);
            hero.addAgility(increaseVal);
        }
        if (attributeAffect.equals("All")) {
            hero.addHp(increaseVal);
            hero.addStrength(increaseVal);
            hero.addMp(increaseVal);
            hero.addAgility(increaseVal);
            hero.addDexterity(increaseVal);
            hero.addDefense(increaseVal);
        }
    }

    public void printPotionInfo() {
        System.out.println(name + " " + price + " " + level + " " + increaseVal + " " + attributeAffect);
    }

    public void printAccuratePotionInfo() {
        System.out.println("Potion name: " + name + " price: " + price + " level: " + level + " increase value: " + increaseVal + " attribute affected: " + attributeAffect);
    }


}
