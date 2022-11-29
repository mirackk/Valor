// Bush spaces increase the dexterity
public class BushCell extends Cell{
    public BushCell(Position pos){
        super(pos);
    }
    public void heroIntoCell(Hero hero){
        System.out.println("Bush spaces increase the dexterity");
        hero.dexterity = (int)(hero.dexterity*1.1);
    }

    @Override
    public void heroExitCell(Hero hero) {
        System.out.println("Exit Bush");
        hero.dexterity = (int)(hero.dexterity/1.1);
    }

    public void monsterIntoCell(Monster monster){

    }
}
