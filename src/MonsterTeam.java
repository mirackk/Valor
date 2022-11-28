import java.util.ArrayList;

public class MonsterTeam extends Team{
    protected ArrayList<Monster> monsters;
    protected MonsterFactory monsterFactory;
    public MonsterTeam(Config config){
        super(config);
        this.monsterFactory=new MonsterFactory();
        this.monsters = new ArrayList<>();
    }

    public void createMonsters(int level){
        for(int i=0;i<3;i++){
            //monsters[i] = monsterFactory.create(config,level);
            Monster newMonster = monsterFactory.create(config,level);

            int row= 0;
            int col;
            if(i==0){
                col = 1;
            }
            else if(i==1){
                col = 4;
            }
            else {
                col =7;
            }
            //monsters[i].pos = new Position(row,col);
            newMonster.pos= new Position(row,col);
            // put it in the right lane
            newMonster.setLane(i);
            //add monster to the team
            monsters.add(newMonster);
        }
    }

    public void removeMonster(){

    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}

