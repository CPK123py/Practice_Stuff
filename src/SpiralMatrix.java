public class SpiralMatrix{
    public static void main(String[] args){
        int[][] ar =  {
            {1, 2, 3, 4},
            {5, 1, 2, 3}, 
            {9, 5, 1, 2},
            {13, 9, 5, 1}
        };
        

        int top = 0;
        int bottom = ar.length - 1;
        int left = 0;
        int right = ar[0].length - 1;
        
        // Loop until boundaries cross
        while (top <= bottom && left <= right) {
            
            // 1. Move Left to Right along top row
            for (int i = left; i <= right; i++) {
                System.out.print(ar[top][i]+" ");
            }
            top++; // Shrink top boundary
            
            // 2. Move Top to Bottom along right column
            for (int i = top; i <= bottom; i++) {
                System.out.print(ar[i][right]+" ");
            }
            right--; // Shrink right boundary
            
            // 3. Move Right to Left along bottom row
            // (Check to ensure we haven't crossed rows)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    System.out.print(ar[bottom][i]+" ");
                }
                bottom--; // Shrink bottom boundary
            }
            
            // 4. Move Bottom to Top along left column
            // (Check to ensure we haven't crossed columns)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(ar[i][left]+" ");
                }
                left++; // Shrink left boundary
            }
        }
    }
}