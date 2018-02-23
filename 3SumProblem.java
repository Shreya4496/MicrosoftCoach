/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Note: The solution set must not contain duplicate triplets.
For example, given array S = [-1, 0, 1, 2, -1, -4],
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

Approach Used: Hashing
		1. Given an array, create two for loops and break down the problem into - for each element 'a' in the array, find a pair s.t their sum=-a
		2. For finding a pair which has its sum=target_sum
		   2.1 create a hashset 
		   2.2 traverse the inner loop :
		       for each element 'b' check whether the hashset contains target_sum-b
		          if true:
		             create a list of 3 integers
		                ele1=Min(a,b,target_sum-b)
		                ele3=Max(a,b,target_sum-b)
		                ele2=-(ele1+ele3)
		          
		          add the element 'b' in the set
		3. clear the set to further traverse the outer loop             
		4. if the solution exists:
		 	return the list containing the list of triplets (whose sum add to 0)  
		   else
		    return null
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sum3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner (System.in);
		int n=sc.nextInt();
		int arr[]=new int[n];
		for(int i=0;i<n;i++)
			arr[i]=sc.nextInt();
		
		List<List<Integer>> reslist=new ArrayList<List<Integer>>();
		reslist=check3Sum(arr);
		if(reslist==null)
			System.out.println("Invalid Input or Solution doesn't exists");
		else
		{
			for (List<Integer> l:reslist)
			{ 
				System.out.println(Arrays.toString(l.toArray()));
			}
		}
	}


    public static List<List<Integer>> check3Sum(int arr[])
    {
    	if (arr == null || arr.length < 2)
    		return null;
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for(int i=0;i<arr.length-1;i++)
    	{   HashSet<Integer> seenValues= new HashSet<>();
    		int target=-arr[i];
    		for(int j=i+1;j<arr.length;j++)
    		{   
    			if(seenValues.contains(target-arr[j]))
    			{       			     
    				 int a= Math.min(arr[i],Math.min(arr[j],target-arr[j]));
    				 int b= Math.max(arr[i],Math.max(arr[j],target-arr[j]));                      
    				 List<Integer> l=new ArrayList<Integer>();
					 l.add(a);
					 l.add(-(a+b));
					 l.add(b);
                 	 if(!result.contains(l))
				         result.add(l);
    			}
      			seenValues.add(arr[j]);
    		}
           
    	}
    	
    	if(!result.isEmpty())
    		return result;
           
    	else
    		return null  ;
    }
    
    
    @Test(enabled=true)
    public static void test1()
    {
       Assert.assertEquals(null,check3Sum(new int[]{1,2,3,4,5}));
    }
    @Test(enabled=true)
    public static void testMultipleSolution()
    {  List<List<Integer>> r = new ArrayList<List<Integer>>();
	   r.add(Arrays.asList(new Integer[] {-1, 0, 1}));
	   r.add(Arrays.asList(new Integer[] {-1,-1,2}));    
       Assert.assertEquals(r ,check3Sum(new int[]{-1,0,1,2,-1,4}));
    }
    
    @Test(enabled=true)
    public static void testUniqueSol()
    {
	   List<List<Integer>> r = new ArrayList<List<Integer>>();
	   r.add(Arrays.asList(new Integer[] {0,0,0}));
	   Assert.assertEquals( r,check3Sum(new int[]{0,0,0,0}));
    }
	
    @Test(enabled=true)
    public static void testEmptySol()
    {
       Assert.assertEquals(null,check3Sum(new int[]{}));
    }
    @Test(enabled=true)
    public static void testNoSol()
    {
       Assert.assertEquals(null,check3Sum(new int[]{-1,1,2,-2}));
    }
    
}