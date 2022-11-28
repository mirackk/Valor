//heroes can not move to here
public class InaccessibleCell extends Cell {
    public InaccessibleCell(Position pos) {
        super(pos);
    }

    @Override
    public void heroIntoCell(Hero hero) {
        System.out.println("This is an Inaccessible place");
        System.out.println("You can not move to here");
    }

    @Override
    public void monsterIntoCell(Monster monster) {

    }

    @Override
    public void heroExitCell(Hero hero) {

    }
}
