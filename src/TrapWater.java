public class TrapWater{
    public static void main(String[] args){
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};

        int out = trap(height);

        System.out.print(out);

    }

    public static int trap(int[] height){
        int n = height.length;
        int le = 0;
        int ri = n-1;
        int rm = 0;
        int lm = 0;
        int res=0;

        while(le < ri){
            if (height[le] < height[ri]){
            if(lm <= height[le]){
                lm = height[le];
            }
            else{
                res+=(lm-height[le]);
            }
            le++;
        }
        else{
             if(rm <= height[ri]){
                rm = height[ri];
            }
            else{
                res+=(rm-height[ri]);
            }
            ri--;
        }
        }
        return res;
    }
}