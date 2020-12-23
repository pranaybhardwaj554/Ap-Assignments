import java.util.ArrayList;

public class Restraunt implements User{
    private final String name;
    protected String category;
    private int discount;
    private final String address;
    private int reward;
    private ArrayList<food> food_list;
    private int order_taken;
    Restraunt(String name,String address){
        this.name=name;
        this.address=address;
        reward=0;
        category="";
        food_list=new ArrayList<food>();
        discount=0;
        order_taken=0;
    }
    public void addFood(String name,int price, int quantity,String category,int offer,int id){
        this.food_list.add(new food(name, price, quantity, category, offer,id));
    }
    public food getFood(int index){
        int o=0;
        for(int i=0;i<this.food_list.size();i++){
            if(index==this.food_list.get(i).getId()){
                o=i;
            }
        }
        return this.food_list.get(o);
    }
    public int getFoodindex(int index){
        int o=0;
        for(int i=0;i<this.food_list.size();i++){
            if(index==this.food_list.get(i).getId()){
                o=i;
            }
        }
        return o;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getAddress() {
        return address;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public ArrayList<food> getFood_list() {
        return food_list;
    }

    public void setFood_list(ArrayList<food> food_list) {
        this.food_list = food_list;
    }
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public void printallrest(int i){
        System.out.println("    "+(i+1)+") "+this.getName()+" "+this.getCategory());

    }
    public double initiatediscount(double amount){
        //amount=amount*(1-(this.getDiscount()/100.0));
        return amount;
    }
    public int sendPoints(double value){
        int balue=(int) value;
        int rewards=(balue/100)*5;
        return rewards;
    }

    public int getOrder_taken() {
        return order_taken;
    }

    public void setOrder_taken(int order_taken) {
        this.order_taken = order_taken;
    }

    @Override
    public void printall(int i){
        this.printallrest(i);
    }
    @Override
    public void print(){
        System.out.println(     this.getName()+this.getCategory()+", "+this.getAddress()+", "+this.getOrder_taken());
    }
}
