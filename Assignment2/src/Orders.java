import java.util.ArrayList;

public class Orders {
    private ArrayList<Order> order;
    Orders(){
        order= new ArrayList<>();
    }
    public void addOrder(String item, int quantity, double price, String rest_name, int delivery){
        if(order.size()==10){
            order.remove(0);
        }
        order.add(new Order(item,quantity,price,rest_name,delivery));
    }
    public ArrayList<Order> getOrder() {
        return order;
    }
    public void printorders(){
        for(int i=0;i<this.getOrder().size();i++){
            System.out.println("Bought item: "+this.getOrder().get(i).getItem()+", quantity: "+this.getOrder().get(i).getQuantity()+" for Rs "+this.getOrder().get(i).getPrice()+" from Restaurant "+this.getOrder().get(i).getRest_name()+" and Delivery Charge: "+this.getOrder().get(i).getDelivery());
        }
    }
    public void setOrder(ArrayList<Order> order) {
        this.order = order;
    }
}
