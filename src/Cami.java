import java.util.Scanner;
public class Cami{
    public class calc{
        public int sum(int... a){
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += a[i];
        }
        return sum;
    }
        public int sub(int... a){
        int sub = 0;
        for(int i = 0; i < a.length; i++){
            sub -= a[i];
        }
        return sub;
    }
        public int multi(int... a){
        int mul = 1;
        for(int i = 1; i < a.length; i++){
            mul *= a[i];
        }
        return mul;
    }
        public int div(int a, int b){
        int div = a / b;
        return div;
    }
    }
    public static void main(String... c){
        System.out.println("Press 1 to Add");
        System.out.println("Press 2 to Subtract");
        System.out.println("Press 3 to Multiply");
        System.out.println("Press 4 to Divide");
        Scanner sc = new Scanner(System.in);

    }
}

