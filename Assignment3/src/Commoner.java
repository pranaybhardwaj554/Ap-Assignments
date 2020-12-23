public class Commoner extends Player{
    Commoner(int id){
        super(id);
        this.setHP(1000);
    }
    @Override
    public boolean equals(Object o1){
        if(o1 instanceof Commoner){
            Commoner o=(Commoner) o1;
            return(this.getId()==o.getId());
        }
        else{
            return false;
        }
    }
}

