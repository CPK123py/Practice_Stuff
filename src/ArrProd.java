public class ArrProd{
    public static void main(String[] args){
        int[] height = {1,2,3,4};

        height = soln(height);

        for(int i = 0;i<height.length;i++){
            System.out.print(height[i]+" ");
        }
    }

    public static int[] soln(int[] nums){
        int n = nums.length;     
        int[] answer = new int[n];  
        int[] pre = new int[n];  
        int suf = 1;

        for(int i = 0; i < n; i++){   
            if(i==0){
                pre[i] = 1;
            }
            else{
                pre[i] = pre[i-1] * nums[i-1];
            }
            
        }
        for(int i = n-1; i >= 0;i--){
            answer[i] = pre[i] * suf;
            suf = suf * nums[i];
        }
        return answer;
    }
}