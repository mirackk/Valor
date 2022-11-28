import java.util.List;

// create instance of potions
public class PotionFactory {
    private String menuStr = "Name/cost/required level/attribute increase/attribute affected";

    public Potion create(Config config, int index) {
        List<String> potion = config.potionsInfo.get(index);
        String name = potion.get(0);
        int cost = Integer.parseInt(potion.get(1));
        int level = Integer.parseInt(potion.get(2));
        int increase = Integer.parseInt(potion.get(3));
        String affect = potion.get(4);
        return new Potion(name, cost, level, increase, affect);
    }
}
