public class Authentic extends Restraunt {
    Authentic(String name,String address){
        super(name,address);
        category="(Authentic)";

    }
    @Override
    public double initiatediscount(double amount){
        amount=amount*(1-(this.getDiscount()/100.0));
        if(amount>100){
            amount=amount-50;
        }
        return amount;
    }
    @Override
    public int sendPoints(double value){
        int balue=(int) value;
        int rewards=(balue/200)*25;
        return rewards;
    }

}
