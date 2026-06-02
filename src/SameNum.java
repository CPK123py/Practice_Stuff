import java.util.Scanner;

public class SameNum{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("starting:");
        int n1 = sc.nextInt();
        System.out.println("ending:");
        int n2 = sc.nextInt();

        int count = 0;
        int ount= 0;
        

        for(int i = n1; i < n2; i++){
            boolean flag = false;
            int c = i;
            System.out.println(c);
            ount+=1;


            while(c!=0){
                int ls = c%10;
                int rem = c/10;

                while(rem>0){
                    if (ls == rem%10){
                        flag = true;
                        break;
                    }
                    rem = rem/10;
                }
                
                if (flag){
                    count+=1;
                    break;
                }
                c/=10;

            }
        }

        System.out.println("The total non-repeating numbers :"+ (ount-count));
 

    }
}