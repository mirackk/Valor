//monster
public class Exoskeletons extends Monster {
    private String monsterType;

    public Exoskeletons(String name, int level, int hp, int damage, int defense, int dodge, Position pos) {
        super(name, level, hp, damage, defense, dodge, pos);
        this.monsterType = "Exoskeletons";
    }
}
