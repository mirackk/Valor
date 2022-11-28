// create monster instance
// heroes will only meet monsters with same level

import java.util.List;
import java.util.Random;

public class MonsterFactory {
    private String menuStr = "Name/level/damage/defense/dodge chance";

    public Monster create(Config config, int level) {
        Random rd = new Random();
        int rate = rd.nextInt(9);
        if (rate < 3) {
            return createDragon(config.dragonsInfo, level);
        } else if (rate >= 3 && rate < 6) {
            return createExoSkeleton(config.exoskeletonsInfo, level);
        } else {
            return createSpirit(config.spiritsInfo, level);
        }
    }

    public Monster createDragon(List<List<String>> monsters, int level) {
        for (List<String> monster : monsters) {
            if (Integer.parseInt(monster.get(1)) != level)
                continue;
            else {
                String name = monster.get(0);
                int hp = level * 100;
                int damage = Integer.parseInt(monster.get(2));
                int defense = Integer.parseInt(monster.get(3));
                int dodge = Integer.parseInt(monster.get(4));
                // the position of monsters do not matter.
                Position pos = new Position(-1, -1);
                Monster m = new Dragons(name, level, hp, damage, defense, dodge, pos);
                return m;
            }
        }
        return null;
    }

    public Monster createExoSkeleton(List<List<String>> monsters, int level) {
        for (List<String> monster : monsters) {
            if (Integer.parseInt(monster.get(1)) != level)
                continue;
            else {
                String name = monster.get(0);
                int hp = level * 100;
                int damage = Integer.parseInt(monster.get(2));
                int defense = Integer.parseInt(monster.get(3));
                int dodge = Integer.parseInt(monster.get(4));
                // the position of monsters do not matter.
                Position pos = new Position(-1, -1);
                Monster m = new Exoskeletons(name, level, hp, damage, defense, dodge, pos);
                return m;
            }
        }
        return null;
    }

    public Monster createSpirit(List<List<String>> monsters, int level) {
        for (List<String> monster : monsters) {
            if (Integer.parseInt(monster.get(1)) != level)
                continue;
            else {
                String name = monster.get(0);
                int hp = level * 100;
                int damage = Integer.parseInt(monster.get(2));
                int defense = Integer.parseInt(monster.get(3));
                int dodge = Integer.parseInt(monster.get(4));
                // the position of monsters do not matter.
                Position pos = new Position(-1, -1);
                Monster m = new Spirits(name, level, hp, damage, defense, dodge, pos);
                return m;
            }
        }
        return null;
    }
}
