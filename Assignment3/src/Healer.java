import java.util.Random;

public class Healer extends Player{
    static Random choose = new Random();
    Healer(int id){
        super(id);
        this.setHP(800);
    }
    public static int healerVote(Game game){
        int index=choose.nextInt(game.getPlayers().size());
        int heal=game.getPlayers().get(index).getId();
        return heal;
    }
    public static void gohealerHeal(Player heal){
        heal.setHP(heal.getHP()+500);
    }
    @Override
    public boolean equals(Object o1){
        if(o1 instanceof Healer){
            Healer o=(Healer) o1;
            return(this.getId()==o.getId());
        }
        else{
            return false;
        }
    }
}
