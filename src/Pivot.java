    public class Pivot{
        public static void main(String[] args){
            int[] height = {1,7,3,6,5,6};

            int ans = piv(height);
            System.out.println(ans);

        }

        public static int piv(int[] nums){
            int n = nums.length;     
            int ans = 0;  


            
            for(int i = 0; i < n; i++){  
                int pre = 0;  
                int suf = 0;
                
        
                    if(i!=0){
                        for(int k = 0; k < i;k++){
                            pre+=nums[k];
                        }
                        for(int k = i+1; k < n;k++){
                            suf+=nums[k];
                        }
                    }else{
                        for(int k = i+1; k < n;k++){
                            suf+=nums[k];
                        }
                    }

                if(pre==suf){
                    ans = i;
                    break;
                }
            }
            return ans;
        }
    }