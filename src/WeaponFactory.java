import java.util.List;

public class WeaponFactory {
    private String menuStr = "Name/cost/level/damage/required hands";

    public Weapon create(Config config, int index) {
        List<String> weapon = config.weaponryInfo.get(index);
        String name = weapon.get(0);
        int cost = Integer.parseInt(weapon.get(1));
        int level = Integer.parseInt(weapon.get(2));
        int damage = Integer.parseInt(weapon.get(3));
        int hands = Integer.parseInt(weapon.get(4));
        return new Weapon(name, cost, level, damage, hands);
    }
}
