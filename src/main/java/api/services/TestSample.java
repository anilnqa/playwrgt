package api.services;

import lombok.val;
import org.apache.logging.log4j.util.StringBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestSample {

    //Java is a programming language

   // Input: Java is a programming language
    //Output: egau gn a lgnimmargor pasiavaJ
    public static void main (String[] args) {
        String input = "Java is a programming language";
        char[] ch = input.toCharArray();

        List<Integer> listSpace = new ArrayList<>();

        for (int i = 0 ; i < ch.length-1 ;i ++ ){
            if(ch[i] == ' '){
                listSpace.add(Integer.valueOf(i));
            }
        }

        System.out.println(listSpace);

        String revStr = new StringBuilder().append(input).reverse().toString();
        revStr = revStr.replace(" ", "");

        List<Character> revListChar = new ArrayList<>();
        for (Character c:revStr.toCharArray()
             ) {
            revListChar.add(c);
        }


        System.out.println(revListChar);

        for(int j = 0 ; j<listSpace.size();j++){
            revListChar.add(listSpace.get(j), ' ');
        }

        List<String> ls = new ArrayList<>();
        for (Character c:revListChar
             ) {
            ls.add(c.toString());
        }
        System.out.println("ls : " + ls);
        System.out.println("string join " + String.join(" " ,ls));










    }





}
