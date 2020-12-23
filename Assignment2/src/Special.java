public class Special extends Customer {
    Special(String name,String address){
        super(name,address);
        category="(Special)";
    }
    @Override
    public void delCharge(){
        this.getCart().setDelcharge(20);
    }
    @Override
    public double initiateddiscount(double amount){
        if(amount>200){
            amount=amount-25;
        }
        return amount;
    }
}
