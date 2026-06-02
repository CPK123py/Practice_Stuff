public class MoveZeros{
    public static void main(String[] args){

        int[] arr = {0,0,1,3};

        System.out.println(arr.length);

        int jump = 0;

        for(int i = 0; i< arr.length -jump;i++){
           
           if(arr[i]==0){
            for(int j = i; j<=arr.length-2;j++){
              
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
           } 
           if(arr[i]==0){
            i--;
            jump++;
           }

        }

        for(int i = 0; i< arr.length;i++){
           System.out.print(arr[i] + ", ");
        }
    }
}