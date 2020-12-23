import java.util.Random;

public class Detective extends Player {
    static Random choose = new Random();
    Detective(int id){
        super(id);
        this.setHP(800);
    }
    public static boolean checkDetective(Player m) {
        return m instanceof Detective;
    }
    public static int detectiveVote(Game game){
        int index=choose.nextInt(game.getPlayers().size());
        int test=game.getPlayers().get(index).getId();
        while(!game.checkDetective(test)){
            index=choose.nextInt(game.getPlayers().size());
            test=game.getPlayers().get(index).getId();
        }
        return test;
    }
    public static int godetectiveTest(Player test){
        int i=0;
        if(test instanceof Mafia){
            i= 0;
        }
        else{
            i= 1;
        }
        return i;
    }
    @Override
    public boolean equals(Object o1){
        if(o1 instanceof Detective){
            Detective o=(Detective) o1;
            return(this.getId()==o.getId());
        }
        else{
            return false;
        }
    }
}
