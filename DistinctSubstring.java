package distinctsubstring;
/*
 Given a string, find the length of the longest substring without repeating characters.
 Examples:
 Given "abcabcbb", the answer is "abc", which the length is 3.
 Given "bbbbb", the answer is "b", with the length of 1.
 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class DistinctSubstring {

    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string : ");
        String input = sc.nextLine();
        int n;
        if((n=findMaxLength(input))==-1)
            System.out.println("Invalid Input");
        else 
            System.out.println(n);
    }
    public static int findMaxLength(String input)
    {
       if(input.contains(" "))
           return -1;
       int i,j=0;
       int maxlen=0;
       int len =0;
       Set<Character> s = new HashSet<Character>();
       for(i=0;i<input.length();)
       {
            if(s.isEmpty()|| !(s.contains(input.charAt(i))))
                {
                     s.add(input.charAt(i));
                     i++;
                     len = i-j;
                }
            else
                {
                  s.remove(input.charAt(j));
                  j++;
                }
            if(len>maxlen)
                maxlen = len;
       }
       return maxlen;
    }
    @Test
    public static void testCode()
    {
        assertEquals(0,findMaxLength(""));
		assertEquals(0,findMaxLength(null));
        assertEquals(3,findMaxLength("abc"));
        assertEquals(3,findMaxLength("abcabacbb"));
        assertEquals(2,findMaxLength("Bbbbb"));
        assertEquals(3,findMaxLength("#*#@"));
        assertEquals(3,findMaxLength("pwwkew"));
    }
}
