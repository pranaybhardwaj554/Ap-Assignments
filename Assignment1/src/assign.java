import java.util.*;
class Patient{
    String name;
    float temp;
    int oxy;
    int age;
    int id;
    int recovery;
    String hospital="0";
    boolean status;
    Patient(String name,float temp,int oxy,int age,int id){
        this.name=name;
        this.temp=temp;
        this.oxy=oxy;
        this.age=age;
        this.id=id;
    }
    void setId(int id){
        this.id=id;
    }
    void setRecovery(int recovery){
        this.recovery=recovery;
    }
    void setStatus(boolean status){
        this.status=status;
    }
    void pDetails(){
        System.out.println(this.name);
        System.out.println("Temperature is "+this.temp);
        System.out.println("Oxygen levels is "+this.oxy);
        String h;
        if(this.status){
            h="Admitted";
        }
        else{
            h="Not Admitted";
        }
        System.out.println("Admission Status-"+h);
        if(this.hospital.equals("0")){

        }
        else{
            System.out.println("Admitting Institute - "+this.hospital);
        }
    }
}
class Hospital{
    String name;
    float temp;
    int oxy;
    int beds;
    ArrayList<Patient> arr ;
    boolean status;
    Hospital(String name,float temp,int oxy,int beds){
        arr=new ArrayList<Patient>();
        this.name=name;
        this.temp=temp;
        this.oxy=oxy;
        this.beds=beds;
        status=true;
    }
    void setStatus(boolean status){
        this.status=status;
    }
    void pDetails(Hospital l){
        System.out.println(l.name);
        System.out.println("Temperature should be <= "+ l.temp);
        System.out.println("Oxygen levels should be >= "+ l.oxy);
        System.out.println("Number of Available beds- "+ (l.beds-l.arr.size()));
        String h;
        if(l.status){
            h="OPEN";
        }
        else{
            h="CLOSED";
        }
        System.out.println("Admission Status-"+h);
    }
}
public class assign {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Patient> Patients= new ArrayList<Patient>();
        ArrayList<Hospital> Hospitals= new ArrayList<Hospital>();
        int n= sc.nextInt();
        for(int i=0;i<n;i++){
            String name= sc.next();
            float temp=sc.nextFloat();
            int oxy=sc.nextInt();
            int age= sc.nextInt();
            Patients.add(new Patient(name,temp,oxy,age,i+1));
        }
        int arr12[]=new int[1];
        arr12[0]=1;
        while(true){
            int query=sc.nextInt();
            if(query==0){
                break;
            }
            if(query==1){
                String name=sc.next();
                System.out.println("Temperature Criteria-");
                float temp=sc.nextFloat();
                System.out.println("Oxygen Levels-");
                int oxy = sc.nextInt();
                System.out.println("Number of Available beds-");
                int beds=sc.nextInt();
                Hospitals.add(new Hospital(name,temp,oxy,beds));
                int sio=Hospitals.size();
                Hospital l = new Hospital(name,temp,oxy,beds);
                l.pDetails(l);
                for(int i=0;i<Patients.size();i++){
                    if(Patients.get(i).oxy>=oxy && Hospitals.get(sio-1).status && !Patients.get(i).status){
                        Hospitals.get(sio-1).arr.add(Patients.get(i));
                        System.out.println("Recovery days for admitted patient ID -"+Patients.get(i).id);
                        Patients.get(i).setRecovery(sc.nextInt());
                        Patients.get(i).setStatus(true);
                        Patients.get(i).hospital=name;
                    }
                    if(Hospitals.get(sio-1).beds==Hospitals.get(sio-1).arr.size()){
                        Hospitals.get(sio-1).status=false;
                    }
                }
                for(int i=0;i<Patients.size();i++){
                    if(Patients.get(i).temp<=temp && Hospitals.get(sio-1).status && !Patients.get(i).status){
                        Hospitals.get(sio-1).arr.add(Patients.get(i));
                        System.out.println("Recovery days for admitted patient ID -"+Patients.get(i).id);
                        Patients.get(i).setStatus(true);
                        Patients.get(i).hospital=name;
                        Patients.get(i).setRecovery(sc.nextInt());
                    }
                    if(Hospitals.get(sio-1).beds==Hospitals.get(sio-1).arr.size()){
                        Hospitals.get(sio-1).status=false;
                    }
                }
            }
            if(query==2){
                System.out.println("Account ID removed of admitted patients");
                ArrayList<Patient> remove=new ArrayList<Patient>();
                for(int i=0;i<Patients.size();i++){
                    if(Patients.get(i).status==true){
                        remove.add(Patients.get(i));
                        System.out.println(Patients.get(i).id);
                    }
                }
                for(int i=0;i<remove.size();i++){
                    Patients.remove(remove.get(i));
                }
            }
            if(query==3){
                System.out.println("Accounts removed of Institute whose admission is closed");
                ArrayList<Hospital> remove=new ArrayList<Hospital>();
                for(int i=0;i<Hospitals.size();i++){
                    if(Hospitals.get(i).status==false){
                        remove.add(Hospitals.get(i));
                        System.out.println(Hospitals.get(i).name);
                    }
                }
                for(int i=0;i<remove.size();i++){
                    Hospitals.remove(remove.get(i));
                }
            }
            if(query==4){
                int p=0;
                for(int i=0;i<Patients.size();i++){
                    if(Patients.get(i).status==false){
                        p++;
                    }
                }
                System.out.println(p+" patients");
            }
            if(query==5){
                int p=0;
                for(int i=0;i<Hospitals.size();i++){
                    if(Hospitals.get(i).status){
                        p++;
                    }
                }
                System.out.println(p+" institutes are admitting patients currently");
            }
            if(query==6){
                Hospital l=new Hospital("l",1,2,3);
                String name=sc.next();
                for(int i=0;i<Hospitals.size();i++){
                    if(Hospitals.get(i).name.equals(name)){
                        l=Hospitals.get(i);
                        break;
                    }
                }
                l.pDetails(l);
            }
            if(query==7){
                Patient l=new Patient("l",1,2,3,4);
                int id=sc.nextInt();
                for(int i=0;i<Patients.size();i++){
                    if(Patients.get(i).id==id){
                        l=Patients.get(i);
                        break;
                    }
                }
                l.pDetails();
            }
            if(query==8){
                for(int i=0;i<Patients.size();i++){
                    System.out.println(Patients.get(i).id+" "+Patients.get(i).name);
                }
            }
            else if(query==9){
                Hospital l=new Hospital("l",1,2,3);
                String name=sc.next();
                for(int i=0;i<Hospitals.size();i++){
                    if(Hospitals.get(i).name.equals(name)){
                        l=Hospitals.get(i);
                        break;
                    }
                }
                for(int i=0;i<l.arr.size();i++){
                    System.out.println(l.arr.get(i).name+",recovery time is "+l.arr.get(i).recovery+" days");
                }
            }
        }
    }
}
