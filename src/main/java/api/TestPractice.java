package api;
//
//import javax.print.DocFlavor;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class TestPractice {
//
//    public static void main (String[] args){
//        String str = "aabbddccffff";
//        //expected "a2b2d2c2f4"
//
//        char[] chr = str.toCharArray();
//        List<String> lst = new ArrayList();
//        int cnt=1;
//        for(int i=0;i< chr.length;i++ ){
//            if(i==0)
//                lst.add(String.valueOf(chr[i]));
//            else if(i>0){
//                if(chr[i]==chr[i-1]){
//
//                    cnt=cnt+1;
//
//                    lst.add(String.valueOf(cnt));
//                }
//                else{
//                    cnt=1;
//                    lst.add(String.valueOf(chr[i]));
//                }
//            }
//
//        }
//
//        System.out.println(lst);
//
//    }
//}


public class TestPractice {
    //Checks for the largest common prefix
    public static String lcp(String s, String t){
        int n = Math.min(s.length(),t.length());
        for(int i = 0; i < n; i++){
            if(s.charAt(i) != t.charAt(i)){
                return s.substring(0,i);
            }
        }
        return s.substring(0,n);
    }

    public static void main(String[] args) {
        String str = "acbdfghybdf";
        String lrs="";
        int n = str.length();
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                //Checks for the largest common factors in every substring
                String x = lcp(str.substring(i,n),str.substring(j,n));
                //If the current prefix is greater than previous one
                //then it takes the current one as longest repeating sequence
                if(x.length() > lrs.length()) lrs=x;
            }
        }
        System.out.println("Longest repeating sequence: "+lrs);
    }
}