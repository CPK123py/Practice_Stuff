import java.util.Scanner;

public class Prime{

    public void main(String... a){
            Scanner sc = new Scanner(System.in);
            System.out.println("N1:");
            int li = sc.nextInt();
            System.out.println("N2");
            int ls = sc.nextInt();
            

            for(int i = li; i <= ls;i++){
                ispri(i);
            }
    }

    public void ispri(int a){

        if(a==1){
            System.out.println(' ');
        }
        if(a==0){
            System.out.println(' ');
        }
        else if(a==2 || a==2){
            System.out.println(a);
        }
        else if(a%3!=0 && a%2!=0){
            System.out.println(a);
        }
        else{
            System.out.println(' ');
        }
    }
}