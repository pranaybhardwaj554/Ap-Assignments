public class Elite extends Customer {
    Elite(String name,String address){
        super(name,address);
        category="(Elite)";
    }
    @Override
    public void delCharge(){
        this.getCart().setDelcharge(0);
    }
    @Override
    public double initiateddiscount(double amount){
        if(amount>200){
            amount=amount-50;
        }
        return amount;
    }
}
