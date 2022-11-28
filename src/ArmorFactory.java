import java.util.List;

// create instance of armor
public class ArmorFactory {
    private String menuStr = "Name/cost/required level/damage reduction";

    public Armor create(Config config, int index) {
        List<String> armor = config.armoryInfo.get(index);
        String name = armor.get(0);
        int cost = Integer.parseInt(armor.get(1));
        int level = Integer.parseInt(armor.get(2));
        int reduction = Integer.parseInt(armor.get(3));
        return new Armor(name, cost, level, reduction);
    }
}
