import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game implements Cloneable {
    static Scanner input = new Scanner(System.in);
    private ArrayList<Mafia> mafias;
    private int noPlayers;
    private ArrayList<Player> players;
    final private int userType;
    private int userId;
    public Game(ArrayList<Player> players, int noPlayers,int player,ArrayList<Mafia> mafias)
    {
        this.noPlayers = noPlayers;
        this.players = players;
        this.userType = player;
        this.mafias=mafias;
        this.assignPlayers();
    }
    public Game clone() {
        try { // deep copy
            Game copy = (Game) super.clone();
            copy.players = new ArrayList<Player>(players);
            copy.mafias = new ArrayList<Mafia>(mafias);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null; // won't ever happen
        }
    }
    public void assignPlayers(){
        int id=0;
        ArrayList<Integer> ids= new ArrayList<Integer>(getNoPlayers());
        for(int i=0;i<getNoPlayers();i++){
            ids.add(i+1);
        }
        Collections.shuffle(ids);
        for(int i=0;i<getNoPlayers()/5;i++){
            if(getUserType()==1){ getMafias().add(new Mafia(ids.get(id))); getPlayers().add(getMafias().get(i)) ;this.setUserId(ids.get(id));id++; }
            else{
                getMafias().add(new Mafia(ids.get(id))); getPlayers().add(getMafias().get(i));
                id++;
            }
        }
        for(int i=0;i<getNoPlayers()/5;i++){
            if(getUserType()==2){getPlayers().add(new Detective(ids.get(id))); this.setUserId(ids.get(id));id++;}
            else{
                getPlayers().add(new Detective(ids.get(id)));
                id++;
            }
        }
        for(int i=0;i<Math.max(1,getNoPlayers()/10);i++){
            if(getUserType()==3){getPlayers().add(new Healer(ids.get(id))); this.setUserId(ids.get(id));id++;}
            else{
                getPlayers().add(new Healer(ids.get(id)));
                id++;
            }
        }
        int remain=getNoPlayers()-getPlayers().size();
        for(int i=0;i<remain;i++){
            if(getUserType()==4){getPlayers().add(new Commoner(ids.get(id))); this.setUserId(ids.get(id));id++;}
            else{
                getPlayers().add(new Commoner(ids.get(id)));
                id++;
            }
        }
        Collections.sort(getPlayers(),new CustaComparator());
    }
    public boolean checkExistence(int a){
        boolean b=false;
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i).getId()==a){
                b=true;
            }
        }
        return b;
    }
    public void gomafiaKill(int id){
        int index=0;
        for(int i=0;i<this.getPlayers().size();i++){
            if(this.getPlayers().get(i).getId()==id){
                index=i;
            }
        }
        Player toKill=this.getPlayers().get(index);
        Mafia.gomafiaKill(this.getMafias(),toKill);
    }
    public int countDetectives(){
        int count=0;
        for (int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i) instanceof Detective){count++;}
        }
        return count;
    }
    public void godetectiveKill(int id,boolean[] a){
        int index=0;
        for(int i=0;i<this.getPlayers().size();i++){
            if(this.getPlayers().get(i).getId()==id){
                getMafias().remove(this.getPlayers().get(i));
                index=i;
            }
            if(this.getPlayers().get(i).getId()==getUserId()){a[0]=false;};
        }
        System.out.println("Player"+id+" has been voted out.");
        getPlayers().remove(index);
    }
    public int godetectivetest(int id){
        int index=0;
        for(int i=0;i<this.getPlayers().size();i++){
            if(this.getPlayers().get(i).getId()==id){
                index=i;
            }
        }
        Player toKill=this.getPlayers().get(index);
        return Detective.godetectiveTest(toKill);
    }
    public void gohealerHeal(int id){
        int index=0;
        for(int i=0;i<this.getPlayers().size();i++){
            if(this.getPlayers().get(i).getId()==id){
                index=i;
            }
        }
        Player toKill=this.getPlayers().get(index);
        Healer.gohealerHeal(toKill);

    }
    public boolean checkMafia(int a){
        boolean b=true;
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i).getId()==a){
                if(Mafia.checkMafia(getPlayers().get(i))){
                    b=false;
                }
            }
        }
        return b;
    }
    public boolean checkDetective(int a){
        boolean b=true;
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i).getId()==a){
                if(Detective.checkDetective(getPlayers().get(i))){
                    b=false;
                }
            }
        }
        return b;
    }
    public String printDetectives(){
        String dec="";
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i) instanceof Detective && getPlayers().get(i).getId()!=getUserId()){
                dec=dec+ " Player"+Integer.toString(getPlayers().get(i).getId());
            }
        }
        return dec;
    }
    public String printMafias(){
        String dec="";
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i) instanceof Mafia && getPlayers().get(i).getId()!=getUserId()){
                dec=dec+ " Player"+Integer.toString(getPlayers().get(i).getId());
            }
        }
        return dec;
    }
    public int numberofmafia(){
        int count=0;
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i) instanceof Mafia){
                count++;
            }
        }
        return count;
    }
    public int numberofnonmafia(){
        int count=0;
        for(int i=0;i<getPlayers().size();i++){
            if(!(getPlayers().get(i) instanceof Mafia)){
                count++;
            }
        }
        return count;
    }
    public String printHealers(){
        String dec="";
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i) instanceof Healer && getPlayers().get(i).getId()!=getUserId()){
                dec=dec+ " Player"+Integer.toString(getPlayers().get(i).getId());
            }
        }
        return dec;
    }
    public String printCommoners(){
        String dec="";
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i) instanceof Commoner && getPlayers().get(i).getId()!=getUserId()){
                dec=dec+ " Player"+Integer.toString(getPlayers().get(i).getId());
            }
        }
        return dec;
    }
    public int getUserIndex(){
        int index=0;
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i).getId()==getUserId()){
                index=i;
            }
        }
        return index;
    }
    public boolean killofHP0(){
        boolean a[]={true};
        int count=0;
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i).getHP()==0 && !(getPlayers().get(i) instanceof Mafia)){
                System.out.println("Player"+getPlayers().get(i).getId()+" has died.");
                count++;
                if(getPlayers().get(i).getId()==getUserId()){
                    a[0]=false;

                }
                getPlayers().remove(i);
                break;
            }
        }
        if(count==0){
            System.out.println("No one has died");
        }
        return a[0];
    }
    public int voteeach(boolean a){
        for(int i=0;i<getPlayers().size();i++){
            int chosen;
            if(a&& (getPlayers().get(i).getId()==getUserId())){
                System.out.print("Select a person to voteout");
                int index=input.nextInt();
                chosen = idtoindex(index);
                while(!checkExistence(index)){
                    System.out.println("Chosen Player does not exit: Choose once more:");
                    index=input.nextInt();
                    chosen = idtoindex(index);
                }
                while(getPlayers().get(i).equals(getPlayers().get(chosen))){
                    System.out.println("You can't choose youself: Choose once more");
                    index=input.nextInt();
                    chosen = idtoindex(index);
                }
            }
            else{
                chosen=getPlayers().get(i).vote(getPlayers().size());
                while(getPlayers().get(i).equals(getPlayers().get(chosen))){
                  chosen=getPlayers().get(i).vote(getPlayers().size());
                }
            }
            getPlayers().get(chosen).setVote(getPlayers().get(chosen).getVote()+1);
        }
        while (this.tieornot()){
            for(int i=0;i<getPlayers().size();i++){getPlayers().get(i).setVote(0);}
            //if(a){System.out.println("Voting has tied");}
            for(int i=0;i<getPlayers().size();i++){
                int chosen;
                chosen=getPlayers().get(i).vote(getPlayers().size());
                while(getPlayers().get(i).equals(getPlayers().get(chosen))){
                    chosen=getPlayers().get(i).vote(getPlayers().size());
                }
                getPlayers().get(chosen).setVote(getPlayers().get(chosen).getVote()+1);
            }
        }
        int idso=0;
        int maxvote=this.getPlayers().get(idso).getVote();
        for(int i=0;i<this.getPlayers().size();i++){
            if(maxvote<this.getPlayers().get(i).getVote()){
                maxvote=this.getPlayers().get(i).getVote();
                idso=this.getPlayers().get(i).getId();
            }
        }
        for(int i=0;i<getPlayers().size();i++){getPlayers().get(i).setVote(0);}
        return idso;
    }
    public  int idtoindex(int id){
        int index=0;
        for(int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i).getId()==id){
                index=i;
            }
        }
        return index;
    }
    public void Kill(int kill){
        System.out.println("Player"+getPlayers().get(idtoindex(kill)).getId()+" has been voted out.");
        if(getPlayers().get(idtoindex(kill)) instanceof Mafia){
            getMafias().remove(getPlayers().get(idtoindex(kill)));
        }
        getPlayers().remove((idtoindex(kill)));

    }
    public int numberofHealers(){
        int count=0;
        for (int i=0;i<getPlayers().size();i++){
            if(getPlayers().get(i) instanceof Healer){count++;}
        }
        return count;
    }
    public boolean tieornot(){
        int maxvote=this.getPlayers().get(0).getVote();
        for(int i=0;i<this.getPlayers().size();i++){
            if(maxvote<this.getPlayers().get(i).getVote()){
                maxvote=this.getPlayers().get(i).getVote();
            }
        }
        int rep=0;
        for(int i=0;i<this.getPlayers().size();i++){
            if(maxvote==this.getPlayers().get(i).getVote()){
                rep++;
            }
        }
        if(rep>1){
            return true;
        }
        else{
            return false;
        }
    }
    public int getNoPlayers() {
        return noPlayers;
    }
    public void mafiaKill(){

    }
    private void setNoPlayers(int noPlayers) {
        this.noPlayers = noPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getUserType() {
        return userType;
    }

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Mafia> getMafias() {
        return mafias;
    }

    private void setMafias(ArrayList<Mafia> mafias) {
        this.mafias = mafias;
    }
}
