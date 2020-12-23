import javax.swing.tree.DefaultTreeCellEditor;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void print(ArrayList<? extends Player> list){
        for(int i=0;i<list.size();i++){
            System.out.print("Players,"+list.get(i).getId());
        }
        System.out.println("were" + list.get(1).getClass());
    }
    static Scanner input  = new Scanner(System.in);
    static int welcome(){
        System.out.println("Welcome to Mafia");
        System.out.print("Enter Number of Players: ");
        int noPlayers = input.nextInt();
        while(noPlayers<6){
            System.out.println("Minimum number of Players should be - 6");
            System.out.print("Enter Number of Players: ");
            noPlayers = input.nextInt();
        }
        System.out.println("Choose a Character");
        System.out.println("1) Mafia");
        System.out.println("2) Detective");
        System.out.println("3) Healer");
        System.out.println("4) Commoner");
        System.out.println("5) Assign Randomly");
        return noPlayers;
    }
    static void printRemaining(ArrayList<Player> players){
        System.out.print(players.size()+" players are remaining:");
        for(int i=0;i<players.size();i++){
            System.out.print(" Player"+players.get(i).getId()+",");
        }
        System.out.print(" are alive.");
        System.out.println("");
    }
    public static void last(Game game,Game clones,int player){
        if(player==1){
            System.out.print("Player"+game.getUserId()+"[User],");
            System.out.println(clones.printMafias()+" were Mafia.");
            System.out.println(clones.printDetectives()+" were Detectives.");
            System.out.println(clones.printHealers()+" were Healers.");
            System.out.println(clones.printCommoners()+" were Commoners.");
        }
        else if(player==2){
            System.out.println(clones.printMafias()+" were Mafia.");
            System.out.print("Player"+game.getUserId()+"[User],");
            System.out.println(clones.printDetectives()+" were Detectives.");
            System.out.println(clones.printHealers()+" were Healers.");
            System.out.println(clones.printCommoners()+" were Commoners.");
        }
        else if(player==3){
            System.out.println(clones.printMafias()+" were Mafia.");
            System.out.println(clones.printDetectives()+" were Detectives.");
            System.out.print("Player"+game.getUserId()+"[User],");
            System.out.println(clones.printHealers()+" were Healers.");
            System.out.println(clones.printCommoners()+" were Commoners.");
        }
        else if(player==4){
            System.out.println(clones.printMafias()+"were Mafia.");
            System.out.println(clones.printDetectives()+" were Detectives.");
            System.out.println(clones.printHealers()+" were Healers.");
            System.out.print("Player"+game.getUserId()+"[User],");
            System.out.println(clones.printCommoners()+" were Commoners.");
        }
    }
    static void printInfo(int player, Game g){
        int index=g.getUserId();
        System.out.println("You are Player"+index);
        if(player==1){
            System.out.print("You are a Mafia"+" ");
            System.out.println("Other mafias are:"+g.printMafias());
        }
        else if(player==2){
            System.out.println("You are a detective"+" ");
            System.out.println("Other detectives are:"+g.printDetectives());
        }
        else if(player==3){
            System.out.println("You are a Healer"+" ");
            System.out.println("Other healers are:"+g.printHealers());
        }
        else if(player==4){
            System.out.println("You are a Commoner"+" ");
        }
    }
    public static void main(String[] args){
        int noPlayers;
        boolean[] useralive={true};
        ArrayList<Player> players= new ArrayList<Player>();
        ArrayList<Mafia> mafias= new ArrayList<Mafia>();
        noPlayers=welcome();
        int player=input.nextInt();
        if(player==5){
            player=(int)Math.ceil(Math.random()*4);
        }
        Game game = new Game(players, noPlayers,player,mafias);
        printInfo(player,game);
        Random choose = new Random();
        int round=1;
        Game clones=game.clone();
        //last(game,clones,player);
        while(true){
            System.out.println("Round "+round+":");
            printRemaining(players);
            int chooseToKIll=0;
            if(game.getUserType()==1 && useralive[0]){
                System.out.print("Choose a target: ");
                chooseToKIll= input.nextInt();
                while(!(game.checkMafia(chooseToKIll) && game.checkExistence(chooseToKIll) )){
                    if(game.checkMafia(chooseToKIll)==false){
                        System.out.print("You cannot choose a mafia. Choose a player to kill: ");
                        chooseToKIll=input.nextInt();
                    }
                    else if(game.checkExistence(chooseToKIll)==false){
                        System.out.println("Player"+chooseToKIll+" is not in the game.");
                        System.out.print("Choose a target: ");
                        chooseToKIll=input.nextInt();
                    }
                }
            }
            else{
                chooseToKIll=Mafia.mafiaVote(game);
                System.out.println("Mafia have chosen their target");

            }
            if(mafias.size()>0){
            game.gomafiaKill(chooseToKIll);}
          //         System.out.println("So far so food");
            int test=0;
            if(game.getUserType()==2 && useralive[0]){
                System.out.print("Choose a player to test: ");
                test= input.nextInt();
                while(!(game.checkDetective(test) && game.checkExistence(test)) ){
                    if(game.checkDetective(test)==false){
                        System.out.print("You cannot choose a detective to test. Choose a player to test: ");
                        test=input.nextInt();
                    }
                    else if(game.checkExistence(test)==false){
                        System.out.println("Player"+test+" is not in the game.");
                        System.out.print("Choose a player to test: ");
                        test=input.nextInt();
                    }
                }
                if(game.godetectivetest(test)==0){
                    System.out.println("Player"+test+" is mafia. ");
                }
                else{
                    System.out.println("Player"+test+" is not a mafia. ");
                }
            }
            else{
                test= Detective.detectiveVote(game);
                System.out.println("Detective have chosen a player to test");
            }
            if(game.countDetectives()>0){
                game.godetectivetest(test);
            }
            int heal=0;
            if(game.getUserType()==3 && useralive[0]){
                System.out.print("Choose a player to heal: ");
                heal=input.nextInt();
                while(!game.checkExistence(heal)){
                    System.out.println("Player"+heal+" is not in the game. ");
                    System.out.print("Choose a player to heal: ");
                    heal=input.nextInt();
                }
            }
            else{
                heal= Healer.healerVote(game);
                System.out.println("Healers have chosen someone to heal. ");
            }
            System.out.println("--End of actions--");
            int vote=1;
            if(game.countDetectives()>0){
                vote=game.godetectivetest(test);
            }
            if(game.numberofHealers()>0){
                game.gohealerHeal(heal);
            }
            if(useralive[0]){
            useralive[0]=game.killofHP0();}
            else{
                game.killofHP0();
            }
            //vote hoga is case mai///
            if(vote==1){
                int out=game.voteeach(useralive[0]);
                if (out== game.getUserId()){
                    useralive[0]=false;
                }
                game.Kill(out);
            }
            else if(vote==0){
                game.godetectiveKill(test,useralive);
            }
            System.out.println("--End of Round "+round+"--");
            round++;
            if(game.numberofmafia()==game.numberofnonmafia() || game.numberofmafia()==0){
                break;
            }
        }
        System.out.println("Game-over");
        if(game.numberofmafia()==game.numberofnonmafia()){
            System.out.println("The Mafias have won");
        }
        else{
            System.out.println("The Mafias have lost");
        }
        last(game,clones,player);
    }
}
