import java.util.*;
public class Calc{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        float n1 = sc.nextFloat();
        float n2 = sc.nextFloat();

        System.out.println("Choose operation:");
        
        char op = sc.next().charAt(0);

        System.out.println(cal(n1,n2,op));
    }

    public static float cal(float n1, float n2, char op){
        float ans = 0;
        
        if(op == '+'){
            ans = n1+n2;
        }
        else if(op == '-'){
            ans = n1-n2;
        }
        else if(op == '/'){
            ans = n1/n2;
        }
        else if(op == '*'){
            ans = n1*n2;
        }
        else{
            ans= 0;
        }
        return ans;
    }
}