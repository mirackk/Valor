import java.util.List;

// to create spell instance, including 3 kinds of spells
public class SpellFactory {
    private String menuStr = "Name/cost/required level/damage/mana cost";

    public Spell createFireSpell(Config config, int index) {
        List<String> spell = config.fireSpellsInfo.get(index);
        String name = spell.get(0);
        int cost = Integer.parseInt(spell.get(1));
        int level = Integer.parseInt(spell.get(2));
        int damage = Integer.parseInt(spell.get(3));
        int mpCost = Integer.parseInt(spell.get(4));
        return new Spell(name, cost, level, damage, mpCost, "Fire");
    }

    public Spell createIceSpell(Config config, int index) {
        List<String> spell = config.iceSpellsInfo.get(index);
        String name = spell.get(0);
        int cost = Integer.parseInt(spell.get(1));
        int level = Integer.parseInt(spell.get(2));
        int damage = Integer.parseInt(spell.get(3));
        int mpCost = Integer.parseInt(spell.get(4));
        return new Spell(name, cost, level, damage, mpCost, "Ice");
    }

    public Spell createLightningSpell(Config config, int index) {
        List<String> spell = config.lightningSpellsInfo.get(index);
        String name = spell.get(0);
        int cost = Integer.parseInt(spell.get(1));
        int level = Integer.parseInt(spell.get(2));
        int damage = Integer.parseInt(spell.get(3));
        int mpCost = Integer.parseInt(spell.get(4));
        return new Spell(name, cost, level, damage, mpCost, "Lightning");
    }
}
