public class Twodarr{
    public static void main(String[] args){
        int[][] ar = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[] ma = new int[4];

        for(int i =0;i<4;i++){
            ma[i] = ar[i][i];
            for(int j =1;j<4;j++){
                if(ar[i][j]>ma[i]){
                    ma[i] = ar[i][j];
                }
            }
            System.out.println(' ');
        }
        for(int i =0;i<4;i++){
            System.out.print(ma[i] +" ");
        }
    }
}