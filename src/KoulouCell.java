// Koulou spaces increase the strength
public class KoulouCell extends Cell{
    public KoulouCell(Position pos){
        super(pos);
    }
    public void heroIntoCell(Hero hero){
        System.out.println("Koulou spaces increase the strength");
        hero.strength = (int)(hero.strength*1.1);
    }

    @Override
    public void heroExitCell(Hero hero) {
        System.out.println("Exit Koulou");
        hero.strength = (int)(hero.strength/1.1);
    }

    public void monsterIntoCell(Monster monster){

    }
}
