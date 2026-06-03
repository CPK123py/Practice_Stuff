import java.util.*;

public class GradePrec{
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       char c;
     
        int res = sc.nextInt();
        if(res<=100 || 90<=res){
            c='A';
        }
        else if(res<=89 || 80<=res){
            c='B';
        }
        else if(res<=79 || 70<=res){
            c='C';
        }
        else if(res<=60 || 69<=res){
            c='D';
        }
        else{
            c='F';
        }

        System.out.println(c);
    }
}