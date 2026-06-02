import java.util.Scanner;

public class DateCheck{
    public  boolean checke(int c, int[] d){
        
        for (int i =0; i<d.length;i++){
            if (c == d[i]){
            return  true;
        }
    }
        return false;
    }
      public static void main(String... args) {
        DateCheck program = new DateCheck();
        program.run(args);
    }

    public  void run(String... args){

        Scanner sc =  new Scanner(System.in);
        System.out.println("Enter your date(dd mm yyyy):");

        int dat[] =  new int[3];
        int a[]= {1,3,5,7,8,10,12};
        int b[]= {4,6,9,11};

        for (int i=0;i<3;i++){
            dat[i] = sc.nextInt();
        }

       if(dat[0]>31 || dat[0]<1 || dat[1] > 12 || !(dat[2]>1000 && dat[2]<9999)){
            System.out.println("INVALID date");
       }
       else if(checke(dat[1],a) && dat[0]<32){
            System.out.println("VALID date");
       }
       else if(checke(dat[1],b) && dat[0]<31){
            System.out.println("VALID date");
       }
       else if(dat[1]==2 && ((dat[2]%4==0 && dat[2]%100!=0) || dat[2]%400==0) && dat[0]==29){
            System.out.println("VALID date");
       }
       else{
        System.out.println("INVALID date");
       }
    }
}