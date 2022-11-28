// 3 heroes as a team
public class HeroTeam extends Team{
    protected Hero[] heroes;
    protected HeroFactory heroFactory;

    private int teamLevel;

    public HeroTeam(int num,Config config){
        super(config);
        this.heroes = new Hero[num];
        this.heroFactory=new HeroFactory();
        createTeam();
        this.teamLevel=1;
    }

    public void createTeam(){
        for (int i = 0; i < heroes.length; i++) {
            heroes[i] = heroFactory.create(config);
            heroes[i].setLane(i);
            int col;
            int row = 7;
            if(i==0){
                col = 0;
            }
            else if(i==1){
                col = 3;
            }
            else{
                col = 6;
            }
            Position home = new Position(row,col);
            heroes[i].setHome(home);
            heroes[i].pos=home;
        }
    }

    public Hero[] getHeroes() {
        return heroes;
    }

    public void updateTeamLevel() {
        int newLevel = this.teamLevel;
        for (Hero hero : heroes) {
            newLevel = Math.max(newLevel, hero.getLevel());
        }
        this.teamLevel = newLevel;
    }

    public int getTeamLevel() {
        return teamLevel;
    }
}
