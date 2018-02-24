/*
Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words.
For example, consider the following dictionary: { pear, salmon, foot, prints, footprints, leave, you, sun, girl, enjoy },

Examples:
Given the string “youenjoy”, 
Output: True (The string can be segmented as “you enjoy”)

Input: “youleavefootprints”,
Output: True (The string can be segmented as “you leave footprints” or “you leave foot prints”)

Input:salmonenjoyapples
Output: False

Approach Used (Optimised)- Dynamic Programming

ALGORITHM:

1. Given input string and dictionary check for the null/empty cases.
2. Create a boolean array 'containWord[input.length()] s.t 
		if containWord[i] is true then it denotes that substring(input) from 0 to i can be broken into valid words
		else containWord[i] is false
3. Create an outer for loop from i to input.length()-1
		3.1 Check whether the substring(input) from 0 to i present in the dictionary
		    	if true:
			      	containsWord[i]=true
		3.2 check if i==length of the input word and containsWord[i]==true
			    if true :
			    	return true
	    3.3 check if containsWord[i]==true
	            if true:
	            	3.3.1 Create an inner for loop from j=i+1 to input.length()-1
			            	Check whether the substring(input) from i+1 to j present in the dictionary
				    			if true:
					      			containsWord[j]=true
					      		check if j==length of the input word and containsWord[j]==true
								    if true :
								    	return true
	            	
4. If no solution exists return false

 */



import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Week3WordBreak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();			
		Set<String> dict=new HashSet<String>();
		for(int i=0;i<n;i++)
			dict.add(sc.next());
		String input=sc.next();
		if(checkWordBreak(dict,input))
			System.out.println("True");
		else 
			System.out.println("False");

	}

	public static boolean checkWordBreak(Set<String> dict, String input) {
		// TODO Auto-generated method stub
		
		if(dict.size()==0)
			return false;
		if (input=="" || input==null)
			return true;
			
		boolean [] containsWord=new boolean[input.length()];
		for(int i=0;i<input.length();i++)
		{
			if(dict.contains(input.substring(0, i+1)))
				containsWord[i]=true;
			if (containsWord[i] == true && (i == (input.length() - 1)))
			    return true;
			if(containsWord[i]==true)
			{
				for(int j=i+1;j<input.length();j++)
				{
					if(dict.contains(input.substring(i+1,j+1)))
						containsWord[j]=true;
					if(j == input.length() - 1 && containsWord[j] == true) 
						return true;
				}
			}
				
		}
		
	    return false;
	}
	
	@Test(enabled=true)
	public void checkWordBreak_RepeatingString_True()
	{
		Set<String> dict=new HashSet<String>(Arrays.asList("i","am","at","work"));
		Assert.assertEquals(true,checkWordBreak(dict,"iiiii"));
	}
	
	@Test(enabled=true)
	public void checkWordBreak_EmptyString_True()
	{
		Set<String> dict=new HashSet<String>(Arrays.asList("i","am","at","work"));
		Assert.assertEquals(true,checkWordBreak(dict,""));
	}
	
	@Test(enabled=true)
	public void checkWordBreak_CombinedString_True()
	{
		Set<String> dict=new HashSet<String>(Arrays.asList("i","am","at","work"));
		Assert.assertEquals(true,checkWordBreak(dict,"iamwork"));
	}
	
	@Test(enabled=true)
	public void checkWordBreak_CombinedString_False()
	{
		Set<String> dict=new HashSet<String>(Arrays.asList("i","am","at","work"));
		Assert.assertEquals(false,checkWordBreak(dict,"iamatbeach"));
	}
	
	@Test(enabled=true)
	public void checkWordBreak_EmptyDict_False()
	{
		Set<String> dict=new HashSet<String>(Arrays.asList());
		Assert.assertEquals(false,checkWordBreak(dict,"i"));
	}

}
