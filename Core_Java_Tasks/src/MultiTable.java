import java.util.Scanner;

public class MultiTable{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your number:");
        int a = sc.nextInt();
        
        for(int i = 0;i<11;i++){
            System.out.println(a +"X" + i +"="+ a*i);

        }
    }
}