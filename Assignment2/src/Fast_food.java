public class Fast_food extends Restraunt {
    Fast_food(String name,String address){
        super(name,address);
        category="(Fast Food)";
    }
    @Override
    public double initiatediscount(double amount){
        amount=amount*(1-(this.getDiscount()/100.0));
        return amount;
    }
    @Override
    public int sendPoints(double value){
        int balue=(int) value;
        int rewards=(balue/150)*10;
        return rewards;
    }
}
