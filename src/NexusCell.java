import java.util.Scanner;

// Nexus for both heroes and monsters
// for heroes it also servers as a market
public class NexusCell extends MarketCell{
    public NexusCell(Position pos){
        super(pos);
    }

    public void heroIntoCell(Hero hero){
        System.out.println("\nWelcome to the market!");
        sc = new Scanner(System.in);
        System.out.println("Hi, " + hero.getName());
        while (true) {
            System.out.println("Do you want to Buy or Sell?");
            System.out.println("Enter B/b for buying, S/s for selling, Q/q for quit");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            if (input.equalsIgnoreCase("b")) {
                buy(hero);
            }
            if (input.equalsIgnoreCase("s")) {
                sell(hero);
            }
        }
    }

    @Override
    public void heroExitCell(Hero hero) {
        System.out.println("Exit Nexus");
    }

    public void monsterIntoCell(Monster monster){

    }
}
