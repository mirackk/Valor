import java.util.Scanner;

// controls the role to attack or cast spells
public class Fight {
    private Config config;
    private Scanner sc;

    public Fight(Config config) {
        this.config = config;
        this.sc = new Scanner(System.in);
    }

    public void attack(Hero hero, MonsterTeam monsterTeam) {
        while (true) {
            // continue or not
            sc = new Scanner(System.in);
            System.out.println("Do you want to continue to attack? Q/q to quit, other to continue");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("q")){
                break;
            }

            System.out.println("Please enter the coordinate of the monster you want to attack");
            System.out.println("For example, the left top is (0,0), right top is (0,7) ... ");
            for(int i=0;i<monsterTeam.monsters.size();i++){
                monsterTeam.monsters.get(i).printMonsterInfo();
            }
            sc = new Scanner(System.in);

            System.out.println("Enter row: ");
            while (!sc.hasNextInt()) {
                System.out.println("Not int！Enter again");
                sc.next();
            }
            int row = sc.nextInt();

            System.out.println("Enter col: ");
            while (!sc.hasNextInt()) {
                System.out.println("Not int！Enter again");
                sc.next();
            }
            int col = sc.nextInt();

            Monster target = null;
            // check if the position is valid(there is a monster)
            for (int i = 0; i < monsterTeam.monsters.size(); i++) {
                int x = monsterTeam.monsters.get(i).pos.getX();
                int y = monsterTeam.monsters.get(i).pos.getY();
                if (x == row && y == col) {
                    target = monsterTeam.monsters.get(i);
                    break;
                }
            }
            if (target == null) {
                System.out.println("No monster in this cell");
                continue;
            }

            boolean flag = targetInRange(hero, target, row, col);
            if (!flag) {
                System.out.println("Monster not in range");
                continue;
            }
            hero.attack(target);
            if (target.hp <= 0) {
                // remove dead monster from the team
                System.out.println(target.name+" is dead. Your successfully killed it. ");
                monsterTeam.monsters.remove(target);
                afterFight(hero, target);
            }
            break;
        }
    }

    public void cast(Hero hero, MonsterTeam monsterTeam) {
        while (true) {
            // continue or not
            sc = new Scanner(System.in);
            System.out.println("Do you want to continue to cast? Q/q to quit, other to continue");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("q")){
                break;
            }

            System.out.println("Please enter the coordinate of the monster you want to attack");
            System.out.println("For example, the left top is (0,0), right top is (0,7) ... ");
            for(int i=0;i<monsterTeam.monsters.size();i++){
                monsterTeam.monsters.get(i).printMonsterInfo();
            }
            sc = new Scanner(System.in);

            System.out.println("Enter row: ");
            while (!sc.hasNextInt()) {
                System.out.println("Not int！Enter again");
                sc.next();
            }
            int row = sc.nextInt();

            System.out.println("Enter col: ");
            while (!sc.hasNextInt()) {
                System.out.println("Not int！Enter again");
                sc.next();
            }
            int col = sc.nextInt();

            Monster target = null;
            // check if the position is valid(there is a monster)
            for (int i = 0; i < monsterTeam.monsters.size(); i++) {
                int x = monsterTeam.monsters.get(i).pos.getX();
                int y = monsterTeam.monsters.get(i).pos.getY();
                if (x == row && y == col) {
                    target = monsterTeam.monsters.get(i);
                    break;
                }
            }
            if (target == null) {
                System.out.println("No monster in this cell");
                continue;
            }

            boolean flag = targetInRange(hero, target, row, col);
            if (!flag) {
                System.out.println("Monster not in range");
                continue;
            }
            hero.useSpell(target);
            if (target.hp <= 0) {
                // remove dead monster from the team
                System.out.println(target.name+" is dead. Your successfully killed it. ");
                monsterTeam.monsters.remove(target);
                afterFight(hero, target);
            }
            break;
        }
    }

    //check if the target in hero's atk range
    public boolean targetInRange(Hero hero, Monster monster, int row, int col) {
        if (Math.abs(hero.pos.getX() - row)<= 1&& hero.lane==monster.lane) {
            return true;
        }
        return false;
    }

    // after fight, if hero wins, give them gold and exp, and recover hp mp.
    // when hero gets exp, I check if he can level up
    // if lose quit the game
    public void afterFight(Hero hero, Monster monster) {
        recover(hero);
        reward(hero, monster);
        hero.levelUp();
    }

    // can get exp = 2*number of monsters, gold = 500*monster level
    public void reward(Hero hero, Monster monster) {
        hero.updateExp(monster.level * 2);
        hero.updateGold(monster.level * 500);
    }

    // one can recover some hp and mp after fight
    public void recover(Hero hero) {
        hero.hp = (int) (hero.hp * 1.1);
        hero.mp = (int) (hero.mp * 1.1);
    }

    // if one is dead but wins, he can revive but get nothing
    public void revive(Hero hero) {
        hero.pos = hero.home;
        hero.hp = (int) (hero.level * 50);
        hero.mp = (int) (hero.level * 200);
    }
}
