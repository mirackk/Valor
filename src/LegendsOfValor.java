import java.util.Scanner;

public class LegendsOfValor extends Game{
    private final int worldSize = 8;
    private HeroTeam heroTeam;
    private MonsterTeam monsterTeam;
    private World world;
    private Config config;
    private Fight fight;
    private Scanner sc;

    public LegendsOfValor(){
        this.sc = new Scanner(System.in);
        this.config = new Config();
        config.getCondfigs();
        this.world = new World(worldSize,worldSize,config);
        this.fight= new Fight(config);
    }

    @Override
    public void start() {
        System.out.println("Welcome to Monsters and Heroes game! ");
        System.out.println("Author: Taoyu Chen & Li Xi");
        System.out.println("\n\n");
        this.heroTeam = new HeroTeam(3,config);
        this.monsterTeam = new MonsterTeam(config);
        monsterTeam.createMonsters(1);
        world.printMap(heroTeam,monsterTeam);

        mainLoop();
    }

    public void mainLoop(){
        System.out.println("Game Start! ");
        int count = 0; // mark rounds. after 4 rounds new monsters will spawn
        while(true){
            count++;
            heroTurn();
            monsterTurn();
            if(count==8){
                count =0;
                monsterTeam.createMonsters(heroTeam.getTeamLevel());
            }
        }
    }

    public void heroTurn(){
        for(int i = 0;i<3;i++){
            Hero hero = heroTeam.heroes[i];
            System.out.println("Hi, "+hero.name);
            System.out.println("Enter your command, H/h for help");
            sc = new Scanner(System.in);
            String input = sc.nextLine();
            while (true) {
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Quiting the game ...");
                    System.exit(0);
                } else if (input.equalsIgnoreCase("i")) {
                    System.out.println("Hero " + hero.name);
                    hero.printHeroInfo();
                    System.out.println("");
                }else if (input.equalsIgnoreCase("b")){
                    System.out.println("Hero " + hero.name);
                    hero.bag.printItems();
                    System.out.println("");
                }else if (input.equalsIgnoreCase("e")) {
                    System.out.println("Hero " + hero.name);
                    hero.equip();
                    System.out.println("");
                    break;
                }else if (input.equalsIgnoreCase("p")) {
                    System.out.println("Hero " + hero.name);
                    hero.drinkPotion();
                    System.out.println("");
                    break;
                }else if (input.equalsIgnoreCase("m")) {
                    world.printMap(heroTeam,monsterTeam);
                } else if (input.equalsIgnoreCase("h")) {
                    printCMD();
                }else if(input.equalsIgnoreCase("t")){
                    telePort();
                    break;
                }else if(input.equalsIgnoreCase("r")){
                    hero.pos=hero.home;
                    break;
                }else if(input.equalsIgnoreCase("mv")){
                    movement(hero);
                }else if(input.equalsIgnoreCase("a")){
                    fight.attack(hero,monsterTeam);
                }else if(input.equalsIgnoreCase("s")){
                    fight.cast(hero,monsterTeam);
                }
                else{
                    System.out.println("Invalid input, try again");
                }
            }

        }
    }

    // monsters automatically make their move
    // if there is a hero in their attacking range they will attack
    // otherwise they move forward
    public void monsterTurn(){
        for(int i = 0;i<monsterTeam.monsters.size();i++){
            Monster monster = monsterTeam.monsters.get(i);
        }
    }

    public boolean meetHero(){
        return false;
    }

    public boolean movement(Hero hero) {
        String direction;
        printMoveCMD();
        while(true){
            System.out.println("Please enter your next move");
            sc = new Scanner(System.in);
            direction = sc.nextLine();

            Position oldPos = hero.pos;
            int x = oldPos.getX();
            int y = oldPos.getY();
            if (direction.equalsIgnoreCase("w")) {
                x -= 1;
            }
            else if (direction.equalsIgnoreCase("a")) {
                y -= 1;
            }
            else if (direction.equalsIgnoreCase("s")) {
                x += 1;
            }
            else if (direction.equalsIgnoreCase("d")) {
                y += 1;
            }
            else{
                System.out.println("Invalid input, try again");
                continue;
            }
            // try to move to the new position
            // check legal
            Position newPos = new Position(x, y);
            Cell[][] map = world.getMap();
            if (x >= worldSize || y >= worldSize) {
                System.out.println("Your move out of Map, try again");
                return false;
            } else if (map[x][y] instanceof InaccessibleCell) {
                map[x][y].heroIntoCell(hero);
                return false;
            }
            else {
                intoANewCell(newPos, map,hero);
                return true;
            }
        }
    }

    public void telePort(){

    }

    public void intoANewCell(Position pos, Cell[][] map,Hero hero) {
        int x = pos.getX();
        int y = pos.getY();
        //go into the cell and do correspond thing
        if (map[x][y] instanceof NexusCell) {
            System.out.println("It is a market, do you want to enter?");
            System.out.println("M/m to enter, else to quit and make next move");
            sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("m")) {
                ((NexusCell) map[x][y]).generateRandomItems(config); // create selling list
                map[x][y].heroIntoCell(hero);
            }
        } else {
            //map[x][y].heroIntoCell();
        }
    }

    public void printCMD() {
        System.out.println("");
        System.out.println("Q/q: quit game");
        System.out.println("I/i: show current team information");
        System.out.println("B/b: show current Bag information of every team member");
        System.out.println("E/e: change equipment");
        System.out.println("P/p: drink potions");
        System.out.println("M/m: show map");
        System.out.println("");
        System.out.println("H/h: show help");
    }

    public void printMoveCMD(){
        System.out.println("W/w: move up");
        System.out.println("A/a: move left");
        System.out.println("S/s: move down");
        System.out.println("D/d: move right");
    }
}
