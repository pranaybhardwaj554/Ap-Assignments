import java.util.ArrayList;
import java.util.Random;

public class Mafia extends Player {
    static Random choose = new Random();
    Mafia(int id){
        super(id);
        this.setHP(2500);
    }
    public static int mafiaVote(Game game){
        int index=choose.nextInt(game.getPlayers().size());
        int chooseToKIll=game.getPlayers().get(index).getId();
        while(!game.checkMafia(chooseToKIll)){
            index=choose.nextInt(game.getPlayers().size());
            chooseToKIll=game.getPlayers().get(index).getId();
        }
        return chooseToKIll;
    }
    public static int calmafiasHP(ArrayList<Mafia> mafias){
        int total=0;
        for (int i=0;i<mafias.size();i++){
            total=total+mafias.get(i).getHP();
        }
        return total;
    }
    public static int zeoMafia(ArrayList<Mafia> mafias){
        int y=0;
        for(int i=0;i<mafias.size();i++){
            if(mafias.get(i).getHP()>0){
                y++;
            }
        }
        return y;
    }
    public static int findMinimum(ArrayList<Mafia> mafias){
        int min = mafias.get(0).getHP();
        for(int i=0;i<mafias.size();i++){
            if(mafias.get(i).getHP()!=0){
                min=mafias.get(i).getHP();
            }
        }
        for(int i=0;i<mafias.size();i++){
            if(min>mafias.get(i).getHP() && mafias.get(i).getHP()>0 ){
                min=mafias.get(i).getHP();
            }
        }
        return min;
    }
    public static boolean checkMafia(Player m) {
        return m instanceof Mafia;
    }
    public static void distributeHP(ArrayList<Mafia> mafias,int HP){
        int y=1;
        int x=HP;
        while(x/y>=0){
            y=zeoMafia(mafias);
            if(y==0){break;}
            int z=findMinimum(mafias);
            if(z<x/y){
                System.out.println("Not good");
                for(int i=0;i<mafias.size();i++){
                    mafias.get(i).setHP(mafias.get(i).getHP()-z);
                }
                x=x-z;
            }
            else{
                for(int i=0;i<mafias.size();i++){
                    mafias.get(i).setHP(mafias.get(i).getHP()-x/y);

                }
                break;
            }
            y=zeoMafia(mafias);
            if(y==0){break;}
        }
    }
    public static void gomafiaKill(ArrayList<Mafia> mafias,Player a){
        int x= calmafiasHP(mafias);
        int y=a.getHP();
        if(x>=y){
            a.setHP(0);
            distributeHP(mafias,y);
        }
        else{
            a.setHP(y-x);
            distributeHP(mafias,x);
        }
    }

    @Override
    public boolean equals(Object o1){
        if(o1 instanceof Mafia){
            Mafia o=(Mafia) o1;
            return(this.getId()==o.getId());
        }
        else{
            return false;
        }
    }
}
