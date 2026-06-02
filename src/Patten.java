public class Patten{
    public static void main(String[] args){
        
        for(int i = 0;i < 10;i++){
            if(i == 5){
                for(int j = 0;j<10;j++){
                    System.out.print('*');
                }
            }
            else{
                for(int k = 0; k < 5;k++){
                        System.out.print(' ');
                }

                int limit = (i < 5) ? i : 10 - i;
                for(int l = 0; l < limit; l++) {
                    int skip = (l-1);
                    System.out.print('*');
                    
                }
            }
            System.out.println(' ');
            

        }
            
            
        }
    }
