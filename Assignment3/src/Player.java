import java.util.Random;

//**It is same as character class. It can also be identified as character class**//
public abstract class Player {
    static Random choose = new Random();
    private int HP;
    final private int id;
    private int vote;
    Player(int id){
        this.id=id;
        this.HP=800;
        this.vote=0;
    }
    public int getHP() {
        return HP;
    }
    public int vote(int chose){
        int chooserandom=choose.nextInt(chose);
        return chooserandom;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getId() {
        return id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
    @Override
    public abstract boolean equals(Object o1);
}
