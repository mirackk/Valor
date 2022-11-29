// normal fight place
public class PlainCell extends Cell{
    public PlainCell(Position pos){
        super(pos);
    }
    public void heroIntoCell(Hero hero){
        System.out.println("Plain, no extra attribute");
    }

    @Override
    public void heroExitCell(Hero hero) {
        System.out.println("Exit Plain");
    }

    public void monsterIntoCell(Monster monster){

    }
}
