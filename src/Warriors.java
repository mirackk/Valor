//Warriors are favored on strength and agility
public class Warriors extends Hero {
    private double normalRate;
    private double favorRate;

    public Warriors(String name, int level, int experience, int hp, int mp, int strength, int dexterity, int agility, int gold, Bag bag, Position pos) {
        super(name, level, experience, hp, mp, strength, dexterity, agility, gold, bag, pos);
        this.normalRate = 0.05;
        this.favorRate = 0.1;
    }


    @Override
    public void addAttribute() {
        this.strength += Math.max(1, (int) this.strength * favorRate);
        this.dexterity += Math.max(1, (int) this.dexterity * normalRate);
        this.agility += Math.max(1, (int) this.agility * favorRate);
    }


}
