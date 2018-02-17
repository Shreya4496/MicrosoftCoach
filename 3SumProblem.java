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
    	HashSet<Integer> s= new HashSet<>();
    	List<List<Integer>> r = new ArrayList<List<Integer>>();
    	int target,a,b;
    	for(int i=0;i<arr.length-1;i++)
    	{   
    		target=-arr[i];
    		for(int j=i+1;j<arr.length;j++)
    		{   
    			if(s.contains(target-arr[j]))
    			{       			     
    				 a= Math.min(arr[i],Math.min(arr[j],target-arr[j]));
    				 b= Math.max(arr[i],Math.max(arr[j],target-arr[j]));                      
    				 List<Integer> l=new ArrayList<Integer>();
					 l.add(a);
					 l.add(-(a+b));
					 l.add(b);
                 	 if(!r.contains(l))
				         r.add(l);
    			}
      			s.add(arr[j]);
    		}
            s.clear();
    	}
    	
    	if(!r.isEmpty())
    		return r;
           
    	else
    		return null;
    }
    
    
    @Test(enabled=true)
    public static void test1()
    {
       Assert.assertEquals(null,check3Sum(new int[]{1,2,3,4,5}));
    }
    @Test(enabled=true)
    public static void test2()
    {  List<List<Integer>> r = new ArrayList<List<Integer>>();
	   List<Integer> l=new ArrayList<Integer>();
	   List<Integer> l2=new ArrayList<Integer>();
	   l.add(-1);
	   l.add(0);
	   l.add(1);
	   r.add(l);
	   l2.add(-1);
	   l2.add(-1);
	   l2.add(2);
	   r.add(l2);      
       Assert.assertEquals(r ,check3Sum(new int[]{-1,0,1,2,-1,4}));
    }
    
    @Test(enabled=true)
    public static void test3()
    {
	   List<List<Integer>> r = new ArrayList<List<Integer>>();
	   List<Integer> l=new ArrayList<Integer>();
       l.add(0);
	   l.add(0);
	   l.add(0);
	   r.add(l);
	   Assert.assertEquals( r,check3Sum(new int[]{0,0,0,0}));
    }
	
    @Test(enabled=true)
    public static void test4()
    {
       Assert.assertEquals(null,check3Sum(new int[]{}));
    }
    @Test(enabled=true)
    public static void test5()
    {
       Assert.assertEquals(null,check3Sum(new int[]{-1,1,2,-2}));
    }
    
}