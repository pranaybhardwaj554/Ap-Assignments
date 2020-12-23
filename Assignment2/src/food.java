public class food {
    private String name;
    private int price;
    private int quantity;
    private String category;
    private int offer;
    final int id;
    public food(String name, int price, int quantity, String category, int offer,int id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.offer = offer;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }
}
