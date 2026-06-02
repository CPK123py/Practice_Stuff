    public class Palindronme2{
        public static void main(String[] args){
            String txt = "A man, a plan,  canal: Panama";

            boolean ans = pal(txt);
            System.out.println(ans);

        }

        public static boolean pal(String dat){
            int n = dat.length();
            int lp = 0;
            int rp = n-1;
            int ty = 0;

            
            for(int i =0; i<n; i++){
                
                if(!Character.isLetterOrDigit(dat.charAt(lp))){
                    lp+=1;
                    
                }
                else if(!Character.isLetterOrDigit(dat.charAt(rp))){
                    rp-=1;
                }
                else{
                    if(Character.toLowerCase(dat.charAt(lp))==Character.toLowerCase(dat.charAt(rp))){
                        rp-=1;
                        lp+=1;
                    }
                    else{
                        return ispalin(dat,lp+1,rp) ||ispalin(dat,lp,rp-1);
                    }
                }

            }
            return true;
        }


        public static boolean ispalin(String dat,int lp,int rp){

            if(!Character.isLetterOrDigit(dat.charAt(lp))){
                lp+=1;
                    
            }
            else if(!Character.isLetterOrDigit(dat.charAt(rp))){
                rp-=1;
            }
            else{
                if(Character.toLowerCase(dat.charAt(lp))!=Character.toLowerCase(dat.charAt(rp))){
                        return false;
                    }
                    rp-=1;
                        lp+=1;
            }

            return true;
        }
           
}
    