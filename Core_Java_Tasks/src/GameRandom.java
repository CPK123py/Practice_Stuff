import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameRandom{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int c = ThreadLocalRandom.current().nextInt(1,100);
        System.out.print("Make your guess:");
        int guess = sc.nextInt();

        while (guess!=c) {

            System.out.println("Wrong!");
            if(guess<c){
                System.out.println("Try Larger");
            }
            else{
                System.out.println("Try Smaller");
            }
            System.out.print("Make your guess:");
            guess = sc.nextInt();
        }
        System.out.println("Correct!");
        System.out.println(c);

    }
}