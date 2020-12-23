import java.util.ArrayList;

public class Customer implements User {
    final protected String name;
    protected String category;
    final private String address;
    private double wallet;
    private int reward;
    private Cart cart;
    private Orders order;
    Customer(String name,String address){
        this.name=name;
        this.address=address;
        wallet=1000;
        category="";
        reward=0;
        cart= new Cart();
        order=new Orders();
    }
    public void printOrders(){
        this.getOrder().printorders();
    }
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getName() {
        return name;
    }
    public void printallcust(int i){
        System.out.println("    "+(i+1)+". "+this.getName()+" "+this.getCategory());

    }
    public String getAddress() {
        return address;
    }
    public double getWallet() {
        return wallet;
    }
    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void custprintCart(){
        for(int i=0;i<this.getCart().getItems().size();i++){
            System.out.println(this.getCart().getItems().get(i).getId()+" "+this.getCart().getItems().get(i).getName()+" - "+this.getCart().getItems().get(i).getName()+" "+this.getCart().getItems().get(i).getPrice()+" "+this.getCart().getItems().get(i).getQuantity()+" "+this.getCart().getItems().get(i).getOffer()+"% off "+this.getCart().getItems().get(i).getCategory());
        }
    }
    public void delCharge(){
        this.getCart().setDelcharge(40);
    }
    public void printi(){
        System.out.println(     this.getName()+this.getCategory()+", "+this.getAddress()+", "+this.getWallet()+"/-");
    }
    @Override
    public void printall(int i) {
        this.printallcust(i);
    }
    @Override
    public void print(){
        this.printi();
    }
    public double initiateddiscount(double amount){
        return amount;
    }
}
