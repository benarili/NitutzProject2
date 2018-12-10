package Entries;

public abstract class StringChecker {

    public static boolean stringsAreEqual(String first, String second){
        //check if both are null
        if (first == null && second == null){
            return true;
        }
        //check if one of them is null
        if (first == null || second == null){
            return false;
        }
        //check same lengthand content
        boolean ans = first.length()==second.length() ? true : false;
        if(ans){
            for (int i = 0; i < first.length() && ans; i++) {
                ans = first.charAt(i) == second.charAt(i);
            }
        }
        return ans;
    }

    /**
     *
     * @param s - string to check
     * @return true if s isn't null, empty, and only contains letters. Else returns false
     */
    public static boolean isWriting(String s){
        if (s == null || s.length()==0){
            return false;
        }
        char[] chars = s.toCharArray();
        for (char c:chars) {
            if(!Character.isLetter(c))
                return false;
        }
        return true;
    }

    public static String copy(String s){
        if (s==null)
            return null;
        String copy = "";
        for (int i = 0; i < s.length(); i++) {
            copy+=s.charAt(i);
        }
        return copy;
    }

    public static boolean contains(String s, char c){
        for (int i =0 ; i < s.length(); i++){
            if(s.charAt(i)==c)
                return true;
        }
        return false;
    }


}
