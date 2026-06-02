public class SortColor{
    public static void main(String[] args){
        int[] nums = {1,2,1,2,0,1,0,2,0};
        int n = nums.length;

        nums = clrsrt(nums, n);

        for(int i =0;i<n; i++){
            System.out.print(nums[i] + " ");
        }
        
    }

    public static int[] clrsrt(int[] nums, int n){
        int a = 0;
        int b = 0;
        int c = 0;
        int ind = 0;
        for(int j =0;j<n;j++){
                if(nums[j]==0){
                    a+=1;
                }
                else if(nums[j]==1){
                    b+=1;
                }
                else if(nums[j]==2){
                    c+=1;
                }
                else{
                    System.out.println("non-valid colors");
                }
              
        }
        
        for(int i =0;i<a;i++){
            nums[i] =  0;
            ind+=1;
        }

        for(int i = ind;i < (b+a);i++){
            nums[i] =  1;
            ind+=1;
        }

          for(int i = ind;i < (b+a+c);i++){
            nums[i] =  2;
            ind+=1;
        }
        
        return nums;

    }
}