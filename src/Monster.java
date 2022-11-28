// for monsters
public class Monster extends Role {
    private int damage;
    private int defense;
    private int dodge;

    protected int lane; // represent which lane the monster is in

    public Monster(String name, int level, int hp, int damage, int defense, int dodge, Position pos) {
        super(name, level, hp, pos);
        this.damage = damage;
        this.defense = defense;
        this.dodge = dodge;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public void printMonsterInfo() {
        System.out.println("Monster name: " + name + " level: " + level + " hp: " + hp + " damage: " + damage + " defense: " + defense + " dodge: " + dodge);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }
}
