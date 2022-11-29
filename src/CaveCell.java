// Cave spaces increase the agility
public class CaveCell extends Cell{
    public CaveCell(Position pos){
        super(pos);
    }
    public void heroIntoCell(Hero hero){
        System.out.println("Cave spaces increase the agility");
        hero.agility = (int)(hero.agility*1.1);
    }

    @Override
    public void heroExitCell(Hero hero) {
        System.out.println("Exit Cave");
        hero.agility = (int)(hero.agility/1.1);
    }

    public void monsterIntoCell(Monster monster){

    }
}
