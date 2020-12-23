import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

public class Cart {
    ArrayList<food> items;
    private double value;
    private int delcharge;
    Cart(){
        items=new ArrayList<>();
        value=0;
        delcharge=0;
    }
    public void calbil(Customer a,Restraunt b){
        double amount=0;
        for(int i=0;i<this.getItems().size();i++){
            int val=this.getItems().get(i).getPrice()*this.getItems().get(i).getQuantity();
            double val2= val-(val*this.getItems().get(i).getOffer())/100.0;
            amount=amount+val2;
        }
        amount=b.initiatediscount(amount);
        amount=a.initiateddiscount(amount);
        amount=amount+this.getDelcharge();
        this.setValue(amount);
    }
    public int getDelcharge() {
        return delcharge;
    }

    public void setDelcharge(int delcharge) {
        this.delcharge = delcharge;
    }

    public ArrayList<food> getItems() {
        return items;
    }
    public void addItems(food a){
        food b=new food(a.getName(),a.getPrice(),a.getQuantity(),a.getCategory(),a.getOffer(),a.getId());
        items.add(b);
    }
    public void setItems(ArrayList<food> items) {
        this.items = items;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public  void distribute(Customer a, Restraunt b, company c){
        c.setRes_charges(c.getRes_charges()+(0.01*(this.getValue()-this.getDelcharge())));
        c.setDev_charges(c.getDev_charges()+this.getDelcharge());
        if(a.getReward()==0){
            a.setWallet(a.getWallet()-this.getValue());
            int rewards=b.sendPoints(this.getValue()-this.getDelcharge());
            a.setReward(rewards);
        }
        else{
            double value=this.getValue()-a.getReward();
            b.setReward(b.getReward()+a.getReward());
            a.setWallet(a.getWallet()-value);
            int rewards=b.sendPoints(value-this.getDelcharge());
            a.setReward(a.getReward()+rewards);
        }
    }
    public int total(){
        int it=0;
        for(int i=0;i<this.getItems().size();i++){
            it=it+this.getItems().get(i).getQuantity();
        }
        return it;
    }
}
