public class Toepliz{
    public static void main(String[] args){
        int[][] ar =  {
            {1, 2, 3, 4},
            {5, 1, 2, 3}, 
            {9, 5, 1, 2},
            {13, 9, 5, 1}
        };
        boolean flag= true;


        for(int i =0;i<ar.length-1;i++){
            for(int j =0;j<ar[i].length-1;j++){
                if(ar[i][j]!=ar[i+1][j+1]){
                    flag=false;
                }
            }
        }

        if(flag){
            System.out.println(" Toepliz");
        }
        else{
            System.out.println(" Not a Toepliz");
        }
    }
}