    public class Palindronme{
        public static void main(String[] args){
            String txt = "A man, a plan, a canal: Panama";

            boolean ans = pal(txt);
            System.out.println(ans);

        }

        public static boolean pal(String dat){
            int n = dat.length();
            int lp = 0;
            int rp = n-1;

            
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
                        return false;
                    }
                }

            }
            return true;
        }
    }