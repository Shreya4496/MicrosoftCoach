package distinctsubstring;
/*
 Given a string, find the length of the longest substring without repeating characters.
 Examples:
 Given "abcabcbb", the answer is "abc", which the length is 3.
 Given "bbbbb", the answer is "b", with the length of 1.
 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
import static org.testng.Assert.assertEquals;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.testng.annotations.Test;

public class Week1DistinctLongestSubstring {
	
	public static void main(String args[])
	{
		Scanner sc= new Scanner (System.in);
		String input= sc.nextLine();
		int n;
		if((n=findMaxLength(input))==-1)
		{
			System.out.println("Invalid input");
		}
		else
		{
			System.out.println(n);
		}
}

	public static int findMaxLength(String input) {
		// TODO Auto-generated method stub
		if (input==null)
			return 0;
		if(input.length()==1)
			return 1;
		Set<Character> s= new HashSet<Character>();
		int len=0,maxlen=0,j=0,i=0;
		while(i<input.length()&&j<input.length())
		{   
			if(input.charAt(i)==' ')
				return -1;
			
			if(s.isEmpty() || !s.contains(input.charAt(i)))
			{
				s.add(input.charAt(i));
				i++;
				len=i-j;
			}
			else
			{
				s.remove(input.charAt(j));
				j++;
			}
			
			if(len>maxlen)
				maxlen=len;
		}	
		return maxlen;
	}
	
	@Test(enabled=true)
	public void test1()
	{
		assertEquals(4,findMaxLength("abcsbcd"));
	}
	
	@Test(enabled=true)
	public void test2()
	{
		assertEquals(0,findMaxLength(null));
	}
	
	@Test(enabled=true)
	public void test3()
	{
		assertEquals(1,findMaxLength("bbbbb"));
	}
	
	@Test(enabled=true)
	public void test4()
	{
	    assertEquals(4,findMaxLength("sabsad"));
	}
	
	@Test(enabled=true)
	public void test5()
	{
		assertEquals(3,findMaxLength("pwwkew"));
	}
	
	@Test(enabled=true)
	public void test6()
	{
		assertEquals(4,findMaxLength("$34a"));
	}
		
}

