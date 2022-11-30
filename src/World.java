import java.util.Random;

// the whole map where user can take actions
public class World {
    private Cell[][] map;
    private int rowSize;
    private int colSize;

    private Config config;

    public World(int rowSize, int colSize, Config config) {
        this.map = new Cell[rowSize][colSize];
        this.colSize = colSize;
        this.rowSize = rowSize;
        this.config = config;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                map[i][j] = createCell(i, j);
            }
        }

        int i = 0;
        for(int j=0;j<colSize;j++){
            if(j==2||j==5){
                map[i][j]=createInaccessible(i,j);
            }
            else{
                map[i][j]=createNexus(i,j);
            }
        }
        i++;
        for(;i<rowSize-1;i++){
            for(int j=0;j<colSize;j++){
                if(j==2||j==5){
                    map[i][j]=createInaccessible(i,j);
                }
                else{
                    map[i][j]=createCell(i,j);
                }
            }
        }
        for(int j=0;j<colSize;j++){
            if(j==2||j==5){
                map[i][j]=createInaccessible(i,j);
            }
            else{
                map[i][j]=createNexus(i,j);
            }
        }
    }

    // generate the Nexus
    public Cell createNexus(int row, int col){
        return new NexusCell(new Position(row,col));
    }

    public Cell createInaccessible(int row, int col){
        return new InaccessibleCell(new Position(row,col));
    }

    // 4 kinds of cells with 0.25 chances
    public Cell createCell(int row, int col) {
        double rate = Math.random();
        Position pos = new Position(row, col);
        if (rate < 0.25) {
            Cell cell = new BushCell(pos);
            return cell;
        } else if (rate >= 0.25 && rate < 0.5) {
            Cell cell = new CaveCell(pos);
            return cell;
        } else if (rate >= 0.5 && rate < 0.75) {
            Cell cell = new KoulouCell(pos);
            return cell;
        }
        else{
            Cell cell = new PlainCell(pos);
            return cell;
        }
    }


    public Cell[][] getMap() {
        return map;
    }



    // print map
    public void printMap(HeroTeam heroTeam, MonsterTeam monsterTeam) {
        for (int i = 0; i < rowSize; i++) {
            for(int cellRow = 0;cellRow<3;cellRow++){
                String curTyep="";
                for(int j=0;j<colSize;j++) {
                    Cell cell = map[i][j];
                    // decide cell type
                    if(cell instanceof NexusCell){
                        curTyep="N";
                    }
                    if(cell instanceof InaccessibleCell){
                        curTyep="I";
                    }
                    if(cell instanceof PlainCell){
                        curTyep="P";
                    }
                    if(cell instanceof BushCell){
                        curTyep="B";
                    }
                    if(cell instanceof CaveCell){
                        curTyep="C";
                    }
                    if(cell instanceof KoulouCell){
                        curTyep="K";
                    }
                    // generate
                    if(cellRow==0){
                        boxUp(curTyep);
                    }
                    if(cellRow==1){
                        boxContent(curTyep,heroTeam,monsterTeam,i,j);
                    }
                    if(cellRow==2){
                        boxDown(curTyep);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("H as your heroes places, M as monsters places");
    }

    public void boxUp(String type){
        System.out.print(type+" - "+type+" - "+type+"  ");
    }

    //todo: when there is hero or monster
    public void boxContent(String type,HeroTeam heroTeam,MonsterTeam monsterTeam,int mapX, int mapY){
        int[] cols = new int[3];
        int[] rows = new int[3];
        for(int i=0;i<heroTeam.heroes.length;i++){
            rows[i] = heroTeam.heroes[i].pos.getX();
            cols[i] = heroTeam.heroes[i].pos.getY();
        }
        String content = "|       |  ";
        if(type.equalsIgnoreCase("I")){
            content = "| X X X |  ";
            //System.out.print("| X X X |  ");
        }
        else{
            String tmp = "";
            for(int heroIndex=0;heroIndex<3;heroIndex++){
                if(mapX==heroTeam.heroes[heroIndex].pos.getX()&& mapY==heroTeam.heroes[heroIndex].pos.getY()){
                    String h = ""+heroIndex;
                    tmp = "| H"+h+"    |  ";
                }
            }
            for(int monsterIndex = 0;monsterIndex<monsterTeam.monsters.size();monsterIndex++){
                if(mapX==monsterTeam.monsters.get(monsterIndex).pos.getX()&&mapY==monsterTeam.monsters.get(monsterIndex).pos.getY()){
                    String m = ""+monsterIndex;
                    if(tmp.equals("")){
                        tmp = "|    M"+m+" |  ";
                    }
                    else{
                        tmp = tmp.substring(0,4);
                        tmp = tmp+" M"+m+" |  ";
                    }
                }
            }
            if(tmp.equals("")){
            }
            else{
                content=tmp;
            }
        }

        System.out.print(content);
    }

    public void boxDown(String type){
        System.out.print(type+" - "+type+" - "+type+"  ");
    }

    public boolean heroInCell(int row,int col,HeroTeam heroTeam){
        for(int heroIndex=0;heroIndex<3;heroIndex++){
            if(row == heroTeam.heroes[heroIndex].pos.getX()&&col ==heroTeam.heroes[heroIndex].pos.getY()){
                return true;
            }
        }
        return false;
    }

    public boolean monsterInCell(int row,int col,MonsterTeam monsterTeam){
        for(int monsterIndex = 0;monsterIndex<monsterTeam.monsters.size();monsterIndex++){
            if(row==monsterTeam.monsters.get(monsterIndex).pos.getX()&&col==monsterTeam.monsters.get(monsterIndex).pos.getY()){
                return true;
            }
        }
        return false;
    }
}
