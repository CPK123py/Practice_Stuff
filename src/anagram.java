public class anagram{
    public static void main(String[] args){
        String s = "anagram";
        String t = "nagaram";

        boolean b = isAnagram(s,t);
        if(b){
            System.out.println("Yup it's an anagram");
        }
        else{
            System.out.println("Nope it's not an anagram");
        }
    }
    public static boolean isAnagram(String s, String t) {
        int[] counts = new int[26];

        int n = s.length();
        int m = t.length();

        if(n!=m){
            return false;
        }

       
        for(int i = 0; i<n; i++){
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        
        for(int i = 0; i< counts.length; i++){
            if(counts[i] != 0){
                return false;
            }
        }
        return true;
    }
}