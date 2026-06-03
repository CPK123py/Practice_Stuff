import java.util.*;
public class EvenOddChecker{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        cal(n1);
    }

    public static void cal(int n1){
        if( n1 == 0){
            System.out.println("Neither ODD nor EVEN");
        }
        else if(n1%2 == 0){
            System.out.println("EVEN");
        }
        else{
            System.out.println("ODD");
        }
    }
}