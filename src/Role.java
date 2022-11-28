// to create roles like hero and monster
public abstract class Role {
    protected String name;
    protected int level;
    protected int hp;
    protected Position pos; // current position

    public Role(String name, int level, int hp, Position pos) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
