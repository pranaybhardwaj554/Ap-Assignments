public class Order {
    private final String item;
    private final int quantity;
    private final double price;
    private final String rest_name;
    private final int delivery;

    public Order(String item, int quantity, double price, String rest_name, int delivery) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.rest_name = rest_name;
        this.delivery = delivery;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getRest_name() {
        return rest_name;
    }

    public int getDelivery() {
        return delivery;
    }
}
