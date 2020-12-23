import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[] rest1={0};
    static ArrayList<Restraunt> rest_list= new ArrayList<Restraunt>();
    static int id=1;
    static ArrayList<Customer> cut_list= new ArrayList<Customer>();
    static company Company=new company(0,0);
    static Scanner scan = new Scanner(System.in);
    static String[] arr ={"Name","Price","Quantity","Category","Offer"};
    static void printMenu(){
        System.out.println("Welcome to Zotato:");
        System.out.println("    1) Enter as Restaurant Owner");
        System.out.println("    2) Enter as Customer");
        System.out.println("    3) Check User Details");
        System.out.println("    4) Company Account details");
        System.out.println("    5) Exit");
    }
    static void printlist(ArrayList<Restraunt> rest_list){
        System.out.println("Choose Restaurant");
        for(int i=0;i<rest_list.size();i++){
            rest_list.get(i).printall(i);
        }
    }
    static void printcutlist(ArrayList<Customer> cust_list){
        for(int i=0;i<cust_list.size();i++){
            cust_list.get(i).printall(i);
        }
    }
    static void printfood(Restraunt a,int i){
        System.out.println(i+1+" "+a.getName()+" - "+a.getFood_list().get(i).getName()+" "+a.getFood_list().get(i).getPrice()+" "+a.getFood_list().get(i).getQuantity()+" "+a.getFood_list().get(i).getOffer()+"% off "+a.getFood_list().get(i).getCategory());
    }
    static void printrestoptions(Restraunt a){
        System.out.println("Welcome "+a.getName());
        System.out.println("    1) Add item");
        System.out.println("    2) Edit item");
        System.out.println("    3) Print Rewards");
        System.out.println("    4) Discount on bill value");
        System.out.println("    5) Exit");
    }
    static void printcustoptions(Customer a){
        System.out.println("Welcome "+a.getName());
        System.out.println("Customer Menu");
        System.out.println("    1) Select Restaurant");
        System.out.println("    2) checkout cart");
        System.out.println("    3) Reward won");
        System.out.println("    4) print the recent orders");
        System.out.println("    5) Exit");
    }
    static void printallfood(Restraunt a){
        for(int i=0;i<a.getFood_list().size();i++){
            System.out.println(a.getFood_list().get(i).getId()+" "+a.getName()+" - "+a.getFood_list().get(i).getName()+" "+a.getFood_list().get(i).getPrice()+" "+a.getFood_list().get(i).getQuantity()+" "+a.getFood_list().get(i).getOffer()+"% off "+a.getFood_list().get(i).getCategory());
        }
    }
    static void setDiscount(Restraunt a,int k){
        a.setDiscount(k);
    }
    static void printeditatri(Restraunt a){
        System.out.println("Choose an attribute to edit:");
        System.out.println("    1) Name");
        System.out.println("    2) Price");
        System.out.println("    3) Quantity");
        System.out.println("    4) Category");
        System.out.println("    5) Offer");
    }
    static void addItem(Restraunt a,String name,int price, int quantity,String category,int offer){
        a.addFood(name,price,quantity,category,offer,id);
        System.out.println(id+" "+name+" "+price+" "+quantity+" "+offer+"% off "+category);
        id++;
    }
    static void addfood(int choose){
        System.out.println("Enter food item details");
        scan.nextLine();
        System.out.println("Food name:");
        String name = scan.nextLine();
        System.out.println("item price:");
        int price = scan.nextInt();
        System.out.println("item quantity:");
        int quantity = scan.nextInt();
        System.out.println("item category:");
        String category = scan.next();
        System.out.println("Offer:");
        int offer = scan.nextInt();
        addItem(rest_list.get(choose - 1), name, price, quantity, category, offer);
    }
    static void edititem(int choose){
        System.out.println("Choose item by code");
        printallfood(rest_list.get(choose - 1));
        int k = scan.nextInt() - 1;
        printeditatri(rest_list.get(choose - 1));
        int chng =scan.nextInt() - 1;
        System.out.println("Enter the new " + arr[chng] + " - ");
        if (chng == 0) {
            rest_list.get(choose - 1).getFood_list().get(k).setName(scan.next());
        } else if (chng == 1) {
            rest_list.get(choose - 1).getFood_list().get(k).setPrice(scan.nextInt());
        } else if (chng == 2) {
            rest_list.get(choose - 1).getFood_list().get(k).setQuantity(scan.nextInt());
        } else if (chng == 3) {
            rest_list.get(choose - 1).getFood_list().get(k).setCategory(scan.next());
        } else if (chng == 4) {
            rest_list.get(choose - 1).getFood_list().get(k).setOffer(scan.nextInt());
        }
        printfood(rest_list.get(choose - 1), k);
        return;

    }
    static void chooserestrau() {
        printlist(rest_list);
        int choose = scan.nextInt();
        while (true) {
            printrestoptions(rest_list.get(choose - 1));
            int l = scan.nextInt();
            if (l == 1) {
                addfood(choose);
            }
            if (l == 2) {
                edititem(choose);
                }
            if (l == 3) {
                System.out.println("Reward Points - "+rest_list.get(choose - 1).getReward());
            }
            if (l == 4) {
                System.out.println("Enter offer on total bill value -");
                setDiscount(rest_list.get(choose - 1), scan.nextInt());
            }
            if (l == 5) {
                break;
            }
        }
        menu();
    }
    static int addItem(int choose){
        printlist(rest_list);
        int rest =scan.nextInt();
        System.out.println("Choose item by code");
        printallfood(rest_list.get(rest-1));
        int food= scan.nextInt();
        System.out.println("Enter item quantity");
        int quantity= scan.nextInt();
        int size=cut_list.get(choose-1).getCart().getItems().size();
        cut_list.get(choose-1).getCart().addItems(rest_list.get(rest-1).getFood(food));
        cut_list.get(choose-1).getCart().getItems().get(size).setQuantity(quantity);
        int index=rest_list.get(rest-1).getFoodindex(food);
        rest_list.get(rest-1).getFood_list().get(index).setQuantity(rest_list.get(rest-1).getFood_list().get(index).getQuantity()-quantity);
        System.out.println("Items added to cart");
        cut_list.get(choose-1).delCharge();
        cut_list.get(choose-1).getCart().calbil(cut_list.get(choose-1),rest_list.get(rest-1));
        rest1[0]=rest;
        return rest;
    }
    static void checkoutcart(int choose,int rest){
        System.out.println("Items in Cart -");
        cut_list.get(choose-1).custprintCart();
        System.out.println("Delivery charge - "+cut_list.get(choose-1).getCart().getDelcharge()+"/-");
        System.out.println("Total order value - INR "+cut_list.get(choose-1).getCart().getValue()+"/-");
        if(cut_list.get(choose-1).getReward()+cut_list.get(choose-1).getWallet()>cut_list.get(choose-1).getCart().getValue()){
            System.out.println("    1) Proceed to checkout");
            int check=scan.nextInt();
            if(check==1){
                cut_list.get(choose-1).getCart().distribute(cut_list.get(choose-1),rest_list.get(rest-1),Company);
                int items=cut_list.get(choose-1).getCart().total();
                System.out.println(items+" items successfully bought for INR "+cut_list.get(choose-1).getCart().getValue() +"/-");
                cut_list.get(choose-1).getOrder().addOrder(cut_list.get(choose-1).getCart().getItems().get(0).getName(),cut_list.get(choose-1).getCart().getItems().get(0).getQuantity(),cut_list.get(choose-1).getCart().getValue(),rest_list.get(rest-1).getName(),cut_list.get(choose-1).getCart().getDelcharge());
                cut_list.get(choose-1).getCart().getItems().clear();
                rest_list.get(rest).setOrder_taken(rest_list.get(rest-1).getOrder_taken()+1);
            }
        }
        else{
            System.out.println("You don't have enough money we will empty your Cart please shop again");
            cut_list.get(choose-1).getCart().getItems().clear();
        }
    }
    static void choosecusta(){
        printcutlist(cut_list);
        int choose= scan.nextInt();
        while(true){
            printcustoptions(cut_list.get(choose-1));
            int options= scan.nextInt();
            if(options==1){
                rest1[0]=addItem(choose);
            }
            if(options ==2){
                checkoutcart(choose,rest1[0]);
            }
            if(options==3){
                System.out.println("Total Rewards - "+cut_list.get(choose-1).getReward());
            }
            if(options==4){
                cut_list.get(choose-1).printOrders();
            }
            if(options==5){
                break;
            }
        }
        menu();
    }
    static void printDetails(){
        System.out.println("    1) Customer List");
        System.out.println("    2) Restaurant List");
        int custa=scan.nextInt();
        if(custa==1) {
            printcutlist(cut_list);
            int custo= scan.nextInt()-1;
            cut_list.get(custo).print();
        }
        else{
            printlist(rest_list);
            int custo= scan.nextInt()-1;
            rest_list.get(custo).print();
        }
    }
    static void menu(){
        printMenu();
        int menu1= scan.nextInt();
        if(menu1==1){
            chooserestrau();
        }
        if(menu1==2){
            choosecusta();
        }
        if(menu1==3){
            printDetails();
            menu();
        }
        if(menu1==4){
            System.out.println("Total Company balance - INR "+Company.getRes_charges()+"/-");
             System.out.println("Total Delivery Charges Collected - INR "+Company.getDev_charges()+"/-");
             menu();
        }
        if(menu1==5){
            return;
        }
    }

    public static void main(String args[]){

        cut_list.add(new Elite("Ram","Pune"));
        cut_list.add(new Elite("Sam","Mumbai"));
        cut_list.add(new Special("Ram","Delhi"));
        cut_list.add(new Customer("Kim","Haryana"));
        cut_list.add(new Customer("Jim","Uttar Pradesh"));
        rest_list.add(new Authentic("Shah","Pune") );
        rest_list.add(new Restraunt("Ravi","Delhi") );
        rest_list.add(new Authentic("The Chinese","Mumbai") );
        rest_list.add(new Fast_food("Wang","New Delhi") );
        rest_list.add(new Restraunt("Paradise","Haryana") );
        menu();


    }
}
